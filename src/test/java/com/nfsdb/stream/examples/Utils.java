package com.nfsdb.stream.examples;

import com.nfsdb.journal.JournalWriter;
import com.nfsdb.journal.exceptions.JournalException;
import com.nfsdb.journal.utils.Dates;
import com.nfsdb.stream.examples.model.MarketQuote;

import java.util.Random;

public final class Utils {

    private Utils() {
    }

    public static void generate(JournalWriter<MarketQuote> w, int count) throws JournalException {
        String symbols[] = {"AGK.L", "BP.L", "TLW.L", "ABF.L", "LLOY.L", "BT-A.L", "WTB.L", "RRS.L", "ADM.L", "GKN.L", "HSBA.L"};
        String exchanges[] = {"MW", "C", "FU", "KW", "KQ", "MI", "M"};
        long timestamps[] = {Dates.toMillis("2013-09-04T10:00:00.000Z"), Dates.toMillis("2013-10-04T10:00:00.000Z"), Dates.toMillis("2013-11-04T10:00:00.000Z")};
        MarketQuote q = new MarketQuote();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            q.clear();
            q.setSymbol(symbols[Math.abs(r.nextInt() % (symbols.length))]);
            q.setAsk(Math.abs(r.nextLong() % 10000));
            q.setBid(Math.abs(r.nextLong() % 10000));
            q.setAskSize(Math.abs(r.nextLong() % 100000));
            q.setBidSize(Math.abs(r.nextLong() % 100000));
            q.setExchange(exchanges[Math.abs(r.nextInt() % (exchanges.length))]);
            q.setMode("Fast trading");
            q.setTimestamp(timestamps[i * timestamps.length / count]);
            w.append(q);
        }
        w.commit();
    }

}
