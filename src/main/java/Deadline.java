public class Deadline extends Task{

    protected String by;
    public Deadline(String description, String by){
        super(description + " (by: "+by+")");
        this.by = by;
        this.symbol = "D";
    }

    public void setBy(String by) {
        this.by = by;
        setDescription(getDescription() + "(by: "+by+")");
    }

    public String getBy() {
        return by;
    }
}
