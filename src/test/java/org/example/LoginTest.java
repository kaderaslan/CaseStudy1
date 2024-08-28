package org.example;
import java.time.Duration;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;




public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("testdeneme133392@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("987654321Test");
        driver.findElement(By.cssSelector("button[data-test='login-button']")).click();


        System.out.println("CAPTCHA'yı çöz ve devam et...");


        Thread.sleep(20000);

        driver.quit();
    }
    @Test
    public void testInvalidLogin() {
        driver.findElement(By.id("username")).sendKeys("invalidemail@example.com");
        driver.findElement(By.id("pass")).sendKeys("WrongPassword");
        driver.findElement(By.cssSelector("button[data-test='login-button']")).click();


        // Hata mesajını doğrulama
        boolean isErrorDisplayed = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[2]/div/div[1]/div/p")).isDisplayed();
        assertTrue(isErrorDisplayed, "Error message should be displayed for invalid login credentials.");

        driver.quit();
    }


    @Test
    public void testEmptyFieldsLogin() {
        driver.findElement(By.cssSelector("button[data-test='login-button']")).click();

        // Boş alanlar için uyarı mesajlarını doğrulama
        boolean isUsernameWarningDisplayed = driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div[2]/div/div[1]/div/form/div[1]/div/span")).isDisplayed();

        assertTrue(isUsernameWarningDisplayed, "Username warning should be displayed when username is empty.");

        driver.quit();
    }

    @Test
    public void testRememberMeOption() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("testdeneme133392@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("987654321Test");
        driver.findElement(By.cssSelector("label[for='rememberMe']")).click(); // "Beni Hatırla" seçeneğini işaretleme
        driver.findElement(By.cssSelector("button[data-test='login-button']")).click();

        System.out.println("CAPTCHA'yı çöz ve devam et...");
        Thread.sleep(20000); // Kullanıcıya CAPTCHA'yı çözmesi için zaman veriyoruz

        driver.quit();
    }

    @Test
    public void LoginGoogleOption() throws InterruptedException {

        WebElement googleButton = driver.findElement(By.xpath("//*[@id=\"googleButton\"]"));
        googleButton.click();

        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        Thread.sleep(10000);


        driver.switchTo().window(mainWindowHandle);

        driver.quit();
    }

    @Test
    public void LoginFacebookOption() throws InterruptedException {

        WebElement googleButton = driver.findElement(By.xpath("//*[@id=\"facebookButton\"]"));
        googleButton.click();

        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }


        Thread.sleep(10000);

        driver.switchTo().window(mainWindowHandle);

        driver.quit();
    }

    @Test
    public void LoginAppleOption() throws InterruptedException {

        WebElement googleButton = driver.findElement(By.xpath("//*[@id=\"appleButton\"]"));
        googleButton.click();

        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }


        Thread.sleep(10000);

        driver.switchTo().window(mainWindowHandle);

        driver.quit();
    }

    @Test
    public void TestHemenUyeOl() throws InterruptedException {
        WebElement hemenUyeOlButton = driver.findElement(By.id("registerLink"));
        hemenUyeOlButton.click();
        String expectedUrl = "https://www.kariyer.net/aday/uyeol";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(expectedUrl));

        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));

        driver.quit();

    }


    @Test
    public void TestIsverenmisin() throws InterruptedException {
        WebElement isverenMisinButton = driver.findElement(By.id("publisherLink"));
        isverenMisinButton.click();

        String expectedUrl = "https://www.kariyer.net/isveren/giris";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(expectedUrl));

        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));

        driver.quit();
    }
}

