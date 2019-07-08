package com.project.contactlist.application.resource;


import com.project.contactlist.domain.model.Contact;
import com.project.contactlist.domain.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("contacts")
public class ContactResource {

    private final ContactService contactService;

    @Autowired
    public ContactResource(ContactService contactService) {
        this.contactService = contactService;
    }

//    @GetMapping
//    public ResponseEntity<List<Contact>> findAll(ContactFilter contactFilter) {
//        return new ResponseEntity<>(contactService.filterContact(contactFilter), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<Contact>> findAll() {
        return new ResponseEntity<>(contactService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "photo", consumes = {"multipart/form-data"})
    public ResponseEntity<Contact> createPhoto(@RequestPart(value = "file", required = false) MultipartFile file,
                                          @Valid @RequestPart(value = "contact") Contact contact) {

        return new ResponseEntity<>(contactService.create(contact, file), HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Contact> create(@Valid @RequestBody Contact contact) {

        return new ResponseEntity<>(contactService.create(contact), HttpStatus.CREATED);
    }


    @PutMapping(value = "photo/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Contact> update(@PathVariable Long id,
                                          @RequestPart(value = "file", required = false) MultipartFile file,
                                          @Valid @RequestPart Contact contact) {
        return new ResponseEntity<>(contactService.update(contact, id, file), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id,
                                          @Valid @RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.update(contact, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }
}
