package ProductRevision;

public class PersonalAccount extends BankAccount{
    @Override
    public void registerAccount() {
        this.setUserName("personal1");
        this.setPassWord("123abc");
        System.out.println("Tao thanh cong tai khoan ca nhan:\nTen dang nhap: "+ this.getUserName() + "\nSo du tai khoan: " + this.getSoDuTK());
    }
}
