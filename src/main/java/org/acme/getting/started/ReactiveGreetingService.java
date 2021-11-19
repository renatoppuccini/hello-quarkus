package org.acme.getting.started;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ReactiveGreetingService {

    public Uni<String> greeting(String name) {
        System.out.println("Could not get local address2222");
        return Uni.createFrom().item(name)
                .onItem().transform(n -> String.format("hello %s", name));
    }

    public Multi<String> greetings(int count, String name) {
        System.out.println("Could not get local address3333");
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(n -> String.format("hello %s - %d", name, n))
                .transform().byTakingFirstItems(count);

    }

}
