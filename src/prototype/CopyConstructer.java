package prototype;


class CAddress{

    public String streetAddress, city, country;
    public CAddress(String streetAddress, String city, String country)
    {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public CAddress(CAddress other){
        this(other.streetAddress, other.city, other.country);
    }
    @Override
    public String toString() {
        return "CAddress{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
class CEmployee {
    public String name;
    public CAddress address;

    public CEmployee(String name, CAddress address) {
        this.name = name;
        this.address = address;
    }

    public CEmployee(CEmployee other) {
        name = other.name;
        address = new CAddress(other.address);
    }

    @Override
    public String toString() {
        return "CEmployee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}


class CopyConstructerTest {
    public static void main(String[] args) {
        CEmployee john = new CEmployee("John",
                new CAddress("123 London Road", "London", "UK"));
        CEmployee chris = new CEmployee(john);
        chris.name = "Chris";

        System.out.println(john);
        System.out.println(chris);
    }
}
