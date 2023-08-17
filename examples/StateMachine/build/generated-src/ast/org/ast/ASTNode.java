/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.5-38-gb46f73b */
package org.ast;
import java.util.*;
/**
 * @ast node
 * @astdecl ASTNode;
 * @production ASTNode;

 */
public class ASTNode<T extends ASTNode> implements Cloneable {
  /**
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:109
   */
  Set<State> asSet(State o) {
    HashSet<State> result = new HashSet<State>();
    result.add(o);
    return result;
  }
  /**
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:115
   */
  Set<State> union(Set<State> s1, Set<State> s2) {
    HashSet<State> result = new HashSet<State>();
    for (State s: s1) result.add(s);
    for (State s: s2) result.add(s);
    return result;
  }
  /**
   * @declaredat ASTNode:1
   */
  public ASTNode() {
    super();
    init$Children();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:11
   */
  public void init$Children() {
  }
  /**
   * Cached child index. Child indices are assumed to never change (AST should
   * not change after construction).
   * @apilevel internal
   * @declaredat ASTNode:18
   */
  private int childIndex = -1;
  /** @apilevel low-level 
   * @declaredat ASTNode:21
   */
  public int getIndexOfChild(ASTNode node) {
    if (node == null) {
      return -1;
    }
    if (node.childIndex >= 0) {
      return node.childIndex;
    }
    for (int i = 0; children != null && i < children.length; i++) {
      if (getChild(i) == node) {
        node.childIndex = i;
        return i;
      }
    }
    return -1;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public static final boolean generatedWithCacheCycle = true;
  /** @apilevel low-level 
   * @declaredat ASTNode:41
   */
  protected ASTNode parent;
  /** @apilevel low-level 
   * @declaredat ASTNode:44
   */
  protected ASTNode[] children;
  /**
   * @declaredat ASTNode:46
   */
  public final ASTState.Trace trace() {
    return state().trace();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  private static ASTState state = new ASTState();
  /** @apilevel internal 
   * @declaredat ASTNode:54
   */
  public final ASTState state() {
    return state;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:59
   */
  public final static ASTState resetState() {
    return state = new ASTState();
  }
  /**
   * @return an iterator that can be used to iterate over the children of this node.
   * The iterator does not allow removing children.
   * @declaredat ASTNode:68
   */
  public java.util.Iterator<T> astChildIterator() {
    return new java.util.Iterator<T>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < getNumChild();
      }

      @Override
      public T next() {
        return hasNext() ? (T) getChild(index++) : null;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
  /** @return an object that can be used to iterate over the children of this node 
   * @declaredat ASTNode:90
   */
  public Iterable<T> astChildren() {
    return new Iterable<T>() {
      @Override
      public java.util.Iterator<T> iterator() {
        return astChildIterator();
      }
    };
  }
  /**
   * @declaredat ASTNode:99
   */
  public static String nodeToString(Object node) {
    return (node != null ? node.getClass().getSimpleName() : "null");
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:103
   */
  public T getChild(int i) {
    ASTNode node = this.getChildNoTransform(i);
    if (node != null && node.mayHaveRewrite()) {
      ASTNode rewritten = node.rewrittenNode();
      if (rewritten != node) {
        rewritten.setParent(this);
        node = rewritten;
      }
    }
    return (T) node;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:115
   */
  public ASTNode addChild(T node) {
    setChild(node, getNumChildNoTransform());
    return this;
  }
  /**
   * Gets a child without triggering rewrites.
   * @apilevel low-level
   * @declaredat ASTNode:123
   */
  public T getChildNoTransform(int i) {
    if (children == null) {
      return null;
    }
    T child = (T) children[i];
    return child;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:131
   */
  protected int numChildren;
  /** @apilevel low-level 
   * @declaredat ASTNode:134
   */
  protected int numChildren() {
    return numChildren;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:139
   */
  public int getNumChild() {
    return numChildren();
  }
  /**
   * Behaves like getNumChild, but does not invoke AST transformations (rewrites).
   * @apilevel low-level
   * @declaredat ASTNode:147
   */
  public final int getNumChildNoTransform() {
    return numChildren();
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:151
   */
  public ASTNode setChild(ASTNode node, int i) {
    if (children == null) {
      children = new ASTNode[(i + 1 > 4 || !(this instanceof List)) ? i + 1 : 4];
    } else if (i >= children.length) {
      ASTNode c[] = new ASTNode[i << 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = node;
    if (i >= numChildren) {
      numChildren = i+1;
    }
    if (node != null) {
      node.setParent(this);
      node.childIndex = i;
    }
    return this;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:170
   */
  public ASTNode insertChild(ASTNode node, int i) {
    if (children == null) {
      children = new ASTNode[(i + 1 > 4 || !(this instanceof List)) ? i + 1 : 4];
      children[i] = node;
    } else {
      ASTNode c[] = new ASTNode[children.length + 1];
      System.arraycopy(children, 0, c, 0, i);
      c[i] = node;
      if (i < children.length) {
        System.arraycopy(children, i, c, i+1, children.length-i);
        for(int j = i+1; j < c.length; ++j) {
          if (c[j] != null) {
            c[j].childIndex = j;
          }
        }
      }
      children = c;
    }
    numChildren++;
    if (node != null) {
      node.setParent(this);
      node.childIndex = i;
    }
    return this;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:196
   */
  public void removeChild(int i) {
    if (children != null) {
      ASTNode child = (ASTNode) children[i];
      if (child != null) {
        child.parent = null;
        child.childIndex = -1;
      }
      // Adding a check of this instance to make sure its a List, a move of children doesn't make
      // any sense for a node unless its a list. Also, there is a problem if a child of a non-List node is removed
      // and siblings are moved one step to the right, with null at the end.
      if (this instanceof List || this instanceof Opt) {
        System.arraycopy(children, i+1, children, i, children.length-i-1);
        children[children.length-1] = null;
        numChildren--;
        // fix child indices
        for(int j = i; j < numChildren; ++j) {
          if (children[j] != null) {
            child = (ASTNode) children[j];
            child.childIndex = j;
          }
        }
      } else {
        children[i] = null;
      }
    }
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:223
   */
  public ASTNode getParent() {
    return (ASTNode) parent;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:227
   */
  public void setParent(ASTNode node) {
    parent = node;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:294
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:298
   */
  public void flushTreeCache() {
    flushCache();
    if (children != null) {
      for (int i = 0; i < children.length; i++) {
        if (children[i] != null) {
          ((ASTNode) children[i]).flushTreeCache();
        }
      }
    }
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:309
   */
  public void flushCache() {
    flushAttrAndCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:313
   */
  public void flushAttrAndCollectionCache() {
    flushAttrCache();
    flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:318
   */
  public void flushAttrCache() {
  }
  /** @apilevel internal 
   * @declaredat ASTNode:321
   */
  public void flushCollectionCache() {
  }
  /** @apilevel internal 
   * @declaredat ASTNode:324
   */
  public ASTNode<T> clone() throws CloneNotSupportedException {
    ASTNode node = (ASTNode) super.clone();
    node.flushAttrAndCollectionCache();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:330
   */
  public ASTNode<T> copy() {
    try {
      ASTNode node = (ASTNode) clone();
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
   * @declaredat ASTNode:349
   */
  @Deprecated
  public ASTNode<T> fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:359
   */
  public ASTNode<T> treeCopyNoTransform() {
    ASTNode tree = (ASTNode) copy();
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
   * @declaredat ASTNode:379
   */
  public ASTNode<T> treeCopy() {
    ASTNode tree = (ASTNode) copy();
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
   * Performs a full traversal of the tree using getChild to trigger rewrites
   * @apilevel low-level
   * @declaredat ASTNode:396
   */
  public void doFullTraversal() {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).doFullTraversal();
    }
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:66
   */
    /** @apilevel internal */
  protected void collect_contributors_StateMachine_numberOfTransitionsColl(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_StateMachine_numberOfTransitionsColl(_root, _map);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_StateMachine_numberOfTransitionsColl(Counter collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:83
   */
    /** @apilevel internal */
  protected void collect_contributors_StateMachine_errors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_StateMachine_errors(_root, _map);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_StateMachine_errors(Set<String> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Graph.jrag:9
   */
    /** @apilevel internal */
  protected void collect_contributors_State_transitions(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_State_transitions(_root, _map);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_State_transitions(Set<Transition> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:48
   */
    /** @apilevel internal */
  protected void collect_contributors_State_altSuccessors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_State_altSuccessors(_root, _map);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_State_altSuccessors(Set<State> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:57
   */
    /** @apilevel internal */
  protected void collect_contributors_State_predecessors(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_State_predecessors(_root, _map);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_State_predecessors(Set<State> collection) {
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:102
   */
    /** @apilevel internal */
  protected void collect_contributors_State_altReachable(StateMachine _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_State_altReachable(_root, _map);
    }
  }
  /** @apilevel internal */
  protected void contributeTo_State_altReachable(Set<State> collection) {
  }

  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return this;
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /** @apilevel internal */
  public State Define_lookupForward(ASTNode _callerNode, ASTNode _childNode, String label) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookupForward(self, _callerNode, label)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookupForward(self, _callerNode, label);
  }

  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:17
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupForward
   */
  protected boolean canDefine_lookupForward(ASTNode _callerNode, ASTNode _childNode, String label) {
    return false;
  }
  /** @apilevel internal */
  public Set<Transition> Define_transitionsOf(ASTNode _callerNode, ASTNode _childNode, State s) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_transitionsOf(self, _callerNode, s)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_transitionsOf(self, _callerNode, s);
  }

  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute transitionsOf
   */
  protected boolean canDefine_transitionsOf(ASTNode _callerNode, ASTNode _childNode, State s) {
    return false;
  }
  /** @apilevel internal */
  public StateMachine Define_theMachine(ASTNode _callerNode, ASTNode _childNode) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_theMachine(self, _callerNode)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_theMachine(self, _callerNode);
  }

  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:73
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute theMachine
   */
  protected boolean canDefine_theMachine(ASTNode _callerNode, ASTNode _childNode) {
    return false;
  }
  /** @apilevel internal */
  public State Define_lookup(ASTNode _callerNode, ASTNode _childNode, String label) {
    ASTNode self = this;
    ASTNode parent = getParent();
    while (parent != null && !parent.canDefine_lookup(self, _callerNode, label)) {
      _callerNode = self;
      self = parent;
      parent = self.getParent();
    }
    return parent.Define_lookup(self, _callerNode, label);
  }

  /**
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/NameAnalysis.jrag:10
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookup
   */
  protected boolean canDefine_lookup(ASTNode _callerNode, ASTNode _childNode, String label) {
    return false;
  }
public ASTNode rewrittenNode() { throw new Error("rewrittenNode is undefined for ASTNode"); }

}
