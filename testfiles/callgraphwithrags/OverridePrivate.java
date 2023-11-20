// PackageName: org.extendj.callgraph.OverridePrivate
// MethodName: main

package org.extendj.callgraph;
public class OverridePrivate {
  public static void main(String[] args) {
    Root r = new Root();
    r.a2();
  }
}

class Root {
  private void a1() { a2(); }
  public void a2() { a1(); }
}

class B extends Root {
  private void a1() { return; }
}
