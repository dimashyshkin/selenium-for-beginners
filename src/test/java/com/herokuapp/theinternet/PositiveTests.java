package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void logInTest() {
		System.out.println("Starting logIn test");
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		sleep(3000);

		driver.manage().window().maximize();

		// open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened.");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		// push log in button
		WebElement logInButton = driver.findElement(By.className("radius"));
		logInButton.click();

		// verifications
		// new url
		String expectedPageUrl = "http://the-internet.herokuapp.com/secure";
		String actualPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedPageUrl, actualPageUrl);

		// log out button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "LogOutButtos is not displayed.");

		// Successful log in message
		WebElement successMessage = driver.findElement(By.id("flash"));
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = successMessage.getText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"Actual success message does not contain expected\nactualSuccessMessage: " + actualSuccessMessage
						+ "\nexpectedSuccessMessage: " + expectedSuccessMessage);

		sleep(3000);

		// Close browser
		driver.quit();

	}

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
