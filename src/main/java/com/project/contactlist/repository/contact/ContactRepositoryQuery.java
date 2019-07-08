package com.project.contactlist.repository.contact;

import com.project.contactlist.domain.model.Contact;
import com.viewb.contactlist.repository.filter.ContactFilter;

import java.util.List;

public interface ContactRepositoryQuery {
    List<Contact> filterContact();
}
