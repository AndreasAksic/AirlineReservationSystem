package database.entities;

public class Ticket {
    private int id;
    private String classCode;
    private String seat;

    private Passenger passenger;
    private Flight flight;

    public Ticket() {}

    public Ticket(String classCode, String seat, Passenger passenger, Flight flight) {
        this.classCode = classCode;
        this.seat = seat;
        this.passenger = passenger;
        this.flight = flight;
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

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    public Flight getFlight() {
        return flight;
    }

    public void print() {
        System.out.println(this.representation());
    }

    public String representation() {
        return "[" + id + " - " + classCode + " - " + seat + " - " + passenger.getId() + " - " + flight.getId() + "]";
    }
}
