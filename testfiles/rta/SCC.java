// PackageName: org.extendj.callgraph.SCC
// MethodName: main

package org.extendj.callgraph;
public class SCC {
  public static void main(String[] args) {
    Root r = new Root();
    r.a1();
  }
}

class Root {
  public void a1() { a2(); }
  public void a2() {
    a1();
    a3();
  }
  public void a3() { a4(); }
  public void a4() { a3(); }
}
