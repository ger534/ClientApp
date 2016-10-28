package com.example.user.clientapp;

/**
 * Created by User on 18/10/2016.
 */
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


public class Rest {

    Client client;
    WebTarget target;

    public void hola() {
        client = ClientBuilder.newClient();

        target = client.target("http://172.26.108.121:9080/RestChef");//text/plain json am

        System.out.println(target.path("chef").request().accept(MediaType.APPLICATION_JSON).get(String.class));
    }

}
