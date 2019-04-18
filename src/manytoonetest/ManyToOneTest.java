/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manytoonetest;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author eyvind
 */
public class ManyToOneTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManyToOneTest main = new ManyToOneTest();
        main.startUp();

    }

    private void startUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ManyToOneTestPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q=em.createQuery("select o from Family o");
        int size=q.getResultList().size();
        if(size<1)
        {
        
        Family f = new Family();
        f.setDescription("testers");
        List<Person> members = new ArrayList();
        for (int i = 0; i < 40; i++) {
            Person person = new Person();
            person.setFname("Jim " + i);
            person.setLname("Jones " + i);
            person.setFamily(f);
            members.add(person);
        }
        f.setPersons(members);
        em.persist(f);
        em.getTransaction().commit();
        }
        em.close();

    }

}
