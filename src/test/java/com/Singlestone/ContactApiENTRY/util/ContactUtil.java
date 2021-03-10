package com.Singlestone.ContactApiENTRY.util;

import com.Singlestone.ContactApiENTRY.Model.Contact;
import com.Singlestone.ContactApiENTRY.Model.Name;
import com.Singlestone.ContactApiENTRY.Model.Phone;
import com.Singlestone.ContactApiENTRY.Model.Address;

public class ContactUtil {
    public static Contact createContact_WithNoId(){
        Contact contact = new Contact();
        contact.setEmail("kevkafuku@thisisatest.com");

        Address address= new Address();
        address.setCity("City");
        address.setState("State");
        address.setStreet("Street");
        address.setZip("Zip");
        contact.setAddress(address);

        Name name = new Name();
        name.setFirstName("Kevin");
        name.setMiddleName("Ruguge");
        name.setLastName("Kafuku");
        contact.setName(name);

        Phone phone = new Phone();
        phone.setNumber("000-000-0000");
        phone.setNumberType("TESTING");
        contact.addPhone(phone);

        return contact;
    }
}
