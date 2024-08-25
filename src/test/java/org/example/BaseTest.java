package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // WebDriverManager ile FirefoxDriver'ı kuruyoruz
        WebDriverManager.firefoxdriver().setup();

        // Firefox tarayıcısını başlatıyoruz
        driver = new FirefoxDriver();

        // Timeout ve pencere ayarlarını yapıyoruz
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Kariyer.net giriş sayfasına gidiyoruz
        driver.get("https://www.kariyer.net/aday/giris");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
