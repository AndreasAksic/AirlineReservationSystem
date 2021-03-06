import database.entities.Flight;
import database.entities.Passenger;
import database.entities.Ticket;
import database.mysql.MySQLConnector;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseTester {
    private static MySQLConnector connector;

    @BeforeAll
    static void setUp() {
        SessionFactory factory = new Configuration().configure("test.cfg.xml").buildSessionFactory();
        connector = new MySQLConnector(factory);
    }

    @Test
    @Order(1)
    public void testWriteAndReadForIndependentTable() {
        Passenger passenger = new Passenger("Schmidt", "Stefan", 18, "male");
        Integer passengerId = connector.persistObject(passenger);

        Passenger fromDb = connector.loadForTypeWithId(passenger.getClass(), passengerId);
        assertEquals(passenger.representation(), fromDb.representation());
    }

    @Test
    @Order(2)
    public void testDependentTable() {
        Passenger passenger = new Passenger("Schmidt", "Peter", 18, "male");
        Flight flight = new Flight("LH457", "LAX", "FRA", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "A26");
        Ticket ticket = new Ticket("Y", "14A", passenger, flight);

        Integer passengerId = connector.persistObject(passenger);
        Integer flightId = connector.persistObject(flight);
        Integer ticketId = connector.persistObject(ticket);

        Passenger passengerFromDb = connector.loadForTypeWithId(passenger.getClass(), passengerId);
        Flight flightFromDb = connector.loadForTypeWithId(flight.getClass(), flightId);
        Ticket ticketFromDb = connector.loadForTypeWithId(ticket.getClass(), ticketId);

        assertEquals(passenger.representation(), passengerFromDb.representation());
        assertEquals(flight.representation(), flightFromDb.representation());
        assertEquals(ticket.representation(), ticketFromDb.representation());
    }

    @Test
    @Order(3)
    public void subsequentTableRead() {
        Passenger passenger = new Passenger("Schmidt", "Kuno", 18, "male");
        Flight flight = new Flight("LH187", "ATL", "DUS", new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "A26");
        Ticket ticket = new Ticket("M", "12C", passenger, flight);

        connector.persistObject(passenger);
        connector.persistObject(flight);
        connector.persistObject(ticket);

        List<? extends Passenger> passengerList = connector.loadAllDataForType(passenger.getClass());
        List<? extends Flight> flightList = connector.loadAllDataForType(flight.getClass());
        List<? extends Ticket> ticketList = connector.loadAllDataForType(ticket.getClass());

        for (Object o : passengerList) {
            ((Passenger) o).print();
        }

        for (Object o : flightList) {
            ((Flight) o).print();
        }

        for (Object o : ticketList) {
            ((Ticket) o).print();
        }
    }
}
