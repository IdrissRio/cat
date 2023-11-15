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
import java.util.List;
import java.util.Map;
import org.extendj.ast.InvocationTarget;

/**
 * Represents a node in the call graph.
 */
public class CallGraphNode {
  private String methodName;
  private List<CallGraphNode> callees;
  private List<CallGraphNode> callers;
  private List<String> kinds = new ArrayList<>();
  private int sccID;
  private InvocationTarget target;
  /**
   * Creates a new CallGraphNode with the given method name.
   *
   * @param methodName The name of the method represented by this node.
   */
  public CallGraphNode(String methodName, List<String> kinds,
                       InvocationTarget target) {
    this.methodName = methodName;
    this.callees = new ArrayList<>();
    this.callers = new ArrayList<>();
    this.kinds = kinds;
    this.sccID = 0;
    this.target = target;
    target.setCallGraphNode(this);
  }

  public List<String> getKinds() { return kinds; }
  public boolean isBridge() { return false; }

  /**
   * Adds a callee to the current node's list of callees.
   *
   * @param callee The callee to add.
   */
  public void addCallee(CallGraphNode callee) {
    if (callees.contains(callee))
      return;
    callees.add(callee);
    callee.addCaller(this);
  }

  public void addCaller(CallGraphNode caller) { callers.add(caller); }

  public void removeCallee(CallGraphNode callee) {
    callees.remove(callee);
    callee.removeCaller(this);
  }

  public InvocationTarget getTarget() { return target; }

  public void removeCaller(CallGraphNode caller) { callers.remove(caller); }

  /**
   * Gets the list of callers for the current node.
   *
   * @return The list of callers.
   */
  public List<CallGraphNode> getCallers() { return callers; }

  /**
   * Gets the name of the method represented by this node.
   *
   * @return The method name.
   */
  public String getMethodName() { return methodName; }

  /**
   * Gets the list of callees for the current node.
   *
   * @return The list of callees.
   */
  public List<CallGraphNode> getCallees() { return callees; }

  /**
   * Returns a string representation of the CallGraphNode.
   * It includes the method name and the names of its callees.
   *
   * @return The string representation of the CallGraphNode.
   */
  @Override
  public String toString() {
    List<CallGraphNode> sortedCallees = new ArrayList<>(callees);
    sortedCallees.sort(Comparator.comparing(CallGraphNode::getMethodName));

    StringBuilder sb = new StringBuilder();
    sb.append(methodName);
    if (!sortedCallees.isEmpty()) {
      sb.append(" -> ");
      for (int i = 0; i < sortedCallees.size(); i++) {
        sb.append(sortedCallees.get(i).getMethodName());
        if (i < sortedCallees.size() - 1) {
          sb.append(", ");
        }
      }
    }
    return sb.toString();
  }

  /**
   * Sets the sscID of the current node.
   *
   * @param sscID The sscID to set.
   */
  public void setSccID(Integer sscID) { this.sccID = sscID; }

  /**
   * Gets the sscID of the current node.
   *
   * @return The sscID.
   */
  public Integer getSccID() { return sccID; }
}