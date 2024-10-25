import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class RoomManagerTest {
    private RoomManager roomManager;

    @BeforeEach
    public void setUp() {
        roomManager = new RoomManager();
    }



    @Test
    public void testCancelBooking_JustOutside24Hours_SuccessAfterWait() throws InterruptedException {
        // Startzeit ist genau 24 Stunden und 5 Sekunden in der Zukunft
        LocalDateTime startTime = LocalDateTime.now().plusHours(24).plusSeconds(5);
        LocalDateTime endTime = startTime.plusHours(2);

        // Buche ein Zimmer
        ConferenceRoom room = roomManager.getRooms().get(0);
        String bookingResult = roomManager.bookRoom(room, startTime, endTime, "Alice", "Meeting");

        // Sicherstellen, dass die Buchung erfolgreich war
        assertEquals("Room " + room.getName() + " successfully booked from "+startTime+" to "+endTime+".", bookingResult);

        // Warte 6 Sekunden, um über die 24-Stunden-Grenze zu kommen
        Thread.sleep(6000);

        // Versuche, die Buchung zu stornieren
        String cancellationResult = roomManager.cancelBooking(room);

        // Überprüfe, ob die Stornierung erfolgreich war, da wir jetzt knapp außerhalb der 24-Stunden-Frist sind
        assertEquals("Cancellations are only allowed at least 24 hours before the meeting.", cancellationResult);
    }

    @Test
    public void testCancelBooking_MoreThan24Hours_Success() {
        // Erstelle Startzeit, die mehr als 24 Stunden in der Zukunft liegt
        LocalDateTime startTime = LocalDateTime.now().plusDays(2);
        LocalDateTime endTime = startTime.plusHours(2);

        // Buche ein Zimmer
        ConferenceRoom room = roomManager.getRooms().get(0);
        String bookingResult = roomManager.bookRoom(room, startTime, endTime, "Alice", "Weekly Sync");

        // Sicherstellen, dass die Buchung erfolgreich durchgeführt wurde
        assertEquals("Room " + room.getName() + " successfully booked from "+startTime+" to "+endTime+".", bookingResult);

        // Versuche, die Buchung zu stornieren
        String cancellationResult = roomManager.cancelBooking(room);

        // Überprüfe, ob die Stornierung erfolgreich war, wenn mehr als 24 Stunden im Voraus storniert wird
        assertEquals("Booking for room " + room.getName() + " has been canceled.", cancellationResult);
    }
}
