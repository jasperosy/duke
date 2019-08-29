import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class addEventCommand extends Command {

    String line;

    public addEventCommand(String line) {
        super();
        this.line = line;
    }
    public void execute(TaskList arr, Ui ui, Storage storage) throws DukeException {
        String linesplit[] = line.split("/at");
        if (linesplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of an event needs a date.");
        }
        String start = linesplit[0].trim();
        String end = linesplit[1].trim();
        if (end.length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The datetime of an event cannot be empty.");
        }
        else if (isTimeStampValid(end)) {
            String pattern = "dd-MM-yyyy HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(end));
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            end = formatter2.format(timestamp.toLocalDateTime());
            Task task = new Event(start, end);
            arr.addTask(task);
            ui.addTaskMessage(task, arr.getSize());
            storage.writeToFile(arr);
        }
        else {
            System.out.println("Time format is wrong! Try again.");
        }
    }
    public boolean isTimeStampValid(String inputString)
    {
        SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm");
        try{
            String str = "";
            format.parse(inputString);
            String pattern = "dd-MM-yyyy HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(inputString));
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            str = formatter2.format(timestamp.toLocalDateTime());
            return true;
        }
        catch(ParseException | DateTimeException e)
        {
            return false;
        }
    }
    public boolean isExit() {
        return false;
    }
}
