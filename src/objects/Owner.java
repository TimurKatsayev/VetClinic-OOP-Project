package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Owner extends Person {
    private String petName;

    // CONSTRUCTOR
    public Owner(int personId, String firstName, String lastName, String phone, int age, String petName){
        super(personId, firstName, lastName, phone, age);
        this.petName = petName;
    }

    //DEFAULT_CONSTRUCTOR
    public Owner() {
        this.petName = null;
    }

    // SETTERS

    public void setPetName(String petName){
        if (petName == null) {
            throw new IllegalArgumentException("Pets list cannot be null or empty");
        }

        this.petName = petName;
    }

    //GETTERS

    public String getPetName() {
        return petName;
    }

    // ADDITIONAL METHODS
    public void is_owner_of(String pet_name) {
        if (Objects.equals(pet_name, petName)) {
            System.out.println("Yes");
        } else{
            System.out.println("No");
        }
    }

    @Override
    public void work() {
        System.out.println(getFullName() + " is an owner and takes care of " + petName + " pet.");
    }

    @Override
    public void printCard() {
        System.out.println("Role: Owner");
        System.out.println("Name: " + getFullName());
        System.out.println("Phone: " + phone);
        System.out.println("Pets name: " + petName);
    }

    @Override
    public String toString() {
        return "OwnerPerson{" + getFullName() + ", pets name =" + petName + "}";
    }

}
