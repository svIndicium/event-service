package hu.indicium.dev.services.events;

import hu.indicium.dev.model.event.Event;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.ArrayList;
import java.util.List;

@Controller()
public class EventController {
    @Get
    public List<Event> getEvents() {
        return new ArrayList<>();
    }
}
