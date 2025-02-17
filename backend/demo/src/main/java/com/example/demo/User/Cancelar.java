package com.example.demo.User;

import java.time.LocalDateTime;

public class Cancelar {
  private LocalDateTime hour;
  private String cpf;

  public Cancelar(String cpf, LocalDateTime hour) {
    this.hour = hour;
    this.cpf = cpf;
  }

  public LocalDateTime getHour() {
    return hour;
  }

  public String getCpf() {
    return cpf;
  }
}
