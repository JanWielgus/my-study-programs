package lista8;

// Jan Wielgus

/**
 * Interfejs zwierzat
 */
public interface Zwierz
{
    /**
     * @return Animal name.
     */
    public String pobierzImie();

    /**
     * @return Animal type.
     */
    public String pobierzRodzaj();

    /**
     * @return Sound of that animal.
     */
    public String pobierzGlos();

    /**
     * @return Base info about the animal.
     * Form: "type" "name" daje glos "sound"!
     */
    public String toString();
}
