import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import java.time.LocalDateTime;

public class JGivenRoomManagerSteps extends Stage<JGivenRoomManagerSteps> {

    @ProvidedScenarioState
    private RoomManager roomManager;

    @ProvidedScenarioState
    private ConferenceRoom room;

    @ProvidedScenarioState
    private String result;

    public JGivenRoomManagerSteps a_room_manager() {
        roomManager = new RoomManager();
        return self();
    }

    public JGivenRoomManagerSteps an_available_room_named_$with_capacity_$located_$with_equipment$(
            String name, int capacity, String location, String equipment) {

        room = new ConferenceRoom(name, capacity, equipment, location);
        roomManager.getRooms().add(room); // Add this room to RoomManager’s list of rooms
        return self();
    }

    public JGivenRoomManagerSteps the_room_is_booked_from_$to$(LocalDateTime startTime, LocalDateTime endTime) {
        result = roomManager.bookRoom(room, startTime, endTime, "Alex", "Team Meeting");
        return self();
    }

    public JGivenRoomManagerSteps booking_should_succeed_with_message_$ (String expectedMessage) {
        assert result.equals(expectedMessage) : "Expected message was: " + expectedMessage + ", but got: " + result;
        return self();
    }
}
