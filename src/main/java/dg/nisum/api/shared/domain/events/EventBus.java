package dg.nisum.api.shared.domain.events;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
