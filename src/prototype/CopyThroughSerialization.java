package prototype;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

class Foo implements Serializable {

    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}

class CopyThroughSerializationTest{
    public static void main(String[] args) {
        Foo life = new Foo(42, "Life");
        Foo life2 = SerializationUtils.roundtrip(life);

        life2.whatever = "Test";

        System.out.println(life);
        System.out.println(life2);

    }
}
