package com.example.demo.MainApp;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Patient implements Serializable {
    private String name;
    private String cpf;
    private String email;
    private int age;
    @JsonIgnore // Isso impede que a lista seja serializada, evitando a recursão infinita
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
      Consultation [] consult = new Consultation[historyConsultations.size()];

      for (int i = 0; i < consult.length; i++) {
        consult[i] = historyConsultations.get(i);
      }

      return consult;
  }

  public boolean cancelarConsulta(LocalDateTime c) {
    
    for (Consultation consultation : historyConsultations) {
      if (consultation.getDateHour().equals(c)) {
        historyConsultations.remove(consultation);
        return true;
      }
    }

    return false;
  }

  public void reagendarConsulta(Consultation c) {
    
  }

  @Override
  public String toString() {
    return name + "," + cpf + "," + email + "," + age;
  }
}
