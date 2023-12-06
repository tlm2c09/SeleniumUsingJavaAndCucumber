package utils;

import configs.DriverManager;
import io.cucumber.core.exception.CucumberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WaitsUtil {

    public static WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(10));
    private static final Logger logger = LogManager.getLogger(WaitsUtil.class);

    public static void initializeNewWebDriverWait() {
        wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(10));
    }

    public WaitsUtil(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebElement waitForElementToBeVisible(By locator) {
        return wait.until(visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        return wait.until(elementToBeClickable(locator));
    }

    public static void waitForElementInvisibility(By locator) {
        wait.until(invisibilityOfElementLocated(locator));
    }

    public static void waitForPageTitleToBe(String title) {
        wait.until(titleIs(title));
    }

    public static void waitForUrlToContain(String partialUrl) {
        wait.until(urlContains(partialUrl));
    }

    public static void waitForElementToHaveExactTexts(By by, String expectedTexts) {
        wait.until(textToBe(by, expectedTexts));
    }

    public static void waitForElementNotToHaveExactTexts(By by, String expectedTexts) {
        wait.until(not(textToBe(by, expectedTexts)));
    }

    public static void waitForAttributeOfElementToContain(By by, String attribute, String value) {
        wait.until(attributeContains(by, attribute, value));
    }

    public static void waitForAttributeOfElementToNotContain(By by, String attribute, String value) {
        wait.until(not(attributeContains(by, attribute, value)));
    }

    public static Alert waitForAlertToBePresent() {
        return wait.until(alertIsPresent());
    }

    public static void waitForElementToStale(WebElement element) {
        wait.until(refreshed(stalenessOf(element)));
    }

    public static void waitFor(String unit, int number){
        try {
            switch (unit) {
                case "seconds" -> Thread.sleep(Duration.ofSeconds(number));
                case "minutes" -> Thread.sleep(Duration.ofMinutes(number));
                default -> throw new CucumberException("Invalid unit " + unit);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
