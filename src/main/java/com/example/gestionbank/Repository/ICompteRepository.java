package com.example.gestionbank.Repository;

import com.example.gestionbank.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompteRepository extends JpaRepository<Compte, Long> {
}
