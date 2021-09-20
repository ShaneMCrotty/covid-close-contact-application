package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id1")
    private String id1;

    @Column(name = "id2")
    private String id2;

    @Column(name = "dateOfContact")
    private Date dateOfContact;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public Date getDateOfContact() {
        return dateOfContact;
    }

    public void setDateOfContact(Date dateOfContact) {
        this.dateOfContact = dateOfContact;
    }
}