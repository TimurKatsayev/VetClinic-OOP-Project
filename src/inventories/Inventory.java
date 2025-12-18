package inventories;
import java.util.ArrayList;
import objects.Appointment;

public class Inventory<T> {
    private ArrayList<T> items = new ArrayList<>();

    public T get(int index) {
        return items.get(index);
    }

    public void add(T item) {
        items.add(item);
    }

    public void display() {
        for (T item : items) {
            System.out.println(item.toString());
        }
    }
}
