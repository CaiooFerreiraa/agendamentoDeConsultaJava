package com.example.demo.MainApp;
import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Serializable {
    private String name;
    private String cpf;
    private String email;
    private int age;
    private ArrayList<Consultation> historyConsultations;

    public Patient(String name, String cpf, String email, int age) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.age = age;
        this.historyConsultations = new ArrayList<>();
    }

    public String getCpf() {
      return this.cpf;
    }

    public String getName() {
      return this.name;
    }

    public String getEmail() {
      return this.email;
    }

    public int getAge() {
      return this.age;
    }

    public void addConsultation(Consultation objConsultation) {
      if (historyConsultations.add(objConsultation)) {
        RegisterPatient cp = new RegisterPatient();
        cp.saveToFile();
      } else {
        System.out.println("Consulta não realizada");
      }; 
    }

    public Consultation[] getHistoryConsultations() {
      Consultation[] history = new Consultation[historyConsultations.size()];

      for (int i = 0; i < historyConsultations.size(); i++) {
          Consultation objConsultation = historyConsultations.get(i);
          if (objConsultation != null) {
              history[i] = objConsultation;
          }
      }

      return history;
  }

  public void cancelarConsulta(Consultation c) {
    historyConsultations.remove(c);
  }

  public void reagendarConsulta(Consultation c) {
    
  }

  @Override
  public String toString() {
    return name + "," + cpf + "," + email + "," + age;
  }
}
