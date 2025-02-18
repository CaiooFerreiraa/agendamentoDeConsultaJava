package com.example.demo.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import com.example.demo.MainApp.Consultation;
import com.example.demo.MainApp.Doctor;

public class User implements Serializable {
    private String name, cpf, email, password;
    private int age;
    private String doctor;
    private String status;
    private String time;
    private String reason;
    private String notes;
    private String indenfier;
    private String speciality;
    ArrayList<Consultation> array;
    Doctor d1;

    public User() {

    }

    public User(String name, String cpf, String email, String password, int age)  {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public User(String specialty, String doctor, String time, String reason, String notes, String name, String email, int age, String cpf) {
      this.speciality = specialty;
      this.doctor = doctor;
      this.time = time;
      this.reason = reason;
      this.notes = notes;
      this.name = name;
      this.email = email;
      this.age = age;
      this.cpf = cpf;
    }

    public User(String indentifier, String password) {
      this.cpf = indentifier;
      this. password = password;
    }

    public User(String indenfier) {
      this.indenfier = indenfier;
    }

    public int getAge() {
      return age;
    }

    public String getSpeciality() {
      return speciality;
    }

    public String getIndenfier() {
      return indenfier;
    }

    public String getStatus() {
      return status;
    }

    public String getTime() {
        return time;
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

    public String getDoctor() {
      return this.doctor;
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
