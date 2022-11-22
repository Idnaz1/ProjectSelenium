
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
        var firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("Idan"); // find the element "First name" & write this text
        var lastName = driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Azuri"); // find the element "last name" & write this text
        var eMail = driver.findElement(By.id("input-email"));
        eMail.sendKeys("idn.az01110@gmail.com"); // find the element "Email" & write this text
        var telephoneNub = driver.findElement(By.id("input-telephone"));
        telephoneNub.sendKeys("0545646076"); // find the element "Telephone" & write this text
        var passwordNum = driver.findElement(By.id("input-password"));
        passwordNum.sendKeys("123456"); // find the element "First name" & write this text
        var confirmBotm = driver.findElement(By.id("input-confirm"));
        confirmBotm.sendKeys("123456"); // find the element "Confirm" & write this text
        var agreeBotm = driver.findElement(By.name("agree"));
        agreeBotm.click(); // find the element "Agree" & write this text
        var continueBotm = driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"));
        continueBotm.click(); // find the element by xpath & click
        Assert.assertEquals("http://tutorialsninja.com/demo/index.php?route=account/success" , driver.getCurrentUrl()); // Check if this is the correct and exact address of the site by matching.
    }
    @Test
    void loginProcessTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(); // Call to the Webdriver by "driver" var
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/login"); // Get to this site
        var eMail2 = driver.findElement(By.id("input-email"));
        eMail2.sendKeys("idn.az010155@gmail.com"); // find the element "Email" & write this text
        var password2 = driver.findElement(By.id("input-password"));
        password2.sendKeys("123456"); // find the element "Password" & write this text
        var loginBotm = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));
        loginBotm.click();  // find the element by xpath & click
        Assert.assertEquals("http://tutorialsninja.com/demo/index.php?route=account/login" , driver.getCurrentUrl()); // Check if this is the correct and exact address of the site by matching.
    }
    @Test
    void addToCartProcessTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(); // Call to the Webdriver by "driver" var
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=43"); // Get to this site
        var addToCartBotm = driver.findElement(By.id("button-cart"));
        addToCartBotm.click(); // find the element by id & click
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=33"); // Get to this site
        var addToCartBotm2 = driver.findElement(By.id("button-cart"));
        addToCartBotm2.click(); // find the element by id & click
        var shoppingCartBotm = driver.findElement(By.xpath("//*[@id=\"cart\"]/button"));
        shoppingCartBotm.click();
    }
}