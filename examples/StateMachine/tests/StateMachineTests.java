package tests;

import junit.framework.TestCase;
import org.ast.*;

public class StateMachineTests extends TestCase {
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
  public void testNameAnalysis() {
    for (Declaration d : m.getDeclarationList()) {
      if (d instanceof Transition) {
        Transition t = (Transition)d;
        assertEquals(t.getSourceLabel(), t.source().getLabel());
        assertEquals(t.getTargetLabel(), t.target().getLabel());
      }
    }
  }
  public void testSuccessors() {
    assertFalse(S1.successors().contains(S1));
    assertTrue(S1.successors().contains(S2));
    assertFalse(S1.successors().contains(S3));
    assertTrue(S2.successors().contains(S1));
    assertFalse(S2.successors().contains(S2));
    assertTrue(S2.successors().contains(S3));
    assertFalse(S3.successors().contains(S1));
    assertFalse(S3.successors().contains(S2));
    assertFalse(S3.successors().contains(S3));
  }

  public void testReachable() {
    assertTrue(S1.reachable().contains(S1));
    assertTrue(S1.reachable().contains(S2));
    assertTrue(S1.reachable().contains(S3));
    assertTrue(S2.reachable().contains(S1));
    assertTrue(S2.reachable().contains(S2));
    assertTrue(S2.reachable().contains(S3));
    assertFalse(S3.reachable().contains(S1));
    assertFalse(S3.reachable().contains(S2));
    assertFalse(S3.reachable().contains(S3));
  }
}
