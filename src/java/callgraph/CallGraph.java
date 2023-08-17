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
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;
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
    CallGraphNode caller = graph.get(callerName);
    if (caller == null) {
        caller = new CallGraphNode(callerName, new ArrayList<>(callerKinds));
        graph.put(callerName, caller);
    }

    CallGraphNode callee = graph.get(calleeName);
    if (callee == null) {
        callee = new CallGraphNode(calleeName, new ArrayList<>(calleeKinds));
        graph.put(calleeName, callee);
    }

    caller.addCallee(callee);
}


  /**
   * Checks if a method call between two methods already exists in the call
   * graph.
   *
   * @param callerName The name of the calling method.
   * @param calleeName The name of the called method.
   * @return true if the link exists, false otherwise.
   */
  public boolean isMethodCallExists(String callerName, String calleeName) {
    CallGraphNode caller = graph.get(callerName);
    if (caller == null) {
      return false;
    }
    List<CallGraphNode> callees = caller.getCallees();
    for (CallGraphNode callee : callees) {
      if (callee.getMethodName().equals(calleeName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Detects all strongly connected components in the call graph using Tarjan's
   * algorithm.
   *
   * @return A list of strongly connected components (each represented as a list
   *     of method names).
   */
  public List<List<String>> findStronglyConnectedComponents2() {
    List<List<String>> stronglyConnectedComponents = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    Map<String, Integer> indexes = new HashMap<>();
    Map<String, Integer> lowLinkValues = new HashMap<>();
    Set<String> onStack = new HashSet<>();

    int index = 0;

    for (String methodName : graph.keySet()) {
      if (!indexes.containsKey(methodName)) {
        strongConnect(methodName, index, stack, indexes, lowLinkValues, onStack,
                      stronglyConnectedComponents);
      }
    }

    return stronglyConnectedComponents;
  }

  /**
   * Helper method for Tarjan's algorithm to find strongly connected components.
   */
  private void strongConnect(String methodName, int index, Stack<String> stack,
                             Map<String, Integer> indexes,
                             Map<String, Integer> lowLinkValues,
                             Set<String> onStack,
                             List<List<String>> stronglyConnectedComponents) {
    indexes.put(methodName, index);
    lowLinkValues.put(methodName, index);
    index++;
    stack.push(methodName);
    onStack.add(methodName);

    CallGraphNode node = graph.get(methodName);
    List<CallGraphNode> callees = node.getCallees();

    for (CallGraphNode callee : callees) {
      String calleeName = callee.getMethodName();
      if (!indexes.containsKey(calleeName)) {
        strongConnect(calleeName, index, stack, indexes, lowLinkValues, onStack,
                      stronglyConnectedComponents);
        lowLinkValues.put(methodName, Math.min(lowLinkValues.get(methodName),
                                               lowLinkValues.get(calleeName)));
      } else if (onStack.contains(calleeName)) {
        lowLinkValues.put(methodName, Math.min(lowLinkValues.get(methodName),
                                               indexes.get(calleeName)));
      }
    }

    if (lowLinkValues.get(methodName).equals(indexes.get(methodName))) {
      List<String> scc = new ArrayList<>();
      String poppedMethod;
      do {
        poppedMethod = stack.pop();
        onStack.remove(poppedMethod);
        scc.add(poppedMethod);
      } while (!poppedMethod.equals(methodName));
      stronglyConnectedComponents.add(scc);
    }
  }

  /**
   * Gets the call graph node for a given method name.
   *
   * @param methodName The name of the method.
   * @return The CallGraphNode representing the method.
   */
  public CallGraphNode getCallGraphNode(String methodName) {
    return graph.get(methodName);
  }

  /**
   * Returns a Graphviz DOT format representation of the call graph
   * with different colors for each strongly connected component.
   *
   * @return The DOT representation of the call graph with colors.
   */
  public String toDot() {
    List<List<String>> stronglyConnectedComponents =
        findStronglyConnectedComponents();

    StringBuilder sb = new StringBuilder();
    sb.append("digraph CallGraph {\n");

    // Assign colors to strongly connected components
    String[] colors = {"blue", "red", "green", "orange", "purple", "yellow"};

    int componentIndex = 0;
    Map<String, String> methodToColor = new HashMap<>();
    for (List<String> component : stronglyConnectedComponents) {
      String color = colors[componentIndex % colors.length];
      for (String methodName : component) {
        methodToColor.put(methodName, color);
      }
      componentIndex++;
    }

    for (CallGraphNode node : graph.values()) {
      String methodName = node.getMethodName();
      String color = methodToColor.getOrDefault(methodName, "black");

      List<CallGraphNode> callees = node.getCallees();
      if (!callees.isEmpty()) {
        for (CallGraphNode callee : callees) {
          String calleeName = callee.getMethodName();
          sb.append("\t")
              .append("\"")
              .append(methodName)
              .append("\"")
              .append(" -> ")
              .append("\"")
              .append(calleeName)
              .append("\"")
              .append(" [color=")
              .append(color)
              .append("]")
              .append(";\n");
        }
      } else {
        sb.append("\t")
            .append("\"")
            .append(methodName)
            .append("\"")
            .append(" [color=")
            .append(color)
            .append("]")
            .append(";\n");
      }
    }
    sb.append("}\n");

    return sb.toString();
  }

  /**
   * Returns a string representation of the entire CallGraph.
   * It lists all the nodes and their callees.
   *
   * @return The string representation of the CallGraph.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (CallGraphNode node : graph.values()) {
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
   * Returns a list of nodes that are not part of any cycle in the call graph.
   *
   * @return A list of method names representing the nodes without cycles.
   */
  public List<String> findNodesWithoutCycles() {
    List<List<String>> stronglyConnectedComponents =
        findStronglyConnectedComponents();
    Set<String> nodesInCycle = new HashSet<>();

    // Gather nodes that are part of cycles
    for (List<String> component : stronglyConnectedComponents) {
      if (component.size() > 1) {
        nodesInCycle.addAll(component);
      }
    }

    // Find nodes without cycles
    List<String> nodesWithoutCycles = new ArrayList<>();
    for (String methodName : graph.keySet()) {
      if (!nodesInCycle.contains(methodName)) {
        nodesWithoutCycles.add(methodName);
      }
    }

    return nodesWithoutCycles;
  }

  /**
   * Helper method for Kosaraju's algorithm to find strongly connected
   * components.
   */
  private void dfs(String methodName, Map<String, Boolean> visited,
                   Stack<String> stack) {
    visited.put(methodName, true);

    CallGraphNode node = graph.get(methodName);
    List<CallGraphNode> callees = node.getCallees();

    for (CallGraphNode callee : callees) {
      String calleeName = callee.getMethodName();
      if (!visited.containsKey(calleeName)) {
        dfs(calleeName, visited, stack);
      }
    }

    stack.push(methodName);
  }

  /**
   * Helper method for Kosaraju's algorithm to find strongly connected
   * components.
   */
  private void reverseDfs(String methodName, Map<String, Boolean> visited,
                          List<String> component) {
    visited.put(methodName, true);
    component.add(methodName);

    CallGraphNode node = graph.get(methodName);
    List<CallGraphNode> callers = node.getCallers();

    for (CallGraphNode caller : callers) {
      String callerName = caller.getMethodName();
      if (!visited.containsKey(callerName)) {
        reverseDfs(callerName, visited, component);
      }
    }
  }

  /**
   * Detects all strongly connected components in the call graph using
   * Kosaraju's algorithm.
   *
   * @return A list of strongly connected components (each represented as a list
   *         of method names).
   */
  public List<List<String>> findStronglyConnectedComponents() {
    List<List<String>> stronglyConnectedComponents = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    Map<String, Boolean> visited = new HashMap<>();

    // First DFS to populate the stack
    for (String methodName : graph.keySet()) {
      if (!visited.containsKey(methodName)) {
        dfs(methodName, visited, stack);
      }
    }

    // Clear the visited map for the second DFS
    visited.clear();

    // Second DFS using reversed edges
    while (!stack.isEmpty()) {
      String methodName = stack.pop();
      if (!visited.containsKey(methodName)) {
        List<String> component = new ArrayList<>();
        reverseDfs(methodName, visited, component);
        stronglyConnectedComponents.add(component);
      }
    }

    return stronglyConnectedComponents;
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