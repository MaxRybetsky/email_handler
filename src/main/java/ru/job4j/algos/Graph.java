package ru.job4j.algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Data structure for working with graphs in context
 * of task solution.
 * Contains data about vertices (via {@link Vertex} object)
 * in {@link Map} data structure, data about vertices used
 * in depth-first search, users data and help list to save emails
 * belong to one user.
 * Has method to fill data from user map to an adjacency list like
 * map structure, depth-first search method and main handler method
 * to hand user data of emails and users to ready dataset.
 * The main idea of solution is to find all connectivity components
 * in bipartite graph, which in fact is our dataset. The start vertex
 * of searching always has user value, and all other vertices representing
 * users are discarded by setting {@code true} in map {@link Graph#users}
 * with user value as key. Vertices representing emails are added to
 * {@link List} {@link Graph#emails}. Then method {@link Graph#handler}
 * fills result map with start vertex as key and {@link Graph#emails} as
 * value. At the end {@link Graph#emails} is assigned new {@link List}
 * object for new refilling by emails of other user doesn't have common
 * emails with current one.
 */
public class Graph {

    /**
     * The adjacency list like {@link Map} structure contains
     * data about all vertices in graph
     */
    private final Map<Vertex, List<Vertex>> graph = new HashMap<>();

    /**
     * Shows which vertices was visited in search process
     */
    private final Map<Vertex, Boolean> used = new HashMap<>();

    /**
     * Contains information about users copies
     */
    private final Map<Vertex, Boolean> users = new HashMap<>();

    /**
     * Used as buffer to save emails from different user's nicknames
     * when traversing the {@link Graph#graph}
     */
    private List<String> emails = new ArrayList<>();

    /**
     * Sends initial data to {@link Graph#fillData} for convert it to
     * {@link Graph#graph}. Then go over {@link Graph#users} and if current
     * user is not copy begins depth-first search with this user's vertex as start.
     * After filling list {@link Graph#emails} puts entry to result map with user as key
     * and the list as value.
     *
     * @param data Initial data of users and their emails
     * @return Map of users and their emails without duplicates
     */
    public Map<String, List<String>> handler(Map<String, List<String>> data) {
        fillData(data);
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<Vertex, Boolean> user : users.entrySet()) {
            if (!user.getValue()) {
                Vertex uKey = user.getKey();
                dfs(uKey);
                result.put(uKey.getValue(), emails);
                emails = new ArrayList<>();
            }
        }
        return result;
    }

    /**
     * Translate initial data to adjective list like map.
     * Save result to {@link Graph#graph}.
     * Example of how process works. Let the input map be
     * next:
     * <blockquote><pre>
     *  {["user1" -> "em1@el.el", "em2@el.el", "em3@el.el"],
     *   ["user2" -> "em1@el.el", "em4@el.el"]}
     * </pre></blockquote>
     * After the method works we'll get:
     * <blockquote><pre>
     *  {["user1" -> "em1@el.el", "em2@el.el", "em3@el.el"],
     *   ["em1@el.el" -> "user1", "user2"],
     *   ["em2@el.el" -> "user1"],
     *   ["em3@el.el" -> "user1"],
     *   ["user2" -> "em1@el.el", "em4@el.el"],
     *   ["em4@el.el" -> "user2"]}
     * </pre></blockquote>
     *
     * @param userData Initial data of users and their emails with duplicates
     */
    private void fillData(Map<String, List<String>> userData) {
        for (Map.Entry<String, List<String>> entry : userData.entrySet()) {
            Vertex userKey = new Vertex(entry.getKey());
            used.put(userKey, false);
            users.put(userKey, false);
            List<Vertex> vertices = entry.getValue().stream()
                    .map(e -> {
                        Vertex vertex = new Vertex(e, false);
                        used.put(vertex, false);
                        if (!graph.containsKey(vertex)) {
                            graph.put(vertex, new ArrayList<>());
                        }
                        graph.get(vertex).add(userKey);
                        return vertex;
                    })
                    .collect(Collectors.toList());
            graph.put(userKey, vertices);
        }
    }

    /**
     * Depth-first search in {@link Graph#graph}. Using for
     * searching all connectivity components in bipartite graph in
     * method {@link Graph#handler}.
     *
     * @param start The {@link Vertex} object to start searching from
     */
    private void dfs(Vertex start) {
        used.replace(start, true);
        for (Vertex v : graph.get(start)) {
            if (!used.get(v)) {
                if (v.isUser()) {
                    users.replace(v, true);
                } else {
                    emails.add(v.getValue());
                }
                dfs(v);
            }
        }
    }
}
