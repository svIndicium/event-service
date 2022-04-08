package hu.indicium.dev.services.events.api;

import hu.indicium.dev.rest.HttpResponse;
import hu.indicium.dev.services.events.api.commands.CreateEventCommand;
import hu.indicium.dev.services.events.model.Event;
import hu.indicium.dev.structure.BaseController;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.ArrayList;

@Controller()
class EventController extends BaseController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Get
    public HttpResponse getEvents() {
        return HttpResponse.Builder.Companion.ok()
                .data(new ArrayList<>())
                .build();
    }

    @Post
    public HttpResponse createEvent(CreateEventCommand createEventCommand) {
        Event createdEvent = eventService.storeEvent(createEventCommand);
        return HttpResponse.Builder.Companion.created()
                .data(createdEvent)
                .build();
    }
}
