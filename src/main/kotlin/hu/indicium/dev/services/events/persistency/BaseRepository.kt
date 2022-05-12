package hu.indicium.dev.services.events.persistency

import com.mongodb.client.model.CreateCollectionOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.util.KMongoUtil
import kotlin.reflect.KClass

abstract class BaseRepository<T: Any>(
    client: CoroutineClient,
    database: String,
    private val clazz: KClass<T>
) {
    private val db = client.getDatabase(database)
    private val collectionName = KMongoUtil.defaultCollectionName(clazz)
    protected val collection: CoroutineCollection<T> by lazy {
        db.database.getCollection(collectionName, clazz.java).coroutine
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            if (collectionName !in db.listCollectionNames().toList()) {
                db.createCollection(collectionName, CreateCollectionOptions())
                afterCreationHook()
            }
        }
    }

    protected open suspend fun afterCreationHook() {}

    open suspend fun findAll(): List<T> =
        collection.find().toList()

    open suspend fun findById(identifier: Id<T>): T? =
        collection.findOneById(identifier)

    open suspend fun save(document: T): T? =
        document.apply { collection.insertOne(document) }
}