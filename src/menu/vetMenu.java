package menu;

import objects.*;
import database.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * vetMenu - Week 8
 * FULLY DATABASE-DRIVEN
 */
public class vetMenu implements Menu{
    private Scanner scanner;
    private PetDAO petDAO;
    private PersonDAO personDAO;

    public vetMenu() {
        this.scanner = new Scanner(System.in);
        this.petDAO = new PetDAO();
        this.personDAO = new PersonDAO();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  VET MANAGEMENT SYSTEM v2.0            ║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MAIN MENU - Week 8            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─ CLINIC MANAGEMENT ────────────────────┐");
        System.out.println("│ 1. Add Pet                            │");
        System.out.println("│ 2. Add Owner                          │");
        System.out.println("│ 3. Add Veterinarian                   │");
        System.out.println("│ 4. View All Pets                      │");
        System.out.println("│ 5. View All Persons                   │");
        System.out.println("│ 6. View Owners Only                   │");
        System.out.println("│ 7. View Veterinarians Only            │");
        System.out.println("│ 8. Update Person                      │");
        System.out.println("│ 9. Delete Person                      │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 10. Search Person by Name             │");
        System.out.println("│ 11. Search by Age Range               │");
        System.out.println("│ 12. View Experienced Vets (5+ yrs)    │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│ 13. Polymorphism Demo                 │");
        System.out.println("│ 0. Exit                               │");
        System.out.println("└────────────────────────────────────────┘");
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("\n👉 Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1: addPet(); break;
                    case 2: addOwner(); break;
                    case 3: addVeterinarian(); break;
                    case 4: viewAllPets(); break;
                    case 5: viewAllPersons(); break;
                    case 6: viewOwners(); break;
                    case 7: viewVeterinarians(); break;
                    case 8: updatePerson(); break;
                    case 9: deletePerson(); break;
                    case 10: searchByName(); break;
                    case 11: searchByAgeRange(); break;
                    case 12: viewExperiencedVets(); break;
                    case 13: demonstratePolymorphism(); break;
                    case 0:
                        running = false;
                        System.out.println("\n👋 Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice! 0-13.");
                }
                if (choice != 0) pressEnterToContinue();
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Enter a valid number!");
                scanner.nextLine();
            }
        }
    }

    // ========================================
    // CREATE OPERATIONS
    // ========================================

    private void addPet() {
        try {
            System.out.println("\n┌─ ADD PET ─────────────────────────────┐");
            System.out.print("│ Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("│ Name: "); String name = scanner.nextLine();
            System.out.print("│ Type (Dog/Cat...): "); String type = scanner.nextLine();
            System.out.print("│ Age: "); int age = scanner.nextInt(); scanner.nextLine();
            System.out.print("│ Gender (M/F): "); char gender = scanner.nextLine().charAt(0);
            System.out.print("│ Weight: "); float weight = scanner.nextFloat(); scanner.nextLine();
            System.out.print("│ Condition: "); String cond = scanner.nextLine();

            petDAO.insertPet(new Pet(id, name, type, age, gender, weight, cond));
        } catch (Exception e) { System.out.println("❌ Error: " + e.getMessage()); }
    }

    private void addOwner() {
        try {
            System.out.println("\n┌─ ADD OWNER ───────────────────────────┐");
            System.out.print("│ Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("│ First Name: "); String fName = scanner.nextLine();
            System.out.print("│ Last Name: "); String lName = scanner.nextLine();
            System.out.print("│ Phone: "); String phone = scanner.nextLine();
            System.out.print("│ Age: "); int age = scanner.nextInt(); scanner.nextLine();
            System.out.print("│ Pet Name: "); String petName = scanner.nextLine();

            personDAO.insertOwner(new Owner(id, fName, lName, phone, age, petName));
        } catch (Exception e) { System.out.println("❌ Error: " + e.getMessage()); }
    }

    private void addVeterinarian() {
        try {
            System.out.println("\n┌─ ADD VETERINARIAN ────────────────────┐");
            System.out.print("│ Enter ID: "); int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("│ First Name: "); String fName = scanner.nextLine();
            System.out.print("│ Last Name: "); String lName = scanner.nextLine();
            System.out.print("│ Specialization: "); String spec = scanner.nextLine();
            System.out.print("│ Experience (Years): "); int exp = scanner.nextInt(); scanner.nextLine();
            System.out.print("│ Available (true/false): "); boolean avail = scanner.nextBoolean(); scanner.nextLine();

            personDAO.insertVeterinarian(new Veterinarian(id, fName, lName, "N/A", 30, spec, exp, avail));
        } catch (Exception e) { System.out.println("❌ Error: " + e.getMessage()); }
    }

    // ========================================
    // READ OPERATIONS
    // ========================================

    private void viewAllPets() {
        List<Pet> pets = petDAO.getAllPets();
        System.out.println("\n🐾 --- REGISTERED PETS ---");
        pets.forEach(System.out::println);
    }

    private void viewAllPersons() {
        personDAO.displayAllPeople(); // Используем метод из DAO
    }

    private void viewOwners() {
        List<Person> people = personDAO.getAllPeople();
        System.out.println("\n🏠 --- OWNERS LIST ---");
        for (Person p : people) {
            if (p instanceof Owner) System.out.println(p);
        }
    }

    private void viewVeterinarians() {
        List<Person> people = personDAO.getAllPeople();
        System.out.println("\n🩺 --- VETERINARIANS LIST ---");
        for (Person p : people) {
            if (p instanceof Veterinarian) System.out.println(p);
        }
    }

    // ========================================
    // UPDATE & DELETE
    // ========================================

    private void updatePerson() {
        System.out.print("\n👉 Enter Person ID to update: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Person existing = personDAO.getPersonById(id);

        if (existing == null) {
            System.out.println("❌ Person not found.");
            return;
        }

        System.out.println("│ Current: " + existing.getFullName());
        System.out.print("│ New First Name [" + existing.getFirstName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) existing.setFirstName(name);

        if (existing instanceof Owner) {
            System.out.print("│ New Pet Name: ");
            String pet = scanner.nextLine();
            if (!pet.isEmpty()) ((Owner) existing).setPetName(pet);
            personDAO.updateOwner((Owner) existing);
        } else if (existing instanceof Veterinarian) {
            System.out.print("│ New Specialization: ");
            String spec = scanner.nextLine();
            if (!spec.isEmpty()) ((Veterinarian) existing).setSpecialization(spec);
            personDAO.updateVeterinarian((Veterinarian) existing);
        }
    }

    private void deletePerson() {
        System.out.print("\n⚠️ Enter ID to delete: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Are you sure? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            personDAO.deletePerson(id);
        }
    }

    // ========================================
    // SEARCH
    // ========================================

    private void searchByName() {
        System.out.print("🔍 Enter name to search: ");
        String name = scanner.nextLine();
        List<Person> results = personDAO.searchByName(name);
        displaySearchResults(results, "Name: " + name);
    }

    private void searchByAgeRange() {
        System.out.print("Min Age: "); int min = scanner.nextInt();
        System.out.print("Max Age: "); int max = scanner.nextInt(); scanner.nextLine();
        List<Person> results = personDAO.searchByAgeRange(min, max);
        displaySearchResults(results, "Age between " + min + "-" + max);
    }

    private void viewExperiencedVets() {
        List<Person> people = personDAO.getAllPeople();
        System.out.println("\n⭐ --- EXPERIENCED VETERINARIANS (5+ Years) ---");
        people.stream()
                .filter(p -> p instanceof Veterinarian && ((Veterinarian)p).isExperienced())
                .forEach(System.out::println);
    }

    private void demonstratePolymorphism() {
        personDAO.demonstratePolymorphism();
    }

    private void displaySearchResults(List<Person> results, String criteria) {
        System.out.println("\n🔎 Results for " + criteria + ":");
        if (results.isEmpty()) System.out.println("📭 Nothing found.");
        else results.forEach(p -> System.out.println("[" + (p instanceof Owner ? "Owner" : "Vet") + "] " + p));
    }

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}