public class Task {
    protected String line;
    protected boolean status;
    public Task(String line) {
        this.line = line;
        this.status = false;
    }
    public void setStatus() {
        this.status = true;
    }
    public boolean status() {
        return status;
    }
    public String getStatusIcon() {
        return (status ? "✓" : "✗"); //return tick or X symbols
    }
    public String getLine() {
        return this.line;
    }
}
