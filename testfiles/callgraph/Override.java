// PackageName: org.extendj.callgraph.Override
// MethodName: main

package org.extendj.callgraph;
public class Override {
  public static void main(String[] args) {
    Root r = new Root();
    r.a1();
  }
}

class Root {
  public void a1() { a2(); }
  public void a2() { return; }
}

class B extends Root {
  public void a1() { return; }
}