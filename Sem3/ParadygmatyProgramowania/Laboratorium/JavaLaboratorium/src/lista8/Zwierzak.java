package lista8;

// Jan Wielgus

public abstract class Zwierzak implements Zwierz
{
    private String imie;

    /**
     * Sets default name for the animal.
     */
    public Zwierzak()
    {
        this("Bez imienia");
    }

    /**
     * Set name for the animal.
     * @param imie Name.
     */
    public Zwierzak(String imie)
    {
        this.imie = imie;
    }

    @Override
    public String pobierzImie()
    {
        return imie;
    }

    @Override
    public String toString()
    {
        return pobierzRodzaj() + " " + pobierzImie() + " daje glos " + pobierzGlos() + "!";
    }
}
