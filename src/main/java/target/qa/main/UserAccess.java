package target.qa.main;

import java.time.Duration;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import target.qa.base.Baseclass;

public class UserAccess extends Baseclass{

	public UserAccess() {
		PageFactory.initElements(driver, this);
	}
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(id="account-sign-in")
	WebElement accounts;
	
	@FindBy(xpath="//button[text()='Create account']")
	WebElement createAccount;
	
	@FindBy(xpath="//button[text()='Sign in']")
	WebElement signIN;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="firstname")
	WebElement fname;
	
	@FindBy(id="lastname")
	WebElement lastname;
	
	@FindBy(id="phone")
	WebElement phone;
	
	@FindBy(id="password-checkbox")
	WebElement pwdCheck;
	
	@FindBy(id="password")
	WebElement pwd;
	
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(id="createAccount")
	WebElement accountCreate;
	
	
	public void signUP(String uname, String fname, String lname, String phone, String pwd){
		js.executeScript("arguments[0].click()",accounts);
		js.executeScript("arguments[0].click()",createAccount);
		
		wait.until(ExpectedConditions.visibilityOf(username));
		
		username.sendKeys(uname);
		this.fname.sendKeys(fname);
		lastname.sendKeys(lname);
		this.phone.sendKeys(phone);
		
		js.executeScript("arguments[0].click()",pwdCheck);
		wait.until(ExpectedConditions.elementToBeSelected(pwdCheck));
		
		byte[] encodedBytes = Base64.encodeBase64(pwd.getBytes());
		this.pwd.sendKeys(new String(encodedBytes));
		System.out.println(encodedBytes);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].click()",accountCreate);
		
		
		
		Reporter.log("Account is Created..");
	}
	
	
	public void login(String email, String pwd) {
		js.executeScript("arguments[0].click()",accounts);
		js.executeScript("arguments[0].click()",signIN);
		wait.until(ExpectedConditions.visibilityOf(username));
		this.username.sendKeys(email);
		byte[] encodedPwd = Base64.encodeBase64(pwd.getBytes());
		this.pwd.sendKeys(new String(encodedPwd));
		System.out.println(encodedPwd);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Login.click();
	}
}
