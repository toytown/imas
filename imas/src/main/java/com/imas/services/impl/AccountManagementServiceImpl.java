package com.imas.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.imas.dao.interfaces.UserDao;
import com.imas.model.User;
import com.imas.services.interfaces.AccountManagementService;
import com.imas.valueobjects.ContactRequest;

@Transactional
public class AccountManagementServiceImpl implements AccountManagementService {

	@Autowired
	private UserDao userDao;


	@Override
	public User findUser(String userName, String password) {
		return userDao.findUser(userName, password);
	}

	@Override
	@Transactional
	public boolean sendActivationEmail(User user) {
	    /*
		if (user != null && user.getContactDetails() != null && !user.getContactDetails().isEmpty()) {
			PageParameters pm = new PageParameters();
			pm.put("userId", user.getId().toString());
			pm.put("activationCode", user.getActivationCode());
			
			CharSequence charSeq = RequestCycle.get().urlFor(CustomerActivationPage.class, pm);
			String url = RequestUtils.toAbsolutePath(charSeq.toString());
			sendEmail(customer, url);
			return true;
		}
		*/
		return false;
	}

	@Override
	@Transactional
	public boolean sendPasswordEmail(String userName, String oldPassword) {
		if (userName != null && oldPassword != null) {
			User user = findUser(userName, oldPassword);
			String newPassword = generateNewPassword(user);
			user.setPassword(newPassword);
			this.update(user);
			return true;
		}
		return false;
	}
	
	public String generateNewPassword(User user) {
		String oldPassword = user.getPassword();
		String newPassword = user.getUserName() + oldPassword .hashCode() * 13;
		return newPassword;
	}

    @Override
	@Transactional
	public void save(User aUser) {
		aUser.setActivationCode(UUID.randomUUID().toString());
		userDao.save(aUser);
		sendActivationEmail(aUser);
	}

	@Transactional
	public User update(User aUser) {
		return userDao.update(aUser);
	}

	/*
	private void sendEmail(final User user, final String url) {
	    
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				for (CustomerContactDetails contact : customer.getContactDetails()) {
					message.setTo(contact.getEmail());
				}

				message.setFrom("ptuladhar@gmx.net");
				Map model = new HashMap();
				model.put("user", customer);
				model.put("url", url);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "registration-confirmation.vm", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
		
	}
*/

	public void sendContactMessage(final User user, final ContactRequest contactRequest) {
	    /*
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				for (CustomerContactDetails contact : customer.getContactDetails()) {
					message.setTo(contact.getEmail());
				}

				message.setFrom(contactRequest.getEmail());
				Map model = new HashMap();
				model.put("user", customer);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "contact_information.vm", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);		
		*/
	}

    @Override
    public User findUserById(Long userId) {
        return userDao.findById(userId);
    }
}
