
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TatocTest {
	public static void main (String[]args) { }
	  WebDriver webdriver ;
	
			  @BeforeTest
			  
	   public void setpath(){			  
	  System.setProperty("webdriver.chrome.driver","C:\\Users\\shivanjalisingh\\Downloads\\chromedriver_win32\\chromedriver.exe");
	  webdriver = new ChromeDriver();
	  webdriver.get("http://10.0.1.86/tatoc/basic/grid/gate");
			  }
			  
			  @Test(priority = 0)
				public void clickOnGreenBox() {
					
					webdriver.findElement(By.cssSelector("td div.greenbox")).click();
				}

				@Test(priority = 1)

				public void repaintBox() {
					webdriver.switchTo().frame(webdriver.findElement(By.id("main")));
					String Box1 = webdriver.findElement(By.xpath("//div[text()='Box 1']")).getAttribute("class");
					System.out.println("Hii" + Box1);
					webdriver.switchTo().frame(webdriver.findElement(By.id("child")));
					String Box2 = webdriver.findElement(By.xpath("//div[text()='Box 2']")).getAttribute("class");
					while (!Box1.equals(Box2)) {
						webdriver.switchTo().defaultContent();
						webdriver.switchTo().frame(webdriver.findElement(By.id("main")));
						webdriver.findElement(By.xpath("//*[text()[contains(.,'Repaint Box 2')]]")).click();
						webdriver.switchTo().frame(webdriver.findElement(By.id("child")));
						Box2 = webdriver.findElement(By.xpath("//div[text()='Box 2']")).getAttribute("class");

					}
					webdriver.switchTo().defaultContent();
					webdriver.switchTo().frame(webdriver.findElement(By.id("main")));
					webdriver.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]")).click();
				}

				@Test(priority = 2)
				public void dragAndDrop() {
					webdriver.switchTo().defaultContent();

					WebElement drag = webdriver.findElement(By.xpath("//*[text()[contains(.,'DRAG ME')]]"));
					WebElement drop = webdriver.findElement(By.xpath("//*[text()[contains(.,'DROPBOX')]]"));
					Actions act = new Actions(webdriver);
					act.dragAndDrop(drag, drop).build().perform();
					webdriver.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]")).click();
				}

				@Test(priority = 3)
				public void launchPopUpWidow() {
					webdriver.findElement(By.xpath("//*[text()[contains(.,'Launch Popup Window')]]")).click();

					for (String handle : webdriver.getWindowHandles()) {

						webdriver.switchTo().window(handle);
					}
					webdriver.findElement(By.id("name")).sendKeys("shivanjali");
					webdriver.findElement(By.id("submit")).click();
					for (String handle : webdriver.getWindowHandles()) {

						webdriver.switchTo().window(handle);
					}
					webdriver.findElement(By.xpath("//*[text()[contains(.,'Proceed')]]")).click();
				}
				
				@Test(priority=4)
			public void generateCookie () 
				{
					webdriver.findElement(By.xpath("//a[contains(text(),'Generate Token')]")).click();
					String token = webdriver.findElement(By.xpath("//span[@id='token']")).getText();
					token = token.substring(7);
					System.out.println(token);
					Cookie name = new Cookie("Token", token);
					webdriver.manage().addCookie(name);
					webdriver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();

				}
			}

