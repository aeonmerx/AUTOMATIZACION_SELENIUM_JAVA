package test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class CrossBrowserTesting {
	private  WebDriver driver; 
	By searchBox = By.name("q");
	By videolocator = By.xpath("//a[contains(@href, 'youtube.com/channel/UCwgjdrBsYZsnpHF_kCG0FMA')]");
  @BeforeClass
  @Parameters({"URL","BrowserType"})
  public void beforeClass(String url , String browserType) {
	  if (browserType.equalsIgnoreCase("Chrome")) {
		  System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver.exe");
		  driver = new ChromeDriver();
	} else if(browserType.equalsIgnoreCase("Firefox")) {
		  System.setProperty("webdriver.gecko.driver", "src/test/resources/firefoxdriver/geckodriver.exe");
		  driver = new FirefoxDriver();
	} else if(browserType.equalsIgnoreCase("Edge")){
		System.setProperty("webdriver.edge.driver", "src/test/resources/iedriver/msedgedriver.exe");
		  driver = new EdgeDriver();
	}
	  driver.manage().window().maximize();
	  driver.get(url);
	  System.out.println("Opening: "+ browserType);
  }

  @Test
  public void googleSearch() {
	
	  WebElement searchboxElement = driver.findElement(searchBox) ;
	  searchboxElement.clear();
	  searchboxElement.sendKeys("Aeon Merx");
	  searchboxElement.submit();
	  
	WebDriverWait wait = new WebDriverWait(driver, 7);
	  wait.until(ExpectedConditions.presenceOfElementLocated(videolocator));
	  assertTrue(driver.findElement(videolocator).isDisplayed());
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
