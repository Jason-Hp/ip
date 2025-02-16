package task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description,String from,String to){
        super(description + " (from: "+from+" to: "+to+")");
        this.from=from;
        this.to=to;
        this.symbol = "E";
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
        setDescription(getDescription() + "(from: "+from+" to: "+to+")");
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
        setDescription(getDescription() + "(from: "+from+" to: "+to+")");
    }
}
