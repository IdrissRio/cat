/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.5-38-gb46f73b */
package org.ast;
import java.util.*;
/**
 * @ast node
 * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/StateMachine.ast:7
 * @astdecl Declaration : ASTNode;
 * @production Declaration : {@link ASTNode};

 */
public abstract class Declaration extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect PrintInfoAboutCycles
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintInfoAboutCycles.jrag:10
   */
  public void printInfoAboutCycles() {}
  /**
   * @aspect PrettyPrint
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrettyPrint.jrag:12
   */
  public void pp() {}
  /**
   * @aspect PrintReachable
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/PrintReachable.jrag:8
   */
  public void printReachable() { }
  /**
   * @declaredat ASTNode:1
   */
  public Declaration() {
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
  /** @apilevel low-level 
   * @declaredat ASTNode:13
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:19
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:23
   */
  public void flushAttrCache() {
    super.flushAttrCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public Declaration clone() throws CloneNotSupportedException {
    Declaration node = (Declaration) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:44
   */
  @Deprecated
  public abstract Declaration fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:52
   */
  public abstract Declaration treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:60
   */
  public abstract Declaration treeCopy();
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
    Transition transitionOf_State_value = null;
    state().trace().computeEnd("Exercises", this, "Declaration.transitionOf(State)", _parameters, transitionOf_State_value);
    return transitionOf_State_value;
  }
  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:18
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:18")
  public State localLookup(String label) {
    Object _parameters = label;
    state().trace().computeBegin("NameAnalysis", this, "Declaration.localLookup(String)", _parameters, "");
    State localLookup_String_value = null;
    state().trace().computeEnd("NameAnalysis", this, "Declaration.localLookup(String)", _parameters, localLookup_String_value);
    return localLookup_String_value;
  }
  /**
   * @attribute inh
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:15
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:15")
  public State lookupForward(String label) {
    Object _parameters = label;
    state().trace().computeBegin("Exercises", this, "Declaration.lookupForward(String)", _parameters, "");
    State lookupForward_String_value = getParent().Define_lookupForward(this, null, label);
    state().trace().computeEnd("Exercises", this, "Declaration.lookupForward(String)", _parameters, lookupForward_String_value);
    return lookupForward_String_value;
  }
  /**
   * @attribute inh
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:72
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="Exercises", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:72")
  public StateMachine theMachine() {
    state().trace().computeBegin("Exercises", this, "Declaration.theMachine()", "", "");
    StateMachine theMachine_value = getParent().Define_theMachine(this, null);
    state().trace().computeEnd("Exercises", this, "Declaration.theMachine()", "", theMachine_value);
    return theMachine_value;
  }
  /**
   * @attribute inh
   * @aspect NameAnalysis
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:8")
  public State lookup(String label) {
    Object _parameters = label;
    state().trace().computeBegin("NameAnalysis", this, "Declaration.lookup(String)", _parameters, "");
    State lookup_String_value = getParent().Define_lookup(this, null, label);
    state().trace().computeEnd("NameAnalysis", this, "Declaration.lookup(String)", _parameters, lookup_String_value);
    return lookup_String_value;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }

}
