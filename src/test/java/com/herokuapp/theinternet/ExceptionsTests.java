package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ExceptionsTests {
	WebDriver driver;

	@BeforeMethod
	private WebDriver setUp() {
		System.out.println("Creating driver");
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();

		sleep(3000);

		driver.manage().window().maximize();
		return driver;
	}

	@AfterMethod
	private void tearDown() {
		sleep(3000);
		System.out.println("Close driver");
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
