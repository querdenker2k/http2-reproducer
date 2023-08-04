## Curl
### Success with Http 1.1

``` bash
curl --data @huge.txt -XPOST -v http://localhost:8080/rs/bigdata --http1.1
```

### Success with Http 2

``` bash
curl --data @huge.txt -XPOST -v http://localhost:8080/rs/bigdata --http2
```
