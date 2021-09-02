package prototype;

import java.util.Arrays;

class Address implements Cloneable
{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    //deep copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}

class PPerson implements Cloneable
{
    public String [] names;
    public Address address;

    public PPerson(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "PPerson{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new PPerson(names.clone(), (Address) address.clone());
    }
}

class PrototypePatternTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        PPerson john = new PPerson(new String[] {"John", "Smith"}, new Address("Long Road", 123));
        PPerson jane = (PPerson) john.clone();
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;
        System.out.println(john);
        System.out.println(jane);
    }
}
