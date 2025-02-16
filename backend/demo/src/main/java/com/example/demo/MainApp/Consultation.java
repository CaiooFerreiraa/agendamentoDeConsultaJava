package com.example.demo.MainApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class Consultation implements Serializable {
  private Patient patient;
  private Doctor doctor;
  private LocalDateTime dateHour;
  private String reason;
  private String notes;
  private String status;

  public Consultation(Patient pat, Doctor doc, LocalDateTime date, String reason, String notes) {
    this.patient = pat;
    this.doctor = doc;
    this.dateHour = date;
    this.reason = reason;
    this.notes = notes;
    this.status = "Agendada";
  }
  
  public void saveToFile() throws URISyntaxException {
    try {
      File dirFile = new File(MainApp.class.getResource("/Data/consultas.txt").toURI());
      FileWriter file = new FileWriter(dirFile, true);
      
      String linha = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
        patient.getName(),
        patient.getCpf(),
        patient.getEmail(),
        patient.getAge(),

        doctor.getName(),
        doctor.getSpecialty(),
        doctor.getEmail(),
        doctor.getCm(),

        reason,
        notes,
        dateHour.toString(),
        status
      );

      file.write(linha + System.lineSeparator());
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  } 

  @Override
  public String toString() {
    return "Paciente: " + patient.getName() + "\nDoutor: " + doctor.getName() + "\nData e Hora: " + dateHour + "\nMotivo: " + reason + "\nNotas: " + notes + "\nStatus: " + status;
  }
}
