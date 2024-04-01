package com.example.travp3;

import com.example.travp3.entities.Patient;
import com.example.travp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Travp3Application implements CommandLineRunner {
	//utiliser interface patienrepo avec injection des dependance en utilisant auto
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(Travp3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"Yassine",new Date(),false,34));
		patientRepository.save(new Patient(null,"Rim",new Date(),false,4321));
		patientRepository.save(new Patient(null,"Lina",new Date(),false,344));










		/*//pour cree un patient solu1: const sans param et faire les setters
		Patient patient=new Patient();
		patient.setId(null); //parceque il est autoincrement
		patient.setNom("Rim");
		patient.setDateNaissance(new Date());
		patient.setMalade(false);
		patient.setScore(23);

		//solution2 constr avec para
		Patient patient2 =new Patient(null,"Yassine",new Date(),false,123);

		//solution3 utiliser builder
		Patient patient3=Patient.builder()
				.nom("Lina")
				.dateNaissance(new Date())
				.score(56)
				.malade(true)
				.build();*/



	}
}
