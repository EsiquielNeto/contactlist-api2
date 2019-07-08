package com.project.contactlist.domain.service;

import com.project.contactlist.application.exception.GenericException;
import com.project.contactlist.domain.model.Contact;
import com.project.contactlist.repository.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final UploadPhotoService uploadPhotoService;

    @Autowired
    public ContactService(ContactRepository contactRepository, UploadPhotoService uploadPhotoService) {
        this.contactRepository = contactRepository;
        this.uploadPhotoService = uploadPhotoService;
    }

    private Class<Contact> getEntityClass(){
        return Contact.class;
    }

    private String getModelName() {
        return this.getEntityClass().getSimpleName().substring(0, 1).toLowerCase() + this.getEntityClass().getSimpleName().substring(1);
    }

//    public List<Contact> filterContact(ContactFilter contactFilter) {
//        return contactRepository.filterContact(contactFilter);
//    }

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findById(Long id) {
        existsById(id);
        return contactRepository.getOne(id);
    }

    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact create(Contact contact, MultipartFile file) {

        if (file != null) {
            contact.setPhoto(uploadPhotoService.uploadPhoto(file));
        }

        return contactRepository.save(contact);
    }

    public Contact update(Contact contact, Long id) {
        Contact contactSave = findById(id);

        BeanUtils.copyProperties(contact, contactSave, "id");

        return contactRepository.save(contactSave);
    }

    public Contact update(Contact contact, Long id, MultipartFile file) {
        Contact contactSave = findById(id);

        if (!StringUtils.isEmpty(contactSave.getPhoto()) || contactSave.getPhoto() != null) {
            uploadPhotoService.removePhoto(file, contactSave.getPhoto());
            contact.setPhoto(uploadPhotoService.uploadPhoto(file));
        } else {
            contact.setPhoto(uploadPhotoService.uploadPhoto(file));
        }

        BeanUtils.copyProperties(contact, contactSave, "id");

        return contactRepository.save(contactSave);
    }

    public void delete(Long id) {
        existsById(id);
        contactRepository.deleteById(id);
    }

    private void existsById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new GenericException(getModelName() + ".notFound");
        }
    }
}
