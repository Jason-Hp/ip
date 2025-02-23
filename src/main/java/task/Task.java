package task;

/**
 * Represent a task. A <code>Task</code> object contains all the information required by a basic task and
 * handles them.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.symbol = "";
    }

    /**
     * Returns task's description.
     *
     * @return Task's Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set task's description.
     *
     * @param description Updated Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the status of the Task.
     *
     * @return Task's Status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns task status icon.
     *
     * @return Task's Status Icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmark Task as done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Return task symbol.
     *
     * @return Task's Symbol.
     */
    public String getSymbol() {
        return symbol;
    }
}