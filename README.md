# CAT - CallGraph Analysis Tool

<p align="center">
  <img width="300"  src="https://raw.githubusercontent.com/idrissrio/cat/main/resources/cat.png">
</p>

CAT (CallGraph Analysis Tool) is a Java tool that constructs call graphs based 
on Class Hierarchy Analaysis and a variant of Rapid Type Analysis. It uses JastAdd and reference attribute 
grammars for its implementation. CAT also uses some API provided by the ExtendJ Java 
compiler to compute the call graph.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

## Introduction

CAT (CallGraph Analysis Tool) is designed to compute the call graphs of Java programs.
By using CHA and RTA techniques, CAT generates call graphs where nodes are methods and edges are 
method calls. CAT also provides a web-based visualisation tool to visualise the generated call graphs in 
the browser.

## Features

- **Call Graph Generation**: CAT generates call graphs for Java programs using Class Hierarchy Analysis techniques.
- **Web-based Visualisation**: CAT provides a web-based visualisation tool to visualise the generated call graphs.
- **Command-line Interface**: CAT provides a command-line interface to generate call graphs for Java programs.
- **JSON Output**: CAT outputs the generated call graphs in JSON format.

## Installation

1. Clone the repository.
```bash
git clone https://github.com/IdrissRio/cat
```

2. Naviate to the cloned repository.
```bash
cd cat
```

3. Download submodules.
```bash
git submodule update --init --recursive
```

4. Build the project.
```bash
./gradlew build
```

## Usage

CAT is designed to be user-friendly and easy to use. Here's how you can use it:

1. Generate a call graph for a Java program.
```bash
java -jar cat.jar <path-to-java-program> -o <output-file> -entryPoint <package-name>.<class-name> <method-name>
```

2. Visualise the generated call graph.
```bash
java -jar cat.jar <path-to-java-program> -visualise -entryPoint <package-name>.<class-name> <method-name>
```
Navigate to `http://localhost:8080` to view the visualisation.

## Examples

Let us consider the following Java program saved in a filed called ~/Example.java:

```java
// Example from http://web.cs.ucla.edu/~palsberg/tba/papers/dean-grove-chambers-ecoop95.pdf

public class Example {
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
  void p() {
  }
}

class E extends C {
  @Override
  void m() {
  }
}

class D extends B {
}

class C extends A {
  static { final A G = new G(); }
  @Override
  void m() {
  }
}

class B extends A {
  @Override
  void m() {
  }
}

class A {
  void m() {
  }

  void p() {
  }
}
```

To generate a call graph for this program, we can run the following command:

```bash
java -jar cat.jar ~/Example.java -o ~/Example.json -entryPoint Example main
```

This will generate a call graph in JSON format and save it in a file called ~/Example.json.

To visualise the generated call graph, we can run the following command:

```bash
java -jar cat.jar ~/Example.java  -entryPoint Example main --visualise
```

By visiting `http://localhost:8080` in a web browser, we can view the visualisation of the generated call graph.

<p align="center">
  <img  src="https://raw.githubusercontent.com/idrissrio/cat/main/resources/CallGraphVisualisation.png">
</p>

The user can choose to view the call graph in different formats, such as a tree or a graph
by clicking on the settings icon in the top left corner of the visualisation.

## Contributing

We welcome contributions to CAT! If you'd like to contribute, please follow these steps:

1. Fork the CAT repository.
2. Create a new branch for your feature/fix: git checkout -b feature/your-feature.
3. Commit your changes and push to your forked repository.
4. Create a pull request detailing your changes.

## License
CAT is released under the BSD 3-Clause License.



