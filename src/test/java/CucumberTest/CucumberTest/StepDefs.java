package CucumberTest.CucumberTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import gherkin.*;
import cucumber.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;
//import cucumber.annotation.en.Then;
//import cucumber.annotation.en.When;
import cucumber.runtime.table.*;
import taf.ActorLibrary;
import taf.OperationStepProcess;
import taf.SpineReturn;


public class StepDefs{

	public static WebDriver driver;
	public static String osSystem = System.getProperty("os.name");
	public static String Name;
	public static String Introduced;
	public static String Discontinued;
	public static String Company;
	public static HashMap<String,String> scenarioInfoContainer;
	public static String Browser;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//
	@Given("^I am on the CRUD web portal$")
	public void user_is_on_Home_Page() throws Throwable {
		//WebDriver driver;
		//String osSystem = 
	
		//Browser = "Chrome";
		Browser = "Firefox";
		
		//ActorLibrary.scenarioExecution(1);
		
		System.out.println(osSystem);
		
		switch (Browser){
		case "Firefox":
			switch (osSystem){
				case "Mac OS X":	
					System.setProperty("webdriver.gecko.driver", "//geckodriver//geckodriver"); // need to document this
					//ChromeOptions options = new ChromeOptions();
				   // System.setProperty("webdriver.chrome.logfile", "\\path\\chromedriver.log");
				   
				    //ChromeOptions options = new ChromeOptions();
				    //options.addArguments("--start-maximized");
				    
				    //System.setProperty("webdriver.chrome.driver", "\\path\\chromedriver.exe");
				    //System.setProperty("webdriver.chrome.args", "--disable-logging");
				    //System.setProperty("webdriver.chrome.silentOutput", "true");
					
					driver = new FirefoxDriver();
					break;
				default:
					System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe"); // need to document this
					driver = new FirefoxDriver();
					break;
			}
			break;
		case "Chrome":	
			switch (osSystem){
				case "Mac OS X":	
					System.setProperty("webdriver.chrome.driver", "//chromedriver//chromedriver"); // need to document this
					driver = new ChromeDriver();
					break;
				default:
					System.setProperty("webdriver.chrome.driver", "C:\\GeckoDriver\\geckodriver.exe"); // need to document this
					driver = new ChromeDriver();
					break;
			}
			
			break;
		
		}
		
		driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.get("http://computer-database.herokuapp.com/computers"); //goto the CRUD portal
	      
		}
	

	@Given("^I have clicked the add a new computer button$")
	public void i_have_clicked_the_add_a_new_computer_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.cssSelector("#add")).click(); // click the add button
	    //throw new PendingException();
	}

	@Given("^I have entered computer details$")
	public void i_have_entered_computer_details(DataTable computerDetails) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		List<Map<String,String>> data = computerDetails.asMaps(String.class,String.class);
		
		int milliAppend = dataUtils.getTimeInt();
		String appendString = Integer.toString(milliAppend);
		
		Name = (data.get(0).get("Name") + appendString);
		System.out.println(Name);
		Introduced = data.get(0).get("Introduced");
		System.out.println(Introduced);
		Discontinued = data.get(0).get("Discontinued");
		System.out.println(Discontinued);
		Company = data.get(0).get("Company");
		System.out.println(Company);
		
		//Map<String, String> data : computerDetails.asMaps(String.class, String.class);
		
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    driver.findElement(By.id("add")).click();
	    //driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys(Name);
	    //driver.findElement(By.id("introduced")).clear();
	    driver.findElement(By.id("introduced")).sendKeys(Introduced);
	    //driver.findElement(By.id("discontinued")).clear();
	    driver.findElement(By.id("discontinued")).sendKeys(Discontinued);
	    driver.findElement(By.id("company")).click();
	    //driver.wait();
	    
	    
	    
	    switch (osSystem){
		case "Mac OS X":	
			 // need to document this
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("company"))).selectByVisibleText(Company);
			break;
		default:
			System.out.println("windows list select");
			driver.findElement(By.id("company")).click();
			driver.findElement(By.id("company")).sendKeys(Company);
			break;
	}
	    
	    
	    //
	    //WebElement dropDownListBox = driver.findElement(By.id("company")); 
	    //Select clickThis = new Select(dropDownListBox); clickThis.selectByValue("Apple Inc.");
	    
	    
	    //new Select(driver.findElement(By.id("company")).selectByVisibleText(Company));
		
	    //throw new PendingException();
	}

	@When("^I click create this computer$")
	public void i_click_create_this_computer() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		driver.findElement(By.cssSelector("input.btn.primary")).click();

	}

	@Then("^the computer details I have entered are displayed in the main listing correctly$")
	public void the_computer_details_I_have_entered_are_displayed_in_the_main_listing_correctly() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		// Warning: assertTextPresent may require manual changes
		String expectedConfBanner;
		expectedConfBanner = "Done! Computer " + Name + " has been created";
		String actualConfBanner = driver.findElement(By.cssSelector("div.alert-message.warning")).getText();
		//assertTextPresent(driver.findElement(By.cssSelector("BODY")).getText().matches(expectedConfBanner));

		// Warning: assertTextPresent may require manual changes
		assertEquals(expectedConfBanner, actualConfBanner);
		
		// I want to write a fancy method for interrogating the table, but I don't have time!
	
	}
	
	@When("^I click cancel$")
	public void i_click_cancel() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		driver.findElement(By.linkText("Cancel")).click();	
	}
		
	@Then("^I am returned to the main screen$")
	public void i_am_returned_to_the_main_screen() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new PendingException();
		//assertTrue(isElementPresent(By.cssSelector("#main > h1")));
		 // public void testAssertelement() throws Exception {
			    //assertTrue(isElementPresent(By.cssSelector("form")));
			 // }
	
		assertTrue(isElementPresent(By.cssSelector("form")));
		
		
	}
	
	public boolean isElementPresent(By by) {
	    try {
	      driver.findElements(by);
	      return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	      return false;
	    }
	}
	
	
	@Given("^I have recalled the created computer$")
		public void i_have_recalled_the_created_computer() throws Throwable{
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	  
	    driver.findElement(By.id("searchbox")).clear();
	    driver.findElement(By.id("searchbox")).sendKeys(Name);
	    driver.findElement(By.id("searchsubmit")).click();
	    
	    
	    driver.findElement(By.linkText(Name)).click();
	    //driver.findElement(By.id("link=/" + Name)).click();
	    
	    
	}

	@Given("^I delete the created computer$") 
	    // Write code here that turns the phrase above into concrete actions
		public void i_delete_the_created_computer() throws Throwable{
			    // Write code here that turns the phrase above into concrete actions
			    //throw new PendingException();
			  
		    driver.findElement(By.cssSelector("input.btn.danger")).click();


			}
	
	@Given("^The computer is deleted$")
		public void the_computer_is_deleted() throws Throwable {
		

	    String expectedConfBanner;
		expectedConfBanner = "Done! Computer has been deleted";
		String actualConfBanner = driver.findElement(By.cssSelector("div.alert-message.warning")).getText();
		assertEquals(expectedConfBanner, actualConfBanner);
	}
	
	@Given("^I have created a new computer$") // compound step definition....
	public void i_have_created_a_new_computer(DataTable computerDetails) throws Throwable {
	    // Write code here that turns the phra_usinse above into concrete actions
		
		
		user_is_on_Home_Page(); // nav to portal
		i_click_create_this_computer(); // click create computer
		
		i_have_entered_computer_details(computerDetails); // enter computer details
		i_click_create_this_computer(); // click create computer button
		
		//String[] argArray = new String[] {"SCENARIOINDEX:1"}; 
		
		//SpineReturn rs = new SpineReturn();
		///SpineReturn rs = ActorLibrary.main(argArray); // kick off scenario 1
		//driver = rs.getDriver();
		//scenarioInfoContainer = rs.getScenarioInfoContainer();
		
		//Name = scenarioInfoContainer.get("NAME");
		//System.out.println("Name handed over from SCAFRA = " + Name);
		
	}
	
	
	@Given("^I have created a new computer using pre requisite scenario one$") // compound step definition....
	public void i_have_created_a_new_computer_using_pre_requisite_scenario_one() throws Throwable {
	    // Write code here that turns the phra_usinse above into concrete actions
		scafra_call ("SCENARIOINDEX:1");
	}
		
	@When("^I search for the computer using the name filter$")
	public void i_search_for_the_computer_using_the_name_filter() throws Throwable {
		i_have_recalled_the_created_computer();
	}
	
	
	@Then("^the computer details are displayed$")
	public void the_computer_details_are_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new PendingException();
		//assertEquals(Name, driver.findElement(By.id("name")).getText());
		//assertEquals(Introduced, driver.findElement(By.id("introduced")).getText());
		//assertEquals(Discontinued, driver.findElement(By.id("discontinued")).getText());
		//assertEquals(Company, driver.findElement(By.id("company")).getText());
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date IntroducedParsed = df.parse(Introduced);
		Date DiscontinuedParsed = df.parse(Discontinued);
		
		String expectedResultString = Name + " " + IntroducedParsed + " " + DiscontinuedParsed + " " + Company;
		System.out.println(expectedResultString);
		
		//Date date = new SimpleDateFormat("dd-mmm-yyyy").parse("");
		//String formattedDateIntroduced = new SimpleDateFormat("dd-mmm-yyyy").format(Introduced);
		//String formattedDateDiscontinued = new SimpleDateFormat("dd-mmm-yyyy").format(Discontinued);
		//System.out.println(formattedDateIntroduced);
		//System.out.println(formattedDateDiscontinued);
		//String expectedResultString = Name + " " + Introduced + " " + Discontinued + " " + Company;
	
		
		String resultBodyText = driver.findElement(By.tagName("BODY")).getText();
		
		String lines[] = resultBodyText.split("\\r?\\n");
		System.out.println(Arrays.toString(lines));
		String computerDetails = lines[4];
		System.out.println(computerDetails); // it's a hack but it works!
		
		assertEquals(expectedResultString,computerDetails);
		
		System.out.println(lines);
		
		//assertEquals(expectedResultString, resultBodyText);
		
		
		
	}
	
	
	@Given("^I have created a new computer and selected for edit using pre requisite scenario two$")
	public void i_have_created_a_new_computer_and_selected_for_edit_using_pre_requisite_scenario_two() throws Throwable {
		scafra_call ("SCENARIOINDEX:2");
	}

	
	@Given("^I have entered computer details using pre requisite scenario one point one$")
	public void i_have_entered_computer_details_using_pre_requisite_scenario_one_point_one() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		scafra_call ("SCENARIOINDEX:1.1");
	}

	
	@Given("^I have executed pre requisite scenario (.*)$")
    // Write code here that turns the phrase above into concrete actions
	public void execute_pre_requisite(Integer arg1){
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		scafra_call ("SCENARIOINDEX:"+ arg1);
	
	}
	
	
	public void scafra_call (String scenarioArg){ // use this function call to launch a scafra pre-requisite scenario
		
		// current command line options:
		// 1. SCENARIO_INDEX
		// passed in using format 'SCENARIOINDEX:n' where 'n' is the numeric value that represents the scenario index
		// - i.e. SCENARIOINDEX:1 will call pre-requisite scenario 1.
		// other options TBC
		
		String[] argArray = new String[] {scenarioArg}; 
		
		//SpineReturn rs = new SpineReturn();
		SpineReturn rs = ActorLibrary.main(argArray); // kick off scenario 
		driver = rs.getDriver();
		scenarioInfoContainer = rs.getScenarioInfoContainer();
		String scafraExecStatus = scenarioInfoContainer.get("STATUS"); // pass, fail?
		
		Name = scenarioInfoContainer.get("NAME");
		System.out.println("Driver handed over from SCAFRA = " + driver);
		System.out.println("Name handed over from SCAFRA = " + Name);
		
		System.out.println("PASS / FAIL execution status handed over from SCAFRA = " + scafraExecStatus);
		
		assertEquals("pass",scafraExecStatus); // this is a bodge!
		
		//return scafraExecStatus;
		//throw new PendingException();
		
	}
	
	@When("^I edit the computer record$")
	public void i_edit_the_computer_record() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		Name = Name + "edit test"; // create updated name
		
		driver.findElement(By.id("name")).sendKeys(Name); // edit the name
		
		//driver.findElement(By.xpath("//input[@value='Save this computer']")).click(); // click save
		
		OperationStepProcess.operationExecution("CRUD","click save this computer button"); // experimental scafra call
		
	    //throw new PendingException();
	}
	
	
	@Then("^The computer record is is edited$")
	public void the_computer_record_is_is_edited() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	
		 // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		// Warning: assertTextPresent may require manual changes
		String expectedConfBanner;
		expectedConfBanner = "Done! Computer " + Name + " has been updated";
		String actualConfBanner = driver.findElement(By.cssSelector("div.alert-message.warning")).getText();
		//assertTextPresent(driver.findElement(By.cssSelector("BODY")).getText().matches(expectedConfBanner));

		// Warning: assertTextPresent may require manual changes
		assertEquals(expectedConfBanner, actualConfBanner);
		
		// I want to write a fancy method for interrogating the table, but I don't have time!
	
	}
	
}

