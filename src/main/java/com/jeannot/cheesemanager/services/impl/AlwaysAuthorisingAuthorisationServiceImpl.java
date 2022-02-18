package com.jeannot.cheesemanager.services.impl;

import org.springframework.stereotype.Service;

import com.jeannot.cheesemanager.services.AuthorisationService;

@Service
public class AlwaysAuthorisingAuthorisationServiceImpl implements AuthorisationService {

	@Override
	public boolean authoriseUser(String data) {
		return true;
	}

}
