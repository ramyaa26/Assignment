package com.training.uniform.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.training.generics.ScreenShot;
import com.training.pom.CustomerPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import com.training.readexcel.ApachePOIReadExcel;

public class UniformTests_Complex {

	private WebDriver driver;
	private DashboardPOM dashboardPOM;
	private ScreenShot screenShot;
	private String baseUrl;
	private static Properties properties;
	private LoginPOM loginPOM;
	private CustomerPOM customerPOM;
	private String excelPath;
	private ApachePOIReadExcel excel;
	private String rowvalue;

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
		customerPOM = new CustomerPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		excelPath = properties.getProperty("excelfile1");
		screenShot = new ScreenShot(driver);
		excel = new ApachePOIReadExcel();
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		// driver.quit();
	}

	@Test(enabled = false)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		screenShot.captureScreenShot("1-Dashboard page");
	}

	@Test(enabled = false)
	public void WriteExcelFile(String filename,List<String> data) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(1);

		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		for(int i=0;i<data.size();i++)
		{
		String product = data.get(i);
		sheet.getRow(i).getCell(0).setCellValue(product);
		}
		FileOutputStream fos = new FileOutputStream(filename);
		workbook.write(fos);
		fos.close();
		
		workbook.close();
	}

	// UNF_075

	// to update the customer details with data from an excel sheet

	@Test(enabled = false)
	public void customerUpdate() throws WebDriverException, IOException {
		try {
			validLoginTest();
			dashboardPOM.clickCustomers();
			screenShot.captureScreenShot("2-Dashboard-Customers");

			dashboardPOM.clickCustomer();

			System.out.println("Page title is " + driver.getTitle());
			screenShot.captureScreenShot("3-Dashboard-Customers-Customer List");

			if (driver.getTitle().equals("Customers")) {
				customerPOM.clickEditIcon("ashwini@gmail.com");
				if (customerPOM.verifyEditCustomerPage()) {
					screenShot.captureScreenShot("4-Edit Customer page");
					rowvalue = null;
					System.out.println("Start");
					// String filename = SetupExcelFile("CustomerDetails.xlsx");
					String filename = "C:\\Ramya\\Learning\\Reskill Program\\2Phase Selenium Assignments\\CustomerDetails.xlsx";

					// reading customer group from excel
					rowvalue = excel.ReadValuesFromExcel(filename, "General", 1, 0);
					System.out.println("Customer Group= " + rowvalue);
					customerPOM.setCustomerGrp(rowvalue);

					// reading telephone from excel
					rowvalue = excel.ReadValuesFromExcel(filename, "General", 1, 4);
					System.out.println("Telephone= " + rowvalue);
					customerPOM.setTelephone(rowvalue);

					// reading fax from excel
					rowvalue = excel.ReadValuesFromExcel(filename, "General", 1, 5);
					System.out.println("Fax= " + rowvalue);
					customerPOM.setFax(rowvalue);

					// reading password from excel
					rowvalue = excel.ReadValuesFromExcel(filename, "General", 1, 6);
					System.out.println("Password= " + rowvalue);
					customerPOM.setPassword(rowvalue);

					// reading confirm password from excel
					rowvalue = excel.ReadValuesFromExcel(filename, "General", 1, 7);
					System.out.println("Confirm Password= " + rowvalue);
					customerPOM.setConfirm_pwd(rowvalue);

					// reading newsletter from excel
					rowvalue = excel.ReadValuesFromExcel(filename, "General", 1, 8);
					System.out.println("Newsletter= " + rowvalue);
					customerPOM.setNewsletter(rowvalue);

					screenShot.captureScreenShot("5-Customer details entered");

					System.out.println("End");

					customerPOM.clickSaveBtn();

					if (customerPOM.verifySuccessAlert()) {
						System.out.println("Customer details updated");
						screenShot.captureScreenShot("6-Customer details updated");
					} else {
						System.out.println("6-Customer details not updated");
						throw new WebDriverException();
					}
				}
			} else {
				System.out.println("Page title is not as expected");
				throw new WebDriverException();
			}
		} catch (WebDriverException we) {
			we.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// UNF_076

	// admin to change order placed by the customer & modified details get
	// stored in Excel Sheet

	@Test
	public void changeOrder_Admin() throws InterruptedException, WebDriverException {

		try {
			validLoginTest();
			dashboardPOM.hoverCartIcon();
			screenShot.captureScreenShot("Second-Dashboard-Shopping Cart");

			// verify Orders, Recurring Orders, Returns, Gift Vouchers and
			// Paypal links are visible as expected
			if (dashboardPOM.verifyCart_Icons()) {
				System.out.println(
						"Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are visible as expected");
				dashboardPOM.clickCartOrders();
				screenShot.captureScreenShot("Third-Order List");
				// verify page title and page contents
				String title = driver.getTitle();
				System.out.println("Page Title is " + title);
				if (title.equals("Orders")) {
					// verify the orders are displayed in the table and click on
					// edit button

					dashboardPOM.clickEditIcon(); // assuming that the Edit
													// button on the first row
													// is clicked
					
					
					  System.out.println("Edit Order page is displayed");
					  screenShot.captureScreenShot("Fourth-Edit Order page");
		
					  dashboardPOM.clickContinue_Btn();
					  
					 Thread.sleep(5000);
					  
					  if(dashboardPOM.verifyProductsTab()) {
					  System.out.println("Products tab is displayed");
					 //Thread.sleep(5000); 
					 screenShot.captureScreenShot("Fifth-Edit Order-Products tab");
					 
					 //click on remove icon, assuming that remove icon on the first row is clicked
					  
					 dashboardPOM.clickRemoveBtn(); //Thread.sleep(5000);
					 System.out.println("One product is removed from the order");
					 screenShot.captureScreenShot("Sixth-Edit Order-Products tab-item removed");
					 
					 //enter valid credentials in choose products textbox
					 
					 dashboardPOM.chooseProduct("Blazer(II-V)", "1");
					 //Thread.sleep(5000); 
					 screenShot.captureScreenShot("Seventh-Edit Order-Products tab-product details entered"); 
					 dashboardPOM.addProduct(); //Thread.sleep(5000);
					 screenShot.captureScreenShot("Eighth-Edit Order-Products tab-New product added");
					 dashboardPOM.clickProductContinue_Btn();
					 if(dashboardPOM.verifyPaymentDetailsTab()){
					 screenShot.captureScreenShot("Nineth-Edit Order-Payment Details tab");
					 dashboardPOM.clickPaymentContinue_Btn();
					 if(dashboardPOM.verifyShippingDetailsTab()){
					 screenShot.captureScreenShot("Tenth-Edit Order-Shipping Details tab");
					 dashboardPOM.clickShippingContinue_Btn();
					 if(dashboardPOM.verifyTotalsTab()){
					 screenShot.captureScreenShot("Eleventh-Edit Order - Totals tab");
					 dashboardPOM.saveOrder(); //Thread.sleep(5000);
					 screenShot.captureScreenShot("Twelveth-Edit Order-Order Saved");
					 } 
					 else{
					 System.out.println("Totals tab is not displayed as expected"); 
					 throw new WebDriverException(); 
					 } 
					 } 
					 else{ 
						 System.out.println("Shipping Details tab is not displayed as expected");
					  throw new WebDriverException(); 
					  } 
					 } 
					 else{
					  System.out.println("Payment Details tab is not displayed as expected");
					  throw new WebDriverException();
					 }					  
					  } 
					  else { System.out.println(
					  "Products tab is not displayed as expected"); 
					  throw new WebDriverException(); 
					  } 
					  }
				List<String> data = dashboardPOM.getOrderListTableValues();
				String filename = "C:\\Ramya\\Learning\\Reskill Program\\2Phase Selenium Assignments\\OrderList.xlsx";
				WriteExcelFile(filename,data);
					 
				} else {
					System.out.println("Orders, Recurring Orders, Returns, Gift Vouchers and Paypal links are not visible as expected");
					throw new WebDriverException();
				}
	}catch(WebDriverException we)
	{
	  we.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}

  }
}

