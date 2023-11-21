// PackageName: org.extendj.callgraph.ThreeComponents
// MethodName: foo

package org.extendj.callgraph;

public class ThreeComponents {
  void foo() { bar(); }

  void bar() {
    foo();
    baz();
  }

  void baz() { zzz(); }
  void zzz() {}
}