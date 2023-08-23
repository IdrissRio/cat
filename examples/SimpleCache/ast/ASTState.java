package ast;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.Stack;
import java.util.HashSet;
/** @apilevel internal 
 * @ast class
 * @declaredat ASTState:34
 */
public class ASTState extends java.lang.Object {
  /**
   * This class stores an attribute value tagged with an iteration ID for
   * a circular evaluation.
   * 
   * @apilevel internal
   * @declaredat ASTState:41
   */
  
  /**
   * This class stores an attribute value tagged with an iteration ID for
   * a circular evaluation.
   *
   * @apilevel internal
   */
  protected static class CircularValue {
    Object value;
    Cycle cycle;
  }
  /**
   * Instances of this class are used to uniquely identify circular evaluation iterations.
   * These iteration ID objects are created for each new fixed-point iteration in
   * a circular evaluation.
   * 
   * @apilevel internal
   * @declaredat ASTState:54
   */
  


  /**
   * Instances of this class are used to uniquely identify circular evaluation iterations.
   * These iteration ID objects are created for each new fixed-point iteration in
   * a circular evaluation.
   *
   * @apilevel internal
   */
  protected static class Cycle {
  }
  /**
   * The iteration ID used outside of circular evaluation.
   * 
   * <p>This is the iteration ID when no circular evaluation is ongoing.
   * @declaredat ASTState:62
   */
  

  /**
   * The iteration ID used outside of circular evaluation.
   *
   * <p>This is the iteration ID when no circular evaluation is ongoing.
   */
  public static final Cycle NON_CYCLE = new Cycle();
  /**
   * Tracks the state of the current circular evaluation. This class defines a
   * stack structure where the next element on the stack is pointed to by the
   * {@code next} field.
   * 
   * @apilevel internal
   * @declaredat ASTState:71
   */
  

  /**
   * Tracks the state of the current circular evaluation. This class defines a
   * stack structure where the next element on the stack is pointed to by the
   * {@code next} field.
   *
   * @apilevel internal
   */
  protected static class CircleState {
    final CircleState next;
    boolean change = false;

    /** Evaluation depth of lazy attributes. */
    int lazyAttribute = 0;

    /** Cycle ID of the latest cycle in this circular evaluation. */
    Cycle cycle = NON_CYCLE;


    protected CircleState(CircleState next) {
      this.next = next;
    }
  }
  /** Sentinel circle state representing non-circular evaluation. 
   * @declaredat ASTState:89
   */
  


  /** Sentinel circle state representing non-circular evaluation. */
  private static final CircleState CIRCLE_BOTTOM = new CircleState(null);
  /**
   * Current circular state.
   * @apilevel internal
   * @declaredat ASTState:95
   */
  

  /**
   * Current circular state.
   * @apilevel internal
   */
  private CircleState circle = CIRCLE_BOTTOM;
  /** @apilevel internal 
   * @declaredat ASTState:98
   */
  

  /** @apilevel internal */
  protected boolean inCircle() {
    return circle != CIRCLE_BOTTOM;
  }
  /** @apilevel internal 
   * @declaredat ASTState:103
   */
  

  /** @apilevel internal */
  protected boolean calledByLazyAttribute() {
    return circle.lazyAttribute > 0;
  }
  /** @apilevel internal 
   * @declaredat ASTState:108
   */
  

  /** @apilevel internal */
  protected void enterLazyAttribute() {
    circle.lazyAttribute += 1;
  }
  /** @apilevel internal 
   * @declaredat ASTState:113
   */
  

  /** @apilevel internal */
  protected void leaveLazyAttribute() {
    circle.lazyAttribute -= 1;
  }
  /** @apilevel internal 
   * @declaredat ASTState:118
   */
  

  /** @apilevel internal */
  protected void enterCircle() {
    CircleState next = new CircleState(circle);
    circle = next;
  }
  /**
   * Maps circular attribute to last evaluated cycle index.
   * @apilevel internal
   * @declaredat ASTState:128
   */
  


  /**
   * Maps circular attribute to last evaluated cycle index.
   * @apilevel internal
   */
  private java.util.Map<Object, Integer> visited = new java.util.IdentityHashMap<Object, Integer>();
  /**
   * Check if attribute was already visited during the current cycle.
   * @apilevel internal
   * @return {@code true} if the attribute was already visited.
   * @declaredat ASTState:135
   */
  

  /**
   * Check if attribute was already visited during the current cycle.
   * @apilevel internal
   * @return {@code true} if the attribute was already visited.
   */
  protected boolean checkAndSetVisited(Object attribute, int cycle) {
    boolean result = visited.containsKey(attribute) && visited.get(attribute) == cycle;
    visited.put(attribute, cycle);
    return result;
  }
  /**
   * Reset visited cycle tracking for this thread.
   * @apilevel internal
   * @declaredat ASTState:145
   */
  

  /**
   * Reset visited cycle tracking for this thread.
   * @apilevel internal
   */
  protected void clearVisited() {
    visited.clear();
  }
  /**
   * Reset visit tracker for a single attribute.
   * @apilevel internal
   * @declaredat ASTState:154
   */
  

  // TODO(joqvist): may not be necessary.
  /**
   * Reset visit tracker for a single attribute.
   * @apilevel internal
   */
  protected void resetVisited(Object attribute) {
    visited.remove(attribute);
  }
  /** @apilevel internal 
   * @declaredat ASTState:159
   */
  

  /** @apilevel internal */
  protected void leaveCircle() {
    circle = circle.next;
  }
  /** @apilevel internal 
   * @declaredat ASTState:164
   */
  

  /** @apilevel internal */
  protected Cycle nextCycle() {
    Cycle cycle = new Cycle();
    circle.cycle = cycle;
    return cycle;
  }
  /** @apilevel internal 
   * @declaredat ASTState:171
   */
  

  /** @apilevel internal */
  protected Cycle cycle() {
    return circle.cycle;
  }
  /** @apilevel internal 
   * @declaredat ASTState:176
   */
  

  /** @apilevel internal */
  protected CircleState currentCircle() {
    return circle;
  }
  /** @apilevel internal 
   * @declaredat ASTState:182
   */
  


  /** @apilevel internal */
  protected void setChangeInCycle() {
    circle.change = true;
  }
  /** @apilevel internal 
   * @declaredat ASTState:187
   */
  

  /** @apilevel internal */
  protected boolean testAndClearChangeInCycle() {
    boolean change = circle.change;
    circle.change = false;
    return change;
  }
  /** @apilevel internal 
   * @declaredat ASTState:194
   */
  

  /** @apilevel internal */
  protected boolean changeInCycle() {
    return circle.change;
  }
  /** @apilevel internal 
   * @declaredat ASTState:199
   */
  

  /** @apilevel internal */
  protected void setCircle(CircleState circle) {
    this.circle = circle;
  }
  /**
   * @declaredat ASTState:205
   */
  



  protected ASTState() {
  }
  /** @apilevel internal 
   * @declaredat ASTNode:281
   */
  public void reset() {
    // Reset circular evaluation state.
    circle = CIRCLE_BOTTOM;
  }

}
