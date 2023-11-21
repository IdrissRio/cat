// PackageName: org.extendj.callgraph.Cyclic
// MethodName: foo

package org.extendj.callgraph;

public class Cyclic {
  void foo() { bar(); }

  void bar() { baz(); }

  void baz() { zzz(); }
  void zzz() { foo(); }
}