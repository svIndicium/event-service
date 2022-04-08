package hu.indicium.dev.services.events.persistency;

import hu.indicium.dev.services.events.model.Event;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface EventRepository extends PageableRepository<Event, String> {
}
