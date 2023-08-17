package org.ast;

import java.util.*;
/**
 * @ast class
 * @declaredat ASTNode:230
 */
public class ASTNodeAnnotation extends java.lang.Object {
  /**
   * @declaredat ASTNode:231
   */
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Child {
    String name();
  }
  /**
   * @declaredat ASTNode:238
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface ListChild {
    String name();
  }
  /**
   * @declaredat ASTNode:245
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface OptChild {
    String name();
  }
  /**
   * @declaredat ASTNode:252
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Token {
    String name();
  }
  /**
   * @declaredat ASTNode:259
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Attribute {
    Kind kind();
    boolean isCircular() default false;
    boolean isNTA() default false;
  }
  /**
   * @declaredat ASTNode:267
   */
  
  public enum Kind { SYN, INH, COLL }
  /**
   * @declaredat ASTNode:269
   */
  

  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Source {
    String aspect() default "";
    String declaredAt() default "";
  }
  /**
   * @declaredat ASTNode:277
   */
  

  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.CONSTRUCTOR)
  @java.lang.annotation.Documented
  public @interface Constructor{
    String[] name(); 
    String[] type(); 
    String[] kind(); 
  }

}
