package com.quantum.pages;


import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.PropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.quantum.Utils.CommonMethods.*;


public class ShoppingCart extends WebDriverBaseTestPage<WebDriverTestPage> {

	public static double firstItemRate;
	public static double secondItemRate;
	public static double cartTotalValue;

	@Override
	protected void openPage(PageLocator locator, Object... args) {

	}

	@FindBy(locator = "click.Continue.link")
	private QAFExtendedWebElement lnkContinue;

	@FindBy(locator = "click.laptopnotebook.link")
	private QAFExtendedWebElement lnkLaptopNoteBook;

	@FindBy(locator = "click.allLaptopNoteBooks.link")
	private QAFExtendedWebElement lnkAllLaptopandNoteBook;

	@FindBy(locator = "sort.Items")
	private QAFExtendedWebElement sortItems;

	@FindBy(locator = "first.item.lnk")
	private QAFExtendedWebElement firstItemLnk;

	@FindBy(locator = "first.item.addToCartBtn")
	private QAFExtendedWebElement firstItemAddToCartBtn;

	@FindBy(locator = "first.item.price")
	private QAFExtendedWebElement firstItemPrice;

	@FindBy(locator = "second.item.lnk")
	private QAFExtendedWebElement secondItemLnk;

	@FindBy(locator = "second.item.addToCartBtn")
	private QAFExtendedWebElement secondItemAddToCartBtn;

	@FindBy(locator = "second.item.price")
	private QAFExtendedWebElement secondItemPrice;

	@FindBy(locator = "crt.total.amount")
	private QAFExtendedWebElement crtTotalAmount;

	@FindBy(locator = "btn.total.cart")
	private QAFExtendedWebElement btnTotalCart;


	By.ByXPath mostExpensiveProduct = new By.ByXPath(" //button[@onclick=\"cart.add('45', '1');\"]//parent::div//parent::div//a");

	public void verifyMostExpensiveLaptop() {
		click(lnkLaptopNoteBook);
		click(lnkAllLaptopandNoteBook);
		selectBVisibleText(sortItems, "Price (High > Low)");
		try{
//			WebDriverWait wait = new WebDriverWait(driver,30);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(mostExpensiveProduct));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstItemLnk);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", firstItemLnk);
			Thread.sleep(5000);
			if(firstItemLnk.getText().trim().equalsIgnoreCase("MacBook Pro")){
				System.out.println("---------------->>The Most Expensive product is MacBook Pro with Price :$"+firstItemPrice.getText().substring(1,9));
			}else{
				System.out.println("---------------->>The Most Expensive product is : "+firstItemLnk.getText()+ " with Price :"+firstItemPrice.getText());
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void addProductsToCart() {
		try{
			firstItemRate = Double.parseDouble(firstItemPrice.getText().substring(1,9).replace(",",""));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstItemAddToCartBtn);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", firstItemAddToCartBtn);
			click(firstItemAddToCartBtn);
			Thread.sleep(3000);
			secondItemRate = Double.parseDouble(secondItemPrice.getText().substring(1,9).replace(",",""));
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", secondItemAddToCartBtn);
			click(secondItemAddToCartBtn);
			Thread.sleep(3000);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void verifyTotalCostofItems() {
		try{
			click(btnTotalCart);
			Thread.sleep(3000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", crtTotalAmount);
			cartTotalValue = Double.parseDouble(crtTotalAmount.getText().replace("$","").replace(",",""));
			if(firstItemRate+secondItemRate==cartTotalValue){

				System.out.println("-------------->>>>> Total Cost in cart "+cartTotalValue+" is matched with Sum of Individual Items Cost "+(firstItemRate+secondItemRate)+" >>>>>");

			}else{
				System.out.println("-------------->>>>> Total Cost in cart "+cartTotalValue+" is not matched with Sum of Individual Items Cost "+(firstItemRate+secondItemRate)+" >>>>>");
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}




}
