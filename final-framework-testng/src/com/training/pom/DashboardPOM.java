package com.training.pom;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPOM {
private WebDriver driver;
    
	public DashboardPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//ul/li/a/i[@class='fa fa-bar-chart-o fa-fw']")
	private WebElement reports; 
	
	@FindBy(xpath="//*/ul/li/a[@class='parent'][child::text()='Sales']")
	private WebElement sales;
	
	@FindBy(xpath="//*/ul/li/a[@class='parent'][child::text()='Products']")
	private WebElement products;
	
	@FindBy(xpath="//*/ul/li/a[@class='parent'][child::text()='Customers']")
	private WebElement customers;
	
	@FindBy(xpath="//*/ul/li/a[@class='parent'][child::text()='Marketing']")
	private WebElement marketing;
	
	@FindBy(xpath="//a[contains(text(),'Sales')]/following::ul[1]/li")
	private List<WebElement> sales_list;
	
	@FindBy(xpath="//*[@id='input-date-start']")
	private WebElement sales_Report_DateStart;
	
	@FindBy(xpath="//*[@id='input-date-end']")
	private WebElement sales_Report_DateEnd;
	
	@FindBy(xpath="//*/label[text()='Group By']")
	private WebElement sales_Report_GroupBy;
	
	@FindBy(xpath="//*[@id='input-group']")
	private WebElement sales_Report_GroupBy_Drp_Dwn;
	
	@FindBy(xpath="//*[@id='input-group']/option")
	private List<WebElement> sales_Report_GroupBy_Drp_Dwn_Values;
	
	@FindBy(xpath="//*[@id='button-filter']")
	private WebElement filter_Btn;
	
	@FindBy(xpath="//*/div[@class='table-responsive']")
	private WebElement salesReport_table;
	
	@FindBy(xpath="//*/td[@class='text-center']")
	private WebElement salesreport_NoResults;
	
	@FindBy(xpath="//*/div[@class='table-responsive']/table/tbody/tr[1]/td")
	private List<WebElement> report_Results;
	
	@FindBy(xpath="//*/tr/td[text()='No. Orders']")
	private WebElement noOfOrders;
	
	@FindBy(xpath="//*/tr/td[text()='Date Start']")
	private WebElement DateStart_col;
	
	@FindBy(xpath="//*/tr/td[text()='Date End']")
	private WebElement DateEnd_col;
	
	@FindBy(xpath="//*/tr/td[text()='No. Products']")
	private WebElement noOfProducts;
	
	@FindBy(xpath="//*/tr/td[text()='Tax Title']")
	private WebElement taxTitle;
	
	@FindBy(xpath="//*/tr/td[text()='Total']")
	private WebElement total_Col;
	
	@FindBy(xpath="//*/tr/td[text()='Shipping Title']")
	private WebElement shippingTitle;
	
	@FindBy(xpath="//ul/li/a/i[@class='fa fa-shopping-cart fa-fw']")
	private WebElement cart;
	
	@FindBy(xpath="//span[text()='Sales']/following::a[text()='Orders'][1]") //indexing is used because 3 matching items were found
	private WebElement orders;
	
	@FindBy(xpath="//span[text()='Sales']/following::a[text()='Recurring Orders']") //indexing is used because 3 matching items were found
	private WebElement rec_orders;
	
	@FindBy(xpath="//span[text()='Sales']/following::a[text()='Returns'][1]")
	private WebElement returns;
	
	@FindBy(xpath="//span[text()='Sales']/following::a[text()='Gift Vouchers'][1]")
	private WebElement gift_vouchers;
	
	@FindBy(xpath="//span[text()='Sales']/following::a[text()='PayPal']")
	private WebElement paypal;
	
	@FindBy(xpath="//h3[@class='panel-title'][text()=' Order List']")
	private WebElement orderlist;
	
	@FindBy(xpath="//div[@class='table-responsive']//tbody/tr")
	private List<WebElement> orderlist_table;
	
	@FindBy(xpath="//div[@class='table-responsive']//tbody/tr/td/a[1]") // list has all view buttons in the page
	private List<WebElement> view_icon;
	
	@FindBy(xpath="//button[@id='button-invoice']")
	private WebElement generate;
	
	@FindBy(xpath="//td[@id='invoice']")
	private WebElement invoiceId;
	
	@FindBy(xpath="//div[@class='table-responsive']//tbody/tr/td/a[2]") // list has all edit buttons in the page
	private List<WebElement> edit_icon;
	
	@FindBy(xpath="//div/button[@id='button-customer']")
	private WebElement continue_btn;
	
	@FindBy(xpath="//a[text()='2. Products']")
	private WebElement products_tab;
	
	@FindBy(xpath="//tbody/tr/td/button")
	private List<WebElement> remove_btn;
	
	@FindBy(xpath="")
	private WebElement chooseproduct;
	
	@FindBy(xpath="")
	private WebElement quantity;
	
	@FindBy(xpath="")
	private WebElement addproduct;
	
	@FindBy(xpath="//a[text()='3. Payment Details']")
	private WebElement payment_tab;
	
	@FindBy(xpath="//a[text()='4. Shipping Details']")
	private WebElement shipping_tab;
	
	@FindBy(xpath="//a[text()='5. Totals']")
	private WebElement totals_tab;
		
	public void hoverReportsIcon() {
		this.reports.click();
	}
	
	public void hoverCartIcon() {
		this.cart.click();
	}
	
	public void clickCartOrders(){
		this.orders.click();
	}
	
	public void clickpaypal(){
		this.paypal.click();
	}
	
	public void clickContinue_Btn(){
		this.continue_btn.click();
	}
	
	public boolean verifyReports_Icons()
	{
		boolean displayed = true;
		if(this.sales.isDisplayed())
		{
			if(this.products.isDisplayed())
			{
				if(this.customers.isDisplayed())
				{
					if(this.marketing.isDisplayed())
					{
						displayed = true;
					}
				}
			}
		}
		else
		{
			displayed =  false;
		}
		return displayed;
	}
	
	public boolean verifyCart_Icons()
	{
		boolean displayed = true;
		if(this.orders.isDisplayed())
		{
			if(this.rec_orders.isDisplayed())
			{
				if(this.returns.isDisplayed())
				{
					if(this.gift_vouchers.isDisplayed())
					{
						if(this.paypal.isDisplayed())
						{
						    displayed = true;
						}
					}
				}
			}
		}
		else
		{
			displayed =  false;
		}
		return displayed;
	}
	
	public void clickSalesIcon() {
		this.sales.click();
	}
	
	public void clickProductsIcon() {
		this.products.click();
	}
	
	public void clickCustomersIcon() {
		this.customers.click();
	}
	
	public void clickMarketingIcon() {
		this.marketing.click();
	}
	
	
	public boolean verifySales_Icons()
	{
        String[] icons = {"Orders","Tax","Shipping","Returns","Coupons"};
		String exp_value = null;
		String actual_value = null;
		int num=0;
		for(int i=0;i<sales_list.size();i++)
		{   
			exp_value = icons[i];
			WebElement element = sales_list.get(i);
			actual_value=sales_list.get(i).getText();
			if(actual_value.equals(exp_value))
			{
				num++;
				continue;
			}
			else
				continue;
						
		}	
		if(num==sales_list.size())
			return true;
		else
			return false;
				
	}
		
	public void clickOrders()
	{
        String flag = null;
		
		for(int i=0;i<sales_list.size();i++)
		{
			WebElement element = sales_list.get(i);
			flag=sales_list.get(i).getText();
			if(flag.equals("Orders"))
			{
				element.click();
				break;
			}
					
		}
			   
	}
	public void clickTax()
	{
        String flag = null;
		
		for(int i=0;i<sales_list.size();i++)
		{
			WebElement element = sales_list.get(i);
			flag=sales_list.get(i).getText();
			if(flag.equals("Tax"))
			{
				element.click();
				break;
			}
					
		}
			   
	}
	public void clickShipping()
	{
        String flag = null;
		
		for(int i=0;i<sales_list.size();i++)
		{
			WebElement element = sales_list.get(i);
			flag=sales_list.get(i).getText();
			if(flag.equals("Shipping"))
			{
				element.click();
				break;
			}
					
		}
			   
	}
	public void clickReturns()
	{
        String flag = null;
		
		for(int i=0;i<sales_list.size();i++)
		{
			WebElement element = sales_list.get(i);
			flag=sales_list.get(i).getText();
			if(flag.equals("Returns"))
			{
				element.click();
				break;
			}
					
		}
			   
	}
	public void clickCoupons()
	{
        String flag = null;
		
		for(int i=0;i<sales_list.size();i++)
		{
			WebElement element = sales_list.get(i);
			flag=sales_list.get(i).getText();
			if(flag.equals("Coupons"))
			{
				element.click();
				break;
			}
					
		}
			   
	}
	
	public boolean verifyGroupBy_Drp_Down(){
		String[] groupBy_Drp_Down_Values = {"Years","Months","Weeks","Days"};
		String exp_drp_dwn_value = null;
		String actual_drp_dwn_value = null;
		int num_val=0;
		if(this.sales_Report_GroupBy.isDisplayed()&& this.sales_Report_GroupBy_Drp_Dwn.isDisplayed())
		{		
			this.sales_Report_GroupBy_Drp_Dwn.click();
		  for(int i=0;i<sales_Report_GroupBy_Drp_Dwn_Values.size();i++)
		  {   
			exp_drp_dwn_value = groupBy_Drp_Down_Values[i];
			WebElement element = sales_Report_GroupBy_Drp_Dwn_Values.get(i);
			actual_drp_dwn_value=sales_Report_GroupBy_Drp_Dwn_Values.get(i).getText();
			if(actual_drp_dwn_value.equals(exp_drp_dwn_value))
			{
				num_val++;
				continue;
			}
			else
				continue;
						
		  }			  
		}		
		if(num_val==sales_Report_GroupBy_Drp_Dwn_Values.size())
			return true;
		  else
			return false;
	}
	
	public void selectWeeks()
	{
        String value = null;
		
		for(int i=0;i<sales_Report_GroupBy_Drp_Dwn_Values.size();i++)
		{
			WebElement drp_dwn_element = sales_Report_GroupBy_Drp_Dwn_Values.get(i);
			value=sales_Report_GroupBy_Drp_Dwn_Values.get(i).getText();
			if(value.equals("Weeks"))
			{
				drp_dwn_element.click();
				break;
			}
					
		}
			   
	}
	
	public void setsalesReportDateStart(String date){
		this.sales_Report_DateStart.clear();
		this.sales_Report_DateStart.sendKeys(date);
		this.sales_Report_DateStart.sendKeys(Keys.TAB);
		
	}
	
	public void setsalesReportDateEnd(String date){
		this.sales_Report_DateEnd.clear();
		this.sales_Report_DateEnd.sendKeys(date);
		this.sales_Report_DateEnd.sendKeys(Keys.TAB);
		
	}
	
	public void clickFilterBtn() {
		this.filter_Btn.click();
	}
	
	
	public boolean verifySalesOrder_Table(){
		
		boolean display = true;
		
		if(this.DateStart_col.isDisplayed())
		{
			if(this.DateEnd_col.isDisplayed())
			{
				if(this.noOfOrders.isDisplayed())
				{
					if(this.noOfProducts.isDisplayed())
					{
						display = true;
					}
				}
			}
		}
		else
		{
			display =  false;
		}
		return display;
		
	}
	
public boolean verifyTaxReport_Table(){
		
		boolean display = true;
		
		if(this.DateStart_col.isDisplayed())
		{
			if(this.DateEnd_col.isDisplayed())
			{
				if(this.taxTitle.isDisplayed())
				{
					if(this.noOfOrders.isDisplayed())
					{
						if(this.total_Col.isDisplayed())
						{
						display = true;
						}
					}
				}
			}
		}
		else
		{
			display =  false;
		}
		return display;
		
	}

public boolean verifyShippingReport_Table(){
	
	boolean display = true;
	
	if(this.DateStart_col.isDisplayed())
	{
		if(this.DateEnd_col.isDisplayed())
		{
			if(this.shippingTitle.isDisplayed())
			{
				if(this.noOfOrders.isDisplayed())
				{
					if(this.total_Col.isDisplayed())
					{
					display = true;
					}
				}
			}
		}
	}
	else
	{
		display =  false;
	}
	return display;
	
}
	
	public String verifySalesOrderTable_Values(int orders,int products){
		
        int order_value = 0;
        int product_value = 0;
        String result = null;
        
        
		order_value=Integer.parseInt(this.report_Results.get(2).getText());
		product_value=Integer.parseInt(this.report_Results.get(3).getText());
        if(!salesreport_NoResults.isEnabled())
        {        
          if(order_value == orders)
           {
			if(product_value == products)
			{
               result = "Matching";
			}
           }
          else{
        	result = "Not Matching";
           }
        } 
         else{
        	  result = "No Results";
          }
        
		return result;
	}
	
	public void verifyTaxReportTable_Values()
	{
		//assuming results are retrieved in the table
		
		String start_Date = this.report_Results.get(0).getText();
		String end_Date = this.report_Results.get(1).getText();
		String tax_Title = this.report_Results.get(2).getText();
		int no_Orders = Integer.parseInt(this.report_Results.get(3).getText());
		String total = this.report_Results.get(4).getText().substring(1);      //substring is used to remove to Rupee symbol from total
		if(start_Date.equals(end_Date))
		{
		    System.out.println("For the date "+start_Date+" total tax paid is displayed as "+total+" for "+no_Orders+" orders at "+tax_Title);
		}
		else
		{
			System.out.println("For dates between "+start_Date+" and "+end_Date+" total tax paid is displayed as "+total+" for "+no_Orders+" orders at "+tax_Title);
		}
		
	}
	
	public void verifyShippingReportTable_Values()
	{
		//assuming results are retrieved in the table
		
		String start_Date = this.report_Results.get(0).getText();
		String end_Date = this.report_Results.get(1).getText();
		String shipping_Title = this.report_Results.get(2).getText();
		int no_Orders = Integer.parseInt(this.report_Results.get(3).getText());
		String total = this.report_Results.get(4).getText().substring(1);      //substring is used to remove to Rupee symbol from total
		if(start_Date.equals(end_Date))
		{
		    System.out.println("For the date "+start_Date+" shipping total is displayed as "+total+" for "+no_Orders+" orders at "+shipping_Title);
		}
		else
		{
			System.out.println("For dates between "+start_Date+" and "+end_Date+" shipping total is displayed as "+total+" for "+no_Orders+" orders at "+shipping_Title);
		}
		
	}
	
	public boolean verifyOrderListTable(){
		
		int size = this.orderlist_table.size();
		boolean tablecontents = true;
		if(size>0)
		{
			System.out.println("Order List table is displayed with all the orders made by the users.");
			tablecontents = true;
		}
		else
		{
			tablecontents = false;
		}
		return tablecontents;
	}
	
	public void clickViewIcon(){
		
		if(verifyOrderListTable())
		{
			this.view_icon.get(0).click();
			
		}
		else
		{
			System.out.println("Order List table is has no data");
		}		
	}
	
	public void clickGenerateBtn(){
		this.generate.click();
	}
	
	public String getInvoiceId(){
		String invoice = this.invoiceId.getText();
		return invoice;
	}
	
    public void clickEditIcon(){
		
		if(verifyOrderListTable())
		{
			this.edit_icon.get(0).click();
			
		}
		else
		{
			System.out.println("Order List table is has no data");
		}		
	}
    
    public boolean verifyProductsTab(){
    	if(this.products_tab.isDisplayed())
    		return true;
    	else
    		return false;
    }
    
    public boolean verifyPaymentDetailsTab(){
    	if(this.payment_tab.isDisplayed())
    		return true;
    	else
    		return false;
    }
    
    public boolean verifyShippingDetailsTab(){
    	if(this.shipping_tab.isDisplayed())
    		return true;
    	else
    		return false;
    }
    
    public boolean verifyTotalsTab(){
    	if(this.totals_tab.isDisplayed())
    		return true;
    	else
    		return false;
    }
       
    public void clickRemoveBtn(){
    	this.remove_btn.get(0).click();
    }
    
    public void chooseProduct(String productname,String qty)
    {
    	this.chooseproduct.clear();
    	this.chooseproduct.sendKeys(productname);
    	this.quantity.clear();
    	this.quantity.sendKeys(qty);
   	
    }
    
    public void addProduct()
    {
    	this.addproduct.click();
    }
    
	
	
	
}
