package solution2;

import java.util.*;

public class Handler {
    private final Map<String, User> emails = new HashMap<>();
    private final Map<User, User> duplicates = new HashMap<>();
    private final Set<User> users = new HashSet<>();

    public void addUsers(List<User> usersList) {
        for(User user : usersList) {
            boolean isMainUser = true;
            for(String email : user.getEmails()) {
                User prevUser = emails.putIfAbsent(email, user);
                if(prevUser != null) {
                    prevUser = duplicates.getOrDefault(prevUser, prevUser);
                    prevUser.addAll(user.getEmails());
                    duplicates.put(user, prevUser);
                    isMainUser = false;
                }
            }
            if(isMainUser) {
                users.add(user);
            }
        }
    }

    @Override
    public String toString() {
        return users.toString();
    }
}
