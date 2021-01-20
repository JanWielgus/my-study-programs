package lista8;

public class Wrona extends Zwierzak
{
    /**
     * Creates Ptak instance with default name.
     */
    public Wrona()
    {
    }

    /**
     * Create new Wrona instance setting its name.
     * @param imie Name.
     */
    public Wrona(String imie)
    {
        super(imie);
    }

    @Override
    public String pobierzRodzaj()
    {
        return "Wrona";
    }

    @Override
    public String pobierzGlos()
    {
        return "Kra, kra";
    }
}
