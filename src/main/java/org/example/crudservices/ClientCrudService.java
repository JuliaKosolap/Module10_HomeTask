package org.example.crudservices;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.HibernateUtil;
import org.example.entities.Client;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ClientCrudService {
    public void create(Client client) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(client);
        transaction.commit();
        session.close();
    }

    public Client getClientById(int id) {
        ArrayList<Integer> allClientsId = getAllClientsId();
        for (int i = 0; i < allClientsId.size(); i++) {
            if (id == allClientsId.get(i)) {
                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                return session.get(Client.class, id);
            }
        }
        System.out.println("There is no client with id: " + id);
        return null;
    }

    public ArrayList<Integer> getAllClientsId() {
        ArrayList<Integer> idList = new ArrayList<>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Client> clientsList = session.createQuery(
                "from Client", Client.class).list();
        for (int i = 0; i < clientsList.size(); i++) {
            Client client = clientsList.get(i);
            int id = client.getId();
            idList.add(id);
        }
        return idList;
    }

    public void update(String oldName, String newName) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update Client c set c.name = :newName where c.name = :oldName"
        );
        query.setParameter("newName", newName);
        query.setParameter("oldName", oldName);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteById(int id) {
        ArrayList<Integer> allClientsId = getAllClientsId();
        for (int i = 0; i < allClientsId.size(); i++) {
            if (id == allClientsId.get(i)) {
                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.remove(getClientById(id));
                transaction.commit();
                session.close();
            } else {
                System.out.println("There is no client with id: " + id);}
        }
    }
}
