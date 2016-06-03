package com.pru.hk.aes;
/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class SeleniumTest {

  private static HashMap accountMap = new HashMap() {
    {
      put("A", new Credential("00010001", "Aes123xx",null));
      put("P", new Credential("685096", "Ww111111",null));
    }
  };
  private static HashMap wordpressMap = new HashMap() {
	    {
	      put("A", new Credential("littlehoi", "!ww123123","http://littlehoi.ddns.net/wordpress/wp-login.php?redirect_to=http%3A%2F%2Flittlehoi.ddns.net%2Fwordpress%2Fwp-admin%2F&reauth=1"));
	    }
	  };

 
  private static HashMap efolderAccountMap = new HashMap() {
    {
      put("A", new Credential("xpmkchmt", "May05May",null));
    	 
    }
  };
  private static HashMap efolderPolicyMap = new HashMap() {
	    {
	      put("JEM", "10883853");
	      put("TGC", "9804371");
	    }
	  };
  
  public static void main(String args[]) throws IOException{
	  SeleniumTest s =new SeleniumTest();
	  //s.dev();
	  //s.uat();
	  //s.efolderProd();
	 s.wordpressSubmit();
  }
  public void dev() throws IOException{
	  /*
    
    System.out.println((new StringBuilder("3. Testing agent login, channel B, agent code=")).append(((Credential) accountMap.get("B")).getUserId()).toString());
    driver = SeleniumUtil.createFireFoxWebDriver();
    driver.get(baseUrl);
    driver.findElement(By.name("username")).sendKeys(new CharSequence[] {((Credential) accountMap.get("B")).getUserId()});
    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("B")).getPassword()});
    driver.findElement(By.id("submit")).click();
    SeleniumUtil.quitDriver(driver);
    System.out.println((new StringBuilder("4. Testing staff login, user Id=")).append(((Credential) accountMap.get("STAFF")).getUserId()).toString());
    driver = SeleniumUtil.createFireFoxWebDriver();
    driver.get(baseUrl);
    driver.findElement(By.name("username")).sendKeys(new CharSequence[] {((Credential) accountMap.get("STAFF")).getUserId()});
    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("STAFF")).getPassword()});
    driver.findElement(By.id("submit")).click();
    SeleniumUtil.quitDriver(driver);*/


	    System.out.println((new StringBuilder("DCMS aes test start time:")).append((new Date()).toString()).toString());
	    String http = "https";
	    //String baseUrl = (new StringBuilder(String.valueOf(http))).append("://apdevrg.prudential.com.hk/ap/jsp/index.jsp").toString();
	    String baseUrl = (new StringBuilder(String.valueOf(http))).append("://apuat3.prudential.com.hk/ap/jsp/index.jsp").toString();
	    System.out.println((new StringBuilder("Base URL:")).append(baseUrl).toString());
	    WebDriver driver = SeleniumUtil.createFireFoxWebDriver();
		try{
		
			System.out.println((new StringBuilder("1. Testing agent login,  agent code=")).append(((Credential) accountMap.get("A")).getUserId()).toString());
	    driver.get(baseUrl);
	    driver.switchTo().frame("contentMain");
	    driver.findElement(By.name("user_id")).sendKeys(new CharSequence[] {((Credential) accountMap.get("A")).getUserId()});
	    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("A")).getPassword()});
	    driver.findElement(By.name("image")).click();

	    System.out.println((new StringBuilder("step 1")));
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    driver.findElement(By.id("disclaimerDiv")).click();
	    System.out.println((new StringBuilder("step 2")));
	    //driver.findElement(By.cssSelector("div.popup > img")).click();
	    //driver.switchTo().frame("contentMain");
	    //String keyCsst="div[style*='border-right:1px solid #FFFFFF;width:100%;height:100%;'";
	    String keyCsst="body div:nth-child(1)>table>tbody>tr>td>span"; // + div>table>tbody>tr>td>span
	    //>table>tbody>tr>td>span
	    WebElement we;

	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
	    Actions builder = new Actions(driver);
	    we=driver.findElement(By.cssSelector("body div:nth-of-type(3) > div > table > tbody > tr > td > span"));
	    builder.moveToElement(we).perform();
	    System.out.println("we="+we);
	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

	    we = driver.findElement(By.cssSelector("body div:nth-of-type(4) div:nth-of-type(1)"));
	    System.out.println("we="+we.getCssValue("visibility")+we.getCssValue("style"));
	    
	    String js = "arguments[0].style.visibility='visible';";
	    ((JavascriptExecutor) driver).executeScript(js, we);
	    
	    we.click();
	    System.out.println((new StringBuilder("step 3")));
	    
	    
	    String CWH=driver.getWindowHandle();
	    System.out.println("Current Window Handle: "+driver.getWindowHandle());
	    Set<String> s = new HashSet();
	    s.addAll(driver.getWindowHandles());   
	    s.remove(CWH);
	    driver.switchTo().window(""+s.toArray()[0]);
	    System.out.println("New Window Handle: "+driver.getWindowHandle());

	    driver.switchTo().frame("AESContent");
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    
	    we = driver.findElement(By.cssSelector("div#menu ul > li:nth-of-type(2)"));
	    we.click();

	    we = driver.findElement(By.cssSelector("a.menu"));
	    System.out.println("we="+we.getAttribute("innerHTML"));
	    we.click();
	    
	    //SeleniumUtil.quitDriver(driver);
	    /*System.out.println((new StringBuilder("2. Testing agent login, channel K, agent code=")).append(((Credential) accountMap.get("K")).getUserId()).toString());
	    driver = SeleniumUtil.createFireFoxWebDriver();
	    driver.get(baseUrl);
	    driver.findElement(By.name("username")).sendKeys(new CharSequence[] {((Credential) accountMap.get("K")).getUserId()});
	    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("K")).getPassword()});
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    SeleniumUtil.quitDriver(driver);
	    System.out.println((new StringBuilder("3. Testing agent login, channel B, agent code=")).append(((Credential) accountMap.get("B")).getUserId()).toString());
	    driver = SeleniumUtil.createFireFoxWebDriver();
	    driver.get(baseUrl);
	    driver.findElement(By.name("username")).sendKeys(new CharSequence[] {((Credential) accountMap.get("B")).getUserId()});
	    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("B")).getPassword()});
	    driver.findElement(By.id("submit")).click();
	    SeleniumUtil.quitDriver(driver);
	    System.out.println((new StringBuilder("4. Testing staff login, user Id=")).append(((Credential) accountMap.get("STAFF")).getUserId()).toString());
	    driver = SeleniumUtil.createFireFoxWebDriver();
	    driver.get(baseUrl);
	    driver.findElement(By.name("username")).sendKeys(new CharSequence[] {((Credential) accountMap.get("STAFF")).getUserId()});
	    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("STAFF")).getPassword()});
	    driver.findElement(By.id("submit")).click();*/
	   // SeleniumUtil.quitDriver(driver);
	    
	  }catch(org.openqa.selenium.NoSuchElementException e){
		  e.printStackTrace();
		  //SeleniumUtil.quitDriver(driver);
	  }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

  public void uat() throws IOException{

	    System.out.println((new StringBuilder("DCMS aes test start time:")).append((new Date()).toString()).toString());
	    String http = "https";
	    //String baseUrl = (new StringBuilder(String.valueOf(http))).append("://apdevrg.prudential.com.hk/ap/jsp/index.jsp").toString();
	    String baseUrl = (new StringBuilder(String.valueOf(http))).append("://apuat3.prudential.com.hk/ap/jsp/index.jsp").toString();
	    System.out.println((new StringBuilder("Base URL:")).append(baseUrl).toString());
	    WebDriver driver = SeleniumUtil.createFireFoxWebDriver();
		try{
		
			System.out.println((new StringBuilder("1. Testing agent login,  agent code=")).append(((Credential) accountMap.get("A")).getUserId()).toString());
	    driver.get(baseUrl);
	    driver.switchTo().frame("contentMain");
	    driver.findElement(By.name("user_id")).sendKeys(new CharSequence[] {((Credential) accountMap.get("A")).getUserId()});
	    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) accountMap.get("A")).getPassword()});
	    driver.findElement(By.name("image")).click();

	    System.out.println((new StringBuilder("step 1")));
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    driver.findElement(By.id("disclaimerDiv")).click();
	    System.out.println((new StringBuilder("step 2")));
	    String keyCsst="body div:nth-child(1)>table>tbody>tr>td>span"; 
	    WebElement we;

	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
	    Actions builder = new Actions(driver);
	    we=driver.findElement(By.cssSelector("body div:nth-of-type(3) > div > table > tbody > tr > td > span"));
	    builder.moveToElement(we).perform();
	    System.out.println("we="+we);
	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

	    we = driver.findElement(By.cssSelector("body div:nth-of-type(4) div:nth-of-type(1)"));
	    System.out.println("we="+we.getCssValue("visibility")+we.getCssValue("style"));
	    
	    String js = "arguments[0].style.visibility='visible';";
	    ((JavascriptExecutor) driver).executeScript(js, we);
	    
	    we.click();
	    System.out.println((new StringBuilder("step 3")));
	    
	    
	    String CWH=driver.getWindowHandle();
	    System.out.println("Current Window Handle: "+driver.getWindowHandle());
	    Set<String> s = new HashSet();
	    s.addAll(driver.getWindowHandles());   
	    s.remove(CWH);
	    driver.switchTo().window(""+s.toArray()[0]);
	    System.out.println("New Window Handle: "+driver.getWindowHandle());

	    driver.switchTo().frame("AESContent");
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

/*
	    we = driver.findElement(By.cssSelector("div#menu ul > li:nth-of-type(2)"));
	    we.click();
	    we = driver.findElement(By.cssSelector("a.menu"));
	    System.out.println("we="+we.getAttribute("innerHTML"));
	    we.click();*/
	    
	  }catch(org.openqa.selenium.NoSuchElementException e){
		  e.printStackTrace();
	  }
  }

  
  public void efolderProd() throws IOException{

	    System.out.println((new StringBuilder("DCMS aes test start time:")).append((new Date()).toString()).toString());
	    String http = "https";
	    String baseUrl = (new StringBuilder(String.valueOf(http))).append("://www.prudential.com.hk/scws/member").toString();
	    System.out.println((new StringBuilder("Base URL:")).append(baseUrl).toString());
	    WebDriver driver = SeleniumUtil.createFireFoxWebDriver();
		try{
		
			System.out.println((new StringBuilder("1. Testing agent login,  agent code=")).append(((Credential) efolderAccountMap.get("A")).getUserId()).toString());
	    driver.get(baseUrl);
	    driver.findElement(By.name("login")).sendKeys(new CharSequence[] {((Credential) efolderAccountMap.get("A")).getUserId()});
	    driver.findElement(By.name("password")).sendKeys(new CharSequence[] {((Credential) efolderAccountMap.get("A")).getPassword()});
	    driver.findElement(By.className("btnLogin").className("btnRed")).click();

	    System.out.println((new StringBuilder("step 1")));
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    driver.findElement(By.name("policyNo")).sendKeys((String)efolderPolicyMap.get("JEM"));
	    driver.findElement(By.xpath("//input[@type='button']")).click();
	    driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    driver.findElement(By.partialLinkText("Policy")).click();
	   
	    System.out.println((new StringBuilder("step 2")));
	    
	    //String keyCsst="body div:nth-child(1)>table>tbody>tr>td>span"; 
	    WebElement we;
/*
	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);
	    Actions builder = new Actions(driver);
	    we=driver.findElement(By.cssSelector("body div:nth-of-type(3) > div > table > tbody > tr > td > span"));
	    builder.moveToElement(we).perform();
	    System.out.println("we="+we);
	    driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.SECONDS);

	    we = driver.findElement(By.cssSelector("body div:nth-of-type(4) div:nth-of-type(1)"));
	    System.out.println("we="+we.getCssValue("visibility")+we.getCssValue("style"));
	    
	    String js = "arguments[0].style.visibility='visible';";
	    ((JavascriptExecutor) driver).executeScript(js, we);
	    
	    we.click();
	    System.out.println((new StringBuilder("step 3")));
	    
	    
	    String CWH=driver.getWindowHandle();
	    System.out.println("Current Window Handle: "+driver.getWindowHandle());
	    Set<String> s = new HashSet();
	    s.addAll(driver.getWindowHandles());   
	    s.remove(CWH);
	    driver.switchTo().window(""+s.toArray()[0]);
	    System.out.println("New Window Handle: "+driver.getWindowHandle());

	    driver.switchTo().frame("AESContent");
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    
	    we = driver.findElement(By.cssSelector("div#menu ul > li:nth-of-type(2)"));
	    we.click();

	    we = driver.findElement(By.cssSelector("a.menu"));
	    System.out.println("we="+we.getAttribute("innerHTML"));
	    we.click();*/
	  }catch(org.openqa.selenium.NoSuchElementException e){
		  e.printStackTrace();
	  }
  }

  
  public void wordpressSubmit() throws IOException{

	    System.out.println((new StringBuilder("wordpress start time:")).append((new Date()).toString()).toString());
	   
	    WebDriver driver = SeleniumUtil.createFireFoxWebDriver();
		try{
		
			System.out.println((new StringBuilder("1. Testing agent login,  agent code=")).append(((Credential) efolderAccountMap.get("A")).getUserId()).toString());
			Credential a =(Credential) wordpressMap.get("A");
	    driver.get(a.getUrl());
	    driver.findElement(By.id("user_login")).sendKeys(new CharSequence[] {a.getUserId()});
	    driver.findElement(By.id("user_pass")).sendKeys(new CharSequence[] {a.getPassword()});
	    driver.findElement(By.id("wp-submit")).click();

	    System.out.println((new StringBuilder("step 1")));
	    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("Ecwid Store")).click();
	    driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("Products")).click();
	   
	    System.out.println((new StringBuilder("step 2")));
	    
	    WebElement we;
	  }catch(org.openqa.selenium.NoSuchElementException e){
		  e.printStackTrace();
	  }
  }
}
