package com.example.gestionbank.Service;

import com.example.gestionbank.Entity.*;
import com.example.gestionbank.Repository.IBankRepository;
import com.example.gestionbank.Repository.ICompteRepository;
import com.example.gestionbank.Repository.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImplements implements IExamService {
    private final IBankRepository iBankRepository;
    private final ICompteRepository iCompteRepository;
    private final ITransactionRepository iTransactionRepository;


    @Override
    public Bank ajouterBank(Bank bank) {
        return iBankRepository.save(bank);
    }

    @Override
    @Transactional
    public Compte ajouterCompteEtAffecterAAgence(Compte compte, String agenceBank) {
        Bank bank = iBankRepository.findByAgence(agenceBank);
        Compte compte1 = iCompteRepository.save(compte);
        if (bank.getComptes() == null) {
            Set<Compte> comptes = new HashSet<>();
            comptes.add(compte1);
            bank.setComptes((comptes));
        } else {
            bank.getComptes().add(compte1);
        }
        return compte1;
    }

//    @Override
//    @Transactional
//    public String ajouterVirement(Transaction transaction) {
//        Compte destinateur = iCompteRepository.findById(transaction.getDestinateur().getIdCompte()).orElseThrow(null);
//        Compte expiditeur = iCompteRepository.findById(transaction.getExpiditeur().getIdCompte()).orElseThrow(null);
//        if (transaction.getTypeTransaction() == TypeTransaction.Virement && expiditeur.getTypeCompte() == TypeCompte.Epargne) {
//            return ("On ne peut pas faire un virment a partir d'un compte epargne");
//        } else if (transaction.getTypeTransaction() == TypeTransaction.Virement && expiditeur.getTypeCompte() == TypeCompte.Courant && expiditeur.getSolde() < transaction.getMontant() + 3) {
//            return ("on ne peut pas faire un virment : Solde insuffisant");
//
//        } else {
//            destinateur.setSolde(expiditeur.getSolde() - transaction.getMontant());
//            expiditeur.setSolde(destinateur.getSolde() + transaction.getMontant());
//            transaction.setDateTransaction(LocalDate.now());
//            iTransactionRepository.save(transaction);
//            return ("VIREMENT de " + transaction.getMontant() + "de compte " + expiditeur.getIdCompte() + "vers le compte " + destinateur.getIdCompte() + "approuvé avec succès.");
//        }
//    }

//    @Override
//    @Transactional
//    public String ajouterRetrait(Transaction transaction) {
//        Compte expiditeur = iCompteRepository.findById(transaction.getExpiditeur().getIdCompte()).orElseThrow(null);
//        if (transaction.getTypeTransaction() == TypeTransaction.Retrait && expiditeur.getSolde() < transaction.getMontant() + 2) {
//            return ("Solde insuffisant");
//        } else {
//            expiditeur.setSolde(expiditeur.getSolde() - transaction.getMontant());
//            transaction.setDateTransaction(LocalDate.now());
//            iTransactionRepository.save(transaction);
//            return ("Retrait de " + transaction.getMontant() + "de compte " + expiditeur.getIdCompte() + "approuvé avec succès.");
//        }
//    }

//    @Override
//    @Transactional
//    public String ajouterVersement(Transaction transaction) {
//        Compte destinateur = iCompteRepository.findById(transaction.getDestinateur().getIdCompte()).orElseThrow(null);
//        if (transaction.getTypeTransaction() == TypeTransaction.Versement && destinateur.getTypeCompte() != TypeCompte.Epargne) {
//            destinateur.setSolde(transaction.getMontant() - 2);
//            iCompteRepository.save(destinateur);
//            return ("versement de " + transaction.getMontant() + "vers compte  " + destinateur.getIdCompte() + "approuvé avec succès.");
//        } else {
//            return ("On ne peut pas affecté ce versement");
//        }
//
//    }

    @Scheduled(cron = "*/30 * * * * *")
    @Override
    public void getAllTransactionByDate() {
        List<Transaction> transactions = iTransactionRepository.findByDateTransaction(LocalDate.of(2024, 01, 04));
        for (Transaction transaction : transactions) {
            log.info("les transaction d'aujourd'hui : " + transaction.getIdTransaction());
        }
    }

    @Override
    @Transactional
    public String ajouterVirement(Transaction transaction) {
        Compte expiditeur = iCompteRepository.findById(transaction.getExpiditeur().getIdCompte()).orElseThrow(null);
        Compte destinateur = iCompteRepository.findById(transaction.getDestinateur().getIdCompte()).orElseThrow(null);
        if (transaction.getTypeTransaction() == TypeTransaction.Virement && expiditeur.getTypeCompte() == TypeCompte.Epargne) {
            return ("On ne peut pas faire un virement à partir d’un compte épargne");
        } else if (transaction.getTypeTransaction() == TypeTransaction.Virement && expiditeur.getTypeCompte() != TypeCompte.Epargne && expiditeur.getSolde() < transaction.getMontant() + 3) {
            return ("On ne peut pas faire un virement : Solde insuffisant");
        } else {
            expiditeur.setSolde(expiditeur.getSolde() - transaction.getMontant());
            destinateur.setSolde(destinateur.getSolde() + transaction.getMontant());
            transaction.setDateTransaction(LocalDate.now());
            iTransactionRepository.save(transaction);
            return ("virmenet de " + transaction.getMontant() + "de compte " + expiditeur.getIdCompte() + "vers" + destinateur.getIdCompte() + "approuvé avec sucees");

        }
    }

    @Override
    @Transactional
    public String ajouterRetrait(Transaction transaction) {
        Compte expiditeur = iCompteRepository.findById(transaction.getExpiditeur().getIdCompte()).orElseThrow(null);
        if (transaction.getTypeTransaction() == TypeTransaction.Retrait && expiditeur.getSolde() < transaction.getMontant() + 2) {
            return ("On ne peut pas faire un retrait");
        } else  {
            expiditeur.setSolde(expiditeur.getSolde() - transaction.getMontant() );
            transaction.setDateTransaction(LocalDate.now());
            iTransactionRepository.save(transaction);
            return ("RETRAIT de" + transaction.getMontant()+ " de compte "+ expiditeur.getIdCompte() +" approuvé avec succès.");
        }
    }

    @Override
    @Transactional
    public String ajouterVersement(Transaction transaction){
        Compte destinateur = iCompteRepository.findById(transaction.getDestinateur().getIdCompte()).orElseThrow(null);
        if (transaction.getTypeTransaction() == TypeTransaction.Versement && destinateur.getTypeCompte() != TypeCompte.Epargne ){
            destinateur.setSolde(transaction.getMontant() - 2 );
            iTransactionRepository.save(transaction);
            return ("Versement de " + transaction.getMontant() +  " vers  compte " + destinateur.getIdCompte() + "approuvé avec succès.");
        }else {
            return ("On ne peut pas effectué ce virment ");
        }
    }

    @Override
    public List<Transaction> getAllTransactionByBankId(long idBank) {
        return iTransactionRepository.getAllTransactionByBankId(idBank);
    }

    @Override
    public List<Transaction> getAllTransactionByTypeAndCompte(TypeTransaction type, long idCompte) {
        return iTransactionRepository.findTransactionByTypeTransactionAndExpiditeur_IdCompte(type , idCompte);
    }
}
