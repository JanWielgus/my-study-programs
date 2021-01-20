package lista8;

// Jan Wielgus

public class Ptak extends Zwierzak
{
    /**
     * Creates Ptak instance with default name.
     */
    public Ptak()
    {
        super();
    }

    /**
     * Create new Ptak instance setting its name.
     * @param imie Name.
     */
    public Ptak(String imie)
    {
        super(imie);
    }

    @Override
    public String pobierzRodzaj()
    {
        return "Ptak";
    }

    @Override
    public String pobierzGlos()
    {
        return "Ćwir, ćwir";
    }
}
