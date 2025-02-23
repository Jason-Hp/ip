package task;

/**
 * Represent a todo task. A <code>Todo</code> object that contains all the information required by a basic todo and
 *  * handles them.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object.
     *
     * @param description Description Of Todo.
     */
    public Todo(String description){
        super(description);
        this.symbol = "T";
    }
}
