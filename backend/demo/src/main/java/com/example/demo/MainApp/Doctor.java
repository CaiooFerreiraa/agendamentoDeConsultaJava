package com.example.demo.MainApp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Doctor implements Serializable{
  private String name, specialty, cm, email;
  private ArrayList<LocalDateTime> avaliableTimes;
  private ArrayList<LocalDateTime> blockTimes;
  
  public Doctor(String name, String specialty, String cm, String email) {
    this.name = name;
    this.specialty = specialty;
    this.cm = cm;
    this.email = email;
    avaliableTimes = new ArrayList<LocalDateTime>();
    blockTimes = new ArrayList<LocalDateTime>();
  }

  public Doctor(String cm) {
    this.cm = cm;
  }

  public String getName() {
    return this.name;
  }

  public String getSpecialty() {
    return this.specialty;
  }

  public String getCm() {
    return this.cm;
  }

  public String getEmail() {
    return this.email;
  }

  public boolean blockHour(LocalDateTime hour) {
    if (avaliableTimes.contains(hour)) {
      return blockTimes.add(hour);
    }
    return false;
  }

  public boolean hourRelease(LocalDateTime hour) {
    return blockTimes.remove(hour);
  }

  public boolean addAvaliableTime(LocalDateTime hour) {
    return avaliableTimes.add(hour);
  }

  public void printHours() {
    for (int i = 0; i < avaliableTimes.size(); i++) {
      System.out.println(avaliableTimes.get(i));
    }
  }

  public ArrayList<LocalDateTime> getHours() {
    return avaliableTimes;
  }

  @Override
  public String toString() {
    return "Doctor: " + name + "\nespecialidade: " + specialty + "\ncm: " + cm + "\nemail: " + email; 
  }
}
