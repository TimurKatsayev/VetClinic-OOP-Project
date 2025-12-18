package objects;

public class Pet {
    private String name;
    private String type;
    private int age;
    private char gender;
    private float weight;
    private String currentCondition;

    // CONSTRUCTOR
    public Pet(String name, String type,  int age, char gender, float weight, String currentCondition){
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.currentCondition = currentCondition;
    }

    //DEFAULT_CONSTRUCTOR

    public Pet() {
        this.name = "Unknown";
        this.type = "Unknown";
        this.age = 0;
        this.gender = 'U';
        this.weight = 0.0f;
        this.currentCondition = "Healthy";
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    // GETTERS

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public float getWeight() {
        return weight;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    // TO_STRING

    public String toString() {
        return "objects.Pet{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", weight=" + weight +
                ", currentCondition='" + currentCondition + '\'' +
                '}';
    }

    // Additional methods

    public void heal(){
        this.currentCondition = "Healthy";
    }

    public void gainWeight(float amount) {
        if (amount > 0) {
            this.weight += amount;
        }
    }

    public void loseWeight(float amount) {
        if (amount > 0 && this.weight - amount > 0) {
            this.weight -= amount;
        }
    }

}
