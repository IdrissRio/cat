/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.5-38-gb46f73b */
package org.ast;
import java.util.*;
/**
 * @ast node
 * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/StateMachine.ast:6
 * @astdecl StateMachine : ASTNode ::= Declaration*;
 * @production StateMachine : {@link ASTNode} ::= <span class="component">{@link Declaration}*</span>;

 */
public class StateMachine extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect PrintInfoAboutCycles
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintInfoAboutCycles.jrag:4
   */
  public void printInfoAboutCycles() {
    for (Declaration d : getDeclarationList()) {
      d.printInfoAboutCycles();
    }
  }
  /**
   * @aspect Graph
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:6
   */
  public
  AttributeTracer trace = new AttributeTracer();
  /**
   * @aspect PrettyPrint
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrettyPrint.jrag:6
   */
  public void pp() {
    for (Declaration d:getDeclarationList()) {
      d.pp();
    }
  }
  /**
   * @aspect PrintReachable
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintReachable.jrag:4
   */
  public void printReachable() {
    for (Declaration d : getDeclarations()) d.printReachable();
  }
  /**
   * @declaredat ASTNode:1
   */
  public StateMachine() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[1];
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Declaration"},
    type = {"List<Declaration>"},
    kind = {"List"}
  )
  public StateMachine(List<Declaration> p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 1;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:29
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public void flushAttrCache() {
    super.flushAttrCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    StateMachine_numberOfTransitionsColl_computed = null;
    StateMachine_numberOfTransitionsColl_value = null;
    StateMachine_errors_computed = null;
    StateMachine_errors_value = null;
    contributorMap_StateMachine_numberOfTransitionsColl = null;
    contributorMap_StateMachine_errors = null;
    contributorMap_State_transitions = null;
    contributorMap_State_altSuccessors = null;
    contributorMap_State_predecessors = null;
    contributorMap_State_altReachable = null;
    collecting_contributors_State_altReachable = false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
   */
  public StateMachine clone() throws CloneNotSupportedException {
    StateMachine node = (StateMachine) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:58
   */
  public StateMachine copy() {
    try {
      StateMachine node = (StateMachine) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:77
   */
  @Deprecated
  public StateMachine fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:87
   */
  public StateMachine treeCopyNoTransform() {
    StateMachine tree = (StateMachine) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:107
   */
  public StateMachine treeCopy() {
    StateMachine tree = (StateMachine) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Replaces the Declaration list.
   * @param list The new list node to be used as the Declaration list.
   * @apilevel high-level
   */
  public StateMachine setDeclarationList(List<Declaration> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the Declaration list.
   * @return Number of children in the Declaration list.
   * @apilevel high-level
   */
  public int getNumDeclaration() {
    return getDeclarationList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Declaration list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Declaration list.
   * @apilevel low-level
   */
  public int getNumDeclarationNoTransform() {
    return getDeclarationListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Declaration list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Declaration list.
   * @apilevel high-level
   */
  public Declaration getDeclaration(int i) {
    return (Declaration) getDeclarationList().getChild(i);
  }
  /**
   * Check whether the Declaration list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasDeclaration() {
    return getDeclarationList().getNumChild() != 0;
  }
  /**
   * Append an element to the Declaration list.
   * @param node The element to append to the Declaration list.
   * @apilevel high-level
   */
  public StateMachine addDeclaration(Declaration node) {
    List<Declaration> list = (parent == null) ? getDeclarationListNoTransform() : getDeclarationList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public StateMachine addDeclarationNoTransform(Declaration node) {
    List<Declaration> list = getDeclarationListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the Declaration list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public StateMachine setDeclaration(Declaration node, int i) {
    List<Declaration> list = getDeclarationList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the Declaration list.
   * @return The node representing the Declaration list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Declaration")
  public List<Declaration> getDeclarationList() {
    List<Declaration> list = (List<Declaration>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Declaration list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Declaration list.
   * @apilevel low-level
   */
  public List<Declaration> getDeclarationListNoTransform() {
    return (List<Declaration>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Declaration list without
   * triggering rewrites.
   */
  public Declaration getDeclarationNoTransform(int i) {
    return (Declaration) getDeclarationListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Declaration list.
   * @return The node representing the Declaration list.
   * @apilevel high-level
   */
  public List<Declaration> getDeclarations() {
    return getDeclarationList();
  }
  /**
   * Retrieves the Declaration list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Declaration list.
   * @apilevel low-level
   */
  public List<Declaration> getDeclarationsNoTransform() {
    return getDeclarationListNoTransform();
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:66
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_StateMachine_numberOfTransitionsColl = null;

  /** @apilevel internal */
  protected void survey_StateMachine_numberOfTransitionsColl() {
    if (contributorMap_StateMachine_numberOfTransitionsColl == null) {
      contributorMap_StateMachine_numberOfTransitionsColl = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_StateMachine_numberOfTransitionsColl(this, contributorMap_StateMachine_numberOfTransitionsColl);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:83
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_StateMachine_errors = null;

  /** @apilevel internal */
  protected void survey_StateMachine_errors() {
    if (contributorMap_StateMachine_errors == null) {
      contributorMap_StateMachine_errors = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_StateMachine_errors(this, contributorMap_StateMachine_errors);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:9
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_State_transitions = null;

  /** @apilevel internal */
  protected void survey_State_transitions() {
    if (contributorMap_State_transitions == null) {
      contributorMap_State_transitions = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_State_transitions(this, contributorMap_State_transitions);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:48
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_State_altSuccessors = null;

  /** @apilevel internal */
  protected void survey_State_altSuccessors() {
    if (contributorMap_State_altSuccessors == null) {
      contributorMap_State_altSuccessors = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_State_altSuccessors(this, contributorMap_State_altSuccessors);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:57
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_State_predecessors = null;

  /** @apilevel internal */
  protected void survey_State_predecessors() {
    if (contributorMap_State_predecessors == null) {
      contributorMap_State_predecessors = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_State_predecessors(this, contributorMap_State_predecessors);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:102
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_State_altReachable = null;

  /** @apilevel internal */
  protected boolean collecting_contributors_State_altReachable = false;

  /** @apilevel internal */
  protected void survey_State_altReachable() {
    if (contributorMap_State_altReachable == null) {
      contributorMap_State_altReachable = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collecting_contributors_State_altReachable = true;
      collect_contributors_State_altReachable(this, contributorMap_State_altReachable);
      collecting_contributors_State_altReachable = false;
    }
  }

  /**
   * @attribute syn
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:64
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:64")
  public int numberOfTransitions() {
    state().trace().computeBegin("Exercises", this, "StateMachine.numberOfTransitions()", "", "");
    int numberOfTransitions_value = numberOfTransitionsColl().value();
    state().trace().computeEnd("Exercises", this, "StateMachine.numberOfTransitions()", "", numberOfTransitions_value);
    return numberOfTransitions_value;
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:15
   * @apilevel internal
   */
  public State Define_lookupForward(ASTNode _callerNode, ASTNode _childNode, String label) {
    if (_callerNode == getDeclarationListNoTransform()) {
      // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:17
      int i = _callerNode.getIndexOfChild(_childNode);
      {
          for (int k = i+1; k<getNumDeclaration(); k++) {
            Declaration d = getDeclaration(k);
            State match = d.localLookup(label);
            if (match != null) return match;
          }
          return null;
        }
    }
    else {
      return getParent().Define_lookupForward(this, _callerNode, label);
    }
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:15
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupForward
   */
  protected boolean canDefine_lookupForward(ASTNode _callerNode, ASTNode _childNode, String label) {
    return true;
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:28
   * @apilevel internal
   */
  public Set<Transition> Define_transitionsOf(ASTNode _callerNode, ASTNode _childNode, State s) {
    if (_callerNode == getDeclarationListNoTransform()) {
      // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:30
      int i = _callerNode.getIndexOfChild(_childNode);
      {
          HashSet<Transition> result = new HashSet<Transition>();
          for (Declaration d : getDeclarationList()) {
            Transition t = d.transitionOf(s);
            if (t != null) result.add(t);
          }
          return result;
        }
    }
    else {
      return getParent().Define_transitionsOf(this, _callerNode, s);
    }
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:28
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute transitionsOf
   */
  protected boolean canDefine_transitionsOf(ASTNode _callerNode, ASTNode _childNode, State s) {
    return true;
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:72
   * @apilevel internal
   */
  public StateMachine Define_theMachine(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getDeclarationListNoTransform()) {
      // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:73
      int i = _callerNode.getIndexOfChild(_childNode);
      return this;
    }
    else {
      return getParent().Define_theMachine(this, _callerNode);
    }
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:72
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute theMachine
   */
  protected boolean canDefine_theMachine(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:8
   * @apilevel internal
   */
  public State Define_lookup(ASTNode _callerNode, ASTNode _childNode, String label) {
    if (_callerNode == getDeclarationListNoTransform()) {
      // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:10
      int i = _callerNode.getIndexOfChild(_childNode);
      { // R4
          for (Declaration d : getDeclarationList()) {
            State match = d.localLookup(label);
            if (match != null) return match;
          }
          return null;
        }
    }
    else {
      return getParent().Define_lookup(this, _callerNode, label);
    }
  }
  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:8
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookup
   */
  protected boolean canDefine_lookup(ASTNode _callerNode, ASTNode _childNode, String label) {
    return true;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /**
   * @attribute coll
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:66
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:66")
  public Counter numberOfTransitionsColl() {
    ASTState state = state();
    if (StateMachine_numberOfTransitionsColl_computed == ASTState.NON_CYCLE || StateMachine_numberOfTransitionsColl_computed == state().cycle()) {
      return StateMachine_numberOfTransitionsColl_value;
    }
    state().trace().computeBegin("Exercises", this, "StateMachine.numberOfTransitionsColl()", "", "");
    StateMachine_numberOfTransitionsColl_value = numberOfTransitionsColl_compute();
    state().trace().computeEnd("Exercises", this, "StateMachine.numberOfTransitionsColl()", "", StateMachine_numberOfTransitionsColl_value);
    if (state().inCircle()) {
      StateMachine_numberOfTransitionsColl_computed = state().cycle();
    
    } else {
      StateMachine_numberOfTransitionsColl_computed = ASTState.NON_CYCLE;
    
    }
    return StateMachine_numberOfTransitionsColl_value;
  }
  /** @apilevel internal */
  private Counter numberOfTransitionsColl_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    StateMachine root = (StateMachine) node;
    root.survey_StateMachine_numberOfTransitionsColl();
    Counter _computedValue = new Counter();
    if (root.contributorMap_StateMachine_numberOfTransitionsColl.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_StateMachine_numberOfTransitionsColl.get(this)) {
        contributor.contributeTo_StateMachine_numberOfTransitionsColl(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle StateMachine_numberOfTransitionsColl_computed = null;

  /** @apilevel internal */
  protected Counter StateMachine_numberOfTransitionsColl_value;

  /**
   * @attribute coll
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:83
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:83")
  public Set<String> errors() {
    ASTState state = state();
    if (StateMachine_errors_computed == ASTState.NON_CYCLE || StateMachine_errors_computed == state().cycle()) {
      return StateMachine_errors_value;
    }
    state().trace().computeBegin("Exercises", this, "StateMachine.errors()", "", "");
    StateMachine_errors_value = errors_compute();
    state().trace().computeEnd("Exercises", this, "StateMachine.errors()", "", StateMachine_errors_value);
    if (state().inCircle()) {
      StateMachine_errors_computed = state().cycle();
    
    } else {
      StateMachine_errors_computed = ASTState.NON_CYCLE;
    
    }
    return StateMachine_errors_value;
  }
  /** @apilevel internal */
  private Set<String> errors_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    StateMachine root = (StateMachine) node;
    root.survey_StateMachine_errors();
    Set<String> _computedValue = new HashSet<String>();
    if (root.contributorMap_StateMachine_errors.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_StateMachine_errors.get(this)) {
        contributor.contributeTo_StateMachine_errors(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle StateMachine_errors_computed = null;

  /** @apilevel internal */
  protected Set<String> StateMachine_errors_value;


}
