public class CHAPaper2 {
  public void main() {
    H h = new H();
    h.m();

    A a = new C();
    a.m();
  }
}

class H extends F {}

class G extends F {}

class F extends C {
  @Override
  void p() {}
}

class E extends C {
  @Override
  void m() {}
}

class D extends B {}

class C extends A {
  @Override
  void m() {}
}

class B extends A {
  @Override
  void m() {}
}

class A {
  void m() {}

  void p() {}
}