# CAT - CallGraph Analysis Tool

<p align="center">
  <img width="300"  src="https://raw.githubusercontent.com/idrissrio/cat/main/resources/cat.png">
</p>

CAT (CallGraph Analysis Tool) is a Java tool that constructs call graphs using Class Hierarchy Analysis (CHA) and a variant of Rapid Type Analysis (RTA). The implementation leverages JastAdd and reference attribute grammars, as well as APIs provided by the ExtendJ Java compiler.

This repository relates to the artifact submitted at SLE 2024: [Efficient Demand Evaluation of Fixed-Point Attributes Using Static Analysis](https://github.com/IdrissRio/RS-SLE2024-Artifact). The tool was first introduced in the following paper:

> **Efficient Demand Evaluation of Fixed-Point Attributes Using Static Analysis**
> Idriss Riouak, Niklas Fors, Jesper Öqvist, Görel Hedin, Christoph Reichenbach
> Proceedings of the 17th ACM SIGPLAN International Conference on Software Language Engineering (SLE 2024).
> DOI: [10.1145/3687997.3695644](https://doi.org/10.1145/3687997.3695644)

The artifact associated with this work is available on Zenodo: [13365896](https://zenodo.org/records/13365896).

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

## Introduction

CAT (CallGraph Analysis Tool) computes call graphs for Java programs, where nodes represent methods and edges denote method calls. It employs CHA and RTA techniques to ensure accurate graph construction and includes a web-based visualization tool for interactive exploration of the generated graphs.

## Features

- **Call Graph Generation**: Generate precise call graphs for Java programs using CHA and RTA.
- **Web-based Visualization**: Interactive browser-based visualization of call graphs.
- **Command-line Interface**: Simple CLI for generating call graphs.
- **JSON Output**: Export call graphs in JSON format for easy integration with other tools.

## Installation

1. Clone the repository:

   ```
   git clone https://github.com/IdrissRio/cat
   ```

2. Navigate to the cloned repository:

   ```
    cd cat
   ```

3. Initialize submodules:

   ```
   git submodule update --init --recursive
   ```


4. Build the project:

   ```
    ./gradlew build -x test -PenableOptimization
   ```

### Java Version
CAT has been tested with **Java 8.0.372.fx-zulu** installed via [SDKMAN](https://sdkman.io/). Ensure you have a compatible Java environment before building or running the tool.

## Usage

CAT provides a command-line interface for generating and visualizing call graphs. The tool requires the path to the Java program, the output file, and the entry point (package name, class name, and method name) to generate a call graph.

### Generate a Call Graph

```
 java -jar cat.jar <path-to-java-program> -o <output-file> -entryPoint <package-name>.<class-name> <method-name>
```


### Visualize a Call Graph

```
java -jar cat.jar <path-to-java-program> -visualise -entryPoint <package-name>.<class-name> <method-name>
```

Access the visualization tool  at `http://localhost:8080`.

## Simple Java Example

Consider the following Java program saved as `~/Example.java`:

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
  void p() {}
}
class E extends C {
  @Override
  void m() {}
}
class D extends B {}
class C extends A {
  static { final A G = new G(); }
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
```

Generate a call graph:
```
 java -jar cat.jar ~/Example.java -o ~/Example.json -entryPoint Example main
```

Visualize the call graph:
```
> java -jar cat.jar ~/Example.java -visualise -entryPoint Example main
```

Open a browser and visit `http://localhost:8080` to explore the generated graph:

<p align="center">
  <img  src="https://raw.githubusercontent.com/idrissrio/cat/main/resources/CallGraphVisualisation.png">
</p>

## Running CAT on ExtendJ

We provide a script, `./cat-on-extendj`, that simplifies running CAT on the ExtendJ Java compiler. This script automates the entire process, from setting up the required environment to running the analysis.

To use the script, simply execute the following command:

```
./cat-on-extendj
```

The script it will start a local server, and you can visualize the generated call graph by navigating to `http://localhost:8080` in your web browser. No additional configuration is required—just run the script and wait for the results!

## Contributing

Contributions are welcome! Follow these steps to contribute:

1. Fork this repository.
2. Create a feature branch: `git checkout -b feature/your-feature`.
3. Commit your changes: `git commit -m "Add feature XYZ"`.
4. Push to your forked repository.
5. Open a pull request detailing your changes.

## License

CAT is released under the BSD 3-Clause License.
