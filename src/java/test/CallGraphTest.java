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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
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
public class CallGraphTest {
  public static final File TEST_DIRECTORY = new File("testfiles/callgraph");
  private final String filename;
  public CallGraphTest(String testFile) { filename = testFile; }

  @Test
  public void runTest() throws Exception {

    PrintStream out = System.out;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         PrintStream outStream = new PrintStream(baos)) {
      String[] args = {filename};
      Cat jChecker = new Cat();
      int execCode = jChecker.run(args);
      if (1 == execCode) {
        Assert.fail("Compilation errors found " + execCode);
      }

      Program program = jChecker.getEntryPoint();
      StringBuilder sb = new StringBuilder(program.callGraph().toString());

      outStream.println(sb.toString());

      UtilTest.compareOutput(
          baos.toString(), new File(UtilTest.changeExtension(filename, ".out")),
          new File(UtilTest.changeExtension(filename, ".expected")));

    } finally {
      System.setOut(out);
    }
  }

  @Parameters(name = "{0}")
  public static Iterable<Object[]> getTests() {
    return UtilTest.getTestParametersSubFolders(TEST_DIRECTORY, ".java");
  }
}