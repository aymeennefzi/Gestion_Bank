package com.example.gestionbank.Repository;

import com.example.gestionbank.Entity.Transaction;
import com.example.gestionbank.Entity.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateTransaction (LocalDate date) ;

    @Query ("select t from Transaction t join Bank b on t.expiditeur member of b.comptes  where b.idBank= :idbank")
    List<Transaction> getAllTransactionByBankId(@Param("idbank") long idBank);


    List<Transaction> findTransactionByTypeTransactionAndExpiditeur_IdCompte(TypeTransaction type , long idcompte);
}
