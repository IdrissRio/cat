/* Copyright (c) 2021, Idriss Riouak <idriss.riouak@cs.lth.se>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.extendj;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.Frontend;
import org.extendj.ast.Program;
import org.extendj.callgraph.CallGraph;

/**
 * Perform static semantic checks on a Java program.
 */
public class Cat extends Frontend {
  public boolean considerOnlyAttributes = false;
  public boolean visualiseCallGraph = false;
  public boolean saveCallGraph = false;
  public boolean mergeNames = false;
  public boolean rta = false;
  public String callGraphPath = "";
  public String entryPointPackage = "";
  public String entryPointMethod = "";
  public static Object DrAST_root_node;
  ;

  private String[] setEnv(String[] args) throws FileNotFoundException {
    if (args.length < 1) {
      System.err.println("You must specify a source file on the command line!");
      printOptionsUsage();
    }

    ArrayList<String> FEOptions = new ArrayList<>();

    for (int i = 0; i < args.length; ++i) {
      String opt = args[i];
      if (opt.contains(".java")) {
        FEOptions.add(args[i]);
        continue;
      }
      switch (opt) {
      case "-attributesOnly":
        considerOnlyAttributes = true;
        break;
      case "-entryPoint":
        entryPointPackage = args[++i];
        entryPointMethod = args[++i];
        break;
      case "-classpath":
        FEOptions.add("-classpath");
        FEOptions.add(args[++i]);
        break;
      case "-visualise":
        visualiseCallGraph = true;
        break;
      case "-o":
        saveCallGraph = true;
        callGraphPath = args[++i];
        break;
      case "-rta":
        rta = true;
        break;
      case "-mergeNames":
        mergeNames = true;
        break;
      default:
        System.err.println("Unrecognized option: " + opt);
        printOptionsUsage();
        break;
      }
    }
    FEOptions.add("-nowarn");
    return FEOptions.toArray(new String[FEOptions.size()]);
  }

  public boolean getConsiderOnlyAttributes() { return considerOnlyAttributes; }

  public boolean getVisualiseCallGraph() { return visualiseCallGraph; }

  public boolean getSaveCallGraph() { return saveCallGraph; }

  public String getCallGraphPath() { return callGraphPath; }

  public boolean getMergeNames() { return mergeNames; }

  public String getEntryPointPackage() { return entryPointPackage; }

  public String getEntryPointMethod() { return entryPointMethod; }

  /**
   * Entry point for the Java checker.
   * @param args command-line arguments
   */
  public static void main(String args[])
      throws FileNotFoundException, InterruptedException, IOException {
    Cat cat = new Cat();
    String[] jCheckerArgs = cat.setEnv(args);
    cat.getEntryPoint().attributesOnly = cat.getConsiderOnlyAttributes();
    cat.getEntryPoint().mergeNames = cat.getMergeNames();
    cat.getEntryPoint().entryPointPackage = cat.getEntryPointPackage();
    cat.getEntryPoint().entryPointMethod = cat.getEntryPointMethod();
    cat.getEntryPoint().rta = cat.rta;
    int exitCode = cat.run(jCheckerArgs);
    DrAST_root_node = cat.getEntryPoint();
    if (exitCode != 0) {
      System.exit(exitCode);
    }
    log("Starting call graph generation");
    CallGraph cg = cat.getEntryPoint().callGraph();
    log("Call graph generation finished");
    log("Starting SCCs computation");
    cg.computeSCCs();
    log("SCCs computation finished");

    String callgraphJson = cg.toJson();
    if (cat.getSaveCallGraph()) {
      PrintWriter out = new PrintWriter(cat.getCallGraphPath());
      out.println(callgraphJson);
      out.close();
      log("Call graph saved to " + cat.getCallGraphPath());
    }

    if (cat.getVisualiseCallGraph()) {
      log("You can visualize the call graph at http://localhost:8080/index.html");
      staticFiles.location("/public");
      port(8080);
      get("/getCallgraphData", (req, res) -> { return callgraphJson; });
    }
  }

  /**
   * Initialize the Java checker.
   */
  public Cat() { super("Cat", ExtendJVersion.getVersion()); }

  /**
   * @param args command-line arguments
   * @return {@code true} on success, {@code false} on error
   * @deprecated Use run instead!
   */
  @Deprecated
  public static boolean compile(String args[]) {
    return 0 == new JavaChecker().run(args);
  }

  /**
   * Run the Java checker.
   * @param args command-line arguments
   * @return 0 on success, 1 on error, 2 on configuration error, 3 on system
   */
  public int run(String args[]) {
    return run(args, Program.defaultBytecodeReader(),
               Program.defaultJavaParser());
  }

  /**
   * Called for each from-source compilation unit with no errors.
   */
  protected void processNoErrors(CompilationUnit unit) {}

  @Override
  protected String name() {
    return "Cat";
  }

  @Override
  protected String version() {
    return "CAT2023";
  }

  public Program getEntryPoint() { return program; }

  private static void log(String message) {
    System.out.println("\u001B[33m[INFO]\u001B[0m: " + message);
  }

  private void printOptionsUsage() {
    System.out.println("Usage: java -jar  cat.jar [options] <source_files>");
    System.out.println("Options:");
    System.out.println(
        "  -attributesOnly  Enable consideration of attributes only");
    System.out.println("  -classpath <path> Specify the classpath");
    System.out.println("  -visualise        Visualize the call graph");
    System.out.println(
        "  -o <path>         Save call graph to a JSON file at the specified path");
    System.exit(1);
  }
}