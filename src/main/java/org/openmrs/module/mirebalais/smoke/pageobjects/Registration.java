package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registration extends AbstractPageObject {

	private Wait<WebDriver> wait;
	
    private static final String[] FIRST_NAMES = 
    	{"Alexandre", "Achint", "Burke", "Cosmin", "Daniel", "Darius", "Ellen", "Émerson", "Evan", "Fernando", 
    	"Glauber", "Louise", "Mário", "Mark", "Natália", "Neil", "Nelice", "Rafal", "Renee", "Wyclif"};
	private static final String[] LAST_NAMES = 
		{"Barbosa", "Sethi", "Mamlin", "Ioan", "Kayiwa", "Jazayeri", "Ball", "Hernandez", "Waters", "Freire", 
		"Ramos", "Sécordel", "Areias", "Goodrich", "Arsand", "Craven", "Heck", "Korytkowski", "Orser", "Luyima"}; 
	
	public Registration(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 5);
    }
	
	public void goThruRegistrationProcessWithoutPrintingCard() {
		registerPatient();
        chooseNotToPrintIdCard();
    }
	
	public void goThruRegistrationProcessPrintingCard() {
		registerPatient();
        chooseToPrintIdCard();
    }

    public String registerSpecificGuyWithoutPrintingCard(String name) {
    	clickOnSearchByNameButton();
		enterFirstAndLastName(name);
		enterSexData();
		enterDateOfBirthData();
		enterAddressLandmarkData();
		enterPatientLocality();
		enterPhoneData();
		confirmData();
        chooseNotToPrintIdCard();
        return driver.findElement(By.id("patientPreferredId")).getText();
    }

	private void registerPatient() {
		clickOnSearchByNameButton();
		enterFirstAndLastName();
		enterSexData();
		enterDateOfBirthData();
		enterAddressLandmarkData();
		enterPatientLocality();
		enterPhoneData();
		confirmData();
	}
	
	public void openSimilarityWindow(String name) {
		clickOnSearchByNameButton();
		enterFirstAndLastName(name);
		
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return 	webDriver.findElement(By.id("confirmExistingPatientDiv")).isDisplayed() &&
						! (webDriver.findElement(By.id("confirmExistingPatientDiv")).getText().contains("searching"));
			}
		});
		driver.findElement(By.id("confirmExistingPatientDiv")).click();
		
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElement(By.id("confirmExistingPatientModalDiv")).isDisplayed();
			}
		});
	}

	private void clickOnSearchByNameButton() {
		driver.findElement(By.id("searchByNameBtn")).click();
	}

    private void enterFirstAndLastName() {
		enterFirstAndLastName(new StringBuilder(getFirstName() + " " + getLastName()).toString());
	}

	public String getFirstName() {
		return FIRST_NAMES[(int)(Math.random() * FIRST_NAMES.length)];
	}

	public String getLastName() {
		return LAST_NAMES[(int)(Math.random() * LAST_NAMES.length)];
	}

    public void finishesRegistration() {
		driver.findElement(By.id("cancelBtn")).click();
		enterSexData();
		enterDateOfBirthData();
		enterAddressLandmarkData();
		enterPatientLocality();
		enterPhoneData();
		confirmData();
        chooseNotToPrintIdCard();
        clickYellowCheckMark();
    }

    protected void enterPatientLocality() {
        driver.findElement(By.id("possibleLocalityField")).sendKeys("Mirebalais");
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver webDriver) {
                return !webDriver.findElement(By.id("loadingGraph")).isDisplayed();
            }
        });
        driver.findElement(By.cssSelector("tr.addressFieldRow.evenRow > td")).click();
        clickNext();

        driver.findElement(By.cssSelector("table#confirmPossibleLocalityModalList.searchTableList > tbody > tr.addressRow > td")).click();
    }

    protected void enterSexData() {
        driver.findElement(By.id("rdioM")).click();
        clickNext();
    }
    
    protected void enterDateOfBirthData() {
    	int day = 1 + (int)(Math.random() * 28);
    	int year = 1930 + (int)(Math.random() * 71);
        driver.findElement(By.id("day")).sendKeys(new Integer(day).toString());
        driver.findElement(By.id("ui-active-menuitem")).click();
        driver.findElement(By.id("year")).sendKeys(new Integer(year).toString());
        clickNext();
    }

    protected void enterAddressLandmarkData() {
		driver.findElement(By.id("addressLandmarkField")).sendKeys("mirebalais");
		clickNext();
	}

    protected void enterPhoneData() {
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElement(By.id("patientInputPhoneNumber")).isDisplayed();
			}
		});
		driver.findElement(By.id("patientInputPhoneNumber")).sendKeys("11111111");
        clickNext();
    }

    protected void confirmData() {
        clickCheckMark();
    }

    private void clickCheckMark() {
		driver.findElement(By.id("checkmark-yellow")).click();
	}

    protected void enterFirstName(String firstName) {
        driver.findElement(By.id("patientInputFirstName")).sendKeys(firstName);
        clickNext();
    }

    protected void enterLastName(String lastName) {
        driver.findElement(By.id("patientInputLastName")).sendKeys(lastName);
        clickNext();
    }

    protected void enterFirstAndLastName(String name) {
    	int spaceIndex = name.indexOf(' '); 
        enterLastName(name.substring(spaceIndex).trim());
        enterFirstName(name.substring(0, spaceIndex).trim());
	}

    protected void chooseNotToPrintIdCard() {
        driver.findElement(By.id("printNo")).click();
    }

    protected void chooseToPrintIdCard() {
        driver.findElement(By.id("printYes")).click();
    }
	
}
