package org.example.crudservices;

import org.example.HibernateUtil;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TicketCrudService {

    public void create(Ticket ticket) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();
        Client client = ticket.getClient();
        Planet toPlanetId = ticket.getToPlanetId();
        Planet fromPlanetId = ticket.getFromPlanetId();
        if (clientService.verifyIfClientExists(client)
                && planetService.verifyIfPlanetExists(fromPlanetId)
                && planetService.verifyIfPlanetExists(toPlanetId)) {
            session.persist(ticket);
        } else {
            System.out.println("Client or Planet does not exist");
        }
        transaction.commit();
        session.close();
    }

    public Ticket getTicketById(int id) {
        ArrayList<Integer> allTicketsId = getAllTicketsId();
        for (int i = 0; i < allTicketsId.size(); i++) {
            if (id == allTicketsId.get(i)) {
                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                return session.get(Ticket.class, id);
            }
        }
        System.out.println("There is no ticket with id: " + id);
        return null;
    }

    public ArrayList<Integer> getAllTicketsId() {
        ArrayList<Integer> idList = new ArrayList<>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Ticket> ticketsList = session.createQuery(
                "from Ticket", Ticket.class).list();
        for (int i = 0; i < ticketsList.size(); i++) {
            Ticket ticket = ticketsList.get(i);
            int id = ticket.getId();
            idList.add(id);
        }
        return idList;
    }

    //updates the ticket with provided id  - assigns it to another client
    public void updateClientByTicketId(int id, Client newClient) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update Ticket t set t.client = :newClient where t.id = :id"
        );
        query.setParameter("newClient", newClient);
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteById(int id) {
        ArrayList<Integer> allTicketsId = getAllTicketsId();
        for (int i = 0; i < allTicketsId.size(); i++) {
            if (id == allTicketsId.get(i)) {
                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.remove(getTicketById(id));
                transaction.commit();
                session.close();
                break;
            }
        }
    }
}
