package hu.indicium.dev.services.events.model

import io.micronaut.core.annotation.Creator
import io.micronaut.core.annotation.Introspected
import org.bson.codecs.pojo.annotations.BsonCreator
import org.litote.kmongo.Id
import java.time.LocalDateTime

@Introspected
data class Event @Creator @BsonCreator constructor(
    val title: String,
    val description: String?,
    val slug: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val _id: Id<Event>? = null
)