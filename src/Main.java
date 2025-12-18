import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import inventories.Inventory;

import objects.Appointment;
import objects.Owner;
import objects.Pet;
import objects.Veterinarian;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Inventory<Owner> ownerInv = new Inventory<>();
        Inventory<Pet> petInv = new Inventory<>();
        Inventory<Veterinarian> vetInv = new Inventory<>();
        Inventory<Appointment> apppointmentInv = new Inventory<>();

        petInv.add(new Pet("Jaba", "Qva", 867, 'm', 465.5f, "ill"));
        ownerInv.add(new Owner("Timur", "katsayev", "87773003030", new ArrayList<>(List.of(
                petInv.get(0)
        ))));
        vetInv.add(new Veterinarian("Almas", "Legushki", 45, "none", true));
        apppointmentInv.add(new Appointment("23.04.25", "14:52", "Bolit zivot", petInv.get(0), ownerInv.get(0), vetInv.get(0)));

        petInv.get(0).heal();
        System.out.println(petInv.get(0).toString());
        ownerInv.get(0).addpets(new Pet("Gena", "Krocodil", 52, 'm', 75.4f, "Bolit zub"));
        System.out.println(ownerInv.get(0).toString());
        System.out.println(vetInv.get(0).isExperienced());
        apppointmentInv.get(0).reschedule("25.04.25", "23:59");
        System.out.println(apppointmentInv.get(0).toString());
    }
}

