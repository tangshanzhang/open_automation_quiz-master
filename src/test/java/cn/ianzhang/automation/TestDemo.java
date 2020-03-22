package cn.ianzhang.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tangshaoze
 * @Description:
 * @Date: Created In 9:11 2020/2/3
 * @Modified By:
 */
public class TestDemo {
    static WebDriver driver;

    @BeforeTest
    public void init() {
    	String url = "https://cn.bing.com/";

        //新建一个浏览器句柄
        driver = new Brower().chrome();
        		
//        		Brower().chrome();
    	
//    	System.setProperty("webdriver.ie.driver","src/lib/IEDriverServer.exe");
		// 创建一个浏览器驱动实例
//		driver = new InternetExplorerDriver();
		
        //打开URL
        driver.get(url);
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void search() throws InterruptedException {
//      driver.switchTo().alert().accept();
//      
//      driver.switchTo().window("https://cn.bing.com/");
      
//      WebDriverWait wait=new WebDriverWait(driver,300);
//      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"sb_form_q\"]")));
      
      Thread.sleep(2000);
    	
      WebElement kw = driver.findElement(By.xpath("//input[@id=\"sb_form_q\"]"));
      kw.sendKeys("Bing");
      
      Thread.sleep(2000);
      
      WebElement su = driver.findElement(By.xpath("//input[@id=\"sb_form_go\"]"));
      su.click();
      
      Thread.sleep(2000);
      //点击[第二页]按钮
      driver.findElement(By.xpath("//*[@title=\"下一页\"]")).click();
      
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
      
      su.click();
      
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      tops =  driver.findElements(By.xpath("//*[@id=\"b_results\"]/li[@class=\"b_algo\"]"));
      
      map = new HashMap<String, Integer>();
      
//      System.out.println(tops.get(0).getText());
      
//      i=Integer.valueOf(tops.get(0).getAttribute("data-bm"));
      i = 7;
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

    @AfterTest
    public void teardown() {
        driver.quit();
    }
    
    public static boolean isElementExit(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}

}