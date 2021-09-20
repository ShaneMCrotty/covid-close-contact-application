// Student Name: Shane Crotty
// Student Number: R00066752

package Controller;

import Model.Contact;
import Model.Person;
import javax.persistence.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Shane Crotty
 * @author R00066752
 */
public class Controller {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("CovidCloseContactApplication");

    /**
     * This method creates a person.
     * @param id A person's ID (Identification).
     * @param firstName A person's first name.
     * @param middleName A person's middle name.
     * @param lastName A person's last name.
     * @param phone A person's phone number.
     * @param email A person's email.
     */
    public void addPerson(String id, String firstName, String middleName, String lastName, String phone, String email){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Person person = new Person();
            person.setId(id);
            person.setFirstName(firstName);
            person.setMiddleName(middleName);
            person.setLastName(lastName);
            person.setPhone(phone);
            person.setEmail(email);
            entityManager.persist(person);
            entityTransaction.commit();
        }
        catch (Exception exception){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            exception.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * This method creates a close contact between two people.
     * @param id1 The ID of the first person.
     * @param id2 The ID of the second person.
     * @param date The date on which the close contact between these two people occured.
     */
    public void addContact(String id1, String id2, Date date){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try{
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Contact contact = new Contact();
            contact.setId1(id1);
            contact.setId2(id2);
            contact.setDateOfContact(date);
            entityManager.persist(contact);
            entityTransaction.commit();
        }
        catch (Exception exception){
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            exception.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * This method finds a person in a database via their ID.
     * @param id The ID of a person.
     * @return A list containing our results of a search query, in this case; a person/s with a particular ID.
     */

    public List<Person> getPersonById(String id){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.id = :id";

        TypedQuery<Person> typedQuery = entityManager.createQuery(query, Person.class);
        typedQuery.setParameter("id", id);
        List<Person> searchedPerson = null;

        try{
            searchedPerson = typedQuery.getResultList();
        }catch(NoResultException exception){
            exception.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return searchedPerson;
    }

    /**
     * This method creates an ObservableList using SQL queries for the purposes of display within a table.
     * @return A list containing the objects/results of a query to the database, provided the ID is not null.
     */
    public List<Person> createObservableListAll(){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.id IS NOT NULL";

        TypedQuery<Person> typedQuery = entityManager.createQuery(query,Person.class);
        List<Person> listOfAllPersons = null;

        try{
            listOfAllPersons = typedQuery.getResultList();
        }catch(NoResultException exception){
            exception.printStackTrace();
        }
        finally{
            entityManager.close();
        }
        return listOfAllPersons;

    }

    /**
     * This method removes a person from a database.
     * @param id The ID of a person.
     */
    public void removePerson(String id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Person person = null;
        Contact contact = null;

        try {
            et = em.getTransaction();
            et.begin();

            contact = em.find(Contact.class, id);
            //Need to also remove all contacts of said person
            person = em.find(Person.class, id);
            em.remove(contact);
            em.remove(person);
            et.commit();

        } catch (Exception ex) {
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    /**
     * This method finds contacts based on their ID and their dates of contact since a given date.
     * @param id The ID of a person.
     * @param dateOfContact The date on which a contact between two people occurred.
     * @return A list containing the results of the query; the contacts which occurred for a person with the given ID,
     * and after or on a given date.
     */
    public List<Contact> getContactByIdDate(String id, Date dateOfContact){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Contact c WHERE c.id1 = :id AND c.dateOfContact >= :dateOfContact";

        TypedQuery<Contact> tq = em.createQuery(query, Contact.class);
        tq.setParameter("id", id);
        tq.setParameter("dateOfContact",dateOfContact);
        List<Contact> matchedContacts = null;
        
        try{
            matchedContacts = tq.getResultList();
        }catch(NoResultException ex){
            ex.printStackTrace();
        }
        finally{
            em.close();
        }
        return matchedContacts;
    }

    /**
     * This method saves objects via serialisation.
     * @param saveList A list of person objects to be serialised
     * @throws IOException An exception to be displayed if there is a problem with our stream, be it corrupt, non-existent
     * or otherwise unreachable.
     */

    public void serialisedSave(List saveList) throws IOException {
        FileOutputStream fos = new FileOutputStream("serialisedPersons.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(saveList);
        oos.reset();
        oos.close();
    }

    /**
     * This method creates an endless loop, forcing an out of memory error (for the purposes of testing).
     */

    public void dummyLoop() {

        ArrayList<Person> collectionOfDummyObjects = new ArrayList<>();
        Person testPerson = new Person();
        testPerson.setId("Test");
        testPerson.setFirstName("Test");
        testPerson.setMiddleName("Test");
        testPerson.setLastName("Test");
        testPerson.setPhone("Test");
        testPerson.setEmail("Test");

        for(int i=0; i < Integer.MAX_VALUE; i++){
            collectionOfDummyObjects.add(testPerson);
        }
    }

}
