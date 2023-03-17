package ProductRevision;

public abstract class BankAccount {
    private String userName;

    private String passWord;

    private long soDuTK;

    public BankAccount() {
        this.soDuTK = 50000;
        registerAccount();
    }


    public boolean validateUserIdentify(String passWord){
        return passWord.equals(this.passWord);
    }

    public float calculateInterestRate(){
        return this.soDuTK*0.55f;
    }

    public abstract void registerAccount();

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getSoDuTK() {
        return this.soDuTK;
    }

    public void setSoDuTK(long soDuTK) {
        this.soDuTK = soDuTK;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "userName='" + userName + '\'' +
                ", soDuTK=" + soDuTK +
                '}';
    }
}
