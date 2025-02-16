package com.example.demo.User;
import java.util.HashSet;

public class UserRegister {
  private static HashSet<User> users = new HashSet<User>(); 

  public boolean register(User u) {
    return users.add(u);
  }

  public boolean checkUser(User u) {
    for (User user : users) {
      if (user.equals(u)) {
        return true;
      }
    }

    return false;
  }

  public User getUser(User u) {
    User auxUser = u;
    for (User user : users) {
      if (user.equals(u)) {
        auxUser = user;
      }
    }
    return auxUser;
  }
}