package com.project.contactlist.repository.contact;

import com.project.contactlist.domain.model.Contact;
import com.project.contactlist.domain.model.QContact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ContactRepositoryQueryImpl implements ContactRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Contact> filterContact() {
        return null;
    }
}
