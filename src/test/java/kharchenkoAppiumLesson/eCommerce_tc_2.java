package kharchenkoAppiumLesson;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends AndroidBaseTest {

    @BeforeMethod
    public void preSetup() {
        // screen to home page
        formPage.setActivity();
    }

    @Test
    public void FillForm_ErrorValidation() {
//        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Alex8_qa_top");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
    }

    @Test
    public void FillForm_PositiveFlow() {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Alex8_qa_top");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Brazil\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
    }
}
