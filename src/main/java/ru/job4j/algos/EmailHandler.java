package ru.job4j.algos;

import java.util.List;
import java.util.Map;

/**
 * Class for solving the problem of
 * duplicate users with same emails.
 * Provides easy access to static method
 * {@link EmailHandler#handler}.
 */
public class EmailHandler {

    /**
     * Provides convenient use of
     * {@link Graph}'s capabilities
     * without the need to create
     * instances of the class.
     *
     * @param data Initial entries of
     *             users and their emails
     * @return {@link Map} object without
     * duplicates of users email data
     */
    public static Map<String, List<String>> handler(Map<String, List<String>> data) {
        return new Graph().handler(data);
    }
}
