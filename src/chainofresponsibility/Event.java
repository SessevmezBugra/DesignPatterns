package chainofresponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<Args> {

    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler)
    {
        int i = index;
        System.out.println(i);
        handlers.put(index++, handler);
        System.out.println(i);
        return i;
    }

    public void unsubscribe(int key)
    {
        handlers.remove(key);
    }

    public void fire(Args args)
    {
        for (Consumer<Args> handler : handlers.values())
            handler.accept(args);
    }
}

class Query
{
    public String creatureName;
    enum Argument
    {
        ATTACK, DEFENSE
    }

    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game
{
    public Event<Query> queries = new Event<>();
}

class EventCreature
{
    private Game game;
    public String name;
    private int baseAttack, baseDefense;

    public EventCreature(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack() {
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense() {
        Query q = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", baseAttack=" + getAttack() +
                ", baseDefense=" + getDefense() +
                '}';
    }
}

class EventCreatureModifier
{
    protected Game game;
    protected EventCreature creature;

    public EventCreatureModifier(Game game, EventCreature creature)
    {
        this.game = game;
        this.creature = creature;
    }
}

class EventIncreaseDefenseModifier extends EventCreatureModifier {

    public EventIncreaseDefenseModifier(Game game, EventCreature creature) {
        super(game, creature);
        game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature.name)
                    && q.argument == Query.Argument.DEFENSE)
            {
                q.result += 3;
            }
        });
    }
}

class EventDoubleAttackModifier extends EventCreatureModifier implements AutoCloseable
{
    private final int token;

    public EventDoubleAttackModifier(Game game, EventCreature creature) {
        super(game, creature);
        token = game.queries.subscribe(q -> {
            if(q.creatureName.equals(creature.name)
                    && q.argument == Query.Argument.ATTACK)
            {
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() throws Exception {
        game.queries.unsubscribe(token);
    }
}

class DemoEvent{
    public static void main(String[] args) {
        Game game = new Game();
        EventCreature goblin = new EventCreature(game, "Strong Goblin", 2, 2);
        System.out.println(goblin);

        EventIncreaseDefenseModifier icm = new EventIncreaseDefenseModifier(game, goblin);
        EventDoubleAttackModifier dam = new EventDoubleAttackModifier(game, goblin);
        try(dam)
        {
            System.out.println(goblin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(goblin);
    }
}