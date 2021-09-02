package singleton;

import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

interface Database
{
    int getPopulation(String name);
}



public class SingletonDatabase implements Database{

    private Dictionary<String, Integer> capitals
            = new Hashtable<>();

    private static int instanceCount = 0;
    public static int getCount() { return instanceCount; }

    private SingletonDatabase() {
        instanceCount ++;
        System.out.println("Initializing database");

        try {

//            File f = new File(
//                    Tests.class.getProtectionDomain()
//                            .getCodeSource().getLocation().getPath()
//            );
//            Path fullPath = Paths.get(f.getPath(), "capitals.txt");
//            List<String> lines = Files.readAllLines(fullPath);

            List<String> lines = Files.readAllLines(Paths.get("./out/production/Design Patterns/capitals.txt"));

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(kv.get(0).trim(),
                            Integer.parseInt(kv.get(1))
                    ));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();
    public static SingletonDatabase getInstance()
    {
        return INSTANCE;
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }

}


class SingletonRecordFinder
{
    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for (String name : names) {
            result += SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}

class ConfigurableRecordFinder
{
    private final Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names) {
            result += database.getPopulation(name);
        }
        return result;
    }

}

class DummyDatabase implements Database
{
    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

class Tests
{

    @Test
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(17500000+17400000, tp);
    }

    @Test
    public void dependentPopulationTest() {
        Database dummyDatabase = new DummyDatabase();
        ConfigurableRecordFinder configurableRecordFinder = new ConfigurableRecordFinder(dummyDatabase);
        assertEquals(4, configurableRecordFinder.getTotalPopulation(
                List.of("alpha", "gamma")
        ));
    }


}