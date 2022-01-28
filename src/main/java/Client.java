public class Client {
    private int id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String adress;
    private String apartamentNumber;

    public Client(int id, String name, String lastName, String phoneNumber, String adress, String apartamentNumber) {
        setId(id);
        setName(name);
        setLastName(lastName);
        setAdress(adress);
        setApartamentNumber(apartamentNumber);
        setPhoneNumber(phoneNumber);
    }

    public Client(String name, String lastName, String phoneNumber, String adress, String apartamentNumber) {
        setName(name);
        setLastName(lastName);
        setAdress(adress);
        setApartamentNumber(apartamentNumber);
        setPhoneNumber(phoneNumber);
    }

    public Client() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getApartamentNumber() {
        return apartamentNumber;
    }

    public void setApartamentNumber(String apartamentNumber) {
        this.apartamentNumber = apartamentNumber;
    }

    @Override
    public String toString() {
        return  (getId() == 0? "id = '0'": "") + "Imie ='" + name + '\'' +
                ", Nazwisko = '" + lastName + '\'' +
                ", Numer telefonu = '" + phoneNumber + '\'' +
                ", Ulica ='" + adress + '\'' +
                ", Nr domu/mieszkania='" + apartamentNumber + '\'';
    }
}
