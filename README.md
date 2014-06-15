nfsdb-streams
=============

Java 8 Streams implementation for NFSdb


There are some interesting examples of how to use NFSdb with Stream. It is quite easy as it turned out.

This Java code:

```java
try (Journal<MarketQuote> journal = factory.reader(MarketQuote.class)) {
  System.out.println(
    $(journal.select("symbol", "askSize"))
      .collect(groupingBy(MarketQuote::getSymbol, summingLong(MarketQuote::getAskSize)))
  );
}
```

is the same as this SQL:

```sql
select symbol, sum(askSize)
from MarketQuote
group by symbol
```

Java version takes 536ms over 10,000,000 records.

Jump [here] (https://github.com/NFSdb/nfsdb-stream/tree/master/src/test/java/com/nfsdb/stream/examples) for working examples.
