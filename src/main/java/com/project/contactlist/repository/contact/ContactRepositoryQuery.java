package com.project.contactlist.repository.contact;

import com.project.contactlist.domain.model.Contact;
import com.project.contactlist.domain.model.QContact;
import com.viewb.contactlist.repository.filter.ContactFilter;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import java.util.List;

public interface ContactRepositoryQuery{
    List<Contact> filterContact();
}
