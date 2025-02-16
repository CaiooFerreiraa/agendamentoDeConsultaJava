package com.example.demo.MainApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterPatient {
  private static Map<String, Patient> map = new HashMap<>();
  private static String routeFile = "C:/Users/cs191/Documents/Projetos/agenda-de-consultas - Copia/backend/demo/src/main/resources/Data/pacientes.txt";

  public boolean register(Patient pat) {
    if (map.containsKey(pat.getCpf())) {
      return false;
    }
    try {
      ObjectOutputStream objWrite = new ObjectOutputStream(new FileOutputStream(routeFile));

      map.put(pat.getCpf(), pat);
  
      objWrite.close();
      return saveToFile();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }

  @SuppressWarnings("unchecked")
  public void reader() {
    try {
      ObjectInputStream objReader = new ObjectInputStream(new FileInputStream(routeFile));

      Object obj = objReader.readObject();

      if (obj instanceof HashMap) {
        map = (HashMap<String, Patient>) obj;
      }

      objReader.close();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Não existe arquivo");;
    }
  }

  public Patient getPatientCpf(String cpf) {
    return map.get(cpf);
  }

  public boolean saveToFile() {
    try {
      ObjectOutputStream objWrite = new ObjectOutputStream(new FileOutputStream(routeFile));

      objWrite.writeObject(map);
  
      objWrite.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
