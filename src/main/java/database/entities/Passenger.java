package database.entities;

public class Passenger {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private String gender;

    public Passenger() {}

    public Passenger(String lastName, String firstName, int age, String gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void print() {
        System.out.println(this.representation());
    }

    public String representation() {
        return "[" + id + " - " + lastName + " - " + firstName + " - " + age + " - " + gender + "]";
    }
}
