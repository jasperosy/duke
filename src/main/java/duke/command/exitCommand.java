package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class exitCommand extends Command {

    public exitCommand() {
        super();
    }
    public void execute(TaskList arr, Ui ui, Storage storage) {
        ui.sayBye();
    }
    public boolean isExit() {
        return true;
    }

}