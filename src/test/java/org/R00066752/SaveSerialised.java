package org.R00066752;

import Model.Person;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// To test whether or not objects can be saved in this way via serialisation
public class SaveSerialised {
    @Test
    public void serialisedSave() throws IOException {

        ArrayList<Person> saveList = new ArrayList<>();
        Person testPerson = new Person();
        testPerson.setId("Test");
        testPerson.setFirstName("Test");
        testPerson.setMiddleName("Test");
        testPerson.setLastName("Test");
        testPerson.setPhone("Test");
        testPerson.setEmail("Test");
        saveList.add(testPerson);

        FileOutputStream fos = new FileOutputStream("serialisedPersons.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(saveList);
        oos.reset();
        oos.close();
    }

    public void understand(){
        System.out.println("understand");
        
    }

}
