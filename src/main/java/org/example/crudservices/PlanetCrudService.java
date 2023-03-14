package org.example.crudservices;

import org.example.HibernateUtil;
import org.example.entities.Client;
import org.example.entities.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlanetCrudService {
    public void create(Planet planet) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
        session.close();
    }

    public Planet getPlanetById(String id) {
        ArrayList<String> allPlanetsId = getAllPlanetsId();
        for (int i = 0; i < allPlanetsId.size(); i++) {
            if (id.equals(allPlanetsId.get(i))) {
                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                return session.get(Planet.class, id);
            }
        }
        System.out.println("There is no planet with id: " + id);
        return null;
    }

    public ArrayList<String> getAllPlanetsId() {
        ArrayList<String> idList = new ArrayList<>();
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Planet> planetsList = session.createQuery(
                "from Planet", Planet.class).list();
        for (int i = 0; i < planetsList.size(); i++) {
            Planet planet = planetsList.get(i);
            String id = planet.getId();
            idList.add(id);
        }
        return idList;
    }

    public void update(String oldName, String newName) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update Planet p set p.name = :newName where p.name = :oldName"
        );
        query.setParameter("newName", newName);
        query.setParameter("oldName", oldName);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteById(String id) {
        ArrayList<String> allPlanetsId = getAllPlanetsId();
        for (int i = 0; i < allPlanetsId.size(); i++) {
            if (id.equals(allPlanetsId.get(i))) {

                Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.remove(getPlanetById(id));
                transaction.commit();
                session.close();
            } else {
                System.out.println("There is no planet with id: " + id);
            }
        }
    }
}
