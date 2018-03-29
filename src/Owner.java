public class Owner {

    String ownerName;
    String ownerAddress;
    String ownerEmail;
    Long mobileNo;

    public Owner() {

    }

    public Owner(String Name, String Address, String Email, Long mobileNo) {
        this.ownerName = Name;
        this.ownerAddress = Address;
        this.ownerEmail = Email;
        this.mobileNo = mobileNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
