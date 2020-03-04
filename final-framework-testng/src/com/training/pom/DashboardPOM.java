package com.training.pom;


import java.sql.Time;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPOM {
private WebDriver driver;
    
	public DashboardPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath="//*[@class='fa fa-bar-chart-o fa-fw']")
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
	
	
	public void hoverReportsIcon() {
		this.reports.click();
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
	
	public String verifyTaxReportTable_Values(int orders,int products)
	{
		String result = null;
		
		return result;
	}
}
