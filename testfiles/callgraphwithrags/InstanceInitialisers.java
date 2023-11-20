// PackageName: org.extendj.callgraph.InstanceInitialisers
// MethodName: main
package org.extendj.callgraph;
class A extends T {
  public A() { System.out.println("A"); }
}

class T {
  {
    System.out.println("Initialiser");
  }
  public T() { System.out.println("T"); }
}

class InstanceInitialisers {
  public static void main(String[] args) {
    A a = new A();
    A ab = new A();
  }
}