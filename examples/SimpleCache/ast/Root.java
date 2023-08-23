/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.4-51-gaa6ddb1 */
package ast;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.Stack;
import java.util.HashSet;
/**
 * @ast node
 * @declaredat lang.ast:1
 * @astdecl Root : ASTNode;
 * @production Root : {@link ASTNode};

 */
public class Root extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect Semantics
   * @declaredat lang.jrag:7
   */
  public
  Map<String, Integer> count = new TreeMap<>();
  /**
   * @aspect Semantics
   * @declaredat lang.jrag:10
   */
  public
  void count(String attr) {
    if (count.containsKey(attr)) {
      count.put(attr, count.get(attr) + 1);
    } else {
      count.put(attr, 1);
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public Root() {
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
    C1_reset();
    C2_reset();
    C3_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:30
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:35
   */
  public Root clone() throws CloneNotSupportedException {
    Root node = (Root) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public Root copy() {
    try {
      Root node = (Root) clone();
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
   * @declaredat ASTNode:59
   */
  @Deprecated
  public Root fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:69
   */
  public Root treeCopyNoTransform() {
    Root tree = (Root) copy();
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
   * @declaredat ASTNode:89
   */
  public Root treeCopy() {
    Root tree = (Root) copy();
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
/** @apilevel internal */
protected ASTState.Cycle C1_cycle = null;
  /** @apilevel internal */
  private void C1_reset() {
    C1_computed = false;
    C1_initialized = false;
    C1_cycle = null;
  }
  /** @apilevel internal */
  protected boolean C1_computed = false;

  /** @apilevel internal */
  protected int C1_value;
  /** @apilevel internal */
  protected boolean C1_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Semantics", declaredAt="lang.jrag:19")
  public int C1() {
    if (C1_computed) {
      return C1_value;
    }
    ASTState state = state();
    if (!C1_initialized) {
      C1_initialized = true;
      C1_value = 0;
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        C1_cycle = state.nextCycle();
        int new_C1_value = C1_compute();
        if (C1_value != new_C1_value) {
          state.setChangeInCycle();
        }
        C1_value = new_C1_value;
      } while (state.testAndClearChangeInCycle());
      C1_computed = true;

      state.leaveCircle();
    } else if (C1_cycle != state.cycle()) {
      C1_cycle = state.cycle();
      int new_C1_value = C1_compute();
      if (C1_value != new_C1_value) {
        state.setChangeInCycle();
      }
      C1_value = new_C1_value;
    } else {
    }
    return C1_value;
  }
  /** @apilevel internal */
  private int C1_compute() {
      count("C1");
      int r = Math.min(3, C2() + 1);
      // System.out.println("computed C1 to " + r);
      return r;
    }
/** @apilevel internal */
protected ASTState.Cycle C2_cycle = null;
  /** @apilevel internal */
  private void C2_reset() {
    C2_computed = false;
    C2_initialized = false;
    C2_cycle = null;
  }
  /** @apilevel internal */
  protected boolean C2_computed = false;

  /** @apilevel internal */
  protected int C2_value;
  /** @apilevel internal */
  protected boolean C2_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Semantics", declaredAt="lang.jrag:25")
  public int C2() {
    if (C2_computed) {
      return C2_value;
    }
    ASTState state = state();
    if (!C2_initialized) {
      C2_initialized = true;
      C2_value = 0;
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        C2_cycle = state.nextCycle();
        int new_C2_value = C2_compute();
        if (C2_value != new_C2_value) {
          state.setChangeInCycle();
        }
        C2_value = new_C2_value;
      } while (state.testAndClearChangeInCycle());
      C2_computed = true;

      state.leaveCircle();
    } else if (C2_cycle != state.cycle()) {
      C2_cycle = state.cycle();
      int new_C2_value = C2_compute();
      if (C2_value != new_C2_value) {
        state.setChangeInCycle();
      }
      C2_value = new_C2_value;
    } else {
    }
    return C2_value;
  }
  /** @apilevel internal */
  private int C2_compute() {
      count("C2");
      int r = N() + C1();
      // System.out.println("computed C2 to " + r);
      return r;
    }
/** @apilevel internal */
protected boolean N_visited = false;
  /**
   * @attribute syn
   * @aspect Semantics
   * @declaredat lang.jrag:32
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Semantics", declaredAt="lang.jrag:32")
  public int N() {
    if (N_visited) {
      throw new RuntimeException("Circular definition of attribute Root.N().");
    }
    N_visited = true;
    try {
        count("N");
        int r = C3();
        // System.out.println("computed N to " + r);
        return r;
      }
    finally {
      N_visited = false;
    }
  }
/** @apilevel internal */
protected ASTState.Cycle C3_cycle = null;
  /** @apilevel internal */
  private void C3_reset() {
    C3_computed = false;
    C3_initialized = false;
    C3_cycle = null;
  }
  /** @apilevel internal */
  protected boolean C3_computed = false;

  /** @apilevel internal */
  protected int C3_value;
  /** @apilevel internal */
  protected boolean C3_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Semantics", declaredAt="lang.jrag:39")
  public int C3() {
    if (C3_computed) {
      return C3_value;
    }
    ASTState state = state();
    if (!C3_initialized) {
      C3_initialized = true;
      C3_value = 0;
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        C3_cycle = state.nextCycle();
        int new_C3_value = C3_compute();
        if (C3_value != new_C3_value) {
          state.setChangeInCycle();
        }
        C3_value = new_C3_value;
      } while (state.testAndClearChangeInCycle());
      C3_computed = true;

      state.leaveCircle();
    } else if (C3_cycle != state.cycle()) {
      C3_cycle = state.cycle();
      int new_C3_value = C3_compute();
      if (C3_value != new_C3_value) {
        state.setChangeInCycle();
      }
      C3_value = new_C3_value;
    } else {
    }
    return C3_value;
  }
  /** @apilevel internal */
  private int C3_compute() {
      count("C3");
      int r = Math.min(3, C4() + 1);
      // System.out.println("computed C3 to " + r);
      return r;
    }
/** @apilevel internal */
protected boolean C4_visited = false;
  /**
   * @attribute syn
   * @aspect Semantics
   * @declaredat lang.jrag:46
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Semantics", declaredAt="lang.jrag:46")
  public int C4() {
    if (C4_visited) {
      throw new RuntimeException("Circular definition of attribute Root.C4().");
    }
    C4_visited = true;
    try {
        count("C4");
        int r = C3() + 1;
        // System.out.println("computed C4 to " + r);
        return r;
      }
    finally {
      C4_visited = false;
    }
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
