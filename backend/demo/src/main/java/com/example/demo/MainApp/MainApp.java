package com.example.demo.MainApp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainApp {
  private static RegisterPatient controllerPatient = new RegisterPatient();

  public static void main(String[] args) {
    
  }
  //Registra consultas com string direta;
  static void registarConsulta(String c) {
    try {
      FileWriter file = new FileWriter("C:/Users/cs191/Documents/Projetos/agenda-de-consultas - Copia/backend/demo/src/main/resources/Data/consultas.txt", true);

      file.write(c + System.lineSeparator());
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Registra consultas utilizando de objetos do tipo Consultation
  static void registarConsulta(Consultation c) {
    try {
      FileWriter file = new FileWriter("C:/Users/cs191/Documents/Projetos/agenda-de-consultas - Copia/backend/demo/src/main/resources/Data/consultas.txt", true);
      
      file.write(c + System.lineSeparator());
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void lerConsultas() {
    try {
      FileReader file = new FileReader("C:/Users/cs191/Documents/Projetos/agenda-de-consultas - Copia/backend/demo/src/main/resources/Data/consultas.txt");
      Scanner fluxo = new Scanner(file);

      while (fluxo.hasNext()) {
        String line = fluxo.nextLine();

        StringTokenizer token = new StringTokenizer(line, ",");

        String namePatient = token.nextToken();
        String cpfPatient = token.nextToken();
        String emailPatient = token.nextToken();
        int agePatient = Integer.parseInt(token.nextToken());

        String nameDoctor = token.nextToken();
        String speciality = token.nextToken();
        String emailDoctor = token.nextToken();
        String cm = token.nextToken();

        String reason = token.nextToken();
        String notes = token.nextToken();
        LocalDateTime date = LocalDateTime.parse(token.nextToken());
        //String status = tokens[11];

        Patient pat = controllerPatient.getPatientCpf(cpfPatient);


        if (pat == null) {
          pat = new Patient(namePatient, cpfPatient, emailPatient, agePatient);
        }

        Doctor doc = new Doctor(nameDoctor, speciality, cm, emailDoctor);
        Consultation c1 = new Consultation(pat, doc, date, reason, notes);
        pat.addConsultation(c1);
        
      }

      file.close();
      fluxo.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void lerDados() {
    controllerPatient.reader();
  }
}
