package builder;

class Animal {
    public String name;
    public int age;

    @Override
    public String toString() {
        return "builder.Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class AnimalBuilder {
    private Animal animal = new Animal();

    public AnimalBuilder itsName(String name){
        this.animal.name = name;
        return this;
    }

    public AnimalBuilder itsAge(int age){
        this.animal.age = age;
        return this;
    }

    public Animal build() {
        return this.animal;
    }
}

class BuilderCodingExerciseTest {
    public static void main(String[] args) {
        Animal animal = new AnimalBuilder().itsName("Lion").itsAge(10).build();
        System.out.println(animal);
    }
}
