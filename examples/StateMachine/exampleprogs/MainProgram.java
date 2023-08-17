package org.ast;
import org.ast.*;

public class MainProgram {

  public static void main(String[] args) {
    // Construct the AST
    StateMachine m = new StateMachine();
    m.trace().setReceiver(m.trace);
    m.addDeclaration(new State("S1"));
    m.addDeclaration(new State("S2"));
    m.addDeclaration(new State("S3"));
    m.addDeclaration(new Transition("a", "S1", "S2"));
    m.addDeclaration(new Transition("b", "S2", "S1"));
    m.addDeclaration(new Transition("a", "S2", "S3"));
    Declaration d = m.getDeclaration(0);
    State S1, S2, S3;
    S1 = d.lookup("S1");
    S2 = d.lookup("S2");
    S3 = d.lookup("S3");
    if (S1.predecessors().contains(S1) | S1.predecessors().contains(S2) |
        S1.predecessors().contains(S3) | S2.predecessors().contains(S1) |
        S2.predecessors().contains(S2) | S2.predecessors().contains(S3) |
        S3.predecessors().contains(S1) | S3.predecessors().contains(S2) |
        S3.predecessors().contains(S3)) {
      System.out.println("Error: Predecessor of a state is itself");
    }

    State S2dupl = new State("S2");
    m.addDeclaration(S2dupl);
    if (S2.hasLaterNamesake() | S1.hasLaterNamesake() | S3.hasLaterNamesake() |
        S2dupl.hasLaterNamesake() | S2.multiplyDeclared() |
        S1.multiplyDeclared() | S2dupl.multiplyDeclared() |
        S3.multiplyDeclared()) {
      System.out.println("Error: Multiply declared state");
    }
    // Print reachable information for all states
    m.printReachable();

    m.trace.printJSON();
  }
}
