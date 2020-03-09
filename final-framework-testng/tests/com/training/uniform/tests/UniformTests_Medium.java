package com.training.uniform.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UniformTests_Medium {
	
	private WebDriver driver;
	private DashboardPOM dashboardPOM;
	private ScreenShot screenShot;
    private String baseUrl;
	private static Properties properties;
	private LoginPOM loginPOM;
	
	
    @BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		dashboardPOM = new DashboardPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}
	
	@Test(enabled=false)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("First-Dashboard page");
	}
	
	//UNF_045
	
	@Test
	public void viewOrderDetails() throws WebDriverException
	{
		try
		{
		validLoginTest();
		dashboardPOM.hoverCartIcon();
		screenShot.captureScreenShot("Second-Dashboard-Shopping Cart");
		
		// verify Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are visible as expected
		if(dashboardPOM.verifyCart_Icons())
		{
			System.out.println("Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are visible as expected");
		    dashboardPOM.clickCartOrders();
		    screenShot.captureScreenShot("Third-Order List");
		    //verify page title and page contents
		    String title = driver.getTitle();
		    System.out.println("Page Title is "+title);
		    if(title.equals("Orders"))
		    {
		    	//verify the orders are displayed in the table and click on view button
		    	
		    	dashboardPOM.clickViewIcon();    //assuming that the View button on the first row is clicked
		    	System.out.println("Order details is displayed");
		    	screenShot.captureScreenShot("Fourth-Order Details");
		    	
		    	//click on the generate icon of the invoice tab
		    	
		    	if(dashboardPOM.getInvoiceId().equals(""))
		    	{
		    	dashboardPOM.clickGenerateBtn();
		    	screenShot.captureScreenShot("Five-Order-Invoice ID generated");
		    	String invoice = dashboardPOM.getInvoiceId();
		    	System.out.println("Invoice is generated with ID "+invoice);
		    	}
		    	else
		    	{
		    		screenShot.captureScreenShot("Five-Order-Existing Invoice ID");
		    		String invoice = dashboardPOM.getInvoiceId();
		    		System.out.println("Invoice ID "+invoice+" is already available");
		    	}
		    	
		    }
		    else
		    {
		    	System.out.println("Page Title is not displayed as expected");		    	
		    }
		}
		else
		{
			System.out.println("Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are not visible as expected");
			throw new WebDriverException();
		}	
		
		}catch(WebDriverException we){
			we.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//UNF_046
	
	@Test
	public void changeOrder_Admin(){
		
		validLoginTest();
		dashboardPOM.hoverCartIcon();
		screenShot.captureScreenShot("Second-Dashboard-Shopping Cart");
		
		// verify Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are visible as expected
		if(dashboardPOM.verifyCart_Icons())
		{
			System.out.println("Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are visible as expected");
		    dashboardPOM.clickCartOrders();
		    screenShot.captureScreenShot("Third-Order List");
		    //verify page title and page contents
		    String title = driver.getTitle();
		    System.out.println("Page Title is "+title);
		    if(title.equals("Orders"))
		    {
		    	//verify the orders are displayed in the table and click on edit button
		    	
		    	dashboardPOM.clickEditIcon();    //assuming that the View button on the first row is clicked
		    	System.out.println("Edit Order page is displayed");
		    	screenShot.captureScreenShot("Fourth-Edit Order page");
		    	
		    	//click on the generate icon of the invoice tab
		    	
		    	dashboardPOM.clickContinue_Btn();
		    	
		    	if(dashboardPOM.verifyProductsTab())
		    	{
		    		System.out.println("Products tab is displayed");
		    		screenShot.captureScreenShot("Fifth-Edit Order-Products tab");
		    		
		    		//click on remove icon, assuming that remove icon on the first row is clicked
		    		
		    		dashboardPOM.clickRemoveBtn();
		    		System.out.println("One product is removed from the order");
		    		screenShot.captureScreenShot("Sixth-Edit Order-Products tab-item removed");
		    		
		    		//enter valid credentials in choose products textbox
		    		dashboardPOM.chooseProduct("Blazer(II-V)", "1");
		    		screenShot.captureScreenShot("Seventh-Edit Order-Products tab-product details entered");
		    		dashboardPOM.addProduct();
		    		
		    	}
		    	else
		    	{
		    		System.out.println("Products tab is not displayed");
		    		throw new WebDriverException();
		    	}		    	
		    }
		}
		else
		{
			System.out.println("Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are not visible as expected");
			throw new WebDriverException();
		}	
		
		
	}

}
