package com.example.springapi.service;

import com.example.springapi.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private final List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        User user1 = new User(1, "Sean", "sean@andrew.cmu.edu", "sean123", "S123456");
        User user2 = new User(2, "Jason", "Jason@andrew.cmu.edu", "jason123", "J345612");
        User user3 = new User(3, "Sunny", "sunny@andrew.cmu.edu", "sunny123", "S561234");

        userList.addAll(Arrays.asList(user1, user2, user3));
        userList.sort(Comparator.comparingInt(User::getId));

    }

    public User getUser(int id) {
        for (User user: userList) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public void deleteUser(int id) {
        userList.removeIf(user -> id == user.getId());
    }

    public User addUser(String name, String email, String username, String password) {
        int id = userList.get(userList.size() - 1).getId() + 1;
        User newUser = new User(id, name, email, username, password);
        userList.add(newUser);
        return newUser;
    }

    public static boolean isValidUsername(String username) {
        return username.length() > 3;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 6 && Character.isUpperCase(password.charAt(0));
    }
}

