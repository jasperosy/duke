package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;

public class deleteCommand extends Command {

    int index;

    public deleteCommand(int index) {
        super();
        this.index = index;
    }
    public void execute(TaskList arr, Ui ui, Storage storage) throws DukeException {
        if (index >= arr.getSize() || index < 0) {
            throw new DukeException("\u2639 OOPS!!! Invalid number!");
        }
        else {
            Task task = arr.getTask(index);
            arr.deleteTask(index);
            ui.deleteMessage(task, arr.getSize());
            storage.writeToFile(arr);
        }
    }
    public boolean isExit() {
        return false;
    }
}
