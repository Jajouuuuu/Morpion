package model;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserManager {

    private List<User> users;
    private final String filePath = "users.json";

    public UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            } else {
                users = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsers() {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
    	if (this.users == null) {
            this.users = new ArrayList<>();
        }
        return this.users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
        saveUsers();
    }
}
