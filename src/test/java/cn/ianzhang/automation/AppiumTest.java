package cn.ianzhang.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {
	
	static AndroidDriver driver;
	
    @BeforeTest
    public void setUp() throws MalformedURLException{
    	DesiredCapabilities des = new DesiredCapabilities();
        des.setCapability("platformName", "Android");
        des.setCapability("platformVersion", "7.0");
        des.setCapability("udid", "192.168.1.6:5555");
        des.setCapability("deviceName", "HUAWEI nova");
        des.setCapability("port", "4723");
        des.setCapability("path", "C:\\Users\\tsz\\Downloads\\ting_rsl_9.6.1_210000.apk");
        des.setCapability("newCommandTimeout", "3600");
        des.setCapability("noReset", "True");
        des.setCapability("appPackage", "com.kunpeng.babyting");
        des.setCapability("appActivity", "com.kunpeng.babyting.ui.BabyTingStartupActivity");
        des.setCapability("unicodeKeyboard", "True");//支持中文输入
        des.setCapability("resetKeyboard", "True");//支持中文输入
        des.setCapability("nosign", "True");//跳过检查和对应用进行 debug 签名的步骤
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),des);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    
    @Test
    public void calcTestcase() throws InterruptedException{
    	Map<String, Object> params = new HashMap<>();
        params.put("bundleId", "com.kunpeng.babyting");
        
        //点击我
        isElementExitAndWaitById("com.kunpeng.babyting:id/tab_mystory");
        driver.findElement(By.id("com.kunpeng.babyting:id/tab_mystory")).click();
        
        //点击登录
        isElementExitAndWaitById("com.kunpeng.babyting:id/tv_login");
        driver.findElement(By.id("com.kunpeng.babyting:id/tv_login")).click();
        
        //点击取消
        isElementExitAndWaitById("com.kunpeng.babyting:id/negative_button");
        driver.findElement(By.id("com.kunpeng.babyting:id/negative_button")).click();
        
        driver.runAppInBackground(Duration.ofSeconds(1));
        
        Thread.sleep(2000);
        
        driver.runAppInBackground(Duration.ofSeconds(1));
        
        Thread.sleep(2000);
        
        //点击qq登录
//        isElementExitAndWaitById("com.kunpeng.babyting:id/login_btn_qq");
//        driver.findElement(By.id("com.kunpeng.babyting:id/login_btn_qq")).click();
        
        if(isElementExitAndWaitById("com.kunpeng.babyting:id/login_btn_qq")) {
        	driver.findElement(By.id("com.kunpeng.babyting:id/login_btn_qq")).click();
        }
        
        driver.runAppInBackground(Duration.ofSeconds(1));
        
        Thread.sleep(2000);
        
        driver.activateApp("com.kunpeng.babyting");
        
        Thread.sleep(2000);
        
        //点击登录
        isElementExitAndWaitById("com.tencent.mobileqq:id/fds");
        driver.findElement(By.id("com.tencent.mobileqq:id/fds")).click();
        
        //完成授权
        if(isElementExitAndWaitById("com.tencent.mobileqq:id/b7p")) {
        	driver.findElement(By.id("com.tencent.mobileqq:id/b7p")).click();
        }
        
        WebElement name = driver.findElement(By.id("com.kunpeng.babyting:id/user_name"));
//        System.out.println(name.getText());
        Assert.assertEquals(name.getText(), "ｉｉ");
    }
    
    
    
//    @DataProvider(name="testdata")
//    public Object[][] getData(){
//        return new Object[][]{{"20","80","100","+"},{"90","3","270","×"},{"6","2","3","÷"}};
//    }
//     
//    @Test(dataProvider = "testdata")
//    public void calcTestcase(String num1,String num2,String result,String calcType){
//        for(char num:num1.toCharArray()){
//            driver.findElement(By.xpath("//android.widget.Button[@text='"+String.valueOf(num)+"']")).click();
//        }
//        driver.findElement(By.xpath("//android.widget.Button[@text='"+calcType+"']")).click();
//        for(char num:num2.toCharArray()){
//            driver.findElement(By.xpath("//android.widget.Button[@text='"+String.valueOf(num)+"']")).click();
//        }
//        driver.findElement(By.xpath("//android.widget.Button[@text='=']")).click();
//        String value = driver.findElement(By.xpath("//android.widget.EditText[@class='android.widget.EditText']")).getAttribute("text");
//        Assert.assertEquals(value, result);    
//    }

public static boolean isElementExitAndWaitById(String xpath) {
	try {
		WebDriverWait explicitwait = new WebDriverWait(driver, 6);
//		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpath)));
	} catch (Exception e) {
		return false;
	}
	return true;
	
	}
}
