
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

package org.ast;
import java.util.LinkedHashMap;
import java.util.Map;
import org.ast.ASTNode;
import org.ast.ASTState;

public class AttributeTracer implements ASTState.Trace.Receiver {
  public Map<String, Integer> attrCount = new LinkedHashMap<String, Integer>();
  public Map<String, Map<ASTNode, Integer>> attrValueCount =
      new LinkedHashMap<String, Map<ASTNode, Integer>>();

  @Override
  public void accept(ASTState.Trace.Event event, ASTNode node, String attribute,
                     Object params, Object value) {
    String attrName =
        attribute.substring(attribute.indexOf('.') + 1, attribute.indexOf('('));
    attrName = node.getClass().getSimpleName() + "::" + attrName;
    switch (event) {
    case COMPUTE_BEGIN: {
      attrCount.put(attrName, attrCount.getOrDefault(attrName, 0) + 1);
      Map<ASTNode, Integer> valueCount = attrValueCount.get(attrName);
      if (valueCount == null) {
        valueCount = new LinkedHashMap<ASTNode, Integer>();
        attrValueCount.put(attrName, valueCount);
      }
      valueCount.put(node, valueCount.getOrDefault(node, 0) + 1);
    }
    case COMPUTE_END: {
      // ...
      break;
    }
    }
  }

  public void printJSON() {
    StringBuilder jsonBuilder = new StringBuilder();
    jsonBuilder.append("{\n");

    boolean firstOuter = true;
    for (Map.Entry<String, Map<ASTNode, Integer>> entry :
         attrValueCount.entrySet()) {
      if (!firstOuter) {
        jsonBuilder.append(",\n");
      }
      firstOuter = false;

      String outerKey = entry.getKey();
      Map<ASTNode, Integer> innerMap = entry.getValue();

      // Process the innerMap to collapse duplicate values under the same key

      jsonBuilder.append("  \"").append(outerKey).append("\": {\n");
      jsonBuilder.append("    \"total\": ")
          .append(innerMap.values().stream().mapToInt(Integer::intValue).sum())
          .append(",\n");
      boolean firstInner = true;
      for (Map.Entry<ASTNode, Integer> collapsedEntry : innerMap.entrySet()) {
        if (!firstInner) {
          jsonBuilder.append(",\n");
        }
        firstInner = false;

        jsonBuilder.append("    \"")
            .append(collapsedEntry.getKey())
            .append("\": ")
            .append(collapsedEntry.getValue());
      }

      jsonBuilder.append("\n  }\n");
    }

    jsonBuilder.append("}\n");

    // Write the JSON content to a file or use it as needed
    String jsonString = jsonBuilder.toString();
    System.out.println(jsonString); // For demonstration purposes
  }
}