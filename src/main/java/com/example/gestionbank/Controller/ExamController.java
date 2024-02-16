package com.example.gestionbank.Controller;

import com.example.gestionbank.Entity.Bank;
import com.example.gestionbank.Entity.Compte;
import com.example.gestionbank.Entity.Transaction;
import com.example.gestionbank.Entity.TypeTransaction;
import com.example.gestionbank.Service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExamController {
    private final IExamService iExamService;

    @PostMapping("/add")
    public Bank ajouterBank(@RequestBody Bank bank) {
        return iExamService.ajouterBank(bank);
    }

    @PostMapping("/add/{agence}")
    public Compte ajouterCompteEtAffecterAAgence(@RequestBody Compte compte, @PathVariable("agence") String agenceBank) {
        return iExamService.ajouterCompteEtAffecterAAgence(compte, agenceBank);
    }

    @PostMapping("/addVirement")
    public String ajouterVirement(@RequestBody Transaction transaction) {
        return iExamService.ajouterVirement(transaction);
    }

    @PutMapping("/Retrait")
    public String ajouterRetrait(@RequestBody Transaction transaction) {
        return iExamService.ajouterRetrait(transaction);
    }

    @PutMapping("/Versement")
    public String ajouterVersement(@RequestBody Transaction transaction) {
        return iExamService.ajouterVersement(transaction);
    }

    @GetMapping("/getAllT/{idbank}")
    public List<Transaction> getAllTransactionByBankId(@PathVariable("idbank") long idBank) {
        return iExamService.getAllTransactionByBankId(idBank);
    }

    @GetMapping("/getAll/{type}/{idcompte}")
    public List<Transaction> getAllTransactionByTypeAndCompte(@PathVariable ("type") TypeTransaction type,@PathVariable ("idcompte") long idCompte) {
        return iExamService.getAllTransactionByTypeAndCompte(type , idCompte);
    }
}

