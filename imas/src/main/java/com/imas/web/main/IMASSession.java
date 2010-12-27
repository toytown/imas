package com.imas.web.main;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.imas.model.User;
import com.imas.services.interfaces.AccountManagementService;

public class IMASSession extends WebSession {

    private static final long serialVersionUID = 1L;

    private User customer = null;
	
	private AccountManagementService accountManagementService;
	
	public IMASSession(Request request) {
		super(request);
	}

	/**
	 * Checks the given customer name and password, returning a Customer object if if the
	 * customer name and password identify a valid customer.
	 * 
	 * @param username
	 *            The customer name
	 * @param password
	 *            The password
	 * @return True if the customer was authenticated
	 */
	public synchronized final boolean authenticate(final String userName, final String password) {
		
		if (this.customer == null) {
			User aUser = accountManagementService.findUser(userName, password);
			
			if (aUser != null) {
				this.customer = aUser;
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}	
	}
	
	/**
	 * @return True if customer is signed in
	 */
	public boolean isSignedIn() {
		return customer != null;
	}

	/**
	 * @return Customer
	 */
	public User getUser() {
		return customer;
	}

	/**
	 * @param customer
	 *            New customer
	 */
	public void setUser(final User customer) {
		this.customer = customer;
	}

}
