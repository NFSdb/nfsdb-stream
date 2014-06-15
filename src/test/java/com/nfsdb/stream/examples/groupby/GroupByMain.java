package com.nfsdb.stream.examples.groupby;

import com.nfsdb.journal.Journal;
import com.nfsdb.journal.JournalWriter;
import com.nfsdb.journal.exceptions.JournalException;
import com.nfsdb.journal.factory.JournalFactory;
import com.nfsdb.journal.utils.Files;
import com.nfsdb.stream.examples.model.MarketQuote;

import java.io.File;

import static com.nfsdb.stream.JournalStreamSupport.$;
import static com.nfsdb.stream.examples.Utils.generate;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

public class GroupByMain {
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
                System.out.println($(journal.select("symbol", "askSize")).collect(groupingBy(MarketQuote::getSymbol, summingLong(MarketQuote::getAskSize))));
            }
            System.out.println("took " + (System.currentTimeMillis() - t) + "ms.");
        }
    }
}
