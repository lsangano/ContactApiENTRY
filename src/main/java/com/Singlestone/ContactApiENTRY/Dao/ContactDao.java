package com.Singlestone.ContactApiENTRY.Dao;

import com.Singlestone.ContactApiENTRY.Model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends CrudRepository<Contact, Long> {
}
