package model;

import java.util.Arrays;
import java.util.List;

public enum Configuration {
    INSTANCE;

    public List<ConferenceRoom> getRooms() {
        return Arrays.asList(
                new ConferenceRoom("Room A", 10, "Projector, Whiteboard", "1st Floor"),
                new ConferenceRoom("Room B", 20, "Teleconferencing", "2nd Floor"),
                new ConferenceRoom("Room C", 15, "Video Equipment", "3rd Floor"),
                new ConferenceRoom("Room D", 12, "Projector, Teleconferencing", "1st Floor"),
                new ConferenceRoom("Room E", 25, "Whiteboard, Video Equipment", "2nd Floor"),
                new ConferenceRoom("Room F", 18, "Projector, Video Equipment", "3rd Floor"),
                new ConferenceRoom("Room G", 30, "Teleconferencing, Whiteboard", "4th Floor"),
                new ConferenceRoom("Room H", 10, "Projector, Whiteboard", "4th Floor"),
                new ConferenceRoom("Room I", 20, "Video Equipment, Teleconferencing", "1st Floor"),
                new ConferenceRoom("Room J", 15, "Projector, Whiteboard", "3rd Floor")
        );
    }
}
