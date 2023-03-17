package CreatorRevision;

import ProductRevision.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private BankAccountFactory factory;

    public Branch(BankAccountFactory factory) {
        this.factory = factory;
    }

    public BankAccount createAccount(String type){
        return this.factory.createAccount(type);

    }

    public BankAccountFactory getFactory() {
        return factory;
    }
}
