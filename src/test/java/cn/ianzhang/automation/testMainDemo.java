package cn.ianzhang.automation;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testMainDemo {

	 static WebDriver driver;
	
	
	
	public static void main(String[] args)  throws InterruptedException, MalformedURLException{
		
		String url = "https://cn.bing.com//";

        //新建一个浏览器句柄
//        driver = new Brower().chrome();
		
		System.setProperty("webdriver.ie.driver","src/lib/IEDriverServer.exe");
		// 创建一个浏览器驱动实例
		driver = new InternetExplorerDriver();
		
        //打开URL
        driver.navigate().to(url);  

        
//        driver.switchTo().alert().accept();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
//        WebDriverWait wait=new WebDriverWait(driver,300);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"sb_form_q\"]")));
        
        WebElement kw = driver.findElement(By.xpath("//input[@id=\"sb_form_q\"]"));
        kw.sendKeys("Bing");
        
        WebElement su = driver.findElement(By.xpath("//input[@id=\"sb_form_go\"]"));
        su.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        
        
        //点击[第二页]按钮
        driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[12]/nav/ul/li[6]")).sendKeys(Keys.ENTER);
        
        Thread.sleep(2000);
        
        List<WebElement> tops =  driver.findElements(By.xpath("//*[@id=\"b_results\"]/li[@class=\"b_algo\"]"));
       
        int i=Integer.valueOf(tops.get(0).getAttribute("data-bm"))-1;
        
        tops.remove(1);
        
        Map<String,Integer> map = new HashMap<String, Integer>();
         
        for (WebElement webElement : tops) {
        	i++;
        	
    		WebElement http = driver.findElement(By.xpath("//*[@data-bm=\""+i+"\"]//a[@target=\"_blank\"]"));
	      	
         	String href = http.getAttribute("href");
        	
        	String com = null;
        	
        	System.out.println(http.getText()+" --> "+href);
        	
        	href = href.substring(href.indexOf(".")+1,href.length());
        	
        	com = href.substring(0,href.indexOf("/"));
        	
        	if(map.isEmpty()) {
        		map.put(com, 1);
        	}else if(null == map.get(com)){
        		map.put(com, 1);
        	}else {
        		int number = map.get(com);
        		map.put(com, number+1);
        	}
		}
        
        for(Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey()+" --> "+entry.getValue());
        }
        
        //输入搜索字符串
        
        kw = driver.findElement(By.xpath("//input[@id=\"sb_form_q\"]"));
        kw.clear();
        kw.sendKeys("SC");
        su = driver.findElement(By.xpath("//input[@id=\"sb_form_go\"]"));
        
        su.sendKeys(Keys.ENTER);
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        tops =  driver.findElements(By.xpath("//*[@id=\"b_results\"]/li[@class=\"b_algo\"]"));
        
        map = new HashMap<String, Integer>();
        
//        System.out.println(tops.get(0).getText());
        
        i=Integer.valueOf(tops.get(0).getAttribute("data-bm"));
        
        tops.remove(1);
      
        for (WebElement webElement : tops) {
        	
    		WebElement http = driver.findElement(By.xpath("//*[@data-bm=\""+i+"\"]//a[@target=\"_blank\"]"));
	      	
         	String href = http.getAttribute("href");
        	
        	String com = null;
        	
        	System.out.println(http.getText()+" --> "+href);
        	
        	href = href.substring(href.indexOf(".")+1,href.length());
        	
        	com = href.substring(0,href.indexOf("/"));
        	
        	if(map.isEmpty()) {
        		map.put(com, 1);
        	}else if(null == map.get(com)){
        		map.put(com, 1);
        	}else {
        		int number = map.get(com);
        		map.put(com, number+1);
        	}
        	i++;
		}
	      
	    for(Entry<String, Integer> entry : map.entrySet()){
	    	System.out.println(entry.getKey()+" --> "+entry.getValue());
	    }
		
	}
	
	public static boolean isElementExitAndWaitById(String xpath) {
		try {
			WebDriverWait explicitwait = new WebDriverWait(driver, 6);
			explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpath)));
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
}



