package com.example.travp3;

import com.example.travp3.entities.Patient;
import com.example.travp3.repository.PatientRepository;
import com.example.travp3.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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
		patientRepository.save(new Patient(null,"Yassine",new Date(),false,123));
		patientRepository.save(new Patient(null,"Rima",new Date(),false,341));
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



//@Bean  pour jdbc authentication
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder=passwordEncoder();
		return args -> {
			//une fois je cree un user je declare un objet user
			UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
			if(u1==null)
			  jdbcUserDetailsManager.createUser(
					User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
			);
			UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");
			if(u2==null)
			  jdbcUserDetailsManager.createUser(
					User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
			);
			UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("admin2");
			if(u3==null)
			  jdbcUserDetailsManager.createUser(
					User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
			);

		};
	}
	@Bean  //si jai executer ca une fois dons les users seront cree donc apres je dois commenter bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		return args -> {
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");
			accountService.addNewUser("user1","1234","user1@gmail.com","1234");
			accountService.addNewUser("user2","1234","user2@gmail.com","1234");
			accountService.addNewUser("admin","1234","admin@gmail.com","1234");

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("admin","ADMIN");



		};
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
