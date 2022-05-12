package hu.indicium.dev.services.events.api

import hu.indicium.dev.services.events.api.commands.CreateEventCommand
import hu.indicium.dev.services.events.model.Event
import hu.indicium.dev.services.events.persistency.EventRepository
import hu.indicium.dev.structure.BaseService
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import org.litote.kmongo.id.StringId
import org.litote.kmongo.id.toId
import javax.validation.Valid

@Singleton
open class EventService(
    private val eventRepository: EventRepository
) : BaseService() {
    open suspend fun storeEvent(@Valid createEventCommand: CreateEventCommand): Event? {
        return Event(
            createEventCommand.title!!,
            createEventCommand.description,
            createEventCommand.slug!!,
            createEventCommand.startDateTime!!,
            createEventCommand.endDateTime!!,
            StringId(createEventCommand.slug)
        ).let {
            eventRepository.save(it)
        }
    }

    open suspend fun getEvents(): List<Event> =
        eventRepository.findAll()

    open suspend fun getEventById(identifier: String): Event? =
        eventRepository.findById(StringId(identifier))
}