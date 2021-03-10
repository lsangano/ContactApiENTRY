package com.Singlestone.ContactApiENTRY.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="ID_SEQ")
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private Name name;

    @Column(name = "Address")
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "Phone")
    private List<Phone> phone;

    @Column(name = "Email")
    private String email;

    public Contact(){
        this.phone = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phoneList) {
        this.phone = phoneList;
    }

    public void addPhone(Phone singlePhone){
        if(this.phone == null)
            this.phone = new ArrayList<>();
        this.phone.add(singlePhone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}