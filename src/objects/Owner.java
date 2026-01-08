package objects;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Pet> pets;

    // CONSTRUCTOR
    public Owner(String firstName, String lastName, String phoneNumber, List<Pet> pets){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }

    //DEFAULT_CONSTRUCTOR
    public Owner() {
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.phoneNumber = "Unknown";
        this.pets = null;
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

    public void setPhoneNumber(String phoneNumber){
        if (phoneNumber == null) throw new IllegalArgumentException("phoneNumber cannot be null");
        this.phoneNumber = phoneNumber;
    }

    public void setpets(List<Pet> pets){
        if (pets == null || pets.isEmpty()) {
            throw new IllegalArgumentException("Pets list cannot be null or empty");
        }

        for (Pet pet : pets) {
            if (pet == null) {
                throw new IllegalArgumentException("Pets list cannot contain null elements");
            }
        }

        this.pets = new ArrayList<>(pets);
    }

    //GETTERS
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getpets() {
        return pets.toString();
    }

    // TO_STRING
    public String toString() {
        return "objects.Pets{" +
                "first name ='" + firstName + '\'' +
                ", last name ='" + lastName + '\'' +
                ", phone number ='" + phoneNumber + '\'' +
                ", pets ='" + pets.toString() + '\'' +
                '}';
    }

    // ADDITIONAL METHODS
    public void addpets(Pet pet) {
        if (pets != null) {
            pets.add(pet);
        }
    }

    public boolean removepets(Pet pet) {
        return pets.remove(pet);
    }

}
