package stepDefinitions;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginStepDefinition{
	WebDriver driver = new ChromeDriver();
	
	 @Given("user is already on Login Page")
	 public void enterUrl() throws InterruptedException{
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\Public\\artifact_environment\\Selenium\\chromedriver.exe");
		   driver.get("https://qa-fake.herokuapp.com/");
		   driver.manage().window().maximize();
		   Thread.sleep(5000);
		}

		@When ("title of login page is QA Fake")
		public void verifyTitle(){
		String expTitle = "QA Fake";
		Assert.assertEquals(expTitle, driver.getTitle());
		}
		
		@Then ("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
		public void enterCredentials(String username,String password){		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		}

		@Then("user checked rember me")
		public void rememberCheckbox() {
		driver.findElement(By.id("rememberme")).click();
		}

		@Then ("user clicks on login button")
		public void clickLogin(){
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		}
	

		@Then ("user get the secret string on home screen")
		public void getString() throws FileNotFoundException{
		String text=driver.findElement(By.xpath("//div[@id='result']")).getText();
		PrintWriter out = new PrintWriter("result.txt");
		out.println(text);

		}

		@Then ("Close the browser")
		public void closeBrowser() {
		driver.close();
		}
	 
}
