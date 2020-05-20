package qa.click.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import qa.click.base.Testbase;
import qa.click.utilities.TestUtil;

public class RegistrationTest extends Testbase{

	@Test(dataProviderClass=TestUtil.class,dataProvider = "dp")
	public void registrationTest(String name,String email,String pwd,String gender) throws InterruptedException {
		
		type("name_Xpath", name);
		type("email_Xpath", email);
		type("pwd_Xpath", pwd);
		
		click("chkbox_Xpath");
		Thread.sleep(2000);
		Select select=new Select(driver.findElement(By.xpath(prop.getProperty("dropbox_Xpath"))));
		select.selectByVisibleText(gender);
		click("DOB_Xpath");
		Thread.sleep(3000);
	}
}
