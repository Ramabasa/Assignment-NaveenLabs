package com.quantum.Utils;


import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import parallelTest.CrossBrowserScript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class CommonMethods  {

    public static QAFWebDriver driver = new WebDriverTestBase().getDriver();
    public static Map<String, String> testcasedata = new HashMap<String,String>();

    public static void setTestcasedata(Map<String, String> testcasedata) {
        CommonMethods.testcasedata = testcasedata;
    }

    public static void clickUsingJS(QAFWebElement ele) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", ele);
    }
    public static void click(QAFWebElement ele) {
        ele.click();
    }
    public static void enterText(WebElement ele, String text){
        ele.sendKeys(text);
    }

    public static void selectByValue(WebElement ele, String value){
        Select slt = new Select(ele);
        slt.selectByValue(value);
    }
    public static void selectBVisibleText(WebElement ele, String text){
        Select slt = new Select(ele);
        slt.selectByVisibleText(text);
    }
    public static void selectByIndex(WebElement ele, int indexValue){
        Select slt = new Select(ele);
        slt.selectByIndex(indexValue);
    }
    public void getScenarioTestData(String testName, String sheetName) throws IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream("resources/application.properties"));
        testcasedata.clear();
        CommonMethods.setTestcasedata(CommonMethods.getData(sheetName, testName, ConfigurationManager.getBundle().getPropertyValue("TestCaseDocument")));
    }

    public static Map<String,String> getData(String sheetName,String scenarioName,String testDataPath)  {

        Fillo fillo=new Fillo();
        Map<String, String> data = new HashMap<>();
        ArrayList<String> fieldNames = new ArrayList<>();
        Connection connection = null;
        try {
            connection = fillo.getConnection(testDataPath);
            String strQuery="Select * from " + sheetName + " Where TestID='" + scenarioName + "'";
            Recordset recordset = connection.executeQuery(strQuery);
            if (recordset.getCount() == 1){
                while(recordset.next()){
                    fieldNames = recordset.getFieldNames();
                    for (int i = 0; i < fieldNames.size(); i++) {
                        data.put(fieldNames.get(i), recordset.getField(fieldNames.get(i)).trim());
                    }
                }
            }else if(recordset.getCount() > 1) {
                throw new Exception("");
            }
            recordset.close();
        }catch(Exception e) {
            //

        }finally {
            Objects.requireNonNull(connection).close();
            fieldNames.clear();
        }
        return data;
    }
    public void updateFlag(String colName,String colValue, String testCaseID) throws IOException, FilloException {

        final Properties properties = new Properties();
        properties.load(new FileInputStream("resources/application.properties"));
        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(ConfigurationManager.getBundle().getPropertyValue("TestCaseDocument"));
        String strQuery = "Update UserList set "+colName+" = '"+colValue+"' Where TestID = '" +testCaseID+"' ";
    }


    public static void waitForElement(QAFWebElement ele){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) ele));
    }





}

