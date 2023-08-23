import ast.*;
import java.util.HashSet;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Root r = new Root();
    System.out.println("C1: " + r.C1());
    System.out.println("C2: " + r.C2());
    System.out.println("N: " + r.N());
    System.out.println("C3: " + r.C3());
    System.out.println("C4: " + r.C4());
    System.out.println(r.count);

    Root r2 = new Root();
    System.out.println("N: " + r2.N());
    System.out.println("C3: " + r2.C3());
    System.out.println("C4: " + r2.C4());
    System.out.println("C2: " + r2.C2());
    System.out.println("C1: " + r2.C1());
    System.out.println(r2.count);
  }
}
