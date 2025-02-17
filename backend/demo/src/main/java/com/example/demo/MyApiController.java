package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MainApp.Consultation;
import com.example.demo.MainApp.Doctor;
import com.example.demo.MainApp.MainApp;
import com.example.demo.MainApp.Patient;
import com.example.demo.MainApp.RegisterDoctor;
import com.example.demo.MainApp.RegisterPatient;
import com.example.demo.User.Cancelar;
import com.example.demo.User.User;
import com.example.demo.User.UserRegister;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://192.168.1.151:8080"})
public class MyApiController {
  static UserRegister controllerUser = new UserRegister();
  static RegisterPatient controllerPatient = new RegisterPatient();
  private static RegisterDoctor controllerDoctor = new RegisterDoctor();
  private static ArrayList<Doctor> doctors = new ArrayList<Doctor>();

  @PostConstruct
  public void init() throws ClassNotFoundException {
    MainApp.lerDados();
    Doctor d1 = new Doctor("Caio Ferreira Almeida", "Cardiologista", "1234", "cs1919328@gmail.com");
    Doctor d2 = new Doctor("Ana Souza", "Cardiologista", "5678", "ana@gmail.com");
    
    Doctor d3 = new Doctor("Marcos Lima", "Pediatra", "2345", "marcos@gmail.com");
    Doctor d4 = new Doctor("Carla Oliveira", "Pediatra", "6789", "carla@gmail.com");
    
    Doctor d5 = new Doctor("Roberto Costa", "Ortopedista", "3456", "roberto@gmail.com");
    Doctor d6 = new Doctor("Juliana Silva", "Ortopedista", "7890", "juliana@gmail.com");
    
    Doctor d7 = new Doctor("Patricia Almeida", "Dermatologista", "4567", "patricia@gmail.com");
    Doctor d8 = new Doctor("Eduardo Martins", "Dermatologista", "8901", "eduardo@gmail.com");
    
    Doctor d9 = new Doctor("Luciana Rocha", "Ginecologista", "5678", "luciana@gmail.com");
    Doctor d10 = new Doctor("Fernando Pereira", "Ginecologista", "1230", "fernando@gmail.com");

    gerarHorarios(d1, 8);
    gerarHorarios(d2, 9);
    gerarHorarios(d3, 10);
    gerarHorarios(d4, 11);
    gerarHorarios(d5, 12);
    gerarHorarios(d6, 13);
    gerarHorarios(d7, 14);
    gerarHorarios(d8, 15);
    gerarHorarios(d9, 16);
    gerarHorarios(d10, 17);
    
    doctors.add(d1);
    doctors.add(d2);
    doctors.add(d3);
    doctors.add(d4);
    doctors.add(d5);
    doctors.add(d6);
    doctors.add(d7);
    doctors.add(d8);
    doctors.add(d9);
    doctors.add(d10);

    controllerDoctor.saveToFile(doctors);
  }

  public static void gerarHorarios(Doctor doctor, int startHour) {
    for (int i = 0; i < 5; i++) {
        doctor.addAvaliableTime((LocalDateTime.of(2025, 2, 18 + i, startHour + i, i * 10)));
    }
}

  @PostMapping("/register") 
  public boolean register(@RequestBody User u) {
    User user = new User(u.getName(), u.getCpf(), u.getEmail(), u.getPassword(), u.getAge());
    Patient p1 = new Patient(u.getName(), u.getCpf(), u.getEmail(), u.getAge());

    controllerPatient.register(p1);
  
    return controllerUser.register(user);
  }

  @PostMapping("/specialty")
  public ArrayList<Doctor> doctorsSpecialty(@RequestBody User u) {
    ArrayList<Doctor> docsForSpecialty = new ArrayList<Doctor>();

    for (Doctor doctor : doctors) {
      if (doctor.getSpecialty().equals(u.getIndenfier())) {
        docsForSpecialty.add(doctor);
      }  
    }

    return docsForSpecialty;
  }

  @PostMapping("/peekHours")
  public ArrayList<LocalDateTime> peek(@RequestBody User u) {
    ArrayList<LocalDateTime> hours = new ArrayList<LocalDateTime>();

    for (Doctor doc : doctors) {
      if (doc.getCm().equals(u.getIndenfier())) {
        hours = doc.getHours();
      }
    }

    return hours;
  }

  @GetMapping("/allDoctors")
  public ArrayList<Doctor> recoveryAllDoctors() {
    return doctors;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User u) {
    User user = new User(u.getCpf(), u.getPassword());

    if (controllerUser.checkUser(user)) {
        // Usuário autenticado com sucesso
        User authenticatedUser = controllerUser.getUser(user);
        return ResponseEntity.ok(authenticatedUser);
    } else {
        // Retorna uma mensagem de erro e status 401
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Usuário inválido ou credenciais incorretas.");
    }
  }

  @SuppressWarnings("null")
  @PostMapping("/registrarconsulta")
  public int RegisterConsulta(@RequestBody User u) {
    Doctor d1 = null;
    System.out.println(u.getTime());
    LocalDateTime time = LocalDateTime.parse(u.getTime());
    
    for (Doctor doctor : doctors) {
      if (doctor.getCm().equals(u.getDoctor())) {
        d1 = doctor;
      }
    }

    Patient p1 = controllerPatient.getPatientCpf(u.getCpf());

    Consultation c1 = new Consultation(p1, d1, time, u.getReason(), u.getNotes());

    p1.addConsultation(c1);
    d1.blockHour(time);

    if (!controllerPatient.register(p1)) {
      return 400;
    }

    return 200;
  }

  @PostMapping("/recovery")
  public Consultation[] history(@RequestBody User u) {
    Patient p1 = controllerPatient.getPatientCpf(u.getCpf());
    Consultation [] data = p1.getHistoryConsultations();

    return data;
  }

  @PostMapping("/cancelar")
  public int cancelar(@RequestBody Cancelar c) {
    Patient p1 = controllerPatient.getPatientCpf(c.getCpf());

    if (p1.cancelarConsulta(c.getHour())) {
      return 200;
    }

    return 400;

  }
}
