package erorr_msg;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.GestureUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ToastMessage {

    @Test
    public void errorMessageTest() throws MalformedURLException {
        File ecomApk = new File("src/test/resources/Codefish-Store.apk");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"PixelB");
        desiredCapabilities.setCapability(MobileCapabilityType.APP,ecomApk.getAbsolutePath());

        URL serverServer = new URL("http://0.0.0.0:4723/wd/hub");

        AndroidDriver driver = new AndroidDriver<>(serverServer,desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement letsShopButton =  driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));
        GestureUtils.elementTap(driver,letsShopButton);
        WebElement errorMessage = driver.findElement(By.xpath(	"//android.widget.Toast[1]"));
        String toastMessage = errorMessage.getAttribute("name");
        String expectedMessage = "Please enter your name";
        Assert.assertEquals(expectedMessage,toastMessage);

    }



}
