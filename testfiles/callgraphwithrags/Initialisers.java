// PackageName: org.extendj.callgraph.Initialisers
// MethodName: main

package org.extendj.callgraph;
class A extends T {
  public A() { System.out.println("A"); }
}

class T {
  static { System.out.println("Initialiser"); }
  public T() { System.out.println("T"); }
}

class Initialisers {
  public static void main(String[] args) { A a = new A(); }
}