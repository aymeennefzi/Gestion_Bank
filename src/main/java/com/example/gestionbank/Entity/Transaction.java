package com.example.gestionbank.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idTransaction ;
    double montant ;
    @Enumerated (value =EnumType.STRING)
    TypeTransaction typeTransaction ;
    @Temporal(TemporalType.DATE)
    LocalDate dateTransaction ;
    @ManyToOne
    Compte expiditeur ;
    @ManyToOne
    Compte destinateur ;
}
