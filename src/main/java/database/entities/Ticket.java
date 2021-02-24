package database.entities;

public class Ticket {
    private int id;
    private String classCode;
    private String seat;

    private Passenger passenger;

    public Ticket() {}

    public Ticket(String classCode, String seat, Passenger passenger) {
        this.classCode = classCode;
        this.seat = seat;
        this.passenger = passenger;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getId() {
        return id;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getSeat() {
        return seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void print() {
        System.out.println(this.representation());
    }

    public String representation() {
        return "[" + id + " - " + classCode + " - " + seat + " - " + passenger.getId() + "]";
    }
}
