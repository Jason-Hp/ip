package task;

/**
 * Represent a deadline task. A <code>Deadline</code> object that contains all the information required by a basic deadline and
 *  * handles them.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Deadline's Description.
     * @param by Deadline's Deadline Date.
     */
    public Deadline(String description, String by){
        super(description + " (by: "+by+")");
        this.by = by;
        this.symbol = "D";
    }


    /**
     * Set deadline's deadline date.
     *
     * @param by New Deadline Date.
     */
    public void setBy(String by) {
        this.by = by;
        setDescription(getDescription() + "(by: "+by+")");
    }

    /**
     * Return deadline's deadline date.
     *
     * @return Deadline Date.
     */
    public String getBy() {
        return by;
    }
}
