/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.5-38-gb46f73b */
package org.ast;
import java.util.*;
/**
 * @ast node
 * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/StateMachine.ast:9
 * @astdecl Transition : Declaration ::= <Label:String> <SourceLabel:String> <TargetLabel:String>;
 * @production Transition : {@link Declaration} ::= <span class="component">&lt;Label:{@link String}&gt;</span> <span class="component">&lt;SourceLabel:{@link String}&gt;</span> <span class="component">&lt;TargetLabel:{@link String}&gt;</span>;

 */
public class Transition extends Declaration implements Cloneable {
  /**
   * @aspect PrettyPrint
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrettyPrint.jrag:18
   */
  public void pp() {
    System.out.println("trans "+getLabel()+":"+getSourceLabel()+"->"+getTargetLabel()+";");
  }
  /**
   * @declaredat ASTNode:1
   */
  public Transition() {
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
    name = {"Label", "SourceLabel", "TargetLabel"},
    type = {"String", "String", "String"},
    kind = {"Token", "Token", "Token"}
  )
  public Transition(String p0, String p1, String p2) {
    setLabel(p0);
    setSourceLabel(p1);
    setTargetLabel(p2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 0;
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

  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public Transition clone() throws CloneNotSupportedException {
    Transition node = (Transition) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:48
   */
  public Transition copy() {
    try {
      Transition node = (Transition) clone();
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
   * @declaredat ASTNode:67
   */
  @Deprecated
  public Transition fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:77
   */
  public Transition treeCopyNoTransform() {
    Transition tree = (Transition) copy();
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
   * @declaredat ASTNode:97
   */
  public Transition treeCopy() {
    Transition tree = (Transition) copy();
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
  public Transition setLabel(String value) {
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
   * Replaces the lexeme SourceLabel.
   * @param value The new value for the lexeme SourceLabel.
   * @apilevel high-level
   */
  public Transition setSourceLabel(String value) {
    tokenString_SourceLabel = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected String tokenString_SourceLabel;
  /**
   * Retrieves the value for the lexeme SourceLabel.
   * @return The value for the lexeme SourceLabel.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="SourceLabel")
  public String getSourceLabel() {
    return tokenString_SourceLabel != null ? tokenString_SourceLabel : "";
  }
  /**
   * Replaces the lexeme TargetLabel.
   * @param value The new value for the lexeme TargetLabel.
   * @apilevel high-level
   */
  public Transition setTargetLabel(String value) {
    tokenString_TargetLabel = value;
    return this;
  }
  /** @apilevel internal 
   */
  protected String tokenString_TargetLabel;
  /**
   * Retrieves the value for the lexeme TargetLabel.
   * @return The value for the lexeme TargetLabel.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="TargetLabel")
  public String getTargetLabel() {
    return tokenString_TargetLabel != null ? tokenString_TargetLabel : "";
  }
  /**
   * @attribute syn
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:39")
  public Transition transitionOf(State s) {
    Object _parameters = s;
    state().trace().computeBegin("Exercises", this, "Declaration.transitionOf(State)", _parameters, "");
    try {
        if (source() == s)
          return this;
        else
          return null;
      }
    finally {
      state().trace().computeEnd("Exercises", this, "Declaration.transitionOf(State)", _parameters, "");
    }
  }
  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:6
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:6")
  public State source() {
    state().trace().computeBegin("NameAnalysis", this, "Transition.source()", "", "");
    State source_value = lookup(getSourceLabel());
    state().trace().computeEnd("NameAnalysis", this, "Transition.source()", "", source_value);
    return source_value;
  }
  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:7
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:7")
  public State target() {
    state().trace().computeBegin("NameAnalysis", this, "Transition.target()", "", "");
    State target_value = lookup(getTargetLabel());
    state().trace().computeEnd("NameAnalysis", this, "Transition.target()", "", target_value);
    return target_value;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /** @apilevel internal */
  protected void collect_contributors_State_transitions(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:11
    if (source() != null) {
      {
        State target = (State) (source());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_State_transitions(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_State_altSuccessors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:50
    if (target() != null && source() != null) {
      {
        State target = (State) (source());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_State_altSuccessors(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_StateMachine_numberOfTransitionsColl(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:68
    {
      StateMachine target = (StateMachine) (theMachine());
      java.util.Set<ASTNode> contributors = _map.get(target);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) target, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_StateMachine_numberOfTransitionsColl(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_StateMachine_errors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:91
    if (source() == null) {
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
    // @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:96
    if (target() == null) {
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
  protected void contributeTo_State_transitions(Set<Transition> collection) {
    super.contributeTo_State_transitions(collection);
    if (source() != null) {
      collection.add(this);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_State_altSuccessors(Set<State> collection) {
    super.contributeTo_State_altSuccessors(collection);
    if (target() != null && source() != null) {
      collection.add(target());
    }
  }
  /** @apilevel internal */
  protected void contributeTo_StateMachine_numberOfTransitionsColl(Counter collection) {
    super.contributeTo_StateMachine_numberOfTransitionsColl(collection);
    collection.add(1);
  }
  /** @apilevel internal */
  protected void contributeTo_StateMachine_errors(Set<String> collection) {
    super.contributeTo_StateMachine_errors(collection);
    if (source() == null) {
      collection.add("Missing declaration of "+getSourceLabel());
    }
    if (target() == null) {
      collection.add("Missing declaration of "+getTargetLabel());
    }
  }

}
