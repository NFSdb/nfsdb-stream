package com.nfsdb.stream.examples.model;

public class MarketQuote {

    private String symbol;
    private long askSize;
    private long ask;
    private long bidSize;
    private long bid;
    private String exchange;
    private String mode;
    private long timestamp;

    public void clear() {
        symbol = null;
        askSize = ask = bidSize = bid = 0L;
        exchange = mode = null;
        timestamp = 0L;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getAskSize() {
        return askSize;
    }

    public void setAskSize(long askSize) {
        this.askSize = askSize;
    }

    public long getAsk() {
        return ask;
    }

    public void setAsk(long ask) {
        this.ask = ask;
    }

    public long getBidSize() {
        return bidSize;
    }

    public void setBidSize(long bidSize) {
        this.bidSize = bidSize;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MarketQuote{" +
                "symbol='" + symbol + '\'' +
                ", askSize=" + askSize +
                ", ask=" + ask +
                ", bidSize=" + bidSize +
                ", bid=" + bid +
                ", exchange='" + exchange + '\'' +
                ", mode='" + mode + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
