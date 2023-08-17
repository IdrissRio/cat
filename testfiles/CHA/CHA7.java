interface Z{}

interface A<T1, T2> extends Z {
}

class B implements A<String, Integer> {
}