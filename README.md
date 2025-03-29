# naive-tokenizer
Naive text file tokenizer.

This tokenizer is naive in the sense that it assumes each `chunk` is delimited. In other words, it doesn't handle the edge case when a single long word can span across multipe chunks.

> Note: This edge case is technically highly unlikely, but still "possible".

# Prerequisites
This project requires the following:
* SDKMAN
* Java Runtime
* Maven

[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.graalvm.org/)
[![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

## Installation
Follow the instructions below to install SDKMAN:
```
https://sdkman.io/install
```

Run the following command to install the exact Java Graal version:
```bash
sdk env install
```

Run the following command to install the exact Maven Version:
```bash
sdk install maven 3.9.9
```

# Build
Run the following command to clean install dependencies:
```shell
mvn clean install
```

Run the following command to run unit tests via terminal:
```shell
mvn test
```

Run the following command to tokenize the `*.txt` file(s) via Maven + terminal:
```shell
mvn exec:java -Dexec.mainClass="com.search.App"
```

Run the following command to tokenize the `*.txt` file(s) via terminal:
```shell
java -cp target/naive_tokenizer-1.0-SNAPSHOT.jar com.search.App
```

# License
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)