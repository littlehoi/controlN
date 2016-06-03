package com.pru.hk.aes;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriverService.Builder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Test;

@RunWith(JUnit4.class)
public class FireFoxDomainSearch extends TestCase {

	private static WebDriver driver;
	static String[] akeys={"camel","cat","cheetah","chicken","chimpanzee","cow","crocodile","deer","dog","dolphin","duck","eagle","elephant","fish","fly","fox","frog","giraffe","goat","goldfish","hamster","hippopotamus","horse","kangaroo","kitten","lion","lobster","monkey","octopus","owl","panda","pig","puppy","rabbit","rat","scorpion","seal","shark","sheep","snail","snake","spider","squirrel","tiger","turtle","wolf","zebra"};
	static String[] keys1={"reactor","readout","reamer","reaper","rear","reata","rebate","rebato","reboxetine","rebozo","recap","receiver","receptacle","recess","recliner","record","recorder","rectifier","rectory","red","redoubt","reducer","reed","reef","reefer","reel","refectory","refill","refinery","reflection","reflectometer","reflector","reflexion","reformatory"};
	static String[] keys2={"reformer","refractometer","refractory","refrigerator","refuge","regalia","regimentals","register","regulator","regulus","rein","reinforcement","relaxant","relaxer"};
	static String[] keys3={"relay","release","relic","relict","relief","relievo","reliquary","remainder","remains","remake","remaking","remedy","remise","remnant","remote"};
	static String[] keys4={"rent","rep","repeater","repertory","replica","replication","repository","repp","representation","represser","repressor","reproducer","reproduction","republican","requirement"};
	static String[] keys={"requisite","rerebrace","reredos","reseau","reservoir","reset","residence","resistance","resistor","resonator","resort hotel","respirator","rest","restaurant","restoration","restorative","restraint","restroom","resuscitator","retainer","retardant","retardation","retardent","reticle","reticulation","reticule","reticulum","retort","retractor","retread","retreat","retrenchment","retrofit","retrorocket","return","revere","revers","reverse","reversible","revetement","revetment","revolver"};
	
	/*
	virtualreadout.com: ok
	virtualreamer.com: ok
	virtualreaper.com: ok
	virtualrear.com: ok
	virtualreata.com: ok
	virtualrebato.com: ok
	virtualreboxetine.com: ok
	virtualrebozo.com: ok
	virtualrecap.com: ok
	virtualreceiver.com: ok
	virtualreceptacle.com: ok
	virtualrecess.com: ok
	virtualreformer.com: ok
	virtualrefractometer.com: ok
	virtualrefractory.com: ok
	virtualrefuge.com: ok
	virtualregalia.com: ok
	virtualregimentals.com: ok
	virtualregulator.com: ok
	virtualregulus.com: ok
	virtualrein.com: ok
	virtualreinforcement.com: ok
	virtualrelaxant.com: ok
	virtualrelaxer.com: ok
	virtualrelay.com: ok
	virtualrent.com: ok
virtualrepeater.com: ok
virtualrepertory.com: ok
virtualrepository.com: ok
virtualrepp.com: ok
virtualrepresentation.com: ok
virtualrepresser.com: ok
virtualrepressor.com: ok
virtualreproducer.com: ok
virtualreproduction.com: ok
virtualrepublican.com: ok
	*/
	static String globalkey="";
	@BeforeClass
	public static void createAndStartService() throws IOException {
	}

	@Before
	public void createDriver() throws IOException {
		System.out.println("start=>1");
		driver = SeleniumUtil.createFireFoxWebDriver();
	}

	@After
	public void quitDriver() {
		driver.quit();
	}

	@Test
	public void testDomainSearch() {
		try{
		System.out.println("start~");
		
		//String key="visualizer";//(1247, 1668)
		//String key="notregyet";//(1247, 1840)
		//String key="vitualzebras";//(1247, 1840)
		
		for(String key:keys){
		globalkey = key;
		key="virtual"+key;
		driver.get("https://hk.godaddy.com/domains/searchresults.aspx?checkAvail=1&domainToCheck="+key);
		WebElement searchBox = driver.findElement(By.id("search_form_btn"));
		searchBox.click();
		WebElement foundKey=driver.findElement(By.xpath("//div[contains(.,'太好了！您可以使用此網域。 立即購買，以免別人捷足先登')]"));
		//System.out.println(foundKey.getSize());
		if ("(1247, 1668)".equalsIgnoreCase(foundKey.getSize().toString())){
			//System.out.println("visual"+key+".com: not avaiable");
		}else{
			System.out.println(""+key+".com: ok");
		}
		 driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(""+globalkey+" working on");			
		}
		
	}
}