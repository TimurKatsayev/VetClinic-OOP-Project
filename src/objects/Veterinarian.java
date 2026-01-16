package objects;

public class Veterinarian extends Person {
    private String specialization;
    private int experienceYears;
    private boolean available;

    public Veterinarian(String firstName, String lastName, String phone, int age, String specialization, int experienceYears, boolean available){
        super(firstName, lastName, phone, age);
        this.lastName = lastName;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.phone = phone;
        this.available = available;
    }

    public Veterinarian(){
        specialization = "Unknown";
        experienceYears = 0;
        phone = "Unknown";
        available = false;
    }

    //GETTERS

    public String getSpecialization() {
        return specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public boolean isAvailable() {
        return available;
    }

    //SETTERS

    public void setSpecialization(String specialization) {
        if (specialization == null) throw new IllegalArgumentException("specialization cannot be null");
        this.specialization = specialization;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) throw new IllegalArgumentException("experienceYears cannot be negative");
        this.experienceYears = experienceYears;
    }

    public void setAvailable(boolean available) {
        if (available != true && available != false) throw new IllegalArgumentException("available cannot be null");
        this.available = available;
    }

    @Override
    public void work() {
        String status = available ? "available" : "not available";
        System.out.println(getFullName() + " is a veterinarian (" + specialization + ") and is " + status + ".");
    }

    @Override
    public void printCard() {
        System.out.println("Role: Veterinarian");
        System.out.println("Specialization: " + specialization);
        System.out.println("Available: " + available);
    }

    @Override
    public String toString() {
        return "VetPerson{" + getFullName() + ", specialization=" + specialization + ", available=" + available + "}";
    }

    // ADDITIONAL METHODS
    public boolean isExperienced() {
        return experienceYears >= 5;
    }

    public void startShift() {
        this.available = true;
    }

    public void endShift() {
        this.available = false;
    }

}
