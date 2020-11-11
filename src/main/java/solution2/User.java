package solution2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Set<String> emails = new HashSet<>();

    public User(String name, String... emails) {
        this.name = name;
        this.emails.addAll(Arrays.asList(emails));
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void addAll(Set<String> otherEmails) {
        emails.addAll(otherEmails);
    }

    @Override
    public String toString() {
        return name + " - " + emails;
    }
}
