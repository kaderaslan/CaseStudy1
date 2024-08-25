package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void testValidEmailSubmission() throws InterruptedException {
        driver.findElement(By.id("forgottenPassLink")).click();
        driver.findElement(By.id("email")).sendKeys("testdeneme133392@gmail.com");
        driver.findElement(By.xpath("//button[@class='submit-button']")).click();


        boolean isConfirmationDisplayed = driver.findElement(By.xpath("//h1[text()='Şifre sıfırlama bağlantısını gönderdik']")).isDisplayed();
        assertTrue(isConfirmationDisplayed, "Confirmation message should be displayed after a valid email submission.");
        driver.quit();
    }


    @Test
    public void testInvalidEmailSubmission() throws InterruptedException {
        driver.findElement(By.id("forgottenPassLink")).click();
        driver.findElement(By.id("email")).sendKeys("invalidemail@domain");
        driver.findElement(By.xpath("//button[contains(text(),'Gönder')]")).click();

        System.out.println("CAPTCHA'yı çöz ve devam et...");

        Thread.sleep(10000);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            WebElement warningMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'toaster-title')]")));
            String warningMessage = warningMessageElement.getText();

            assertTrue(warningMessage.contains("Bir hata oluştu."), "Uyarı mesajı beklenen mesajı içermiyor.");
        } catch (TimeoutException e) {
            System.out.println("Hata mesajı çok kısa sürede kayboldu.");
            fail("Hata mesajı yakalanamadı.");
        }
    }


    @Test
    public void testValidPhoneSubmission() throws InterruptedException {
        driver.findElement(By.id("forgottenPassLink")).click();
        driver.findElement(By.xpath("//span[text()='Telefon']/parent::button")).click();
        driver.findElement(By.id("name")).sendKeys("deneme");
        driver.findElement(By.id("lastname")).sendKeys("deneme");
        driver.findElement(By.id("phone")).sendKeys("5532042665");
        driver.findElement(By.xpath("//button[contains(text(),'Gönder')]")).click();


        boolean isVerificationPageDisplayed = driver.findElement(By.xpath("//*[@id=\"validation-pin\"]/span")).isDisplayed();
        assertTrue(isVerificationPageDisplayed, "Doğrulama kodu girme sayfası gösterilmelidir.");


        driver.quit();
    }


    @Test
    public void testInvalidPhoneSubmission() throws InterruptedException {
        driver.findElement(By.id("forgottenPassLink")).click();
        driver.findElement(By.xpath("//span[text()='Telefon']/parent::button")).click();
        driver.findElement(By.id("name")).sendKeys("deneme");
        driver.findElement(By.id("lastname")).sendKeys("deneme");
        driver.findElement(By.id("phone")).sendKeys("123");
        driver.findElement(By.xpath("//button[contains(text(),'Gönder')]")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement warningMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Bu bilgilere sahip birden fazla kullanıcı bulunmaktadır.')]")));
        boolean isWarningDisplayed = warningMessageElement.isDisplayed();
        Thread.sleep(10000);
        assertTrue(isWarningDisplayed, "Uyarı mesajı gösterilmelidir: 'Bu bilgilere sahip birden fazla kullanıcı bulunmaktadır.'");

        driver.quit();
    }


    @Test
    public void testEmptyFieldsPhoneSubmission() throws InterruptedException {
        driver.findElement(By.id("forgottenPassLink")).click();
        driver.findElement(By.xpath("//span[text()='Telefon']/parent::button")).click();

        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Gönder')]"));
        boolean isButtonDisabled = submitButton.getAttribute("disabled") != null;
        assertTrue(isButtonDisabled, "Gönder butonunun disabled olması gerekiyor.");

        Thread.sleep(10000);

        driver.quit();
    }


    @Test
    public void TestBizeUlas() throws InterruptedException {
        driver.findElement(By.id("forgottenPassLink")).click();
        WebElement bizeUlasLink = driver.findElement(By.xpath("//a[contains(text(),'Bize ulaş')]"));
        bizeUlasLink.click();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://www.kariyer.net/yardim"));

        String currentUrl = driver.getCurrentUrl();

        String baseUrl = currentUrl.split("#")[0];

        String expectedUrl = "https://www.kariyer.net/yardim";

        Assert.assertEquals("URL beklenen URL ile eşleşmiyor", expectedUrl, baseUrl);

        driver.quit();
    }


}
