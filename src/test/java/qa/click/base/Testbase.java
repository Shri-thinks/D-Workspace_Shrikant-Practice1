package qa.click.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import qa.click.utilities.ExcelReader;

public class Testbase {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\data.xlsx");
	public static WebDriverWait wait=new WebDriverWait(driver, 5);

	@BeforeSuite
	public void setUp() {

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "//src//test//resources//properties//OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(config.getProperty("browser").contains("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//executables//chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(config.getProperty("testsiteURL"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		}

	}
	
	public void click(String locator) {
		try {
		driver.findElement(By.xpath(prop.getProperty(locator))).click();
		}catch(Exception e) {
			
		}
	}
	
	public void type(String locator,String value) {
		
		driver.findElement(By.xpath(prop.getProperty(locator))).sendKeys(value);
	}

	public void tearDown() {

	}
}
