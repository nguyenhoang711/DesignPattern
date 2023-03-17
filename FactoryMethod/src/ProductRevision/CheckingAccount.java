package ProductRevision;

public class CheckingAccount extends BankAccount{
    @Override
    public void registerAccount() {
        this.setUserName("checking1");
        this.setPassWord("123abc");
        System.out.println("Tao thanh cong tai khoan kiem thu:\nTen dang nhap: "+ this.getUserName() + "\nSo du tai khoan: " + this.getSoDuTK());
    }
}
