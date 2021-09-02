import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PersonK {
    private String name;
    private int age;


}

class TestC {
}

class TestCTest {

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }

    public static void main(String[] args) {
        Object person = new PersonK();
        Field[] fields = person.getClass().getDeclaredFields();

        List<String> actualFieldNames = getFieldNames(fields);
        System.out.println(Arrays.asList("name", "age")
                .containsAll(actualFieldNames));

    }
}
