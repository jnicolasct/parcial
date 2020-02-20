package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("moneyservice")
public class MoneyLaunderingServiceStub implements MoneyLaunderingService {
	List<SuspectAccount> cuentas = new ArrayList<SuspectAccount>();
	
    @Override
    public void updateAccountStatus(SuspectAccount suspectAccount) {
    	for (SuspectAccount ac: cuentas) {
    		if (ac.getAccountId() == suspectAccount.getAccountId()) {
    			ac.setAmountOfSmallTransactions(suspectAccount.getAmountOfSmallTransactions());
    		}
    	}
    }

    @Override
    public SuspectAccount getAccountStatus(String accountId) {
    	for (SuspectAccount ac: cuentas) {
    		if (ac.getAccountId() == accountId) {
    			return ac;
    		}
    	}
		return null;
    }

    @Override
    public List<SuspectAccount> getSuspectAccounts() {
        //TODO
        return cuentas;
    }

	@Override
	public void addSuspectAccount(SuspectAccount suspectAccount) {
		boolean flag = false;
		for (SuspectAccount ac: cuentas) {
			if (ac.getAccountId() == suspectAccount.getAccountId()) {
    			flag = true;
    		}
		}
		if (!flag) {
			cuentas.add(suspectAccount);
		}

		
	}
}
