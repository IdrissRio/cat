// PackageName: org.extendj.callgraph.OneComponents
// MethodName: foo

package org.extendj.callgraph;

public class OneComponents {
  void foo() { bar(); }

  void bar() {
    foo();
    baz();
  }

  void baz() { zzz(); }
  void zzz() { bar(); }
}