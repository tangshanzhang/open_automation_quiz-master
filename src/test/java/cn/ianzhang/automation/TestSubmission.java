package cn.ianzhang.automation;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class TestSubmission {
	 WebDriver driver;
	    
	    
    @Given("^I navigate to templates.jinshuju.net$")
    public void i_navigate_to_baidu() throws Throwable {
        String url = "https://templates.jinshuju.net/detail/Dv9JPD";

        //新建一个浏览器句柄
        driver = new Brower().chrome();
        
//    	System.setProperty("webdriver.ie.driver","src/lib/IEDriverServer.exe");
//		// 创建一个浏览器驱动实例
//		driver = new InternetExplorerDriver();
//		
//		driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //打开URL
        driver.get(url);
        
    }
    
    @When("^I switch to iframe$")
    public void i_switch_to_iframe() throws Throwable {
    	WebElement frame=driver.findElement(By.xpath("//div[@class=\"child-container\"]/iframe[1]"));
        driver.switchTo().frame(frame);
    }
    
    @When("^I slide down iframe$")
    public void i_slide_down_iframe() throws Throwable {
    	((JavascriptExecutor) driver).executeScript("window.scrollTo(0,500)");  
    }

    @When("^I choose the second option$")
    public void i_choose_the_second_option() throws Throwable {
        WebElement option;
        option = driver.findElement(By.xpath("//div[contains(text(), '连续生产/开工类企事业单位')]"));
        option.click();
    }
    
    @Then("^I screenshot$")
    public void i_screenshot() throws Throwable {
    	//截屏操作
    	//截图到output
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
        	long name = Calendar.getInstance().getTimeInMillis();
            String savePath = "D:\\image\\"+name+".jpg";
            //复制内容到指定文件中
            FileUtils.copyFile(scrFile, new File(savePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @When("^I click next button$")
    public void i_click_next_button() throws Throwable {
        WebElement nextButton;
        nextButton = driver.findElement(By.xpath("//a[contains(text(), '下一页')]"));
        nextButton.click();
    }
    
    @When("^I fill in the second form$")
    public void i_fill_in_the_second_form() throws Throwable {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String date = df.format(new Date());
        driver.findElement(By.xpath("//input[@name=\"entry[field_18]\"]")).sendKeys(date);
        driver.findElement(By.xpath("//input[@name=\"entry[field_19]\"]")).sendKeys("自动化");
        driver.findElement(By.xpath("//input[@name=\"entry[field_20]\"]")).sendKeys("13888888888");
    }
    
    @When("^I fill in the third form$")
    public void i_fill_in_the_third_form() throws Throwable {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String date = df.format(new Date());
        driver.findElement(By.xpath("//input[@name=\"entry[field_23]\"]")).sendKeys("测试公司");
        driver.findElement(By.xpath("//input[@name=\"entry[field_24]\"]")).sendKeys("99");
        driver.findElement(By.xpath("//input[@name=\"entry[field_25]\"]")).sendKeys(date);
        driver.findElement(By.xpath("//input[@name=\"entry[field_26]\"]")).sendKeys("0");
        driver.findElement(By.xpath("//input[@name=\"entry[field_27]\"]")).sendKeys("CEO");
        driver.findElement(By.xpath("//input[@name=\"entry[field_28]\"]")).sendKeys("13888888888");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,700)");
        driver.findElement(By.xpath("//textarea[@name=\"entry[field_29]\"]")).sendKeys("测试内容");
    }
    
    @When("^I click submission button$")
    public void i_click_submission_button() throws Throwable {
        WebElement submissionButton;
        submissionButton = driver.findElement(By.name("commit"));
        submissionButton.click();
    }

//    @Then("^Then The correct search result is returned$")
//    public void the_correct_search_result_is_returned() throws Throwable {
//        WebElement searchResult;
//        searchResult = driver.findElement(By.xpath(".//*[@id='container']/div[2]/div/div[2]/span"));
//        Assert.assertTrue(searchResult.isDisplayed());
//        
//        Thread.sleep(2000);
//        driver.quit();
//    }
}
