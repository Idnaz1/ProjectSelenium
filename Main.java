
import com.google.common.annotations.VisibleForTesting;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        WebDriver Driver = new ChromeDriver();
    }
    @Test
    void registrationProcessTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(); // Call to the Webdriver by "driver" var
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/register"); // Get to this site
        driver.findElement(By.id("input-firstname")).sendKeys("Idan"); // find the element "First name" & write this text
        driver.findElement(By.id("input-lastname")).sendKeys("Azuri"); // find the element "last name" & write this text
        driver.findElement(By.id("input-email")).sendKeys("idn.az110@gmail.com"); // find the element "Email" & write this text
        driver.findElement(By.id("input-telephone")).sendKeys("0545646076"); // find the element "Telephone" & write this text
        driver.findElement(By.id("input-password")).sendKeys("123456"); // find the element "First name" & write this text
        driver.findElement(By.id("input-confirm")).sendKeys("123456"); // find the element "Confirm" & write this text
        driver.findElement(By.name("agree")).click(); // find the element "Agree" & write this text
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]")).click(); // find the element by xpath & click
        Assert.assertEquals("http://tutorialsninja.com/demo/index.php?route=account/success" , driver.getCurrentUrl()); // Check if this is the correct and exact address of the site by matching.
    }
    @Test
    void loginProcessTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(); // Call to the Webdriver by "driver" var
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/login"); // Get to this site
        driver.findElement(By.id("input-email")).sendKeys("idn.az15@gmail.com"); // find the element "Email" & write this text
        driver.findElement(By.id("input-password")).sendKeys("123456"); // find the element "Password" & write this text
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();  // find the element by xpath & click
        Assert.assertEquals("http://tutorialsninja.com/demo/index.php?route=account/login" , driver.getCurrentUrl()); // Check if this is the correct and exact address of the site by matching.
    }
    @Test
    void addToCartProcessTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(); // Call to the Webdriver by "driver" var
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=43"); // Get to this site
        driver.findElement(By.id("button-cart")).click(); // find the element by id & click
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=33"); // Get to this site
        driver.findElement(By.id("button-cart")).click(); // find the element by id & click
    }
}