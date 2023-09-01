package org.extendj.callgraph;
class Test{
	static void foo(){
	}
}

class Test2{
	void foo(){
		Test.foo();
	}
}