package org.acme.getting.started;

import java.net.InetAddress;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestSseElementType;

@Path("/hello")
public class ReactiveGreetingResource {

    @Inject
    ReactiveGreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public Uni<String> greeting(String name) {
        System.out.println("Could not get local address11111");
        return service.greeting(name + ", sorry, but could not get the local address");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{count}/{name}")
    public Multi<String> greetings(int count, String name) {
        return service.greetings(count, name);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{count}/{name}")
    public Multi<String> greetingsAsStream(int count, String name) {
        return service.greetings(count, name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Could not get local address");
        InetAddress myAddress;
        try {
            myAddress = InetAddress.getLocalHost();
        } catch (Exception e) {
            System.out.println("Could not get local address");
        }
        if (myAddress!=null)
            return hello + " from " + myAddress.getHostName() + " - "+ myAddress.getHostAddress();
        else
        return hello + ", sorry, but could not get the local address";
    }
}
