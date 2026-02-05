package database;

import objects.Pet;

public class TestInsert {
    public static void main(String[] args) {
        // Create pet object
        Pet pet = new Pet();
        // Insert into database
        PetDAO dao = new PetDAO();
        dao.insertPet(pet);
    }
}
