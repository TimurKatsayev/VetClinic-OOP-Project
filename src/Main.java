import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import inventories.Inventory;

import objects.Appointment;
import objects.Owner;
import objects.Pet;
import objects.Veterinarian;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    static Inventory<Owner> ownerInv = new Inventory<>();
    static Inventory<Pet> petInv = new Inventory<>();
    static Inventory<Veterinarian> vetInv = new Inventory<>();
    static Inventory<Appointment> appointmentInv = new Inventory<>();

    public static void main(String[] args) {

        // Demo data
        petInv.add(new Pet("Jaba", "Qva", 867, 'm', 465.5f, "ill"));
        ownerInv.add(new Owner("Timur", "katsayev", "87773003030", new ArrayList<>(List.of(
                petInv.get(0)
        ))));
        vetInv.add(new Veterinarian("Almas", "Legushki", 45, "none", true));
        appointmentInv.add(new Appointment("2026-04-23", "14:52", "Bolit zivot",
                petInv.get(0), ownerInv.get(0), vetInv.get(0)));

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addOwner();
                case 2 -> viewAllOwners();
                case 3 -> addPet();
                case 4 -> viewAllPets();
                case 5 -> addAppointment();
                case 6 -> viewAllAppointments();
                case 0 -> {
                    System.out.println("\nGoodbye!");
                    running = false;
                }
                default -> System.out.println("\nInvalid choice!");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println(" VETERINARIAN CLINIC ");
        System.out.println("========================================");
        System.out.println("1. Add Owner");
        System.out.println("2. View All Owners");
        System.out.println("3. Add Pet");
        System.out.println("4. View All Pets");
        System.out.println("5. Add Appointment");
        System.out.println("6. View All Appointments");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    // -------------------------
    // Owner
    // -------------------------
    private static void addOwner() {
        System.out.println("\n--- ADD OWNER ---");

        System.out.print("Enter firstName: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter lastName: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phoneNumber = scanner.nextLine();

        int petCount = readInt("Enter amount of pets: ");
        List<Pet> pets = new ArrayList<>();

        for (int i = 0; i < petCount; i++) {
            System.out.println("\nPet #" + (i + 1));

            System.out.print("Enter pet name: ");
            String petName = scanner.nextLine();

            System.out.print("Enter pet type: ");
            String petType = scanner.nextLine();

            int petAge = readInt("Enter pet age: ");

            char petGender = readChar("Enter pet gender (f/m): ");

            float petWeight = readFloat("Enter pet weight: ");

            System.out.print("Enter pet current condition: ");
            String petCC = scanner.nextLine();

            Pet pet = new Pet(petName, petType, petAge, petGender, petWeight, petCC);

            // Добавляем питомца и в общий инвентарь, и к владельцу
            petInv.add(pet);
            pets.add(pet);
        }

        Owner owner = new Owner(firstName, lastName, phoneNumber, pets);
        ownerInv.add(owner);

        System.out.println("Owner added successfully!");
    }

    private static void viewAllOwners() {
        System.out.println("\n--- ALL OWNERS ---");
        int count = safeSizeOwners();

        if (count == 0) {
            System.out.println("No owners found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            Owner o = ownerInv.get(i);
            System.out.println((i + 1) + ") " + o);
        }
    }

    // -------------------------
    // Pet
    // -------------------------
    private static void addPet() {
        System.out.println("\n--- ADD PET ---");

        System.out.print("Enter pet name: ");
        String petName = scanner.nextLine();

        System.out.print("Enter pet type: ");
        String petType = scanner.nextLine();

        int petAge = readInt("Enter pet age: ");
        char petGender = readChar("Enter pet gender (f/m): ");
        float petWeight = readFloat("Enter pet weight: ");

        System.out.print("Enter pet current condition: ");
        String petCC = scanner.nextLine();

        Pet pet = new Pet(petName, petType, petAge, petGender, petWeight, petCC);
        petInv.add(pet);

        System.out.println("Pet added successfully!");
    }

    private static void viewAllPets() {
        System.out.println("\n--- ALL PETS ---");
        int count = safeSizePets();

        if (count == 0) {
            System.out.println("No pets found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ") " + petInv.get(i));
        }
    }

    // -------------------------
    // Appointment
    // -------------------------
    private static void addAppointment() {
        System.out.println("\n--- ADD APPOINTMENT ---");

        if (safeSizePets() == 0 || safeSizeOwners() == 0 || safeSizeVets() == 0) {
            System.out.println("Cannot create appointment. Need at least: 1 pet, 1 owner, 1 veterinarian.");
            return;
        }

        System.out.print("Enter date (e.g. 23.04.25): ");
        String date = scanner.nextLine();

        System.out.print("Enter time (e.g. 14:52): ");
        String time = scanner.nextLine();

        System.out.print("Enter complaint/description: ");
        String complaint = scanner.nextLine();

        Pet pet = choosePet();
        Owner owner = chooseOwner();
        Veterinarian vet = chooseVet();

        Appointment appointment = new Appointment(date, time, complaint, pet, owner, vet);
        appointmentInv.add(appointment);

        System.out.println("Appointment added successfully!");
    }

    private static void viewAllAppointments() {
        System.out.println("\n--- ALL APPOINTMENTS ---");
        int count = safeSizeAppointments();

        if (count == 0) {
            System.out.println("No appointments found.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ") " + appointmentInv.get(i));
        }
    }

    // -------------------------
    // Choose helpers
    // -------------------------
    private static Pet choosePet() {
        System.out.println("\nChoose a pet:");
        viewAllPets();
        int idx = readInt("Enter pet number: ") - 1;
        idx = clampIndex(idx, safeSizePets());
        return petInv.get(idx);
    }

    private static Owner chooseOwner() {
        System.out.println("\nChoose an owner:");
        viewAllOwners();
        int idx = readInt("Enter owner number: ") - 1;
        idx = clampIndex(idx, safeSizeOwners());
        return ownerInv.get(idx);
    }

    private static Veterinarian chooseVet() {
        System.out.println("\nChoose a veterinarian:");
        viewAllVets();
        int idx = readInt("Enter vet number: ") - 1;
        idx = clampIndex(idx, safeSizeVets());
        return vetInv.get(idx);
    }

    private static void viewAllVets() {
        System.out.println("\n--- ALL VETERINARIANS ---");
        int count = safeSizeVets();
        if (count == 0) {
            System.out.println("No veterinarians found.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ") " + vetInv.get(i));
        }
    }

    // -------------------------
    // Input helpers (без падений)
    // -------------------------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    private static float readFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Float.parseFloat(s);
            } catch (NumberFormatException e) {
                System.out.println("Invalid float. Try again.");
            }
        }
    }

    private static char readChar(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s.charAt(0);
            System.out.println("Empty input. Try again.");
        }
    }

    private static int clampIndex(int idx, int size) {
        if (size <= 0) return 0;
        if (idx < 0) return 0;
        if (idx >= size) return size - 1;
        return idx;
    }

    // -------------------------
    // Inventory size
    private static int safeSizeOwners() { return safeSizeGeneric(ownerInv); }
    private static int safeSizePets() { return safeSizeGeneric(petInv); }
    private static int safeSizeVets() { return safeSizeGeneric(vetInv); }
    private static int safeSizeAppointments() { return safeSizeGeneric(appointmentInv); }

    private static <T> int safeSizeGeneric(Inventory<T> inv) {
        // Пытаемся угадать API Inventory:
        // 1) если есть size(), используй его.
        // 2) если нет — считаем через get(i) пока не упадёт.
        try {

        } catch (Exception ignored) {}

        int i = 0;
        while (true) {
            try {
                inv.get(i);
                i++;
            } catch (Exception e) {
                return i;
            }
        }
    }
}
