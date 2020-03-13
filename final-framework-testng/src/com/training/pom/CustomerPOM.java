package com.training.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerPOM {
	
	private WebDriver driver;

	public CustomerPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3[text()=' Customer List']")
	private WebElement customerlist;
	
	@FindBy(xpath = "//div[@class='table-responsive']//tbody/tr")
	private List<WebElement> customerlist_table;
	
	@FindBy(xpath="//tbody//td[3]")
	private List<WebElement> email_Col;
	
	@FindBy(xpath = "//div[@class='table-responsive']//tbody/tr/td/a")
	private List<WebElement> edit_icon;
	
	@FindBy(xpath="//h3[text()=' Edit Customer']")
	private WebElement editcustomer;
	
	@FindBy(xpath="//div/select[@id='input-customer-group']")
	private WebElement customerGrp;
	
	@FindBy(xpath="//div/input[@id='input-telephone']")
	private WebElement telephone;
	
	@FindBy(xpath="//div/input[@id='input-fax']")
	private WebElement fax;
	
	@FindBy(xpath="//div/input[@id='input-password']")
	private WebElement password;
	
	@FindBy(xpath="//div/input[@id='input-confirm']")
	private WebElement confirm_pwd;
	
	@FindBy(xpath="//div/select[@id='input-newsletter']")
	private WebElement newsletter;
	
	@FindBy(xpath="//button/i[@class='fa fa-save']")
	private WebElement save_Btn;
	
	@FindBy(xpath="//div[text()=' Success: You have modified customers!      ']")
	private WebElement success_alert;
	
	public boolean verifyCustomerListTable() {

		int size = this.customerlist_table.size();
		boolean tablecontents = true;
		if (size > 0) {
			System.out.println("Customer List table has customer details");
			tablecontents = true;
		} else {
			tablecontents = false;
		}
		return tablecontents;
	}
	
	public void clickEditIcon(String email) {

		if (verifyCustomerListTable()) {
			
			for(int i=0;i<this.email_Col.size();i++)
			{
				if(this.email_Col.get(i).getText().equals(email)){
					this.edit_icon.get(i).click();
					break;
				}
			}
		} else {
			System.out.println("Customer List table has no data");
		}
	}
	
	public boolean verifyEditCustomerPage(){
		boolean flag = true;
		if(this.editcustomer.isDisplayed())
			flag = true;
		else
			flag = false;
			return flag;
	}

	
	public WebElement getCustomerGrp() {
		return customerGrp;
	}

	public void setCustomerGrp(String custGrp) {
		Select sel = new Select(this.customerGrp);
		sel.selectByVisibleText(custGrp);
	}

	public void setTelephone(String phone) {
		this.telephone.clear();
		this.telephone.sendKeys(phone);
	}

	public void setFax(String fax) {
		this.fax.clear();
		this.fax.sendKeys(fax);
	}

	public void setPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void setConfirm_pwd(String confirm_pwd) {
		this.confirm_pwd.clear();
		this.confirm_pwd.sendKeys(confirm_pwd);
	}

	public void setNewsletter(String newsletter) {
		Select sel = new Select(this.newsletter);
		sel.selectByVisibleText(newsletter);
	}
	
	public void clickSaveBtn(){
		this.save_Btn.click();
	}
	
	public boolean verifySuccessAlert(){
		if(this.success_alert.isDisplayed())
			return true;
		else
			return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
