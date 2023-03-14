package org.example;

import org.example.crudservices.ClientCrudService;
import org.example.crudservices.PlanetCrudService;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.flywaydb.core.Flyway;
import org.hibernate.MultiIdentifierLoadAccess;

public class App {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./test4", "sa", null).load();
        flyway.migrate();
        ClientCrudService service = new ClientCrudService();
        Client client = new Client("Name1");
        service.create(client);
        System.out.println(service.getClientById(10));
        service.update("Andrew", "Gleb");
        service.deleteById(1);
        PlanetCrudService planetService = new PlanetCrudService();
        Planet planet = new Planet("NP3", "Neptune");
        planetService.create(planet);
        System.out.println(planetService.getPlanetById("MAR1"));
        planetService.update("Saturn", "Jupiter");
        System.out.println(planetService.getPlanetById("SAT1"));
        planetService.deleteById("EAR1");
        planetService.getPlanetById("EAR1");


    }
}