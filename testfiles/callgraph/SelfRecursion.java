package org.extendj.callgraph;
class SelfRecursion {
	void foo(){
		foo();
	}
}