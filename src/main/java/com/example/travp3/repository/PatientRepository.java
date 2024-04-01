package com.example.travp3.repository;

import com.example.travp3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//spring data
public interface PatientRepository extends JpaRepository<Patient,Long> {
    //pour faire la pagination il faut une methode qui retourne une page solu1
    Page<Patient>findByNomContains(String keyword, Pageable pageable); //je peux transf le num page et size
    //solution2 @ et faire qlqsoit un nom
    @Query("select p from Patient p where p.nom like :x")
    Page<Patient> chercher(@Param("x") String keyword, Pageable pageable);
}
