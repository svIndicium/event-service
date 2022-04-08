package hu.indicium.dev.services.events.api;

import hu.indicium.dev.services.events.api.commands.CreateEventCommand;
import hu.indicium.dev.services.events.model.Event;
import hu.indicium.dev.services.events.persistency.EventRepository;
import hu.indicium.dev.structure.BaseService;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class EventService extends BaseService {

    private EventRepository eventRepository;

    public Event storeEvent(CreateEventCommand createEventCommand) {
        this.validate(createEventCommand);
        return eventRepository.save(new Event(
                "1",
                createEventCommand.getTitle(),
                createEventCommand.getDescription(),
                createEventCommand.getSlug(),
                createEventCommand.getStartDateTime(),
                createEventCommand.getEndDateTime()
        ));
    }
}
