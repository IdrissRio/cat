/* Copyright (c) 2023, Idriss Riouak <idriss.riouak@cs.lth.se>
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

package test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.extendj.Cat;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.Program;
import org.extendj.ast.TypeDecl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RTATest {
  public static final File TEST_DIRECTORY = new File("testfiles/rta");
  private final String filename;
  private String packageName;
  private String methodName;
  public RTATest(String testFile) { filename = testFile; }

  @Test
  public void runTest() throws Exception {
    // Options are encoded in the file. Extract the from there.

    PrintStream out = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintStream outStream = new PrintStream(baos)) {
      String[] args = {filename};
      Cat jChecker = new Cat();
      extractOptionsFromTestFile(jChecker.getEntryPoint());
      int execCode = jChecker.run(args);
      if (1 == execCode) {
        Assert.fail("Compilation errors found " + execCode);
      }

      Program program = jChecker.getEntryPoint();
      program.rta = true;
      StringBuilder sb = new StringBuilder(program.callGraph().toString());

      outStream.println(sb.toString());

      UtilTest.compareOutput(
          baos.toString(), new File(UtilTest.changeExtension(filename, ".out")),
          new File(UtilTest.changeExtension(filename, ".expected")));

    } finally {
      System.setOut(out);
    }
  }

  public void extractOptionsFromTestFile(Program program) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      Pattern packagePattern = Pattern.compile("//\\s*PackageName:\\s*(\\S+)");
      Pattern methodPattern = Pattern.compile("//\\s*MethodName:\\s*(\\S+)");

      while ((line = reader.readLine()) != null) {
        Matcher packageMatcher = packagePattern.matcher(line);
        Matcher methodMatcher = methodPattern.matcher(line);

        if (packageMatcher.find()) {
          program.entryPointPackage = packageMatcher.group(1);
        } else if (methodMatcher.find()) {
          program.entryPointMethod = methodMatcher.group(1);
        }
      }
    } catch (IOException e) {
      // Handle file I/O exception
      e.printStackTrace();
    }
  }

  @Parameters(name = "{0}")
  public static Iterable<Object[]> getTests() {
    return UtilTest.getTestParametersSubFolders(TEST_DIRECTORY, ".java");
  }
}