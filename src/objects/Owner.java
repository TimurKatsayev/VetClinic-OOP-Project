package objects;

import java.util.ArrayList;
import java.util.List;

public class Owner extends Person {
    private List<Pet> pets;

    // CONSTRUCTOR
    public Owner(String firstName, String lastName, String phone, int age, List<Pet> pets){
        super(firstName, lastName, phone, age);
        this.pets = pets;
    }

    //DEFAULT_CONSTRUCTOR
    public Owner() {
        this.pets = null;
    }

    // SETTERS

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

    public String getpets() {
        return pets.toString();
    }

    // ADDITIONAL METHODS
    public void addpets(Pet pet) {
        if (pets != null) {
            pets.add(pet);
        }
    }

    @Override
    public void work() {
        System.out.println(getFullName() + " is an owner and takes care of " + pets.toArray().length + " pet(s).");
    }

    @Override
    public void printCard() {
        super.printCard();
        System.out.println("Role: Owner");
        System.out.println("Name: " + getFullName());
        System.out.println("Phone: " + phone);
        System.out.println("Pets count: " + pets.toArray().length);
    }

    @Override
    public String toString() {
        return "OwnerPerson{" + getFullName() + ", petsCount=" + getpets().length() + "}";
    }

    public boolean removepets(Pet pet) {
        return pets.remove(pet);
    }

}
