package dg.nisum.users.shared.domain;

import dg.nisum.users.shared.domain.DomainEvent;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
