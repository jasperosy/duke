package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private Storage storage;
    private TaskList arr;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            arr = new TaskList(storage.readFromFile());
        }
        catch (DukeException exception) {
            ui.showLoadingError(exception);
            arr = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        ui.hello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = ui.scanLine();
                Command command = chooseCommand.choose(line);
                command.execute(arr, ui, storage);
                isExit = command.isExit();
            }
            catch (DukeException exception) {
                ui.showLoadingError(exception);
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}