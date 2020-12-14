package MainPackage;

// Jan Wielgus

public class Pies extends Zwierzak
{
    /**
     * Creates Pies instance with default name.
     */
    public Pies()
    {
        super();
    }

    /**
     * Create new Pies instance setting its name.
     * @param imie Name.
     */
    public Pies(String imie)
    {
        super(imie);
    }

    @Override
    public String pobierzRodzaj()
    {
        return "Pies"; // Mozna zamiast tego dostac sie do nazwy klasy
    }

    @Override
    public String pobierzGlos()
    {
        return "Waf, waf";
    }
}
