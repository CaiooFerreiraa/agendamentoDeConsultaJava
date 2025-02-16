package com.example.demo.MainApp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RegisterDoctor {
  String routeFile = "C:/Users/cs191/Documents/Projetos/agenda-de-consultas - Copia/backend/demo/src/main/resources/Data/medicos.txt";

  public void saveToFile(ArrayList<Doctor> array) throws ClassNotFoundException {
    try {
      ObjectOutputStream objInput = new ObjectOutputStream(new FileOutputStream(routeFile));

      objInput.writeObject(array);

      objInput.close();
    } catch (IOException e) {
      System.out.println("Arquivo não existe");
    }
  }
}
