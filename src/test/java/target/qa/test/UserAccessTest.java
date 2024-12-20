package target.qa.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import target.qa.base.Baseclass;
import target.qa.main.UserAccess;

public class UserAccessTest extends Baseclass{

	public UserAccessTest() {
		super();
	}
	
	UserAccess uA;
	
	@DataProvider(name="signUpData")
	public  Object[][] signUpData(){
		Object[][] signUpData = new Object[1][5];
		signUpData[0][0]="singhashu772@gmail.com";
		signUpData[0][1]="Ashu";
		signUpData[0][2]="Singh";
		signUpData[0][3]="7042579843";
		signUpData[0][4]="Ashu@123";
		
		return signUpData;
	}
	
	@Test(dataProvider = "signUpData")
	public void signUP(String uname, String fname, String lname, String phone, String pwd) throws InterruptedException {
		uA = new UserAccess();
		uA.signUP(uname, fname, lname, phone, pwd);
	}
	
	@Test
	public void login() {
		uA = new UserAccess();
		uA.login("Singhashu772@gmail.com", "Ashu@123");
	}
}
