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

package org.extendj.callgraph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Represents the call graph.
 */
public class CallGraph {
  private Map<String, CallGraphNode> graph;
  private String entryPoint;

  /**
   * Creates a new CallGraph.
   */
  public CallGraph() { this.graph = new LinkedHashMap<>(); }

  /**
   * Creates a new CallGraph with the given entry point.
   *
   * @param entryPoint The name of the entry point method.
   */
  public CallGraph(String entryPoint) {
    this.entryPoint = entryPoint;
    this.graph = new LinkedHashMap<>();
    this.graph.put(entryPoint,
                   new CallGraphNode(entryPoint, new ArrayList<>()));
  }

  /**
   * Returns the entry point of the call graph.
   *
   * @return The entry point method name.
   */
  public String getEntryPoint() { return entryPoint; }

  public Map<String, CallGraphNode> getGraph() { return graph; }

  /**
   * Adds a method call to the call graph.
   *
   * @param callerName The name of the calling method.
   * @param calleeName The name of the called method.
   */
public void addMethodCall(String callerName, List<String> callerKinds, String calleeName, List<String> calleeKinds) {
    CallGraphNode caller = getOrCreateNode(callerName, callerKinds);
    CallGraphNode callee = getOrCreateNode(calleeName, calleeKinds);
    caller.addCallee(callee);
}

private CallGraphNode getOrCreateNode(String methodName, List<String> kinds) {
    if (!graph.containsKey(methodName)) {
      graph.put(methodName,
                new CallGraphNode(methodName, new ArrayList<>(kinds)));
    }
    return graph.get(methodName);
}

  /**
   * Returns a string representation of the entire CallGraph.
   * It lists all the nodes and their callees.
   *
   * @return The string representation of the CallGraph.
   */
@Override
public String toString() {
    List<CallGraphNode> sortedNodes = new ArrayList<>(graph.values());
    sortedNodes.sort(Comparator.comparing(CallGraphNode::getMethodName));

    StringBuilder sb = new StringBuilder();
    for (CallGraphNode node : sortedNodes) {
      sb.append(node.toString()).append("\n");
    }
    return sb.toString();
}

public CallGraph union(Set<CallGraph> callGraphs) {
    for (CallGraph callGraph : callGraphs) {
      this.addCallGraph(callGraph);
    }

    return this;
}

  /**
   * Adds a call graph to the current call graph.
   *
   * @param callGraph The call graph to be added.
   */
  public void addCallGraph(CallGraph callGraph) {
    Set<String> addedEdges = new HashSet<>();

    // Add nodes and edges from the given call graph
    for (CallGraphNode node : callGraph.graph.values()) {
      String methodName = node.getMethodName();
      if (!graph.containsKey(methodName)) {
        graph.put(methodName, new CallGraphNode(methodName, node.getKinds()));
      }
      List<CallGraphNode> callees = node.getCallees();
      for (CallGraphNode callee : callees) {
        String calleeName = callee.getMethodName();
        if (!graph.containsKey(calleeName)) {
          graph.put(calleeName, new CallGraphNode(calleeName, callee.getKinds()));
        }
        String edgeKey = methodName + " -> " + calleeName;
        if (!addedEdges.contains(edgeKey)) {
          graph.get(methodName).addCallee(graph.get(calleeName));
          addedEdges.add(edgeKey);
        }
      }
    }
  }

/**
     * Convert the CallGraph to a JSON string.
     *
     * @return JSON representation of the CallGraph.
     */
    public String toJson() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
    
        
        // Add nodes
        jsonBuilder.append("\"nodes\": [");
        boolean isFirstNode = true;
        for (CallGraphNode node : graph.values()) {
            if (!isFirstNode) {
                jsonBuilder.append(", ");
            }
            jsonBuilder.append("{");
            jsonBuilder.append("\"methodName\": \"").append(node.getMethodName()).append("\", ");
            
            // Add callees
            jsonBuilder.append("\"callees\": [");
            boolean isFirstCallee = true;
            for (CallGraphNode callee : node.getCallees()) {
                if (!isFirstCallee) {
                    jsonBuilder.append(", ");
                }
                jsonBuilder.append("\"").append(callee.getMethodName()).append("\"");
                isFirstCallee = false;
            }
            jsonBuilder.append("]");
            jsonBuilder.append("}");
            isFirstNode = false;
        }
        jsonBuilder.append("]");
        
        jsonBuilder.append("}");
        
        return jsonBuilder.toString();
    }
}