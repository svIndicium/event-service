package hu.indicium.dev.services.events.api

import hu.indicium.dev.rest.HttpResponse
import hu.indicium.dev.rest.HttpResponse.Builder.Companion.created
import hu.indicium.dev.rest.HttpResponse.Builder.Companion.ok
import hu.indicium.dev.services.events.api.commands.CreateEventCommand
import hu.indicium.dev.structure.BaseController
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller
class EventController(private val eventService: EventService) : BaseController() {
    @Get
    suspend fun events(): HttpResponse =
        ok()
            .data(eventService.getEvents())
            .build()

    @Post
    suspend fun createEvent(createEventCommand: CreateEventCommand?): HttpResponse {
        val createdEvent = eventService.storeEvent(createEventCommand!!)
        return created()
            .data(createdEvent)
            .build()
    }
}