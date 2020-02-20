package edu.eci.arsw.exams.moneylaunderingapi;


import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import edu.eci.arsw.exams.moneylaunderingapi.service.MoneyLaunderingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MoneyLaunderingController
{
	 @Autowired
	 @Qualifier("moneyservice")
    MoneyLaunderingService moneyLaunderingService;

    @RequestMapping( value = "/fraud-bank-accounts")
    public List<SuspectAccount> offendingAccounts() {
        return moneyLaunderingService.getSuspectAccounts();
    }
    
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<SuspectAccount> manejadorPostRecursoNuevoSuspectAccount(@RequestBody SuspectAccount acount) {
        try {
            moneyLaunderingService.addSuspectAccount(acount);
            return new ResponseEntity<SuspectAccount>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<SuspectAccount>((SuspectAccount) null, HttpStatus.FORBIDDEN);
        }

    }
    
    

    @RequestMapping(path = "/{accountid}", method = RequestMethod.GET)
    public ResponseEntity<SuspectAccount> manejadorGetRecursoSuspectAccount(@PathVariable("accountid") String accountId) {
        try {
            return new ResponseEntity((SuspectAccount) moneyLaunderingService.getAccountStatus(accountId), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<SuspectAccount>((SuspectAccount) null, HttpStatus.NOT_FOUND);
        }
    }




    @RequestMapping(path = "/{accountid}", method = RequestMethod.PUT)
    public ResponseEntity<SuspectAccount> manejadorPutRecursoAuthorsNombres(@PathVariable("accountid") String accountId, @RequestBody SuspectAccount acount) {
        try {

            moneyLaunderingService.updateAccountStatus(acount);
            return new ResponseEntity<SuspectAccount>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<SuspectAccount>((SuspectAccount) null, HttpStatus.FORBIDDEN);
        }

    }
}
