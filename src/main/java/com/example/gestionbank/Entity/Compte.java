package com.example.gestionbank.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Compte implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idCompte ;
    @Enumerated(value = EnumType.STRING)
    TypeCompte typeCompte ;
    long code ;
    double solde ;
}
