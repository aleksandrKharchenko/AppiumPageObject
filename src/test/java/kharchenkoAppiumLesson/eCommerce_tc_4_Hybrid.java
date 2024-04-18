package kharchenkoAppiumLesson;

import TestUtils.AndroidBaseTest;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {


    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FillForm(HashMap<String, String> input) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
        ProductCatalogue productCatalogue = formPage.submitForm();
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogue.goToCartPage();

        //Thread.sleep(2000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

        double totalSum = cartPage.getProductsSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, displayFormattedSum);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @BeforeMethod(alwaysRun = true)
    public void preSetup() {
        formPage.setActivity();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\eCommerce.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
