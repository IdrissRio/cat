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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class GSCallGraph extends SingleGraph {

  private CallGraph callgraph = null;
  private TarjanStronglyConnectedComponents tscc =
      new TarjanStronglyConnectedComponents();

  public GSCallGraph(String id) {
    super(id);
    constructGraph(new CallGraph());
  }

  public GSCallGraph(String id, CallGraph callGraph) {
    super(id);
    this.callgraph = callGraph;
    constructGraph(callGraph);
  }

  private void constructGraph(CallGraph callGraph) {
    Set<String> addedNodes = new HashSet<>();

    for (CallGraphNode node : callGraph.getGraph().values()) {
      String nodeId = getNodeId(node.getMethodName());

      if (!addedNodes.contains(nodeId)) {
        Node graphNode = addNode(nodeId);
        graphNode.setAttribute("ui.label", node.getMethodName());
        List<String> data =
            (graphNode.getAttribute("data.kind") == null)
                ? new java.util.ArrayList<String>()
                : (List<String>)graphNode.getAttribute("data.kind");
        data.addAll(node.getKinds());
        graphNode.setAttribute("data.kind", data);

        addedNodes.add(nodeId);
      }

      List<CallGraphNode> callees = node.getCallees();
      for (CallGraphNode callee : callees) {
        String calleeId = getNodeId(callee.getMethodName());

        if (!addedNodes.contains(calleeId)) {
          Node graphCallee = addNode(calleeId);
          List<String> data =
              (graphCallee.getAttribute("data.kind") == null)
                  ? new java.util.ArrayList<String>()
                  : (List<String>)graphCallee.getAttribute("data.kind");
          data.addAll(callee.getKinds());
          graphCallee.setAttribute("data.kind", data);
          graphCallee.setAttribute("ui.label", callee.getMethodName());
          addedNodes.add(calleeId);
        }

        String edgeId = nodeId + calleeId;
        try {
          addEdge(edgeId, nodeId, calleeId, true);
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
        }
      }
    }
    tscc.init(this);
    tscc.compute();
  }

  private static String getNodeId(String methodName) {
    return "node_" + methodName.replaceAll("\\W+", "_");
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Node n : this) {
      String label = (String)n.getAttribute("ui.label");
      Integer sccIndexInt =
          (Integer)n.getAttribute(tscc.getSCCIndexAttribute());
      String name = label;

      // Check if the node has a self loop
      final boolean[] hasSelfLoop = {false}; // Using a boolean array

      n.leavingEdges().forEach(edge -> {
        Node neighbor = edge.getTargetNode();
        if (neighbor.equals(n)) {
          hasSelfLoop[0] = true; // Update the boolean array
        }
      });

      sb.append(name + " " + sccIndexInt + " " + hasSelfLoop[0] + "\n");
    }
    return sb.toString();
  }

  public String toJson() {

    StringBuilder jsonBuilder = new StringBuilder();
    jsonBuilder.append("{");

    // Add nodes
    jsonBuilder.append("\"nodes\": [");
    boolean isFirstNode = true;
    for (Node node : this) {
        if (!isFirstNode) {
            jsonBuilder.append(", ");
        }

        String name = (String) node.getAttribute("ui.label");
        Integer sccIndexInt = (Integer) node.getAttribute(tscc.getSCCIndexAttribute());
        List<String> kindList = (List<String>) node.getAttribute("data.kind");

        jsonBuilder.append("{\"methodName\": \"").append(name).append("\", ");
        jsonBuilder.append("\"sccId\": ").append(sccIndexInt).append(", ");
        

            jsonBuilder.append("\"kind\": [");
            for (int i = 0; i < kindList.size(); i++) {
                if (i > 0) {
                    jsonBuilder.append(", ");
                }
                jsonBuilder.append("\"").append(kindList.get(i)).append("\"");
            }
            jsonBuilder.append("] ");
        
        
        jsonBuilder.append("}");
        
        isFirstNode = false;
    }
    jsonBuilder.append("]");

    // Add edges
    jsonBuilder.append(", \"edges\": [");
    boolean isFirstEdge = true;
    for (Edge edge : this.edges().toArray(Edge[]::new)) {
        if (!isFirstEdge) {
            jsonBuilder.append(", ");
        }
        jsonBuilder.append("{\"source\": \"").append((String)edge.getNode0().getAttribute("ui.label")).append("\", ");
        jsonBuilder.append("\"target\": \"").append((String)edge.getNode1().getAttribute("ui.label")).append("\"}");
        isFirstEdge = false;
    }
    jsonBuilder.append("]");

    jsonBuilder.append("}");

    return jsonBuilder.toString();
  }
}
