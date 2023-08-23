all:
	java -jar jastadd2.jar lang.ast lang.jrag --package=ast \
		--cache=all --rewrite=cnta --visitCheck=false \
		--cacheCycle=false --safeLazy
	javac Main.java
	java Main
