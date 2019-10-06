# JMH benchmarks
Java Microbenchmark Harness benchmarks.
## Running benchmarks:
### 1. With a specified number of forks
```
mvn clean install
java -jar target/benchmarks.jar -f 5
```
### 2. From a specified class or package (regexp)
```
mvn clean install
java -jar target/benchmarks.jar ".*HashMapGet.*"
```
