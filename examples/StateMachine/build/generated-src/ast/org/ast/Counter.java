package org.ast;

import java.util.*;
/**
 * @ast class
 * @aspect Exercises
 * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:75
 */
public class Counter extends java.lang.Object {
  /**
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:76
   */
  
    private int value;
  /**
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:77
   */
  
    public Counter() { value = 0; }
  /**
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:78
   */
  
    public void add(int value) { this.value += value; }
  /**
   * @aspect Exercises
   * @declaredat /Users/rio/phd/git/lund/research/artifact/cat/examples/StateMachine/spec/Exercises.jrag:79
   */
  
    public int value() { return value; }

}
