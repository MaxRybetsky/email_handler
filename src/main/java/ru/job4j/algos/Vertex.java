package ru.job4j.algos;

import java.util.Objects;

/**
 * Wrapper of String value of email and user data.
 * Used as vertex of graph in {@link Graph} class.
 * Has info if this object is user or email.
 * Methods {@link Vertex#equals} and {@link Vertex#hashCode()}
 * are overridden for using this objects in {@link java.util.Map}
 */
public class Vertex {

    /**
     * The value of vertex
     */
    private final String value;

    /**
     * Information about kind of
     * value: user or email
     */
    private final boolean isUser;

    /**
     * Specifying only value of vertex means
     * the vertex is user's
     *
     * @param value value of user
     */
    public Vertex(String value) {
        this(value, true);
    }

    /**
     * Explicit specifying variable {@code isUser}
     * means the vertex cannot be only user's, but
     * also email's
     *
     * @param value  value of vertex
     * @param isUser is this vertex user's or not
     */
    public Vertex(String value, boolean isUser) {
        this.value = value;
        this.isUser = isUser;
    }

    /**
     * Get value of vertex
     *
     * @return value of vertex
     */
    public String getValue() {
        return value;
    }

    /**
     * Get info if this vertex represents user
     * or not
     *
     * @return {@code true} if this vertex represents user,
     * otherwise {@code false}
     */
    public boolean isUser() {
        return isUser;
    }

    /**
     * Compares this vertex to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * Vertex} object that has the same values of variables {@code value} and
     * {@code isUser}.
     *
     * @param o The object to compare this {@code Vertex} against
     * @return {@code true} if the given object represents a {@code Vertex}
     * equivalent to this vertex, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return isUser == vertex.isUser
                && Objects.equals(value, vertex.value);
    }

    /**
     * Returns a hash code for this vertex by using
     * method {@link Objects#hash}.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, isUser);
    }

    /**
     * Get value of vertex in {@link String}
     *
     * @return value of vertex
     */
    @Override
    public String toString() {
        return value;
    }

}
