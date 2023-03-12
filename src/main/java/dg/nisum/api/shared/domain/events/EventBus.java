package dg.nisum.api.shared.domain.events;

import dg.nisum.api.shared.domain.events.DomainEvent;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
