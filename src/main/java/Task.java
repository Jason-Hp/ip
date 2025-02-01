public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.symbol = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String getSymbol() {
        return symbol;
    }
}