StateMachine Description
========================

2010-04-30  
G&ouml;rel Hedin  
Lund University, Sweden

This is an implementation of the state machine example
in my tutorial:   
**[An Introductory Tutorial on JastAdd Attribute Grammars][1]**

*To be published during 2010 in the Springer Verlag LNCS
postproceedings of GTTSE 2009.*

Directories and files:

- **AST:** Generated directory with Java code
- **exampleprogs:** Example main programs
- **spec:** JastAdd and parser specification files
- **tests:** JUnit tests
- **tools:** The tools used: jastadd, junit,
and parsing tools (jflex, beaver, JastAddParser).
- **build.xml:** The ant build-file

To run the examples, you need the following programs installed:

- ant
- javac
- java

The build file supports the following targets:

- **ant gen:** generates the AST directory with Java code
- **ant build:** compiles all Java code in this directory
- **ant test:** runs the JUnit test cases
- **ant doc:** generates javadoc
- **ant clean:** removes all generated files (the AST directory and all class files).

Try it out using the following command:

	ant test

This should trigger generation and compilation of Java code,
and running all the JUnit test cases. A window should appear
showing that all the tests pass.

Or, run the example main program:

	java -cp .:tools/beaver-rt.jar exampleprogs/MainProgram

This should print out reachability information about the
state machine in Fig. 3 in the tutorial.

Or, run the "compiler" main program:

	java -cp .:tools/beaver-rt.jar exampleprogs/Compiler exampleprogs/test.sm

which runs a "compiler" for the state machine language that prettyprints the program in file test.sm, and prints out reachability and cycles information for it.

Some notes about the parser:

- For parsing we use a .jflex file to specify tokens and a .parser file to specify the parsing and AST building.
- The .parser file is run through JastAddParser which is a preprocessor to beaver. The generated jflex and beaver files are placed in the generated AST directory.
- The reason for using the JastAddParser preprocessor is that it makes it simpler to specify the tree building, and you can also supply several .parser files if you want to modularize your language.

[1]: http://link.springer.com/chapter/10.1007%2F978-3-642-18023-1_4
