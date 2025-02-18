package com.example.demo.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class UserRegister {
  private static HashSet<User> users = new HashSet<User>();
  private String routeFile = "C:\\Users\\cs191\\Documents\\Projetos\\agenda-de-consultas-lp2\\backend\\demo\\src\\main\\resources\\Data\\users.txt"; 

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

  public void saveToFile() {
    try {
      ObjectOutputStream objWrite = new ObjectOutputStream(new FileOutputStream(routeFile));

      objWrite.writeObject(users);

      objWrite.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public void readeToFile() {
    try {
      ObjectInputStream objReader = new ObjectInputStream(new FileInputStream(routeFile));

      users = (HashSet<User>) objReader.readObject();

      objReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}