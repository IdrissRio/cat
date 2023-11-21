// PackageName: org.extendj.callgraph.TwoComponents
// MethodName: foo

package org.extendj.callgraph;

public class TwoComponents {
  void foo() { bar(); }

  void bar() {
    foo();
    baz();
  }

  void baz() { zzz(); }
  void zzz() { baz(); }
}