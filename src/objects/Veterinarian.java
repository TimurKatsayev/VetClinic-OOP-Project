package objects;

public class Veterinarian {
    private String lastName;
    private String specialization;
    private int experienceYears;
    private String phoneNumber;
    private boolean available;

    public Veterinarian(String lastName, String specialization, int experienceYears, String phoneNumber, boolean available){
        this.lastName = lastName;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.phoneNumber = phoneNumber;
        this.available = available;
    }

    public Veterinarian(){
        lastName = "Unknown";
        specialization = "Unknown";
        experienceYears = 0;
        phoneNumber = "Unknown";
        available = false;
    }

    //GETTERS
    public String getLastName() {
        return lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    //SETTERS
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // TO_STRING
    public String toString() {
        return "objects.Pet{" +
                ", last name ='" + lastName + '\'' +
                ", specialization ='" + specialization + '\'' +
                ", experience years ='" + experienceYears + '\'' +
                ", is available ='" + available + '\'' +
                ", phone number ='" + phoneNumber + '\'' +
                '}';
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
