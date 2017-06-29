package com.sysgears.simplecalculator.history;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Keeps the history which elements stores in {@link ResultPair} class.
 */
public class HistoryHolder {
    private List<ResultPair> history;

    /**
     * Constructs an object
     */
    public HistoryHolder() {
        this.history = new LinkedList<>();
    }

    /**
     * Add a new event into history container
     *
     * @param key the key
     * @param value the value
     */
    public void addEvent(String key, String value) {
        history.add(new ResultPair(key, value));
    }

    /**
     * returns the value associated with the key
     *
     * @param key the key
     * @return the value associated with the key
     */
    public String getResult(String key) {
        int index = history.indexOf(new ResultPair(key));

        if (index == -1) {
            return null;
        } else {
            return history.get(index).getValue();
        }
    }

    /**
     * Returns string contains the history events without duplicates
     *
     * @return history without duplicates
     */
    public String getUniqueHistory() {
        return buildString(new LinkedHashSet<>(history));
    }

    /**
     * Returns string contains the history events
     *
     * @return string contains the history events
     */
    @Override
    public String toString() {
        return buildString(history);
    }

    /**
     * Builds a string with history events from the incoming {@code Collection}
     *
     * @param pairs Collection contains history events
     * @return string contains the history events
     */
    private String buildString(Collection<ResultPair> pairs) {
        return "\t" + pairs.stream().
                                    map(ResultPair::toString).
                                    collect(Collectors.joining(System.lineSeparator() + "\t"));
    }
}