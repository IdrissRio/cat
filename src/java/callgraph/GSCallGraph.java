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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
// import org.graphstream.ui.swingViewer.ViewPanel;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.geom.Point2;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.swing_viewer.util.DefaultMouseManager;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.camera.Camera;

public class GSCallGraph extends SingleGraph {
  private Map<String, String> originalNodeColors;
  private CallGraph callgraph = null;
  private static JEditorPane logTextArea = new JEditorPane();
  private static JToggleButton selection = new JToggleButton("Node Selection");
  private static JTextField nSteps = new JTextField("# of steps");

  public GSCallGraph(String id) {
    super(id);
    originalNodeColors = new HashMap<>();
    constructGraph(new CallGraph());
    // initializeGraphView();
  }

  public GSCallGraph(String id, CallGraph callGraph) {
    super(id);
    this.callgraph = callGraph;
    originalNodeColors = new HashMap<>();
    constructGraph(callGraph);
    // initializeGraphView();
  }

  private void constructGraph(CallGraph callGraph) {
    System.setProperty("org.graphstream.ui", "org.graphstream.ui.swing");
    Set<String> addedNodes = new HashSet<>();
    List<String> nodesWithoutCycles = callGraph.findNodesWithoutCycles();

    for (CallGraphNode node : callGraph.getGraph().values()) {
      String nodeId = getNodeId(node.getMethodName());

      if (!addedNodes.contains(nodeId)) {
        Node graphNode = addNode(nodeId);
        graphNode.setAttribute("ui.label", node.getMethodName());
        List<String> data = (graphNode.getAttribute("data.kind")==null) ? new java.util.ArrayList<String>() :(List<String>) graphNode.getAttribute("data.kind");
        data.addAll(node.getKinds());
        graphNode.setAttribute("data.kind", data);
        String nodeColor = nodesWithoutCycles.contains(node.getMethodName())
                               ? "green"
                               : "orange";
        graphNode.setAttribute("ui.style", "fill-color: " + nodeColor + ";");
        originalNodeColors.put(nodeId, nodeColor);
        addedNodes.add(nodeId);
      }

      List<CallGraphNode> callees = node.getCallees();
      for (CallGraphNode callee : callees) {
        String calleeId = getNodeId(callee.getMethodName());

        if (!addedNodes.contains(calleeId)) {
          Node graphCallee = addNode(calleeId);
           List<String> data = (graphCallee.getAttribute("data.kind")==null) ? new java.util.ArrayList<String>() :(List<String>) graphCallee.getAttribute("data.kind");
        data.addAll(callee.getKinds());
        graphCallee.setAttribute("data.kind", data);
          graphCallee.setAttribute("ui.label", callee.getMethodName());
          String calleeColor =
              nodesWithoutCycles.contains(callee.getMethodName()) ? "green"
                                                                  : "orange";
          graphCallee.setAttribute("ui.style",
                                   "fill-color: " + calleeColor + ";");
          originalNodeColors.put(calleeId, calleeColor);
          addedNodes.add(calleeId);
        }

        String edgeId = nodeId + calleeId;

        if (!edges().anyMatch(e -> e.getId().equals(edgeId))) {
          try {
            addEdge(edgeId, nodeId, calleeId, true);
          } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
          }
        }
      }
    }
  }

  private void initializeGraphView() {
    String cssPath = "url('file:///" + System.getProperty("user.dir") +
                     "/resources/style.css')";
    setAttribute("ui.stylesheet", cssPath);
    setAttribute("ui.quality");
    setAttribute("ui.antialias");

    SpringBox layout = new SpringBox();
    layout.setStabilizationLimit(0.9);
    layout.addSink(this);
    layout.compute();
    Viewer viewer = this.display();
    viewer.enableAutoLayout();

    View view = viewer.getDefaultView();
    ((Component)view).addMouseWheelListener(e -> {
      e.consume();
      int i = e.getWheelRotation();
      double factor = Math.pow(1.25, i);
      Camera cam = view.getCamera();
      double zoom = cam.getViewPercent() * factor;
      Point2 pxCenter =
          cam.transformGuToPx(cam.getViewCenter().x, cam.getViewCenter().y, 0);
      Point3 guClicked = cam.transformPxToGu(e.getX(), e.getY());
      double newRatioPx2Gu = cam.getMetrics().ratioPx2Gu / factor;
      double x = guClicked.x + (pxCenter.x - e.getX()) / newRatioPx2Gu;
      double y = guClicked.y - (pxCenter.y - e.getY()) / newRatioPx2Gu;
      cam.setViewCenter(x, y, 0);
      cam.setViewPercent(zoom);
    });

    MyMouseManager mouseManager = new MyMouseManager(this, view, viewer);
    view.setMouseManager(mouseManager);
    TarjanStronglyConnectedComponents tscc =
        new TarjanStronglyConnectedComponents();
    tscc.init(this);
    tscc.compute();

    nodes().forEach(
        n
        -> n.setAttribute("ui.label",
                          n.getAttribute("ui.label") + "-" +
                              n.getAttribute(tscc.getSCCIndexAttribute())));

    logTextArea.setEditable(false);
    logTextArea.setContentType("text/html");
    logTextArea.setPreferredSize(new Dimension(300, 150));
    logTextArea.setBackground(new Color(54, 54, 54));
    JScrollPane logScrollPane = new JScrollPane(logTextArea);

    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        resetColors();
        appendLogText(logTextArea, "Graph reset.\n", "blue");
      }
    });

    JToggleButton autoLayoutButton = new JToggleButton("Auto Layout");
    autoLayoutButton.setSelected(true);
    autoLayoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean autoLayoutEnabled = autoLayoutButton.isSelected();
        if (autoLayoutEnabled) {
          viewer.enableAutoLayout();
        } else {
          viewer.disableAutoLayout();
        }
        appendLogText(logTextArea,
                      "Auto layout " +
                          (autoLayoutEnabled ? "enabled" : "disabled") + ".\n",
                      "white");
      }
    });

    selection.setSelected(true);
    selection.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean reachability = selection.isSelected();

        appendLogText(logTextArea,
                      "Mode: " +
                          (reachability ? "reachability"
                                        : "strongly connected components") +
                          ".\n",
                      "red");
      }
    });

    JTextField searchField = new JTextField();
    searchField.setPreferredSize(new Dimension(150, 25));
    JButton searchButton = new JButton("Search");
    searchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String searchText = searchField.getText();
        searchAndHighlightNode(searchText);
      }
    });

    JPanel searchPanel = new JPanel();
    searchPanel.add(searchField);
    searchPanel.add(searchButton);
    // searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));

    nSteps.setPreferredSize(new Dimension(150, 25));

    JLabel resetLabel = new JLabel("Reset the graph to its original state.");
    JLabel autoLayoutLabel =
        new JLabel("If disabled, the graph will not move automatically.");
    JLabel searchLabel = new JLabel(
        "Enter the name of an attribute to search for within the graph.");
    JLabel modeLabel =
        new JLabel("Choose between displaying reachability or SCC.");
    JLabel nStepsLabel = new JLabel(
        "Enter the number of steps to highlight. (Reachability only)");
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(2, 5));
    buttonPanel.add(resetLabel);
    buttonPanel.add(autoLayoutLabel);
    buttonPanel.add(modeLabel);
    buttonPanel.add(searchLabel);
    buttonPanel.add(nStepsLabel);
    buttonPanel.add(resetButton);
    buttonPanel.add(autoLayoutButton);
    buttonPanel.add(selection);
    buttonPanel.add(searchPanel);
    buttonPanel.add(nSteps);

    JPanel logPanel = new JPanel();
    logPanel.setLayout(new BorderLayout());
    logPanel.add(new JLabel("Log:"), BorderLayout.NORTH);
    logPanel.add(logScrollPane, BorderLayout.CENTER);

    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BorderLayout());

    controlPanel.add(logPanel, BorderLayout.SOUTH);

    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.add(buttonPanel, BorderLayout.NORTH);
    frame.add(controlPanel, BorderLayout.SOUTH);
    frame.add((Component)view, BorderLayout.CENTER);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  private void searchAndHighlightNode(String searchText) {
    for (Node node : this) {
      String nodeName = (String)node.getAttribute("ui.label");
      if (nodeName.toLowerCase().contains(searchText.toLowerCase())) {
        // Highlight the found node
        node.setAttribute("ui.style", "fill-color: red;");
        // Append a log message
        appendLogText(logTextArea, "Found node: " + nodeName + "\n", "white");
      }
    }
    // view.display(viewer.getGraphicGraph(), true);
  }

  private void resetColors() {
    nodes().forEach(node -> {
      String nodeId = node.getId();
      String originalColor = originalNodeColors.get(nodeId);
      node.setAttribute("ui.style", "fill-color: " + originalColor + ";");
      node.removeAttribute("ui.hide");
    });
    edges().forEach(edge -> { edge.removeAttribute("ui.hide"); });
  }

  public void reset() { resetColors(); }

  private static String getNodeId(String methodName) {
    return "node_" + methodName.replaceAll("\\W+", "_");
  }

  static class MyMouseManager extends DefaultMouseManager {
    private final GSCallGraph graph;
    private final View viewPanel;
    private final Viewer viewer;

    public MyMouseManager(GSCallGraph graph, View viewPanel, Viewer viewer) {
      this.graph = graph;
      this.viewPanel = viewPanel;
      this.viewer = viewer;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
      graph.reset();
      double x = e.getX();
      double y = e.getY();

      GraphicElement node = view.findGraphicElementAt(getManagedTypes(), x, y);

      if (node != null) {
        Node clickedNode = graph.getNode(node.getId());

        if (clickedNode != null) {
          if (selection.isSelected()) {
            // Find all reachable nodes (including the clicked node
            Set<String> reachableNodes = new HashSet<>();
            Integer nStepsVal;
            try {
              nStepsVal = Integer.parseInt(nSteps.getText());
            } catch (Exception ex) {
              appendLogText(logTextArea, "Invalid number of steps. Using ∞",
                            "red");
              nStepsVal = Integer.MAX_VALUE;
            }
            findReachableNodes(clickedNode, reachableNodes, 0, nStepsVal);

            // Hide all non-reachable nodes and edges
            for (Node n : graph) {
              if (!reachableNodes.contains(n.getId())) {
                n.setAttribute("ui.hide");
              } else {
                n.removeAttribute("ui.hide");
                n.setAttribute("ui.style", "fill-color: blue;");
              }
            }

            for (Edge edge : graph.edges().toArray(Edge[] ::new)) {
              if (!reachableNodes.contains(edge.getNode0().getId()) ||
                  !reachableNodes.contains(edge.getNode1().getId())) {
                edge.setAttribute("ui.hide");
              } else {
                edge.removeAttribute("ui.hide");
              }
            }
            String touchedNodeMessage =
                "Touched Node: " + clickedNode.getAttribute("ui.label");
            String reachableNodesMessage = "Reachable Nodes: " + reachableNodes;
            appendLogText(logTextArea, touchedNodeMessage, "white");
            appendLogText(logTextArea, reachableNodesMessage, "white");
          } else {
            String label = (String)clickedNode.getAttribute("ui.label");
            String sccIndex = label.substring(label.lastIndexOf("-") + 1);
            Integer sccIndexInt = Integer.parseInt(sccIndex);
            appendLogText(logTextArea, "Touched Node: " + sccIndexInt, "white");
            for (Node n : graph) {
              String label2 = (String)n.getAttribute("ui.label");
              String sccIndex2 = label2.substring(label2.lastIndexOf("-") + 1);
              Integer sccIndexInt2 = Integer.parseInt(sccIndex2);
              if (!sccIndexInt.equals(sccIndexInt2)) {
                n.setAttribute("ui.hide");
              } else {
                n.removeAttribute("ui.hide");
                n.setAttribute("ui.style", "fill-color: blue;");
              }
            }
            for (Edge edge : graph.edges().toArray(Edge[] ::new)) {
              String node0 = (String)edge.getNode0().getAttribute("ui.label");
              String node1 = (String)edge.getNode1().getAttribute("ui.label");
              String label1 = node1;
              String sccIndex1 = label1.substring(label1.lastIndexOf("-") + 1);
              Integer sccIndexInt1 = Integer.parseInt(sccIndex1);
              String label0 = node0;
              String sccIndex0 = label0.substring(label0.lastIndexOf("-") + 1);
              Integer sccIndexInt0 = Integer.parseInt(sccIndex0);
              if (!sccIndexInt0.equals(sccIndexInt1) ||
                  !sccIndexInt0.equals(sccIndexInt)) {
                edge.setAttribute("ui.hide");
              } else {
                edge.removeAttribute("ui.hide");
              }
            }
          }

          view.display(viewer.getGraphicGraph(), true);
        }
      }
    }

    private void findReachableNodes(Node node, Set<String> reachableNodes,
                                    Integer depth, Integer maxDepth) {
      if (depth > maxDepth) {
        return;
      }
      reachableNodes.add(node.getId());
      node.leavingEdges().forEach(edge -> {
        Node neighbor =
            edge.getTargetNode(); // The neighbor node in a directed edge
        if (!reachableNodes.contains(neighbor.getId())) {
          findReachableNodes(neighbor, reachableNodes, depth + 1, maxDepth);
        }
      });
    }
  }
  private static void appendLogText(JEditorPane logEditorPane, String message,
                                    String color) {
    String LOG_STYLE = "font-family: Arial, sans-serif; font-weight: bold;";
    HTMLEditorKit kit = (HTMLEditorKit)logEditorPane.getEditorKit();
    String logEntry = String.format(
        "<font style='" + LOG_STYLE + "' color='%s'>%s</font>", color, message);

    try {
      int endPos = logEditorPane.getDocument().getLength();
      kit.insertHTML((HTMLDocument)logEditorPane.getDocument(), endPos,
                     logEntry, 0, 0, null);
      logEditorPane.setCaretPosition(logEditorPane.getDocument().getLength());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Node n : this) {
      String label = (String)n.getAttribute("ui.label");
      String sccIndex = label.substring(label.lastIndexOf("-") + 1);
      Integer sccIndexInt = Integer.parseInt(sccIndex);
      String name = label.substring(0, label.lastIndexOf("-"));

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
    TarjanStronglyConnectedComponents tscc =
        new TarjanStronglyConnectedComponents();
    tscc.init(this);
    tscc.compute();

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
