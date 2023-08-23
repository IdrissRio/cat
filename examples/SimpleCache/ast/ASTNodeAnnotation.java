package ast;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.Stack;
import java.util.HashSet;
/**
 * @ast class
 * @declaredat ASTNode:224
 */
public class ASTNodeAnnotation extends java.lang.Object {
  /**
   * @declaredat ASTNode:225
   */
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Child {
    String name();
  }
  /**
   * @declaredat ASTNode:232
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface ListChild {
    String name();
  }
  /**
   * @declaredat ASTNode:239
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface OptChild {
    String name();
  }
  /**
   * @declaredat ASTNode:246
   */
  
  
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Token {
    String name();
  }
  /**
   * @declaredat ASTNode:253
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
   * @declaredat ASTNode:261
   */
  
  public enum Kind { SYN, INH, COLL }
  /**
   * @declaredat ASTNode:263
   */
  

  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
  @java.lang.annotation.Documented
  public @interface Source {
    String aspect() default "";
    String declaredAt() default "";
  }
  /**
   * @declaredat ASTNode:271
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
