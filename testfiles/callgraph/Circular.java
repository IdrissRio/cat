// PackageName: org.extendj.callgraph.Circular
// MethodName: foo

package org.extendj.callgraph;

public class Circular {
  void foo() { bar(); }

  void bar() { baz(); }

  void baz() { zzz(); }
  void zzz() { foo(); }
}