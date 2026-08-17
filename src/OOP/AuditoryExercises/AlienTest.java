package OOP.AuditoryExercises;

class Alien {
    public int alienHealth;
    public String alienName;

    public Alien(int alienHealth, String alienName) {
        this.alienHealth = alienHealth;
        this.alienName = alienName;
    }

    public int alienDamage()
    {
        return 0;
    }
}

class AlienSnake extends Alien {

    public AlienSnake(int alienHealth, String alienName) {
        super(alienHealth, alienName);
    }

    @Override
    public int alienDamage() {
        return 10;
    }
}

class AlienOgre extends Alien {

    public AlienOgre(int alienHealth, String alienName) {
        super(alienHealth, alienName);
    }

    @Override
    public int alienDamage() {
        return 6;
    }
}

class AlienMarshmallow extends Alien {

    public AlienMarshmallow(int alienHealth, String alienName) {
        super(alienHealth, alienName);
    }

    @Override
    public int alienDamage() {
        return 1;
    }
}

class AlienPack {
    private Alien[] aliens;

    public AlienPack(Alien[] aliens) {
        this.aliens = aliens;
    }

    public Alien[] getAliens() {
        return aliens;
    }

    public int calculateDamage() {
        int damage = 0;
        for(Alien a : aliens)
        {
            damage += a.alienDamage();
        }
        return damage;
    }
}

public class AlienTest {
    public static void main(String[] args) {

        Alien a1 = new AlienSnake(100, "Snake1");
        Alien a2 = new AlienSnake(80, "Snake2");
        Alien a3 = new AlienOgre(100, "Ogre");
        Alien a4 = new AlienMarshmallow(50, "Marshmallow1");
        Alien a5 = new AlienMarshmallow(70, "Marshmallow2");

        Alien[] aliens = {a1, a2, a3, a4, a5};

        AlienPack pack = new AlienPack(aliens);

        System.out.println("Total damage: " + pack.calculateDamage());
    }
}