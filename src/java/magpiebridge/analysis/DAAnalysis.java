package org.extendj.magpiebridge.analysis;

import com.ibm.wala.cast.tree.CAstSourcePositionMap.Position;
import com.ibm.wala.util.collections.Pair;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;
import magpiebridge.core.AnalysisResult;
import magpiebridge.core.Kind;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.extendj.ast.Analysis;
import org.extendj.ast.CompilationUnit;
import org.extendj.ast.MethodDecl;
import org.extendj.ast.WarningMsg;
import org.extendj.magpiebridge.CodeAnalysis;
import org.extendj.magpiebridge.MySourceCodeReader;
import org.extendj.magpiebridge.Result;
import org.extendj.magpiebridge.ResultPosition;

public class DAAnalysis implements CodeAnalysis<CompilationUnit> {
  private Collection<AnalysisResult> results = new HashSet<>();

  private static final Analysis ANALYSIS_TYPE = Analysis.DAA;

  @Override
  public void doAnalysis(CompilationUnit cu, URL url) {
    results.clear();

    for (WarningMsg wm : cu.DAA()) {
      String code = "No code";
      ResultPosition position = new ResultPosition(
          wm.lineStart, wm.lineEnd, wm.columnStart, wm.columnEnd, url);
      List<Pair<Position, String>> relatedInfo = new ArrayList<>();

      try {
        code = MySourceCodeReader.getLinesInString(position);
      } catch (Exception e) {
        e.printStackTrace();
      }

      String correctCode = "";

      ResultPosition repairPos = new ResultPosition(wm.lineStart, wm.lineEnd, 0,
                                                    wm.columnEnd + 1, url);

      Pair<Position, String> repair = Pair.make(repairPos, correctCode);

      results.add(new Result(Kind.Diagnostic, position, wm.errMsg, relatedInfo,
                             DiagnosticSeverity.Warning, repair, code));
    }
  }

  @Override
  public Collection<AnalysisResult> getResult() {
    return results;
  }

  @Override
  public String getName() {
    return "DAA";
  }

  public static String name() { return "DAA"; }
}
