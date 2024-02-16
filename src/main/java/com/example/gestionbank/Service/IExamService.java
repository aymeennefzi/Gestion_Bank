package com.example.gestionbank.Service;

import com.example.gestionbank.Entity.Bank;
import com.example.gestionbank.Entity.Compte;
import com.example.gestionbank.Entity.Transaction;
import com.example.gestionbank.Entity.TypeTransaction;

import java.util.List;

public interface IExamService {
    public Bank ajouterBank(Bank bank);
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank);
    public String ajouterVirement(Transaction transaction);
    public String ajouterRetrait(Transaction transaction);
    public String ajouterVersement(Transaction transaction);
    public void getAllTransactionByDate();
    public List<Transaction> getAllTransactionByBankId(long idBank);

    public List<Transaction> getAllTransactionByTypeAndCompte(TypeTransaction type, long idCompte);

}
