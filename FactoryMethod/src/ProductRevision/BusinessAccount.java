package ProductRevision;

public class BusinessAccount extends BankAccount{
    @Override
    public void registerAccount() {
        this.setUserName("business1");
        this.setPassWord("123abc");
        System.out.println("Tao thanh cong tai khoan doanh nghiep:\nTen dang nhap: "+ this.getUserName() + "\nSo du tai khoan: " + this.getSoDuTK());
    }
}
