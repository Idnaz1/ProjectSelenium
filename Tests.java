package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Tests {
    //Shortcut method for FAST create new WebDriver.
    public static WebDriver setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Idan\\Downloads\\chromedriver.exe");
        return new ChromeDriver();
    }
    //Creating the driver OUTside of the tests for general use && general methods like AFTER each etc.
    WebDriver driver = setup();

    //AfterEach Test, this method will run and will terminate the driver (Close & Terminate)
    @AfterEach
    public void closer(){
        driver.quit();
    }

    @Test
    public void registrationProcessTest() {
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/register"); // Get to this site

        var firstName = driver.findElement(By.id("input-firstname"));
        var lastName = driver.findElement(By.id("input-lastname"));
        var eMail = driver.findElement(By.id("input-email"));
        var telephoneNub = driver.findElement(By.id("input-telephone"));
        var passwordNum = driver.findElement(By.id("input-password"));
        var confirmBtn = driver.findElement(By.id("input-confirm"));
        var agreeBtn = driver.findElement(By.name("agree"));
        var continueBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"));


        firstName.sendKeys("Idan");                 // find the element "First name" & write this text
        lastName.sendKeys("Azuri");                 // find the element "last name" & write this text
        eMail.sendKeys("idn.acz01110@gmail.com");    // find the element "Email" & write this text
        telephoneNub.sendKeys("0545646076");        // find the element "Telephone" & write this text
        passwordNum.sendKeys("123456");             // find the element "First name" & write this text
        confirmBtn.sendKeys("123456");              // find the element "Confirm" & write this text

        agreeBtn.click();                                      // find the element "Agree" & write this text
        continueBtn.click();                                   // find the element by xpath & click

        // Check if this is the correct and exact address of the site by matching.
        Assertions.assertEquals("http://tutorialsninja.com/demo/index.php?route=account/success" , driver.getCurrentUrl());
    }

    @Test
    void loginProcessTest() {
        driver.get("http://tutorialsninja.com/demo/index.php?route=account/login"); // Get to this site

        var eMail2 = driver.findElement(By.id("input-email"));
        var password2 = driver.findElement(By.id("input-password"));
        var loginBtn = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input"));

        eMail2.sendKeys("idn.acz01110@gmail.com"); // find the element "Email" & write this text
        password2.sendKeys("123456"); // find the element "Password" & write this text
        loginBtn.click();  // find the element by xpath & click

        { //making sure the SignIn accrued with an existing email && password in the DB (Registered before)
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"account-login\"]/div[1]"));
            Assertions.assertTrue(elements.isEmpty(), "Login failed, Un-existing User");
        }

        // Check if this is the correct and exact address of the site by matching.
        Assertions.assertEquals("http://tutorialsninja.com/demo/index.php?route=account/account" , driver.getCurrentUrl());
    }


    @Test
    public void testAddToCart(){

        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=43"); //Go to Product1 page

        //Adding the product to the cart
        var addToCart = driver.findElement(By.id("button-cart"));
        addToCart.click();
        //Extracting the "Price" from the product and inserting it into variable
        //Extracted text contains Signs (!, @, $ ..) & CANNOT be calculated ->> Replacing Sign With 0
        double pr1 = text2Double(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[1]/h2")));


        //Goes to Product2 page
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=33");

        addToCart = driver.findElement(By.id("button-cart")); //re-configure the element in a new page for re-use the variable
        addToCart.click(); //Adding to cart

        //Extracting the "Price" from the product and inserting it into variable
        double pr2 = text2Double(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[1]/h2")));

        var shoppingBtn = driver.findElement(By.cssSelector("#top-links > ul > li:nth-child(4) > a > i"));
        shoppingBtn.click(); //clicking the Shopping Cart

        //Using method to extract the number without the symbol "$"
        double subTotal = text2Double(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/table/tbody/tr[4]/td[2]")));

        Assertions.assertEquals(pr1+pr2,subTotal); //Asserting that product 1 price + product 2 price are EQUALS to the subtotal given
    }

    public double text2Double(WebElement webElement){
        //Extracted text contains Signs (!, @, $ ..) & CANNOT be calculated ->> Replacing Sign With 0
        double converText = Double.parseDouble(webElement.getText().replace('$','0'));
        return converText;
    }

    @Test
    public void testReview() {
        //Goes to a specific Product page
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/product&product_id=43");
        //Clicking on add to cart
        var reviewSlide= driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/ul[2]/li[3]/a"));
        reviewSlide.click();
        //clicking on "Write a review" button
        var writeReview=driver.findElement(By.cssSelector("#content > div > div.col-sm-4 > div.rating > p > a:nth-child(7)"));
        writeReview.click();
        //Sends to the "Reviewer name" Textbox the given String (Text)
        var reviewerName = driver.findElement(By.id("input-name"));
        reviewerName.sendKeys("LEL");
        //Review MUST be 20 Chars AT-LEAST!
        var reviewInput=    driver.findElement(By.id("input-review"));

        char review = 'a'; //Variable type char contains the review
        //for loop to send the wanted amount of chars by choice
        //min - 25 chars, Max - 1,000
        for (int i = 0; i < 25; i++) {
            reviewInput.sendKeys(review+""); //Giving variable type char instead of String to String-type method
            //+"" - convert it to String
        }

        //Mid-score review
        //Challenge: get the middle review score with List
        List<WebElement> reviewScore = driver.findElements(By.name("rating"));
        int index = reviewScore.size()/2;
        reviewScore.get(index).click();

        //Submitting the review by cling to the continue button
        var continueBtn = driver.findElement(By.id("button-review"));
        continueBtn.click();

        //Finds the ERROR-Exclamation mark message & gets it in the ARRAY
        //Checker to see if the review has sent
        List<WebElement> reviewSubmitChecker =
                driver.findElements(By.cssSelector
                        ("#form-review > div.alert.alert-danger.alert-dismissible > i"));

        /* Array is EMPTY if the review has sent
         *   in case of array is not empty (Contains Element)
         *       it means the review wasn't sent and the Test has failed
         * */
        Assertions.assertTrue(reviewSubmitChecker.isEmpty(),"Review wasn't delivered");
    }
}
