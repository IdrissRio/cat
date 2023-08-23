all:
	java -jar jastadd2.jar lang.ast lang.jrag --package=ast \
		--rewrite=cnta --visitCheck=true \
		--cacheCycle=false  --cache=none
	javac Main.java
	java Main
