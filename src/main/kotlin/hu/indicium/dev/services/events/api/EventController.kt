package hu.indicium.dev.services.events.api

import hu.indicium.dev.rest.HttpResponse
import hu.indicium.dev.rest.HttpResponse.Builder.Companion.created
import hu.indicium.dev.rest.HttpResponse.Builder.Companion.ok
import hu.indicium.dev.services.events.api.commands.CreateEventCommand
import hu.indicium.dev.structure.BaseController
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller
class EventController(private val eventService: EventService) : BaseController() {
    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    suspend fun events(): HttpResponse =
        ok()
            .data(eventService.getEvents())
            .build()

    @Post
    @Secured("ADMIN")
    @Status(HttpStatus.CREATED)
    suspend fun createEvent(createEventCommand: CreateEventCommand?): HttpResponse {
        val createdEvent = eventService.storeEvent(createEventCommand!!)
        return created()
            .data(createdEvent)
            .build()
    }
}