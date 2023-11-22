// PackageName: org.extendj.sccid.ManyTypes
// MethodName: main

package org.extendj.sccid;
public class ManyTypes {
  public static void main() {
    A a = new A();
    a.foo();
  }
}

class A {
  void foo(){};
}
class B extends A {}
