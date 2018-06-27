package com.jobapplication;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XpathMentoring {

	WebDriver driver;

	String firstName;
	String lastName;
	String companyName;
	String jobTittle;
	String businessemail;
	String phoneNumber;
	Faker data = new Faker();

	@BeforeClass
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.orangehrm.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void Forms() {
		firstName = data.name().firstName();
		lastName = data.name().lastName();
		companyName = "cybertek";
		jobTittle = "worker";
		businessemail = "melsejas@yahoo.com";
		phoneNumber = data.phoneNumber().cellPhone().replace(".", "");
	}

	@Test
	public void RequestAQuote() {
		// using
		driver.findElement(By.xpath("//a[@class='btn-orange btn btn--primary '] ")).click();
		// using text-select professional
		driver.findElement(By.xpath("//*[@id='Form_request_Package']")).sendKeys("Professional" + Keys.ENTER);
		// using first name
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys(firstName);
		// using last name
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys(lastName);
		// company name
		driver.findElement(By.xpath("//*[@id='Form_request_CompanyName']")).sendKeys(companyName);
		//select industry 
		
		
		// job title
		driver.findElement(By.xpath("//input[@name='JobTitle']")).sendKeys(jobTittle);
		// phone number
		driver.findElement(By.xpath("//*[@id='Form_request_ContactPhone']")).sendKeys(phoneNumber);
	}

	@Test
	// select random country
	public void randomCountry() {
		WebElement country = driver.findElement(By.id("Form_request_Country"));
		Select selectCountry = new Select(country);
		Select selectCountry1 = new Select(driver.findElement(By.id("Form_request_Country")));
		String demo = selectCountry.getFirstSelectedOption().getText();
		selectCountry.selectByIndex(56);
	}

	@Test
	public void slideShow() throws InterruptedException {
		driver.get("https://cybertekschool.com/image-gallery/");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();

		for (WebElement imagegallery : images) {
			srcs.add(imagegallery.getAttribute("src"));
		}

		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}
		driver.close();
	}
}