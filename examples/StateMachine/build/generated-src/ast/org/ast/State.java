/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.5-38-gb46f73b */
package org.ast;
import java.util.*;
/**
 * @ast node
 * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/StateMachine.ast:8
 * @astdecl State : Declaration ::= <Label:String>;
 * @production State : {@link Declaration} ::= <span class="component">&lt;Label:{@link String}&gt;</span>;

 */
public class State extends Declaration implements Cloneable {
  /**
   * @aspect PrintInfoAboutCycles
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintInfoAboutCycles.jrag:12
   */
  public void printInfoAboutCycles() {
    System.out.print("State "+getLabel()+" is ");
    if (!reachable().contains(this)) {
      System.out.print("not ");
    }
    System.out.println("on a cycle.");
  }
  /**
   * @aspect PrettyPrint
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrettyPrint.jrag:14
   */
  public void pp() {
    System.out.println("state "+getLabel()+";");
  }
  /**
   * @aspect PrintReachable
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintReachable.jrag:10
   */
  public void printReachable() {
    System.out.println(getLabel() + " can reach {" +
        listOfReachableStateLabels() + "}");
  }
  /**
   * @aspect PrintReachable
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintReachable.jrag:15
   */
  public String listOfReachableStateLabels() {
    boolean insideList = false;
    StringBuffer result = new StringBuffer();
    for (State s : reachable()) {
      if (insideList)
        result.append(", ");
      else
        insideList = true;
      result.append(s.getLabel());
    }
    return result.toString();
  }
  /**
   * @declaredat ASTNode:1
   */
  public State() {
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
  }
  /**
   * @declaredat ASTNode:12
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Label"},
    type = {"String"},
    kind = {"Token"}
  )
  public State(String p0) {
    setLabel(p0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:21
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:27
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:31
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    reachable_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    State_transitions_computed = null;
    State_transitions_value = null;
    State_altSuccessors_computed = null;
    State_altSuccessors_value = null;
    State_predecessors_computed = null;
    State_predecessors_value = null;
    State_altReachable_cycle = null;
    State_altReachable_computed = false;
    State_altReachable_initialized = false;
    State_altReachable_value = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:50
   */
  public State clone() throws CloneNotSupportedException {
    State node = (State) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:55
   */
  public State copy() {
    try {
      State node = (State) clone();
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
   * @declaredat ASTNode:74
   */
  @Deprecated
  public State fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:84
   */
  public State treeCopyNoTransform() {
    State tree = (State) copy();
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
   * @declaredat ASTNode:104
   */
  public State treeCopy() {
    State tree = (State) copy();
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
   * Replaces the lexeme Label.
   * @param value The new value for the lexeme Label.
   * @apilevel high-level
   */
  public State setLabel(String value) {
    tokenString_Label = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected String tokenString_Label;
  /**
   * Retrieves the value for the lexeme Label.
   * @return The value for the lexeme Label.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="Label")
  public String getLabel() {
    return tokenString_Label != null ? tokenString_Label : "";
  }
  /**
   * @attribute syn
   * @aspect Graph
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:16
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Graph", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:16")
  public Set<State> successors() {
    state().trace().computeBegin("Graph", this, "State.successors()", "", "");
    try {
        Set<State> result = new HashSet<State>();
        for (Transition t : transitions()) {
          if (t.target() != null)
            result.add(t.target());
        }
        return result;
      }
    finally {
      state().trace().computeEnd("Graph", this, "State.successors()", "", "");
    }
  }
  /**
   * @attribute syn
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:6
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:6")
  public boolean alreadyDeclared() {
    state().trace().computeBegin("Exercises", this, "State.alreadyDeclared()", "", "");
    boolean alreadyDeclared_value = lookup(this.getLabel()) != this;
    state().trace().computeEnd("Exercises", this, "State.alreadyDeclared()", "", alreadyDeclared_value);
    return alreadyDeclared_value;
  }
  /**
   * @attribute syn
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:9
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:9")
  public boolean multiplyDeclared() {
    state().trace().computeBegin("Exercises", this, "State.multiplyDeclared()", "", "");
    boolean multiplyDeclared_value = alreadyDeclared() || hasLaterNamesake();
    state().trace().computeEnd("Exercises", this, "State.multiplyDeclared()", "", multiplyDeclared_value);
    return multiplyDeclared_value;
  }
  /**
   * @attribute syn
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:12
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:12")
  public boolean hasLaterNamesake() {
    state().trace().computeBegin("Exercises", this, "State.hasLaterNamesake()", "", "");
    boolean hasLaterNamesake_value = lookupForward(getLabel()) != null;
    state().trace().computeEnd("Exercises", this, "State.hasLaterNamesake()", "", hasLaterNamesake_value);
    return hasLaterNamesake_value;
  }
  /**
   * @attribute syn
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:27
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:27")
  public Set<Transition> altTransitions() {
    state().trace().computeBegin("Exercises", this, "State.altTransitions()", "", "");
    Set<Transition> altTransitions_value = transitionsOf(this);
    state().trace().computeEnd("Exercises", this, "State.altTransitions()", "", altTransitions_value);
    return altTransitions_value;
  }
/** @apilevel internal */
protected ASTState.Cycle reachable_cycle = null;
  /** @apilevel internal */
  private void reachable_reset() {
    reachable_computed = false;
    reachable_initialized = false;
    reachable_value = null;
    reachable_cycle = null;
  }
  /** @apilevel internal */
  protected boolean reachable_computed = false;

  /** @apilevel internal */
  protected Set<State> reachable_value;
  /** @apilevel internal */
  protected boolean reachable_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Reachability", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Reachability.jrag:7")
  public Set<State> reachable() {
    if (reachable_computed) {
      return reachable_value;
    }
    ASTState state = state();
    if (!reachable_initialized) {
      reachable_initialized = true;
      reachable_value = new HashSet<State>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        reachable_cycle = state.nextCycle();
        state().trace().computeBegin("Reachability", this, "State.reachable()", "", "");
        Set<State> new_reachable_value = reachable_compute();
        state().trace().computeEnd("Reachability", this, "State.reachable()", "", reachable_value);
        if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
          state.setChangeInCycle();
        }
        reachable_value = new_reachable_value;
      } while (state.testAndClearChangeInCycle());
      reachable_computed = true;
      state.startLastCycle();
      Set<State> $tmp = reachable_compute();

      state.leaveCircle();
    } else if (reachable_cycle != state.cycle()) {
      reachable_cycle = state.cycle();
      if (state.lastCycle()) {
        reachable_computed = true;
        Set<State> new_reachable_value = reachable_compute();
        return new_reachable_value;
      }
      state().trace().computeBegin("Reachability", this, "State.reachable()", "", "");
      Set<State> new_reachable_value = reachable_compute();
      state().trace().computeEnd("Reachability", this, "State.reachable()", "", reachable_value);
      if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
        state.setChangeInCycle();
      }
      reachable_value = new_reachable_value;
    } else {
    }
    return reachable_value;
  }
  /** @apilevel internal */
  private Set<State> reachable_compute() { // R2
      HashSet<State> result = new HashSet<State>();
      for (State s : successors()) {
        result.add(s);
        result.addAll(s.reachable());
      }
      return result;
    }
  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:20
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:18")
  public State localLookup(String label) {
    Object _parameters = label;
    state().trace().computeBegin("NameAnalysis", this, "Declaration.localLookup(String)", _parameters, "");
    State localLookup_String_value = // R6
          (label.equals(getLabel())) ? this : null;
    state().trace().computeEnd("NameAnalysis", this, "Declaration.localLookup(String)", _parameters, localLookup_String_value);
    return localLookup_String_value;
  }
  /**
   * @attribute inh
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:28
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:28")
  public Set<Transition> transitionsOf(State s) {
    Object _parameters = s;
    state().trace().computeBegin("Exercises", this, "State.transitionsOf(State)", _parameters, "");
    Set<Transition> transitionsOf_State_value = getParent().Define_transitionsOf(this, null, s);
    state().trace().computeEnd("Exercises", this, "State.transitionsOf(State)", _parameters, transitionsOf_State_value);
    return transitionsOf_State_value;
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
   * @aspect Graph
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:9
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Graph", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:9")
  public Set<Transition> transitions() {
    ASTState state = state();
    if (State_transitions_computed == ASTState.NON_CYCLE || State_transitions_computed == state().cycle()) {
      return State_transitions_value;
    }
    state().trace().computeBegin("Graph", this, "State.transitions()", "", "");
    State_transitions_value = transitions_compute();
    state().trace().computeEnd("Graph", this, "State.transitions()", "", State_transitions_value);
    if (state().inCircle()) {
      State_transitions_computed = state().cycle();
    
    } else {
      State_transitions_computed = ASTState.NON_CYCLE;
    
    }
    return State_transitions_value;
  }
  /** @apilevel internal */
  private Set<Transition> transitions_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    StateMachine root = (StateMachine) node;
    root.survey_State_transitions();
    Set<Transition> _computedValue = new HashSet<Transition>();
    if (root.contributorMap_State_transitions.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_State_transitions.get(this)) {
        contributor.contributeTo_State_transitions(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle State_transitions_computed = null;

  /** @apilevel internal */
  protected Set<Transition> State_transitions_value;

  /**
   * @attribute coll
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:48
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:48")
  public Set<State> altSuccessors() {
    ASTState state = state();
    if (State_altSuccessors_computed == ASTState.NON_CYCLE || State_altSuccessors_computed == state().cycle()) {
      return State_altSuccessors_value;
    }
    state().trace().computeBegin("Exercises", this, "State.altSuccessors()", "", "");
    State_altSuccessors_value = altSuccessors_compute();
    state().trace().computeEnd("Exercises", this, "State.altSuccessors()", "", State_altSuccessors_value);
    if (state().inCircle()) {
      State_altSuccessors_computed = state().cycle();
    
    } else {
      State_altSuccessors_computed = ASTState.NON_CYCLE;
    
    }
    return State_altSuccessors_value;
  }
  /** @apilevel internal */
  private Set<State> altSuccessors_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    StateMachine root = (StateMachine) node;
    root.survey_State_altSuccessors();
    Set<State> _computedValue = new HashSet<State>();
    if (root.contributorMap_State_altSuccessors.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_State_altSuccessors.get(this)) {
        contributor.contributeTo_State_altSuccessors(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle State_altSuccessors_computed = null;

  /** @apilevel internal */
  protected Set<State> State_altSuccessors_value;

  /**
   * @attribute coll
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:57
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:57")
  public Set<State> predecessors() {
    ASTState state = state();
    if (State_predecessors_computed == ASTState.NON_CYCLE || State_predecessors_computed == state().cycle()) {
      return State_predecessors_value;
    }
    state().trace().computeBegin("Exercises", this, "State.predecessors()", "", "");
    State_predecessors_value = predecessors_compute();
    state().trace().computeEnd("Exercises", this, "State.predecessors()", "", State_predecessors_value);
    if (state().inCircle()) {
      State_predecessors_computed = state().cycle();
    
    } else {
      State_predecessors_computed = ASTState.NON_CYCLE;
    
    }
    return State_predecessors_value;
  }
  /** @apilevel internal */
  private Set<State> predecessors_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    StateMachine root = (StateMachine) node;
    root.survey_State_predecessors();
    Set<State> _computedValue = new HashSet<State>();
    if (root.contributorMap_State_predecessors.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_State_predecessors.get(this)) {
        contributor.contributeTo_State_predecessors(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected ASTState.Cycle State_predecessors_computed = null;

  /** @apilevel internal */
  protected Set<State> State_predecessors_value;

/** @apilevel internal */
protected ASTState.Cycle State_altReachable_cycle = null;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:102")
  public Set<State> altReachable() {
    if (State_altReachable_computed) {
      return State_altReachable_value;
    }
    ASTState state = state();
    if (!State_altReachable_initialized) {
      ASTNode _node = this;
      while (_node != null && !(_node instanceof StateMachine)) {
        _node = _node.getParent();
      }
      StateMachine root = (StateMachine) _node;
      if (root.collecting_contributors_State_altReachable) {
        throw new RuntimeException("Circularity during survey phase");
      }
      root.survey_State_altReachable();
      State_altReachable_initialized = true;
      State_altReachable_value = new HashSet<State>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        State_altReachable_cycle = state.nextCycle();
        state().trace().computeBegin("Exercises", this, "State.altReachable()", "", "");
        Set<State> new_State_altReachable_value = altReachable_compute();
        state().trace().computeEnd("Exercises", this, "State.altReachable()", "", State_altReachable_value);
        if (!AttributeValue.equals(State_altReachable_value, new_State_altReachable_value)) {
          state.setChangeInCycle();
        }
        State_altReachable_value = new_State_altReachable_value;
      } while (state.testAndClearChangeInCycle());
      State_altReachable_computed = true;
      state.startLastCycle();
      Set<State> $tmp = altReachable_compute();

      state.leaveCircle();
    } else if (State_altReachable_cycle != state.cycle()) {
      State_altReachable_cycle = state.cycle();
      if (state.lastCycle()) {
        State_altReachable_computed = true;
        Set<State> new_State_altReachable_value = altReachable_compute();
        return new_State_altReachable_value;
      }
      state().trace().computeBegin("Exercises", this, "State.altReachable()", "", "");
      Set<State> new_State_altReachable_value = altReachable_compute();
      state().trace().computeEnd("Exercises", this, "State.altReachable()", "", State_altReachable_value);
      if (!AttributeValue.equals(State_altReachable_value, new_State_altReachable_value)) {
        state.setChangeInCycle();
      }
      State_altReachable_value = new_State_altReachable_value;
    } else {
    }
    return State_altReachable_value;
  }
  /** @apilevel internal */
  private Set<State> altReachable_compute() {
    ASTNode node = this;
    while (node.getParent() != null) {
      node = node.getParent();
    }
    StateMachine root = (StateMachine) node;
    root.survey_State_altReachable();
    Set<State> _computedValue = new HashSet<State>();
    if (root.contributorMap_State_altReachable.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_State_altReachable.get(this)) {
        contributor.contributeTo_State_altReachable(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected boolean State_altReachable_computed = false;

  /** @apilevel internal */
  protected Set<State> State_altReachable_value;
  /** @apilevel internal */
  protected boolean State_altReachable_initialized = false;
  /** @apilevel internal */
  protected void collect_contributors_State_predecessors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:59
    for (State target : (Iterable<? extends State>) (successors())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_State_predecessors(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_StateMachine_errors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:86
    if (alreadyDeclared()) {
      {
        StateMachine target = (StateMachine) (theMachine());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_StateMachine_errors(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_State_altReachable(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:104
    for (State target : (Iterable<? extends State>) (predecessors())) {
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_State_altReachable(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_State_predecessors(Set<State> collection) {
    super.contributeTo_State_predecessors(collection);
    collection.add(this);
  }
  /** @apilevel internal */
  protected void contributeTo_StateMachine_errors(Set<String> collection) {
    super.contributeTo_StateMachine_errors(collection);
    if (alreadyDeclared()) {
      collection.add(getLabel()+" is already declared");
    }
  }
  /** @apilevel internal */
  protected void contributeTo_State_altReachable(Set<State> collection) {
    super.contributeTo_State_altReachable(collection);
    collection.addAll(union(asSet(this),altReachable()));
  }

}
