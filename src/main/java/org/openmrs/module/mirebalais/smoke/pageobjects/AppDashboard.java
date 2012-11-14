/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppDashboard extends AbstractPageObject {

	public void openActiveVisitsApp() {
		driver.get(properties.getWebAppUrl());
		driver.findElement(By.linkText("Active Visits")).click();
	}
	
	public AppDashboard(WebDriver driver) {
		super(driver);
	}

	public void openArchivesRoomApp() {
		driver.get(properties.getWebAppUrl());
		driver.findElement(By.linkText("Archives Room")).click();
	}

	public void openPatientRegistrationApp() {
		driver.get(properties.getWebAppUrl());
		driver.findElement(By.linkText("Patient Registration")).click();
	}

	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
