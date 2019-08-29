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