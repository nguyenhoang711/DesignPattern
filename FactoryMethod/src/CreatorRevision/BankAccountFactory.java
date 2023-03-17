package CreatorRevision;

import ProductRevision.BankAccount;
import ProductRevision.BusinessAccount;
import ProductRevision.CheckingAccount;
import ProductRevision.PersonalAccount;

import java.util.ArrayList;
import java.util.List;

public class BankAccountFactory {
    private List<BankAccount> accounts;

    public BankAccountFactory() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String type){
        BankAccount account = null;
        if(type.equals("P")){
            account = new PersonalAccount();
        }
        else if(type.equals("B")){
            account = new BusinessAccount();
        }else if(type.equals("C")){
            account = new CheckingAccount();

        }else{
            System.out.println("Khong hop le!");
        }
        accounts.add(account);
        return account;
    };

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
