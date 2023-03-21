package org.example;

import org.example.crudservices.ClientCrudService;
import org.example.crudservices.PlanetCrudService;
import org.example.crudservices.TicketCrudService;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.flywaydb.core.Flyway;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./test4", "sa", null).load();
        flyway.migrate();
        ClientCrudService service = new ClientCrudService();
        Client client = new Client("Name3");
        service.create(client);
        int lastClientId = service.getLastClientId();
        System.out.println(service.getClientById(lastClientId));
        PlanetCrudService planetService = new PlanetCrudService();
        Planet planet = new Planet("ST8", "Saturn");
        planetService.create(planet);
        TicketCrudService ticketCrudService = new TicketCrudService();
        ArrayList<Integer> allTicketsId = ticketCrudService.getAllTicketsId();
        ticketCrudService.updateClientByTicketId(2, client);
        Ticket ticketById = ticketCrudService.getTicketById(2);
        System.out.println(ticketById);

    }
}