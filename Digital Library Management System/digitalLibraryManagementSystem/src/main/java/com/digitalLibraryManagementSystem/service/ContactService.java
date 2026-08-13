package com.digitalLibraryManagementSystem.service;

import java.util.List;

import com.digitalLibraryManagementSystem.dao.ContactDao;
import com.digitalLibraryManagementSystem.entity.ContactMessage;

public class ContactService {

	ContactDao contactDao = new ContactDao();

	public boolean addMessage(ContactMessage msg) {
		return contactDao.addMessage(msg);
	}

	public List<ContactMessage> getAllMessages() {
		return contactDao.getAllMessages();
	}
}