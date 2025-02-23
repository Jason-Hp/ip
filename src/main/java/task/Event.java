package task;

/**
 * Represent an event task. A <code>Event</code> object that contains all the information required by a basic event and
 *  * handles them.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object.
     *
     * @param description Event's Description.
     * @param from Event's Start
     * @param to Event's End
     */
    public Event(String description,String from,String to){
        super(description + " (from: "+from+" to: "+to+")");
        this.from=from;
        this.to=to;
        this.symbol = "E";
    }

    /**
     * Returns event start.
     *
     * @return Event's Start.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set event start.
     *
     * @param from New Start Data
     */
    public void setFrom(String from) {
        this.from = from;
        setDescription(getDescription() + "(from: "+from+" to: "+to+")");
    }

    /**
     * Returns event end
     *
     * @return Event's End.
     */
    public String getTo() {
        return to;
    }

    /**
     * Set event end.
     *
     * @param to New End Date.
     */
    public void setTo(String to) {
        this.to = to;
        setDescription(getDescription() + "(from: "+from+" to: "+to+")");
    }
}
