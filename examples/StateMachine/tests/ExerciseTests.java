package tests;

import junit.framework.TestCase;
import org.ast.*;

public class ExerciseTests extends TestCase {
  StateMachine m;
  State S1, S2, S3;
  public void setUp() {
    m = new StateMachine();
    m.addDeclaration(new State("S1"));
    m.addDeclaration(new State("S2"));
    m.addDeclaration(new State("S3"));
    m.addDeclaration(new Transition("a", "S1", "S2"));
    m.addDeclaration(new Transition("b", "S2", "S1"));
    m.addDeclaration(new Transition("a", "S2", "S3"));
    Declaration d = m.getDeclaration(0);
    S1 = d.lookup("S1");
    S2 = d.lookup("S2");
    S3 = d.lookup("S3");
  }

  public void testAlreadyDeclared() {
    State S2dupl = new State("S2");
    m.addDeclaration(S2dupl);
    assertFalse(S1.alreadyDeclared());
    assertFalse(S2.alreadyDeclared());
    assertFalse(S3.alreadyDeclared());
    assertTrue(S2dupl.alreadyDeclared());
  }

  public void testMultiplyDeclared() {
    State S2dupl = new State("S2");
    m.addDeclaration(S2dupl);
    assertFalse(S1.hasLaterNamesake());
    assertTrue(S2.hasLaterNamesake());
    assertFalse(S3.hasLaterNamesake());
    assertFalse(S2dupl.hasLaterNamesake());
    assertFalse(S1.multiplyDeclared());
    assertTrue(S2.multiplyDeclared());
    assertFalse(S3.multiplyDeclared());
    assertTrue(S2dupl.multiplyDeclared());
  }

  public void testAltTransitions() {
    assertEquals(S1.transitions(), S1.altTransitions());
    assertEquals(S2.transitions(), S2.altTransitions());
    assertEquals(S3.transitions(), S3.altTransitions());
  }

  public void testAltSuccessors() {
    assertEquals(S1.successors(), S1.altSuccessors());
    assertEquals(S2.successors(), S2.altSuccessors());
    assertEquals(S3.successors(), S3.altSuccessors());
  }

  public void testPredecessors() {
    assertFalse(S1.predecessors().contains(S1));
    assertTrue(S1.predecessors().contains(S2));
    assertFalse(S1.predecessors().contains(S3));
    assertTrue(S2.predecessors().contains(S1));
    assertFalse(S2.predecessors().contains(S2));
    assertFalse(S2.predecessors().contains(S3));
    assertFalse(S3.predecessors().contains(S1));
    assertTrue(S3.predecessors().contains(S2));
    assertFalse(S3.predecessors().contains(S3));
  }

  public void testNumberOfTransitions() {
    assertEquals(3, m.numberOfTransitions());
  }

  public void testErrorMessages() {
    m.addDeclaration(new State("S2"));
    m.addDeclaration(new Transition("a", "S4", "S5"));
    assertTrue(m.errors().contains("S2 is already declared"));
    assertTrue(m.errors().contains("Missing declaration of S4"));
    assertTrue(m.errors().contains("Missing declaration of S5"));
    assertFalse(m.errors().contains("S1 is already declared"));
    assertFalse(m.errors().contains("Missing declaration of S3"));
  }

  public void testAltReachable() {
    assertEquals(S1.reachable(), S1.altReachable());
    assertEquals(S2.reachable(), S2.altReachable());
    assertEquals(S3.reachable(), S3.altReachable());
  }
}
