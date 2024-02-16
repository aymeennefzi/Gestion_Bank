package com.example.gestionbank.Repository;

import com.example.gestionbank.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankRepository extends JpaRepository<Bank, Long> {

    Bank findByAgence (String agenceBank) ;
}
