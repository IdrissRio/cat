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
import org.extendj.ast.InvocationTarget;
import org.extendj.callgraph.BridgeNode;
import org.extendj.callgraph.CallGraphNode;

/**
 * Represents the call graph.
 */
public class CallGraph {
  private Map<String, CallGraphNode> graph;
  private CallGraphNode entryPoint;
  private int currentSccId = 0;

  /**
   * Creates a new CallGraph.
   */
  public CallGraph() { this.graph = new LinkedHashMap<>(); }

  /**
   * Creates a new CallGraph with the given entry point.
   *
   * @param entryPoint The name of the entry point method.
   */
  public CallGraph(CallGraphNode entryPoint) {
    this.entryPoint = entryPoint;
    this.graph = new LinkedHashMap<>();
    this.graph.put(entryPoint.getMethodName(), entryPoint);
  }

  /**
   * Returns the entry point of the call graph.
   *
   * @return The entry point method name.
   */
  public CallGraphNode getEntryPoint() { return entryPoint; }

  public void setEntryPoint(CallGraphNode entryPoint) {
    this.entryPoint = entryPoint;
  }

  public Map<String, CallGraphNode> getGraph() { return graph; }

  /**
   * Adds a method call to the call graph.
   *
   * @param callerName The name of the calling method.
   * @param calleeName The name of the called method.
   */
  public void addMethodCall(String callerName, List<String> callerKinds,
                            String calleeName, List<String> calleeKinds,
                            InvocationTarget target1,
                            InvocationTarget target2) {
    CallGraphNode caller = getOrCreateNode(callerName, callerKinds, target1);
    CallGraphNode callee = getOrCreateNode(calleeName, calleeKinds, target2);
    caller.addCallee(callee);
  }

  public CallGraphNode addNode(String node, List<String> kinds,
                               InvocationTarget target) {
    return getOrCreateNode(node, kinds, target);
  }

  private CallGraphNode getOrCreateNode(String methodName, List<String> kinds,
                                        InvocationTarget target) {
    if (!graph.containsKey(methodName)) {
      graph.put(methodName,
                new CallGraphNode(methodName, new ArrayList<>(kinds), target));
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
Set<String> addedEdges = new HashSet<>();

/**
 * Adds a call graph to the current call graph.
 *
 * @param callGraph The call graph to be added.
 */
public void addCallGraph(CallGraph callGraph) {

    // Add nodes and edges from the given call graph
    for (CallGraphNode node : callGraph.graph.values()) {
      String methodName = node.getMethodName();
      if (!graph.containsKey(methodName)) {
        graph.put(methodName, new CallGraphNode(methodName, node.getKinds(),
                                                node.getTarget()));
      }
      List<CallGraphNode> callees = node.getCallees();
      for (CallGraphNode callee : callees) {
        String calleeName = callee.getMethodName();
        if (!graph.containsKey(calleeName)) {
          graph.put(calleeName, new CallGraphNode(calleeName, callee.getKinds(),
                                                  callee.getTarget()));
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
   * Computes the Strongly Connected Components (SCCs) of the CallGraph using
   * Kosaraju's algorithm. The SCCs are computed and labeled with unique IDs in
   * this method.
   */
  public void computeSCCs() {

    Stack<CallGraphNode> stack = new Stack<>();
    Set<CallGraphNode> visited = new HashSet<>();

    // Step 1: Perform DFS on the original graph and fill the stack
    for (CallGraphNode node : graph.values()) {
      if (!visited.contains(node)) {
        dfs(node, stack, visited);
      }
    }

    // Step 2: Reverse the graph
    CallGraph reversedGraph = reverseGraph();

    // Step 3: Perform DFS on the reversed graph using the order from the stack
    visited.clear();
    while (!stack.isEmpty()) {
      CallGraphNode node = stack.pop();
      if (!visited.contains(node)) {
        reversedGraph.dfs(node, currentSccId, visited);
        currentSccId++;
      }
    }
  }

  /**
   * Reverse the current CallGraph to create a new CallGraph with edges
   * reversed.
   *
   * @return A new CallGraph instance with the edges reversed.
   */
  private CallGraph reverseGraph() {
    CallGraph reversedGraph = new CallGraph();
    for (CallGraphNode node : graph.values()) {
      for (CallGraphNode callee : node.getCallees()) {
        reversedGraph.addMethodCall(callee.getMethodName(), callee.getKinds(),
                                    node.getMethodName(), node.getKinds(),
                                    node.getTarget(), callee.getTarget());
      }
    }
    return reversedGraph;
  }

  /**
   * Perform a Depth-First Search (DFS) starting from the given node and add
   * nodes to the stack in post-order.
   *
   * @param node     The starting node for DFS.
   * @param stack    The stack to store nodes in post-order.
   * @param visited  A set to track visited nodes.
   */
  private void dfs(CallGraphNode node, Stack<CallGraphNode> stack,
                   Set<CallGraphNode> visited) {
    visited.add(node);
    for (CallGraphNode callee : node.getCallees()) {
      if (!visited.contains(callee)) {
        dfs(callee, stack, visited);
      }
    }
    stack.push(node);
  }

  /**
   * Perform a Depth-First Search (DFS) starting from the given node to identify
   * SCCs and assign SCC IDs.
   *
   * @param node            The starting node for DFS.
   * @param currentSccId    The current SCC ID to assign to nodes in the SCC.
   * @param visited         A set to track visited nodes.
   */
  private void dfs(CallGraphNode node, int currentSccId,
                   Set<CallGraphNode> visited) {
    visited.add(node);
    node.setSccID(currentSccId);
    for (CallGraphNode caller : node.getCallers()) {
      if (!visited.contains(caller)) {
        dfs(caller, currentSccId, visited);
      }
    }
  }

  private void addBridges() {
    Map<Integer, Integer> sccIdCounts = new HashMap<>();

    for (CallGraphNode node : graph.values()) {
      Integer sccID = node.getSccID();
      sccIdCounts.put(sccID, sccIdCounts.getOrDefault(sccID, 0) + 1);
    }

    List<CallGraphNode> nodes = new ArrayList<>(graph.values());
    for (CallGraphNode node : nodes) {
      Integer nodeSccID = node.getSccID();
      List<CallGraphNode> callees = new ArrayList<>(node.getCallees());
      // if (sccIdCounts.get(nodeSccID) >= 2) {
      for (CallGraphNode callee : callees) {
        Integer calleeSccID = callee.getSccID();

        if (calleeSccID != nodeSccID && sccIdCounts.get(calleeSccID) >= 2) {
          node.removeCallee(callee);
          String bridgeNodeName = "Bridge_" + callee.getMethodName();
          CallGraphNode bridgeNode = graph.get(bridgeNodeName);
          if (bridgeNode == null) {
            // Bridge
            bridgeNode =
                new BridgeNode(bridgeNodeName,
                               new ArrayList<String>() {
                                 { add("bridge"); }
                               },
                               callee.getTarget().returnType(),
                               callee.getTarget().paramTypes(),
                               callee.getMethodName(), "", callee.getTarget());
            System.err.println(callee.getMethodName() + "  ---  " +
                               callee.getTarget().targetSignature());

            bridgeNode.setSccID(callee.getSccID());
            graph.put(bridgeNodeName, bridgeNode);
          }
          bridgeNode.addCallee(callee);
          node.addCallee(bridgeNode);
        }
        }
        // }
    }
  }

  /**
   * Convert the CallGraph to a JSON string.
   *
   * @return JSON representation of the CallGraph.
   */
  public String toJson() {
    Map<Integer, Integer> sccIdCounts = new HashMap<>();

    // Count the nodes for each SCC ID
    for (CallGraphNode node : graph.values()) {
      Integer sccID = node.getSccID();
      sccIdCounts.put(sccID, sccIdCounts.getOrDefault(sccID, 0) + 1);
    }
    // addBridges();

    StringBuilder jsonBuilder = new StringBuilder();
    jsonBuilder.append("{\n");

    // Add nodes
    jsonBuilder.append("  \"nodes\": [\n");
    boolean isFirstNode = true;
    for (CallGraphNode node : graph.values()) {
      if (!isFirstNode) {
        jsonBuilder.append(",\n");
      }

      String methodName = node.getMethodName();
      Integer sccID = node.getSccID();
      List<String> kindList = node.getKinds();
      boolean isUniqueSCCAndNoSelfLoop = (sccIdCounts.get(sccID) == null)
                                             ? false
                                             : sccIdCounts.get(sccID) == 1;

      jsonBuilder.append("    {\n");
      jsonBuilder.append("      \"methodName\": \"")
          .append(methodName)
          .append("\",\n");
      jsonBuilder.append("      \"sccId\": ").append(sccID).append(",\n");
      jsonBuilder.append("      \"uniqueSCCAndNoSelfLoop\": ")
          .append(isUniqueSCCAndNoSelfLoop)
          .append(",\n");
      jsonBuilder.append("      \"isEntryPoint\": ")
          .append(methodName.equals(entryPoint.getMethodName()))
          .append(",\n");
      jsonBuilder.append("      \"path\": \"")
          .append(node.getTarget().fullPath())
          .append("\",\n");
      jsonBuilder.append("      \"bridge\": ")
          .append(node.isBridge())
          .append(",\n");
      jsonBuilder.append("      \"paramTypes\": {\n");
      boolean isFirstParam = true;
      for (Map.Entry<String, String> entry :
           node.getTarget().paramTypes().entrySet()) {
        if (!isFirstParam) {
            jsonBuilder.append(",\n");
        }
        jsonBuilder.append("        \"")
            .append(entry.getKey())
            .append("\": \"")
            .append(entry.getValue())
            .append("\"");
        isFirstParam = false;
      }
        jsonBuilder.append("\n      },\n");
        if (node instanceof BridgeNode) {
        BridgeNode bridgeNode = (BridgeNode)node;
        jsonBuilder.append("      \"returnType\": \"")
            .append(bridgeNode.returnType())
            .append("\",\n");

        jsonBuilder.append("      \"originalName\": \"")
            .append(bridgeNode.originalName())
            .append("\",\n");
        jsonBuilder.append("      \"prettyPrinted\": \"")
            .append(bridgeNode.prettyPrinted())
            .append("\",\n");
        }
      jsonBuilder.append("      \"kind\": [\n");
      for (int i = 0; i < kindList.size(); i++) {
        jsonBuilder.append("        \"").append(kindList.get(i)).append("\"");
        if (i < kindList.size() - 1) {
          jsonBuilder.append(",\n");
        }
      }
      jsonBuilder.append("\n      ]\n");
      jsonBuilder.append("    }");

      isFirstNode = false;
    }
    jsonBuilder.append("\n  ],\n");

    jsonBuilder.append("  \"edges\": [\n");
    boolean isFirstEdge = true;
    for (CallGraphNode caller : graph.values()) {
      for (CallGraphNode callee : caller.getCallees()) {
        if (!isFirstEdge) {
          jsonBuilder.append(",\n");
        }
        jsonBuilder.append("    {\n");
        jsonBuilder.append("      \"source\": \"")
            .append(caller.getMethodName())
            .append("\",\n");
        jsonBuilder.append("      \"target\": \"")
            .append(callee.getMethodName())
            .append("\"\n");
        jsonBuilder.append("    }");

        isFirstEdge = false;
      }
    }
    jsonBuilder.append("\n  ]\n");

    jsonBuilder.append("}\n");
    computeStatistics(this);
    return jsonBuilder.toString();
  }

  public static void computeStatistics(CallGraph callGraph) {
    int numberOfNodes = callGraph.getGraph().size();
    int numberOfCircularNodes = countCircularNodes(callGraph);
    Map<Integer, Integer> sccSizes = calculateSCCSizes(callGraph);
    int numberOfLargeSCCs = countLargeSCCs(sccSizes);

    int totalSCCSize = sccSizes.values()
                           .stream()
                           .filter(size -> size > 2)
                           .mapToInt(Integer::intValue)
                           .sum();
    int numSCCGreaterTwo =
        (int)sccSizes.values().stream().filter(size -> size > 2).count();
    double meanSCCSize = totalSCCSize / (double)numSCCGreaterTwo;
    int maxSCCSize = sccSizes.values().stream().max(Integer::compare).orElse(0);

    // System.out.println("Number of nodes: " + numberOfNodes);
    // System.out.println("Number of circular nodes: " + numberOfCircularNodes);
    // System.out.println("Number of SCCs with size greater than two: " +
    //                    numberOfLargeSCCs);
    // System.out.println("Mean size of SCCs: " + meanSCCSize);
    // System.out.println("Maximum size of SCC: " + maxSCCSize);
  }

  private static int countCircularNodes(CallGraph callGraph) {
    int count = 0;
    for (CallGraphNode node : callGraph.getGraph().values()) {
      if (node.getKinds().contains("circular")) {
        count++;
      }
    }
    return count;
  }

  private static Map<Integer, Integer> calculateSCCSizes(CallGraph callGraph) {
    Map<Integer, Integer> sccSizes = new HashMap<>();
    callGraph.computeSCCs();

    for (CallGraphNode node : callGraph.getGraph().values()) {
      int sccID = node.getSccID();
      sccSizes.put(sccID, sccSizes.getOrDefault(sccID, 0) + 1);
    }

    return sccSizes;
  }

  private static int countLargeSCCs(Map<Integer, Integer> sccSizes) {
    return (int)sccSizes.values().stream().filter(size -> size > 2).count();
  }
}