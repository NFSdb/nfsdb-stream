package com.nfsdb.stream;

import com.nfsdb.journal.Journal;
import com.nfsdb.journal.ResultSet;
import com.nfsdb.journal.exceptions.JournalException;
import com.nfsdb.journal.query.api.QueryAll;
import com.nfsdb.journal.query.api.QueryAllBuilder;

import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JournalStreamSupport {

    public static <T> Stream<T> $(ResultSet<T> rs) {
        return StreamSupport.stream(Spliterators.spliterator(rs.bufferedIterator(), rs.size(), 0), false);
    }

    public static <T> Stream<T> $$(ResultSet<T> rs) {
        return StreamSupport.stream(Spliterators.spliterator(rs.iterator(), rs.size(), 0), false);
    }

    public static <T> Stream<T> $(QueryAll<T> q) throws JournalException {
        return $(q.asResultSet());
    }

    public static <T> Stream<T> $(Journal<T> journal) throws JournalException {
        return StreamSupport.stream(Spliterators.spliterator(journal.bufferedIterator(), journal.size(), 0), false);
    }

    public static <T> Stream<T> $(QueryAllBuilder<T> builder) throws JournalException {
        return $(builder.asResultSet());
    }
}
