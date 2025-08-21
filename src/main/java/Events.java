public class Events extends Task{
    private final String fromDate;
    private final String toDate;

    public Events(String description, String date) {
        super(description);
        String[] dates = date.split("/to", 2);
        this.fromDate = dates[0].trim();
        this.toDate = dates[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }
}
