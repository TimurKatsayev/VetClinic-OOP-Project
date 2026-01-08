package objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Appointment {
    private LocalDate date;
    private LocalTime time;
    private String reason;

    private Pet pet;
    private Owner owner;
    private Veterinarian veterinarian;

    // CONSTRUCTOR (String input -> validated parse)
    public Appointment(String date, String time, String reason,
                       Pet pet, Owner owner, Veterinarian veterinarian) {
        setDate(date);
        setTime(time);
        setReason(reason);
        setPet(pet);
        setOwner(owner);
        setVeterinarian(veterinarian);
    }

    // DEFAULT CONSTRUCTOR
    public Appointment() {
        this.date = null;
        this.time = null;
        this.reason = "Not specified";
        this.pet = null;
        this.owner = null;
        this.veterinarian = null;
    }

    // --- SETTERS (with validation) ---

    // Expected format: YYYY-MM-DD
    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty. Use YYYY-MM-DD");
        }

        final LocalDate parsed;
        try {
            parsed = LocalDate.parse(date.trim()); // ISO: yyyy-MM-dd
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format/value. Use YYYY-MM-DD", e);
        }

        if (parsed.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }

        this.date = parsed;
    }

    // Expected format: HH:mm (24-hour)
    public void setTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Time cannot be null or empty. Use HH:mm");
        }

        try {
            this.time = LocalTime.parse(time.trim()); // ISO: HH:mm[:ss]
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format/value. Use HH:mm", e);
        }
    }

    public void setReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            this.reason = "Not specified";
            return;
        }
        this.reason = reason.trim();
    }

    public void setPet(Pet pet) {
        if (pet == null) throw new IllegalArgumentException("Pet cannot be null");
        this.pet = pet;
    }

    public void setOwner(Owner owner) {
        if (owner == null) throw new IllegalArgumentException("Owner cannot be null");
        this.owner = owner;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        if (veterinarian == null) throw new IllegalArgumentException("Veterinarian cannot be null");
        this.veterinarian = veterinarian;
    }

    // --- GETTERS ---

    public String getDate() {
        return (date == null) ? "Unknown" : date.toString(); // yyyy-MM-dd
    }

    public String getTime() {
        return (time == null) ? "Unknown" : time.toString(); // HH:mm[:ss]
    }

    public String getReason() {
        return reason;
    }

    public Pet getPet() {
        return pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public LocalTime getTimeValue() {
        return time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + (date == null ? "Unknown" : date) +
                ", time=" + (time == null ? "Unknown" : time) +
                ", reason='" + reason + '\'' +
                ", pet=" + pet +
                ", owner=" + owner +
                ", veterinarian=" + veterinarian +
                '}';
    }

    // --- ADDITIONAL METHODS ---

    public void reschedule(String newDate, String newTime) {
        // setDate/setTime already validate
        setDate(newDate);
        setTime(newTime);
    }

    // Without parameters: just checks current system date
    public boolean isToday() {
        return date != null && date.equals(LocalDate.now());
    }

    // If you want to pass "todayDate" as String (YYYY-MM-DD)
    public boolean isToday(String todayDate) {
        if (todayDate == null || todayDate.trim().isEmpty() || date == null) return false;
        try {
            return date.equals(LocalDate.parse(todayDate.trim()));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
