package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openmrs.module.mirebalais.smoke.helper.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class VitalsApp extends AbstractPageObject {

	public VitalsApp(WebDriver driver) {
		super(driver);
	}

	public void enterPatientIdentifier(String patientID) {
		setClearTextToField("patient-search-field-search", patientID);
	}

	public void confirmPatient() {
		driver.findElement(By.className("icon-arrow-right")).click();
	}

	public void enterVitals() {
        hitEnterOnInchesField();
        setClearTextToFieldThruSpan("height_cm", "15");
        hitEnterOnLbsField();
		setClearTextToFieldThruSpan("weight_kg", "50");
		hitEnterOnBMI();
        hitEnterOnFahrenheitField();
		setClearTextToFieldThruSpan("temperature_c", "36");
		setClearTextToFieldThruSpan("heart_rate", "50");
		setClearTextToFieldThruSpan("respiratory_rate", "50");
		setClearTextToFieldThruSpan("bp_systolic", "120");
		setClearTextToFieldThruSpan("bp_diastolic", "80");
		setClearTextToFieldThruSpan("o2_sat", "50");
		
		driver.findElement(By.id("confirmationQuestion")).findElement(By.className("confirm")).click();
	}

	private void hitEnterOnBMI() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementById('hidden-calculated-bmi').setAttribute('type', 'text');");
		driver.findElement(By.id("hidden-calculated-bmi")).clear();
		driver.findElement(By.id("hidden-calculated-bmi")).sendKeys(Keys.RETURN);
	}

    private void hitEnterOnFahrenheitField() {
        driver.findElement(By.id("temperature_f")).sendKeys(Keys.RETURN);
    }

    private void hitEnterOnLbsField() {
        driver.findElement(By.id("weight_lbs")).sendKeys(Keys.RETURN);
    }

    private void hitEnterOnInchesField() {
        driver.findElement(By.id("height_inches")).sendKeys(Keys.RETURN);
    }

	public void captureVitalsForPatient(String identifier) {
		enterPatientIdentifier(identifier);
		confirmPatient();
		enterVitals();
	}

	public Boolean isSearchPatientDisplayed() {
        try {
        	Waiter.waitForElementToDisplay(By.className("scan-input"), 5, driver);
        	return true;
        } catch (Exception e) {
        	return false;
        }
	}
	
}
