package builder;

class FPerson {
    public String streetAddress, postcode, city;

    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "FacetedPerson{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class FPersonBuilder {
    protected FPerson person = new FPerson();

    public PersonAddressBuilder lives()
    {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works()
    {
        return new PersonJobBuilder(person);
    }
    public FPerson build() {
        return person;
    }
}

class PersonAddressBuilder extends FPersonBuilder {
    public PersonAddressBuilder(FPerson person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
    }
}

class PersonJobBuilder extends FPersonBuilder
{
    public PersonJobBuilder(FPerson person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName){
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position){
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome){
        person.annualIncome = annualIncome;
        return this;
    }
}
class FacetedBuilderTest {
    public static void main(String[] args) {
        FPersonBuilder pb = new FPersonBuilder();
        FPerson person = pb.lives()
                .at("123 London Road")
                .in("London")
                .withPostcode("SW12BC")
                .works()
                .at("Fabrikam")
                .asA("Engineer")
                .build();
        System.out.println(person);

    }
}
