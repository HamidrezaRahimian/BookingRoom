package lambdasstreams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.ConferenceRoom;
import model.Configuration;

public class DataAnalytics {

    public void test01() {
        System.out.println("Räume, die sowohl einen Projektor als auch ein Whiteboard haben (AND); " +
                        "Räume, die entweder auf dem 1. Stockwerk oder auf dem 4. Stockwerk sind (OR); " +
                        "Räume ausschließen, die eine Telekonferenz-Ausstattung haben (NOT)");

        List<ConferenceRoom> conferenceRooms = Configuration.INSTANCE.getRooms();

        List<ConferenceRoom> filteredRooms = conferenceRooms.stream()
                .filter(room -> room.getEquipment().contains("Projector") && room.getEquipment().contains("Whiteboard"))
                .filter(room -> room.getLocation().equals("1st Floor") || room.getLocation().equals("4th Floor"))
                .filter(room -> !room.getEquipment().contains("Teleconferencing"))
                .toList();

        System.out.println(filteredRooms);
        System.out.println("-----------------");
    }

    public void test02() {
        System.out.println("Konferenzräume nach dem Stockwerk sortiert");

        List<ConferenceRoom> conferenceRooms = Configuration.INSTANCE.getRooms();

        Map<String, List<ConferenceRoom>> roomsByFloor = conferenceRooms.stream()
                .filter(room -> !room.getLocation().equals("4th Floor"))
                .collect(Collectors.groupingBy(ConferenceRoom::getLocation));

        System.out.println(roomsByFloor);
        System.out.println("-----------------");
    }

    public void test03() {
        System.out.println("Konferenzräume mit Kapazität über 15 Personen");
        List<ConferenceRoom> conferenceRooms = Configuration.INSTANCE.getRooms();

        Map<Boolean, List<ConferenceRoom>> bigRooms = conferenceRooms.stream()
                .collect(Collectors.partitioningBy(room -> room.getCapacity() > 15));

        System.out.println(bigRooms);
        System.out.println(" ");
        System.out.println("Räume mit mehr als 15 Personen Kapazität:");
        bigRooms.get(true).forEach(room -> System.out.println(" - " + room.getName() + ": Kapazität " + room.getCapacity()));
        System.out.println("\nRäume mit 15 oder weniger Personen Kapazität:");
        bigRooms.get(false).forEach(room -> System.out.println(" - " + room.getName() + ": Kapazität " + room.getCapacity()));
        System.out.println("-----------------");
    }


    public void test04() {
        System.out.println("Sortierung mit Comparator nach Stockwerk und Kapazität");
        List<ConferenceRoom> conferenceRooms = Configuration.INSTANCE.getRooms();

        List<ConferenceRoom> sortedRooms = conferenceRooms.stream()
                .sorted(Comparator.comparing(ConferenceRoom::getLocation).thenComparing(ConferenceRoom::getCapacity)).toList();

        System.out.println(sortedRooms);
        sortedRooms.forEach(room -> System.out.println(room.getName() + " - Kapazität: " + room.getCapacity() + ", Etage: " + room.getLocation()));
        System.out.println("-----------------");
    }

    public void test05() {
        System.out.println("Filter nach Räumen mit mehr als 10 Plätzen; Gruppieren nach Etage und innerhalb der Etage gruppieren nach Ausstattung Projector (MIT count)");
        List<ConferenceRoom> conferenceRooms = Configuration.INSTANCE.getRooms();

        Map<String, Map<Boolean, Long>> result = conferenceRooms.stream()
                .filter(room -> room.getCapacity() > 10)
                .collect(Collectors.groupingBy(
                        ConferenceRoom::getLocation,
                        Collectors.groupingBy(
                                room -> room.getEquipment().contains("Projector"),
                                Collectors.counting()
                        )
                ));

        result.forEach((floor, projectorMap) -> {
            System.out.println(" ");
            System.out.println("Floor: " + floor);
            projectorMap.forEach((hasProjector, count) -> {
                System.out.println((hasProjector ? "With" : "Without") + " Projector: " + count);
            });
        });
        System.out.println("-----------------");
    }

    public void test06() {
        System.out.println("Filter nach Räumen mit mehr als 10 Plätzen; Gruppieren nach Etage und innerhalb der Etage gruppieren nach Ausstattung Projector (OHNE count)");
        List<ConferenceRoom> conferenceRooms = Configuration.INSTANCE.getRooms();

        Map<String, Map<Boolean, List<ConferenceRoom>>> result = conferenceRooms.stream()
                .filter(room -> room.getCapacity() > 10)
                .collect(Collectors.groupingBy(
                        ConferenceRoom::getLocation,
                        Collectors.groupingBy(
                                room -> room.getEquipment().contains("Projector")
                        )
                ));

        result.forEach((floor, projectorMap) -> {
            System.out.println("Floor: " + floor);
            projectorMap.forEach((hasProjector, roomList) -> {
                System.out.println((hasProjector ? "With" : "Without") + " Projector: ");
                roomList.forEach(room -> System.out.println("  - " + room.getName()));
            });
        });
    }
}
