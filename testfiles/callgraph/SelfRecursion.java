// PackageName: org.extendj.callgraph.SelfRecursion
// MethodName: foo

package org.extendj.callgraph;
class SelfRecursion {
	void foo(){
		foo();
	}
}