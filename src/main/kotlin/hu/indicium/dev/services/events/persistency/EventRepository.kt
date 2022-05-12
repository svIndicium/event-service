package hu.indicium.dev.services.events.persistency

import hu.indicium.dev.services.events.model.Event
import io.micronaut.context.annotation.Property
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

@Singleton
class EventRepository(
    client: CoroutineClient,
    @Property(name = "mongodb.database") databaseName: String,
) : BaseRepository<Event>(client, databaseName, Event::class) {
    override suspend fun afterCreationHook() {
        collection.ensureUniqueIndex(Event::slug)
    }
}