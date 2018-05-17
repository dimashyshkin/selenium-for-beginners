package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	private void setUp(@Optional("chrome") String browser) {
		// Create driver
		System.out.println("Create driver: " + browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome.");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		sleep(3000);

		driver.manage().window().maximize();
	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, groups = { "smokeTests", "negativeTests" })
	public void negativeTest(String username, String password, String expectedErrorMessage) {
		System.out.println("Starting negativeTest");

		// open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened.");

		// enter incorrect username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);

		// enter password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);

		// push log in button
		WebElement logInButton = driver.findElement(By.className("radius"));
		logInButton.click();

		// Verification
		WebElement errorMessageElement = driver.findElement(By.id("flash"));
		String actualErrorMessage = errorMessageElement.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
						+ expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
	}

	@AfterMethod
	private void tearDown() {
		sleep(3000);
		System.out.println("Close driver");
		// Close browser
		driver.quit();
	}

	/*
	 * @Test(priority = 1, groups = { "smokeTests", "negativeTests" }) public void
	 * incorrectUsernameTest() {
	 * System.out.println("Starting incorrectUsernameTest");
	 * 
	 * // Create driver System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); WebDriver driver = new
	 * ChromeDriver();
	 * 
	 * sleep(3000);
	 * 
	 * driver.manage().window().maximize();
	 * 
	 * // open the page String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url); sleep(2000); System.out.println("Page is opened.");
	 * 
	 * // enter incorrect username WebElement username =
	 * driver.findElement(By.id("username"));
	 * username.sendKeys("incorrectUsername");
	 * 
	 * // enter password WebElement password =
	 * driver.findElement(By.id("password"));
	 * password.sendKeys("SuperSecretPassword!");
	 * 
	 * // push log in button WebElement logInButton =
	 * driver.findElement(By.className("radius")); logInButton.click();
	 * 
	 * // Verification WebElement errorMessage = driver.findElement(By.id("flash"));
	 * String expectedErrorMessage = "Your username is invalid!"; String
	 * actualErrorMessage = errorMessage.getText();
	 * Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
	 * "actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
	 * + expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
	 * 
	 * sleep(3000);
	 * 
	 * // Close browser driver.quit();
	 * 
	 * }
	 * 
	 * 
	 * @Test(priority = 2, groups = { "negativeTests" }) public void
	 * incorrectPasswordTest() {
	 * System.out.println("Starting incorrectPasswordTest");
	 * 
	 * // Create driver System.setProperty("webdriver.gecko.driver",
	 * "src/main/resources/geckodriver.exe"); WebDriver driver = new
	 * FirefoxDriver();
	 * 
	 * sleep(3000);
	 * 
	 * driver.manage().window().maximize();
	 * 
	 * // open the page String url = "http://the-internet.herokuapp.com/login";
	 * driver.get(url); sleep(2000); System.out.println("Page is opened.");
	 * 
	 * // enter incorrect username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith");
	 * 
	 * // enter password WebElement password =
	 * driver.findElement(By.id("password"));
	 * password.sendKeys("incorrectPassword!");
	 * 
	 * // push log in button WebElement logInButton =
	 * driver.findElement(By.className("radius")); logInButton.click();
	 * 
	 * // Verification WebElement errorMessage = driver.findElement(By.id("flash"));
	 * String expectedErrorMessage = "Your password is invalid!"; String
	 * actualErrorMessage = errorMessage.getText();
	 * Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
	 * "actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
	 * + expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
	 * 
	 * sleep(3000);
	 * 
	 * // Close browser driver.quit();
	 * 
	 * }
	 */
	/** Static sleep */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
