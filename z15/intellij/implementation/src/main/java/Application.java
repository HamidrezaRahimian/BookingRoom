import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        RoomManager roomManager = new RoomManager();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            System.out.println("\n--- Conference Room Booking System ---");
            System.out.println("1. Check Availability");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Suggest Rooms by Participant Count");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    LocalDateTime startTime = getValidDateTime(scanner, "Enter start time (yyyy-MM-dd HH:mm): ", formatter);
                    LocalDateTime endTime = getValidDateTime(scanner, "Enter end time (yyyy-MM-dd HH:mm): ", formatter);

                    if (startTime == null || endTime == null) {
                        System.out.println("Invalid date format. Please try again.");
                        break;
                    }

                    if (!validateFutureDateTime(startTime, endTime)) {
                        System.out.println("Start and end times must be in the future.");
                        break;
                    }

                    List<ConferenceRoom> availableRooms = roomManager.getAvailableRooms(startTime, endTime);
                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available for the selected time.");
                    } else {
                        System.out.println("Available rooms:");
                        for (ConferenceRoom room : availableRooms) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter room name to book: ");
                    String roomName = scanner.nextLine();
                    LocalDateTime bookingStartTime = getValidDateTime(scanner, "Enter start time (yyyy-MM-dd HH:mm): ", formatter);
                    LocalDateTime bookingEndTime = getValidDateTime(scanner, "Enter end time (yyyy-MM-dd HH:mm): ", formatter);

                    if (bookingStartTime == null || bookingEndTime == null) {
                        System.out.println("Invalid date format. Please try again.");
                        break;
                    }

                    if (!validateFutureDateTime(bookingStartTime, bookingEndTime)) {
                        System.out.println("Start and end times must be in the future.");
                        break;
                    }

                    if (bookingEndTime.isBefore(bookingStartTime.plusHours(1))) {
                        System.out.println("A booking must be at least 1 hour long.");
                        break;
                    }

                    System.out.print("Enter your name: ");
                    String reserver = scanner.nextLine();
                    System.out.print("Enter the purpose of the meeting: ");
                    String meetingPurpose = scanner.nextLine();

                    ConferenceRoom roomToBook = null;
                    for (ConferenceRoom room : roomManager.getRooms()) {
                        if (room.getName().equalsIgnoreCase(roomName)) {
                            roomToBook = room;
                            break;
                        }
                    }

                    if (roomToBook != null) {
                        String result = roomManager.bookRoom(roomToBook, bookingStartTime, bookingEndTime, reserver, meetingPurpose);
                        System.out.println(result);
                    } else {
                        System.out.println("Room not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter room name to cancel: ");
                    String cancelRoomName = scanner.nextLine();

                    ConferenceRoom roomToCancel = null;
                    for (ConferenceRoom room : roomManager.getRooms()) {
                        if (room.getName().equalsIgnoreCase(cancelRoomName)) {
                            roomToCancel = room;
                            break;
                        }
                    }

                    if (roomToCancel != null) {
                        String result = roomManager.cancelBooking(roomToCancel);
                        System.out.println(result);
                    } else {
                        System.out.println("Room not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter the number of participants: ");
                    int participantsCount = scanner.nextInt();
                    List<ConferenceRoom> suggestedRooms = roomManager.suggestRoomsForParticipants(participantsCount);
                    if (suggestedRooms.isEmpty()) {
                        System.out.println("No rooms available for the given participant count.");
                    } else {
                        System.out.println("Suggested rooms:");
                        for (ConferenceRoom room : suggestedRooms) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Methode zur Validierung der Datumseingabe
    private static LocalDateTime getValidDateTime(Scanner scanner, String prompt, DateTimeFormatter formatter) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Expected format: yyyy-MM-dd HH:mm");
            return null;
        }
    }

    // Methode zur Überprüfung, ob die Zeiten in der Zukunft liegen
    private static boolean validateFutureDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        return !startTime.isBefore(now) && !endTime.isBefore(now);
    }
}
