package test;



import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class tatoc_home
{

	WebDriver driver = new ChromeDriver();





	public static void main(String[] args)
	{
		boolean first_pagee;
		boolean second_pagee=false;
		boolean third_pagee=false;
		boolean fourth_pagee=false;
		boolean fifth_pagee=false;

		tatoc_home t=new tatoc_home();
		first_pagee=t.first_page();
		if(first_pagee==true)
		{
			second_pagee=t.second_page();
		}
     if(second_pagee==true)
     {
    	 third_pagee=t.third_page();
     }
     if(third_pagee==true)
     {
    	 fourth_pagee=t.fourth_page();
     }
     if(fourth_pagee==true)
     {
    	 fifth_pagee=t.fifth_page();
     }
     
     if(fifth_pagee==true)
     {
    	 System.out.println("All Completed");
     }
	}

	public boolean first_page()
	{
		driver.get("http://10.0.1.86/tatoc");

		WebElement element = driver.findElement(By.linkText("Basic Course"));
		element.click();
		WebElement f_page_check=driver.findElement(By.className("greenbox"));
		f_page_check.click();
		return true;
	}
	public boolean second_page()
	{
		int flag=0;
		 String first_box_color=new String();
		  String second_box_color=new String();
		driver.switchTo().frame(driver.findElement(By.id("main")));
		WebElement first_box=driver.findElement(By.id("answer"));
		first_box_color=first_box.getAttribute("class");
		

		driver.switchTo().frame(driver.findElement(By.id("child")));
     WebElement second_box=driver.findElement(By.id("answer"));
		second_box_color=second_box.getAttribute("class");
		if(first_box_color.equals(second_box_color))
		{
		
			driver.switchTo().defaultContent();	
			driver.switchTo().frame("main");
			WebElement proceed1 = driver.findElement(By.linkText("Proceed"));
			proceed1.click();
			return true;
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
			element.click();
			driver.switchTo().frame(driver.findElement(By.id("child")));
			second_box=driver.findElement(By.id("answer"));
		    second_box_color=second_box.getAttribute("class");
			
	}
		}
		driver.switchTo().defaultContent();	
		driver.switchTo().frame("main");
		System.out.println("success");
		WebElement proceed = driver.findElement(By.linkText("Proceed"));
		proceed.click();
		return true;
}
	public boolean third_page()
	{
		Actions act=new Actions(driver);
		WebElement element = driver.findElement(By.id("dropbox")); 

		WebElement target = driver.findElement(By.id("dragbox"));

		act.dragAndDrop(target, element).build().perform();
		
		WebElement link = driver.findElement(By.linkText("Proceed"));
		link.click();
		 return true;
}
	public boolean fourth_page()
	{
	int len=0;
	 String child_window;
	 String name="Ashish";
	 String s;
	String parent = driver.getWindowHandle();
		WebElement link = driver.findElement(By.linkText("Launch Popup Window"));
		link.click();
		
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
		    	WebElement submit_field =driver.findElement(By.id("submit"));
		    	name_field.sendKeys("Ashish");
		    	 s=name_field.getAttribute("value");
		    	 if(s.equals(name))
		    	 {
		    		 submit_field.click();
                		    		 
		    		driver.switchTo().window(parent); 
		    		WebElement proceed_field = driver.findElement(By.linkText("Proceed"));
		    		proceed_field.click();
		    		break;
		    	
		         }
		    	
		    }
		    	
                		
		    	
   }
		return true;
    }
	public boolean fifth_page()
	{
		String s;
		int len=0;
		WebElement generate_token= driver.findElement(By.linkText("Generate Token"));
		
		generate_token.click();
		WebElement token_val= driver.findElement(By.id("token"));
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
   		proceed.click();
   		return true;
    }
}