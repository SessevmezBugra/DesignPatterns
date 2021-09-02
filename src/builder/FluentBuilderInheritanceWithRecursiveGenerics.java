package builder;

class Human {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "builder.Human{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class HumanBuilder<SELF extends HumanBuilder<SELF>> {
    protected Human human = new Human();

    public SELF withName(String name) {
        human.name = name;
        return (SELF) this;
    }

    public Human build() {
        return human;
    }

    protected SELF self() {
        return (SELF) this;
    }

}

class EmployeeBuilder extends HumanBuilder<EmployeeBuilder> {
    public EmployeeBuilder worksAt(String position) {
        human.position = position;
        return this;
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

class FluentBuilderInheritanceWithRecursiveGenericsTest {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder();
        Human hilal = eb.withName("Hilal").worksAt("PDR").build();
        System.out.println(hilal);

    }
}
