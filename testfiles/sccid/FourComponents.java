// PackageName: org.extendj.callgraph.FourComponents
// MethodName: foo

package org.extendj.callgraph;

public class FourComponents {
  void foo() { bar(); }

  void bar() { baz(); }

  void baz() { zzz(); }
  void zzz() {}
}