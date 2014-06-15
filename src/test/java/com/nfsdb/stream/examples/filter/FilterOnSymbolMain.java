package com.nfsdb.stream.examples.filter;

import com.nfsdb.journal.Journal;
import com.nfsdb.journal.JournalWriter;
import com.nfsdb.journal.exceptions.JournalException;
import com.nfsdb.journal.factory.JournalFactory;
import com.nfsdb.journal.utils.Files;
import com.nfsdb.stream.examples.model.MarketQuote;

import java.io.File;

import static com.nfsdb.stream.JournalStreamSupport.$;
import static com.nfsdb.stream.examples.Utils.generate;

public class FilterOnSymbolMain {
    public static void main(String[] args) throws JournalException {

        // bootstrap journal directory
        try (JournalFactory factory = new JournalFactory("d:/data")) {

            // delete existing to enable re-runs
            Files.deleteOrException(new File(factory.getConfiguration().getJournalBase(), "quote"));

            // create 10M objects
            try (JournalWriter<MarketQuote> writer = factory.writer(MarketQuote.class)) {
                generate(writer, 10000000);
            }

            long t = System.currentTimeMillis();
            try (Journal<MarketQuote> journal = factory.reader(MarketQuote.class)) {
                // query!
                System.out.println(
                        $(journal.select("exchange", "bid", "ask", "bidSize").query().all().withSymValues("exchange", "FU", "KQ")).filter(q -> q.getBid() < 50 && q.getAsk() > 1000).mapToLong(MarketQuote::getBidSize).sum()
                );
            }
            System.out.println("took " + (System.currentTimeMillis() - t) + "ms.");
        }
    }
}
