package com.qait.automation.tatoc_maven;

import java.util.Iterator;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class test {
	String first_page_url_return;
	String second_page_url_return;
	String third_page_url_return;
	String fourth_page_url_return;
	WebDriver driver;
	
	
	@BeforeClass
	public void initvars()
	{
		 driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
	}
	
	
	@Test
	public void first_page_test(){
		String basic_link_url;
		String current_url_after_basic_click;
		WebElement element = driver.findElement(By.linkText("Basic Course"));
		basic_link_url=element.getAttribute("href");
		System.out.println(basic_link_url);
		Assert.assertTrue(element.isDisplayed());
		System.out.println(element.getText()+ " is displayed");
		element.click();
		current_url_after_basic_click=driver.getCurrentUrl();
		System.out.println(current_url_after_basic_click);
		//Assert.assertEquals(basic_link_url,current_url_after_basic_click);
		WebElement f_page_check=driver.findElement(By.className("greenbox"));
		Assert.assertTrue(f_page_check.isDisplayed());
		f_page_check.click();
		}
	
	@Test  (dependsOnMethods="first_page_test")
	public void second_page()
	{
		String urll=driver.getCurrentUrl();
		int flag=0;
		 String first_box_color=new String();
		  String second_box_color=new String();
		driver.switchTo().frame(driver.findElement(By.id("main")));
		WebElement first_box=driver.findElement(By.id("answer"));
		Assert.assertTrue(first_box.isDisplayed());
     		
		first_box_color=first_box.getAttribute("class");
		
        System.out.println(urll);
		driver.switchTo().frame(driver.findElement(By.id("child")));
     WebElement second_box=driver.findElement(By.id("answer"));
     Assert.assertTrue(second_box.isDisplayed());
	second_box_color=second_box.getAttribute("class");
		if(first_box_color.equals(second_box_color))
		{
		
			driver.switchTo().defaultContent();	
			driver.switchTo().frame("main");
			WebElement proceed1 = driver.findElement(By.linkText("Proceed"));
			Assert.assertTrue(proceed1.isDisplayed());
			proceed1.click();
			
		}
		
		while(flag==0)
		{
		if(first_box_color.equals(second_box_color))
		{
		
		flag=1;
		}
		else
		{
			
			
			driver.switchTo().defaultContent();	
			driver.switchTo().frame("main");	
			WebElement element = driver.findElement(By.linkText("Repaint Box 2"));
			Assert.assertTrue(element.isDisplayed());
			element.click();
			driver.switchTo().frame(driver.findElement(By.id("child")));
			second_box=driver.findElement(By.id("answer"));
			Assert.assertTrue(second_box.isDisplayed());
		    second_box_color=second_box.getAttribute("class");
			
	}
		}
		driver.switchTo().defaultContent();	
		driver.switchTo().frame("main");
		System.out.println("success");
		WebElement proceed = driver.findElement(By.linkText("Proceed"));
		Assert.assertTrue(proceed.isDisplayed());
		proceed.click();
		
}
@Test (dependsOnMethods="second_page")
public void third_page()
{
	Actions act=new Actions(driver);
	WebElement element = driver.findElement(By.id("dropbox"));
	Assert.assertTrue(element.isDisplayed());

	WebElement target = driver.findElement(By.id("dragbox"));
	Assert.assertTrue(target.isDisplayed());

	act.dragAndDrop(target, element).build().perform();
	
	WebElement link = driver.findElement(By.linkText("Proceed"));
	Assert.assertTrue(link.isDisplayed());
	link.click();
	 
}
@Test (dependsOnMethods="third_page")
public void fourth_page()
{
int len=0;
 String child_window;
 String name="Ashish";
 String s;
String parent = driver.getWindowHandle();
	WebElement link = driver.findElement(By.linkText("Launch Popup Window"));
	link.click();
	Assert.assertTrue(link.isDisplayed());
	
	Set<String>s1=driver.getWindowHandles();
	Iterator<String> I1= s1.iterator();
    	 
     
	while(I1.hasNext())
	{

	    child_window=I1.next();
	    if(parent.equals(child_window))
	    {
          	   
           
	    }
	    else
	    {
	    	driver.switchTo().window(child_window);
	    	WebElement name_field = driver.findElement(By.id("name"));
	    	Assert.assertTrue(name_field.isDisplayed());
	    	WebElement submit_field =driver.findElement(By.id("submit"));
	    	Assert.assertTrue(submit_field.isDisplayed());
	    	name_field.sendKeys("Ashish");
	    	 s=name_field.getAttribute("value");
	    	 if(s.equals(name))
	    	 {
	    		 submit_field.click();
            		    		 
	    		driver.switchTo().window(parent); 
	    		WebElement proceed_field = driver.findElement(By.linkText("Proceed"));
	    		Assert.assertTrue(proceed_field.isDisplayed());
	    		proceed_field.click();
	    		break;
	    	
	         }
	    	
	    }
	    	
            		
	    	
}
	
}
@Test (dependsOnMethods="fourth_page")
public void fifth_page()
{
	String s;
	int len=0;
	WebElement generate_token= driver.findElement(By.linkText("Generate Token"));
	Assert.assertTrue(generate_token.isDisplayed());
	generate_token.click();
	WebElement token_val= driver.findElement(By.id("token"));
	Assert.assertTrue(token_val.isDisplayed());
       s=token_val.getText();
        len=s.length();           
       s=s.substring(7, len);
       
       Cookie cc=new Cookie("Token",s);
       driver.manage().addCookie(cc);
         
       Set<Cookie> cookiesList =  driver.manage().getCookies();
		for(Cookie getcookies :cookiesList) 
		{
		    System.out.println(getcookies );
		}
		WebElement proceed= driver.findElement(By.linkText("Proceed"));
		Assert.assertTrue(proceed.isDisplayed());
		proceed.click();
		
}
	@AfterClass
	public void exit() {
		driver.quit();
	}


}