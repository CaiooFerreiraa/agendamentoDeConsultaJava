package com.example.demo.MainApp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RegisterDoctor {
  String routeFile = "C:/Users/cs191/Documents/Projetos/agenda-de-consultas-lp2/backend/demo/src/main/resources/Data/medicos.txt";

  public void saveToFile(ArrayList<Doctor> array) throws ClassNotFoundException {
    try {
      ObjectOutputStream objInput = new ObjectOutputStream(new FileOutputStream(routeFile));

      objInput.writeObject(array);

      objInput.close();
    } catch (IOException e) {
      System.out.println("Sem dados para a leitura");
    }
  }
}
