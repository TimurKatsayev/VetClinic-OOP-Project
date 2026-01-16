package objects;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected int age;

    public Person(String firstName, String lastName, String phone, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
    }

    public Person() {
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.phone = "Unknown";
        this.age = 0;
    }

    // SETTERS
    public void setFirstName(String firstName){
        if (firstName == null) throw new IllegalArgumentException("firstName cannot be null");
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        if (lastName == null) throw new IllegalArgumentException("lastName cannot be null");
        this.lastName = lastName;
    }

    public void setPhone(String phone){
        if (phone == null) throw new IllegalArgumentException("phone cannot be null");
        this.phone = phone;
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }

    // "Action" method (polymorphic target)
    public abstract void work();

    // Common method used by menu view
    public abstract void printCard();

    @Override
    public String toString() {
        return "Person{" + getFullName() + ", phone=" + phone + ", age=" + age + "}";
    }
}

