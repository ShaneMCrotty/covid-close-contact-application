package org.R00066752;

import Model.Person;
import org.junit.Test;

import java.util.ArrayList;

public class EndlessLoop {
    @Test
    // To test whether or not we will get an Out of Memory Error (Java heap space)
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
