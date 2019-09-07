package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

public class AddToDoCommand extends Command {

    String line;

    public AddToDoCommand(String line) {
        super();
        this.line = line;
    }
    public void execute(TaskList arr, Ui ui, Storage storage) throws DukeException {
        line = line.trim();
        if (line.length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Todo(line);
        arr.addTask(task);
        storage.writeToFile(arr);
        ui.addTaskMessage(task, arr.getSize());
    }
    public boolean isExit() {
        return false;
    }
}

