package hu.indicium.dev.services.events.api.commands

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateEventCommand(
    @get:NotBlank
    @get:Size(min = 1, max = 100)
    val title: String? = null,

    @get:NotBlank
    @get:Size(min = 1, max = 100)
    val description: String? = null,

    @get:NotBlank
    @get:Size(min = 1, max = 100)
    val slug: String? = null,

    @get:NotNull
    val startDateTime: LocalDateTime? = null,

    @get:NotNull
    val endDateTime: LocalDateTime? = null
)