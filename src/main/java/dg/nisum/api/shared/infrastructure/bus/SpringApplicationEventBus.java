package dg.nisum.api.shared.infrastructure.bus;

import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.shared.domain.events.DomainEvent;
import dg.nisum.api.shared.domain.events.EventBus;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@Service
public class SpringApplicationEventBus implements EventBus {

    private final ApplicationEventPublisher publisher;

    public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent event){
        this.publisher.publishEvent(event);
    }
}
