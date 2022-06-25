package com.quantum.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.Utils.CommonMethods;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
//import parallelTest.CrossBrowserScript;


import static com.quantum.Utils.CommonMethods.*;
import static java.lang.Boolean.TRUE;
//import static parallelTest.CrossBrowserScript.*;


public class NewUserRegistration extends WebDriverBaseTestPage<WebDriverTestPage> {

	@FindBy(locator = "click.MyAccount.link")
	private QAFExtendedWebElement lnkMyAccount;
	@FindBy(locator = "click.Login.link")
	private QAFExtendedWebElement lnkLogin;
	@FindBy(locator = "click.Register.link")
	private QAFExtendedWebElement lnkRegister;
	@FindBy(locator = "txt.email")
	private QAFExtendedWebElement txtEmail1;
	@FindBy(locator = "txt.password")
	private QAFExtendedWebElement txtPwd;
	@FindBy(locator = "btn.login")
	private QAFExtendedWebElement btnLogin;
	@FindBy(locator = "fname.txtBox")
	private QAFExtendedWebElement txtFname;
	@FindBy(locator = "lastname.txtBox")
	private QAFExtendedWebElement txtLname;
	@FindBy(locator = "email.txtBox")
	private QAFExtendedWebElement txtEmail;
	@FindBy(locator = "telephone.txtBox")
	private QAFExtendedWebElement txtTelePhone;
	@FindBy(locator = "password.txtBox")
	private QAFExtendedWebElement txtPassword;
	@FindBy(locator = "confirm.txtBox")
	private QAFExtendedWebElement txtConfirmPwd;
	@FindBy(locator = "click.Logout.link")
	private QAFExtendedWebElement lnkLogout;

	@FindBy(locator = "agree.chkBox")
	private QAFExtendedWebElement chkAgree;

	@FindBy(locator = "click.continue.btn")
	private QAFExtendedWebElement btnContinue;

	@FindBy(locator = "msg.UserCreattion.Success")
	private QAFExtendedWebElement msgUserCreatedSuccess;


	@FindBy(locator = "msg.UserCreattion.warning")
	private QAFExtendedWebElement msgUserCreattionWarning;

	@FindBy(locator = "click.Continue.link")
	private QAFExtendedWebElement lnkContinue;

	@FindBy(locator = "click.laptopnotebook.link")
	private QAFExtendedWebElement lnkLaptopNoteBook;

	@FindBy(locator = "click.allLaptopNoteBooks.link")
	private QAFExtendedWebElement lnkAllLaptopandNoteBook;

	@FindBy(locator = "login.Confirmation")
	private QAFExtendedWebElement loginConfirmation;

	@FindBy(locator = "msg.Useraccess.warning")
	private QAFExtendedWebElement msgUseraccesswarning;

	@FindBy(locator = "msg.exceedLimt.warning")
	private QAFExtendedWebElement msgExceedLimtWarning;








	@Override
	protected void openPage(PageLocator pageLocator, Object... objects) {

	}

	public void clickOnRegisterLink(){
		click(lnkMyAccount);
		click(lnkRegister);
	}
	public void fillUserDetailsAndCreateTheAccount(){
		enterText(txtFname, testcasedata.get("Fname"));
		enterText(txtLname, testcasedata.get("Lname"));
		String strEmail = testcasedata.get("Fname")+testcasedata.get("Lname")+"@gmail.com";
		enterText(txtEmail, strEmail);
		enterText(txtTelePhone, testcasedata.get("PhoneNumber"));
		enterText(txtPassword, testcasedata.get("Password"));
		enterText(txtConfirmPwd, testcasedata.get("Password"));
		click(chkAgree);
		click(btnContinue);
	}
	public void verifyUserCreatedConfirmationMessage(){
		String ExpectedTitle = "Congratulations! Your new account has been successfully created!";
		try{
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", msgUserCreatedSuccess);
			String ActualTitle = msgUserCreatedSuccess.getText();
			Assert.assertEquals(ExpectedTitle, ActualTitle);
			if(ExpectedTitle.equalsIgnoreCase(ActualTitle)){
				System.out.println("User Account Created Successfully");
				click(lnkContinue);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", msgUserCreattionWarning);
			click(lnkMyAccount);
			click(lnkLogin);
			enterText(txtEmail1,testcasedata.get("Fname")+testcasedata.get("Lname")+"@gmail.com");
			enterText(txtPwd,testcasedata.get("Password"));
			clickUsingJS(btnLogin);
		}catch(Exception e1){
			System.out.println(e1.getMessage());
		}
	}

	public void iLoggedOffTheApplication() {
		try{
			click(lnkMyAccount);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", lnkLogout);
			Thread.sleep(3000);
			click(lnkLogout);
			System.out.println("-------------->>>>> Application logged out Successfully ----------->>>>>");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void enterUserDetails() {
		try{
			try{
				Thread.sleep(5000);
				click(lnkMyAccount);
				click(lnkLogin);
				enterText(txtEmail1,testcasedata.get("Email"));
				enterText(txtPwd,testcasedata.get("Password"));
				clickUsingJS(btnLogin);
			}catch(Exception e1){
				System.out.println(e1.getMessage());
			}

			System.out.println("-------------->>>>> Application logged in Successfully ----------->>>>>");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void iVerifyUserAccessedTheApplication(String TestID) {

		try{
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", loginConfirmation);
			System.out.println("-------------->>>>> Application logged in Successfully ----------->>>>>");
			new CommonMethods().updateFlag("Flag","YES", TestID);
			iLoggedOffTheApplication();

		}catch(Exception e1){
			System.out.println(e1.getMessage());
		}
		try{
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", msgUseraccesswarning);
			System.out.println("-------------->>>>> User have not access the application-------->>>>>");
			new CommonMethods().updateFlag("Flag","NO", TestID);
		}catch(Exception e1){
			System.out.println(e1.getMessage());
		}
		try{
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", msgExceedLimtWarning);
			System.out.println("-------------->>>>> User have not access the application-------->>>>>");
			new CommonMethods().updateFlag("Flag","NO", TestID);
		}catch(Exception e1){
			System.out.println(e1.getMessage());
		}


	}



}
