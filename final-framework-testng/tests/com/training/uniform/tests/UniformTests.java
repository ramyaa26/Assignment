package com.training.uniform.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.training.generics.ScreenShot;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;

public class UniformTests {
	
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
	
	//UNF_022
	
	@Test
	public void openReports_Sales_Orders() throws WebDriverException{
		
		try
		{
		validLoginTest();
		dashboardPOM.hoverReportsIcon();
		screenShot.captureScreenShot("Second-Dashboard-Reports");
		
		//verify Sales,Products,Customers and Marketing links are visible
		
		if(dashboardPOM.verifyReports_Icons())
		{
	     System.out.println("Sales, Products, Customers and Marketing links are visible as expected");
	     dashboardPOM.clickSalesIcon(); 
	     screenShot.captureScreenShot("Third-Dashboard-Reports-Sales");
	     
		//verify Orders,Tax,Shipping,Returns and Coupons link is visible
	     
		  if(dashboardPOM.verifySales_Icons())
		   {
			System.out.println("Orders, Tax, Shipping, Returns and Coupons links are visible as expected");
			dashboardPOM.clickOrders();
			System.out.println("Page title is "+driver.getTitle());
			screenShot.captureScreenShot("Fourth-Dashboard-Reports-Sales Report");
		   }
		  else
		   {
			System.out.println("Orders, Tax, Shipping, Returns and Coupons links are not visible as expected");
			screenShot.captureScreenShot("Fourth-Dashboard-Reports-Sales Report");
			throw new WebDriverException();
		   }
		}
		else
		{
			System.out.println("Sales, Products, Customers and Marketing links are not visible as expected");
			screenShot.captureScreenShot("Third-Dashboard-Reports-Sales");
			throw new WebDriverException();
		}
		
		//enter the Start Date and End Date as yyyy-mm-dd, assume 2020-02-01, Done for retrieving some valid data
		
		dashboardPOM.setsalesReportDateStart("20200201");
		dashboardPOM.setsalesReportDateEnd("20200201");
		
		//verify total number of orders,products details should get displayed
		
		if(dashboardPOM.verifyGroupBy_Drp_Down())
		{
			screenShot.captureScreenShot("Fifth-Sales Report-Group By Drop Down values");
			dashboardPOM.selectWeeks();
			System.out.println("Weeks is selected from the Group By drop down");
			screenShot.captureScreenShot("Sixth-Weeks is selected from Group By Drop Down values");
		}
		else
		{
			System.out.println("Group By drop down values is not available");
			throw new WebDriverException();
		}
		
		//clicking the filter button
		
		dashboardPOM.clickFilterBtn();
		System.out.println("Filter button is clicked");
		screenShot.captureScreenShot("Seventh-Filter button is clicked");
		
		//verify number of orders is displayed, assume there is only 1 order on 2020-02-01 for 12 products
		
		if(dashboardPOM.verifySalesOrder_Table())
		{ 
		String result = dashboardPOM.verifySalesOrderTable_Values(1, 12);
		  if(result.equals("No Results for specified period"))
		  {
			  System.out.println("No Results for specified period");
			  screenShot.captureScreenShot("Eighth-No Results for specified period");
		  }
			
		  else if(result.equals("Matching"))
		  {
		      System.out.println("Total number of orders and products are matching");
		      screenShot.captureScreenShot("Eighth-Total number of orders and products are matching with expected values");
		  }
		  else
		  {
			  System.out.println("Total number of orders and products are not matching");
			  screenShot.captureScreenShot("Eighth-Total number of orders and products are not matching with expected values");
		  }
		}
		else
		{
			System.out.println("Sales Order table is not displayed correctly");
			throw new WebDriverException();
		}
		}catch(WebDriverException e){
			e.printStackTrace();
		}
				
    }
	
	//UNF_023
	
	@Test
	public void openReports_Sales_Tax() throws WebDriverException{
		
		try
		{
		validLoginTest();
		dashboardPOM.hoverReportsIcon();
		screenShot.captureScreenShot("Second-Dashboard-Reports");
		
		//verify Sales,Products,Customers and Marketing links are visible
		
		if(dashboardPOM.verifyReports_Icons())
		{
	     System.out.println("Sales, Products, Customers and Marketing links are visible as expected");
	     dashboardPOM.clickSalesIcon(); 
	     screenShot.captureScreenShot("Third-Dashboard-Reports-Sales");
	     
		//verify Orders,Tax,Shipping,Returns and Coupons link is visible
	     
		  if(dashboardPOM.verifySales_Icons())
		   {
			System.out.println("Orders, Tax, Shipping, Returns and Coupons links are visible as expected");
			dashboardPOM.clickTax();
			System.out.println("Page title is "+driver.getTitle());
			screenShot.captureScreenShot("Fourth-Dashboard-Reports-Tax Report");
		   }
		  else
		   {
			System.out.println("Orders, Tax, Shipping, Returns and Coupons links are not visible as expected");
			screenShot.captureScreenShot("Fourth-Dashboard-Reports-Tax Report");
			throw new WebDriverException();
		   }
		}
		else
		{
			System.out.println("Sales, Products, Customers and Marketing links are not visible as expected");
			screenShot.captureScreenShot("Third-Dashboard-Reports-Sales");
			throw new WebDriverException();
		}
				
		//verify total tax paid is displayed
		
		if(dashboardPOM.verifyGroupBy_Drp_Down())
		{
			screenShot.captureScreenShot("Fifth-Sales Report-Group By Drop Down values");
			dashboardPOM.selectWeeks();
			System.out.println("Weeks is selected from the Group By drop down");
			screenShot.captureScreenShot("Sixth-Weeks is selected from Group By Drop Down values");
		}
		else
		{
			System.out.println("Group By drop down values is not available");
			throw new WebDriverException();
		}
		
		//clicking the filter button
		
		dashboardPOM.clickFilterBtn();
		System.out.println("Filter button is clicked");
		screenShot.captureScreenShot("Seventh-Filter button is clicked");
		
		//verify the total tax paid for orders placed 
		
		if(dashboardPOM.verifyTaxReport_Table())
		{ 
		String result = dashboardPOM.verifySalesOrderTable_Values(1, 12);
		  if(result.equals("No Results for specified period"))
		  {
			  System.out.println("No Results for specified period");
			  screenShot.captureScreenShot("Eighth-No Results for specified period");
		  }
			
		  else if(result.equals("Matching"))
		  {
		      System.out.println("Total number of orders and products are matching");
		      screenShot.captureScreenShot("Eighth-Total number of orders and products are matching with expected values");
		  }
		  else
		  {
			  System.out.println("Total number of orders and products are not matching");
			  screenShot.captureScreenShot("Eighth-Total number of orders and products are not matching with expected values");
		  }
		}
		else
		{
			System.out.println("Sales Order table is not displayed correctly");
			throw new WebDriverException();
		}
		}catch(WebDriverException e){
			e.printStackTrace();
		}
	}
	
	

}
