package com.example.demo.User;

import java.time.LocalDateTime;
import java.util.Objects;

import com.example.demo.MainApp.Doctor;
import com.example.demo.MainApp.Patient;

public class User {
    private String name, cpf, email, password;
    private int age;
    private Doctor doctor;
    private Patient patient;
    private String status;
    private LocalDateTime date;
    private String reason;
    private String notes;

    public User() {

    }

    public User(String name, String cpf, String email, String password, int age) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public User(Doctor doctor, Patient patient, String status, LocalDateTime date, String reason, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.status = status;
        this.date = date;
        this.reason = reason;
        this.notes = notes;
    }

    public User(String indentifier, String password) {
      this.cpf = indentifier;
      this. password = password;
    }

    public int getAge() {
      return age;
    }

    public String getStatus() {
      return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public String getNotes() {
        return notes;
    }

    public String getCpf() {
      return cpf;
    }

    public String getEmail() {
      return email;
    }

    public String getName() {
      return name;
    }

    public String getPassword() {
      return password;
    }

    public Doctor getDoctor() {
      return this.doctor;
    }

    public Patient getPatient() {
      return patient;
    }

    //Para permitir que apenas um usuário seja cadastrado

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // É o mesmo objeto
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Tipos incompatíveis
        }
        User other = (User) obj;
        return Objects.equals(password, other.password) || Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
      return "Nome: " + name + "\nCPF: " + cpf + "\nEmail: " + email + "\nIdade: " + age;
    }
}
