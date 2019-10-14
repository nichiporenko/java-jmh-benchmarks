# JMH benchmarks
Java Microbenchmark Harness benchmarks.

Not important values for lists, sets and keys for maps are generated randomly.
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

## Benchmarks' results (in 5 forks):
### Maps
#### 1. Executing get()
```
Benchmark                        (ENTRIES_BEFORE)   Mode  Cnt   Score   Error  Units

ConcurrentHashMapGet                            0   avgt   25   3.463   0.065  ns/op
ConcurrentHashMapGet                            1   avgt   25   4.812   0.161  ns/op
ConcurrentHashMapGet                         1000   avgt   25   5.703   0.649  ns/op
ConcurrentHashMapGet                       100000   avgt   25   6.616   2.873  ns/op
ConcurrentHashMapGet                      1000000   avgt   25   5.034   0.412  ns/op

HashMapGet                                      0   avgt   25   3.602   0.052  ns/op
HashMapGet                                      1   avgt   25   4.605   0.138  ns/op
HashMapGet                                   1000   avgt   25   5.030   0.365  ns/op
HashMapGet                                 100000   avgt   25   5.428   0.438  ns/op
HashMapGet                                1000000   avgt   25   4.936   0.431  ns/op

HashtableGet                                    0   avgt   25  18.844   0.234  ns/op
HashtableGet                                    1   avgt   25  18.594   0.247  ns/op
HashtableGet                                 1000   avgt   25  18.759   0.278  ns/op
HashtableGet                               100000   avgt   25  18.650   0.233  ns/op
HashtableGet                              1000000   avgt   25  18.702   0.233  ns/op

LinkedHashMapGet                                0   avgt   25   3.344   0.054  ns/op
LinkedHashMapGet                                1   avgt   25   4.587   0.073  ns/op
LinkedHashMapGet                             1000   avgt   25   5.139   0.513  ns/op
LinkedHashMapGet                           100000   avgt   25   4.797   0.275  ns/op
LinkedHashMapGet                          1000000   avgt   25   5.457   0.674  ns/op

TreeMapGet                                      0   avgt   25   2.971   0.184  ns/op
TreeMapGet                                      1   avgt   25   5.030   0.084  ns/op
TreeMapGet                                   1000   avgt   25  36.065   2.855  ns/op
TreeMapGet                                 100000   avgt   25  53.743   1.653  ns/op
TreeMapGet                                1000000   avgt   25  72.607   4.739  ns/op
```
#### 2. Executing containsKey()
```
Benchmark                        (ENTRIES_BEFORE)   Mode  Cnt   Score   Error  Units

ConcurrentHashMapContainsKey                    0   avgt   25   3.085   0.073  ns/op
ConcurrentHashMapContainsKey                    1   avgt   25   4.431   0.083  ns/op
ConcurrentHashMapContainsKey                 1000   avgt   25   5.036   0.631  ns/op
ConcurrentHashMapContainsKey               100000   avgt   25   5.313   0.595  ns/op
ConcurrentHashMapContainsKey              1000000   avgt   25   5.121   0.580  ns/op

HashMapContainsKey                              0   avgt   25   3.111   0.056  ns/op
HashMapContainsKey                              1   avgt   25   4.197   0.055  ns/op
HashMapContainsKey                           1000   avgt   25   4.725   0.490  ns/op
HashMapContainsKey                         100000   avgt   25   5.316   1.047  ns/op
HashMapContainsKey                        1000000   avgt   25   4.548   0.302  ns/op

HashtableContainsKey                            0   avgt   25  18.840   0.257  ns/op
HashtableContainsKey                            1   avgt   25  18.785   0.285  ns/op
HashtableContainsKey                         1000   avgt   25  19.343   0.304  ns/op
HashtableContainsKey                       100000   avgt   25  19.064   0.105  ns/op
HashtableContainsKey                      1000000   avgt   25  19.018   0.501  ns/op

LinkedHashMapContainsKey                        0   avgt   25   3.062   0.043  ns/op
LinkedHashMapContainsKey                        1   avgt   25   4.233   0.071  ns/op
LinkedHashMapContainsKey                     1000   avgt   25   4.435   0.243  ns/op
LinkedHashMapContainsKey                   100000   avgt   25   4.390   0.248  ns/op
LinkedHashMapContainsKey                  1000000   avgt   25   4.698   0.314  ns/op

TreeMapContainsKey                              0   avgt   25   2.570   0.088  ns/op
TreeMapContainsKey                              1   avgt   25   5.170   0.331  ns/op
TreeMapContainsKey                           1000   avgt   25  35.379   3.384  ns/op
TreeMapContainsKey                         100000   avgt   25  53.702   4.840  ns/op
TreeMapContainsKey                        1000000   avgt   25  70.729   4.281  ns/op
```
#### 3. Executing put()
```
Benchmark                        (ENTRIES_BEFORE)   (ENTRIES_PUT)   Mode  Cnt   Score   Error  Units

ConcurrentHashMapPut                            0          100000   avgt   25   8.813   0.341  ms/op
ConcurrentHashMapPut                            1          100000   avgt   25   8.677   0.316  ms/op
ConcurrentHashMapPut                         1000          100000   avgt   25   8.844   0.413  ms/op
ConcurrentHashMapPut                       100000          100000   avgt   25  12.851   0.555  ms/op
ConcurrentHashMapPut                      1000000          100000   avgt   25  19.067   1.913  ms/op

HashMapPut                                      0          100000   avgt   25   7.995   0.349  ms/op
HashMapPut                                      1          100000   avgt   25   8.043   0.375  ms/op
HashMapPut                                   1000          100000   avgt   25   7.896   0.210  ms/op
HashMapPut                                 100000          100000   avgt   25  11.627   0.466  ms/op
HashMapPut                                1000000          100000   avgt   25  16.889   0.909  ms/op

HashtablePut                                    0          100000   avgt   25   8.885   0.411  ms/op
HashtablePut                                    1          100000   avgt   25   8.742   0.298  ms/op
HashtablePut                                 1000          100000   avgt   25   8.563   0.238  ms/op
HashtablePut                               100000          100000   avgt   25  12.339   0.448  ms/op
HashtablePut                              1000000          100000   avgt   25  19.668   1.411  ms/op

LinkedHashMapPut                                0          100000   avgt   25   8.441   0.256  ms/op
LinkedHashMapPut                                1          100000   avgt   25   8.633   0.466  ms/op
LinkedHashMapPut                             1000          100000   avgt   25   8.437   0.278  ms/op
LinkedHashMapPut                           100000          100000   avgt   25  12.571   1.568  ms/op
LinkedHashMapPut                          1000000          100000   avgt   25  16.583   0.573  ms/op

TreeMapPut                                      0          100000   avgt   25  48.461   1.372  ms/op
TreeMapPut                                      1          100000   avgt   25  47.730   1.237  ms/op
TreeMapPut                                   1000          100000   avgt   25  47.734   1.777  ms/op
TreeMapPut                                 100000          100000   avgt   25  81.656   4.050  ms/op
TreeMapPut                                1000000          100000   avgt   25  99.814  19.037  ms/op
```
