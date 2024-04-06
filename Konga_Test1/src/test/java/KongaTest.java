import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class KongaTest {
    // Import selenium WebDriver
    private WebDriver driver;

    @BeforeTest
    public void start() throws InterruptedException {
        // locate where the chromedriver is
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //1. Open your Chrome Browser
        driver = new ChromeDriver();
        //2.Input Konga URL (https://www.konga.com)
        driver.get("https://www.konga.com");
        Thread.sleep(10000);

        //3.maximize the browser
        driver.manage().window().maximize();
        Thread.sleep(5000);

        //4. Click on Login button
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(5000);

        //5. Input an Email address in the email and password in the required field
        driver.findElement(By.id("username")).sendKeys("mide.popson@gmail.com");
        Thread.sleep(2000);

        driver.findElement(By.id("password")).sendKeys("Testifyltd.com");
        Thread.sleep(5000);

        //6. Locate the login button and click it
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 0)
    public void categories() throws InterruptedException {
        //7. Select Computer and Accessories Category
        WebElement elementToHover = driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]"));
        // Create an instance of Actions class
        Actions actions = new Actions(driver);
        // Hover over the element
        actions.moveToElement(elementToHover).build().perform();
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void selectAppleMacBook() throws InterruptedException {
        //8. Select MacBook
        driver.findElement(By.xpath("//*[@id=\"subFixId\"]/div/div/div[1]/a[6]")).click();
        Thread.sleep(10000);
        //9. Select Apple Macbook option
        //Choose the Item to buy
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[3]/div/div/div[1]/a[1]/picture/img")).click();
        Thread.sleep(10000);


    }
    @Test(priority = 2)
    public void addItemToCart() throws InterruptedException {
        //10: Buy item now
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[4]/div[3]/div[1]/div[1]/div[2]/div[2]/form/div[5]/div[1]/button")).click();
        Thread.sleep(10000);
        //11. Click on "Continue to checkout" button
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div[4]/div/div[6]/div/a")).click();
        Thread.sleep(15000);

    }

    @Test(priority = 3)
    public void checkout() throws InterruptedException {
        //12: Click on pay now
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(10000);
        //13. click continue to payment
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(10000);

    }

    @Test(priority = 4)
    public void invalidCardDetails() throws InterruptedException {
        //14. Switch iFrame
        WebElement paymentMethod = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame("kpg-frame-component");
        Thread.sleep(15000);
        //15. Select card method
        WebElement cardPayment = driver.findElement(By.className("Card"));
        cardPayment.click();
        Thread.sleep(15000);
        //16: Input an invalid card details in the fields for card details
        // card details input fields with ids "cardNumber", "expirationDate", and "cvv"
        driver.findElement(By.id("card-number")).sendKeys("1234567890123");
        driver.findElement(By.id("expiry")).sendKeys("01/23");
        driver.findElement(By.id("cvv")).sendKeys("123");

    }

    @Test(priority = 5)
    public void printErrorMessage() throws InterruptedException {
        //17: verify that the error message for invalid card number is displayed
        // Print out the error message; invalid card number
        WebElement message = driver.findElement(By.xpath("//*[@id=\"card-number_unhappy\"]"));
        System.out.println(message.getText());
    }

    @Test(priority = 6)
    public void closeInputCardModal() throws InterruptedException {
       // Close the iframe or modal
        driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside")).click();

    }

    @AfterTest
    public void closeBrowser() {
        //18. Quit the browser
        driver.quit();
    }

}