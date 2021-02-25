package database.entities;

import java.sql.Date;
import java.sql.Time;

public class Flight {
    private int id;
    private String flightNumber;
    private String startAirport;
    private String destAirport;
    private Date startDate;
    private Time startTime;
    private String gate;

    public Flight() {}

    public Flight(String flightNumber, String startAirport, String destAirport, Date startDate, Time startTime, String gate) {
        this.flightNumber = flightNumber;
        this.startAirport = startAirport;
        this.destAirport = destAirport;
        this.startDate = startDate;
        this.startTime = startTime;
        this.gate = gate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public void setDestAirport(String destAirport) {
        this.destAirport = destAirport;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public String getDestAirport() {
        return destAirport;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public String getGate() {
        return gate;
    }

    public String representation() {
        return "[" + id + " - " + flightNumber + " - " + startAirport + " - " + destAirport + " - " + startDate + " - " + startTime + " - " + gate + "]";
    }

    public void print() {
        System.out.println(representation());
    }
}
