package testsuite.CAP;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pageobjects.*;
import utilities.ExcelFile_Util;
import utilities.TestFramework_Initializer;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Regression_Scenario_CAP_22
        extends TestFramework_Initializer
        implements CMPLogin_Page.ILogin, CMPHome_Page.IHome, CommonInfo_Page.ICommonInfo, Names_Page.INames, Rating_Page.IRating, Issue_Page.IIssue_Page, IssuingInfo_Page.IIssuingInfo, PolicyForms_Page.IPolicyForms, FEIN_Page.IFEIN
{
    boolean generateBeyondCompareScriptAndReport = false;
    String excelFileName;
    String excelFilePath;
    String sanNumber;
    String initialTransactionName;
    Map<String, List<String>> testDataAsMap;

    @Test(enabled = true, priority = 0)
    public void Regression_Scenario_CAP_22_Transaction_001_NBQUNBIS() throws IOException, InvalidFormatException, ParserConfigurationException, SAXException, InterruptedException {
        initialTransactionName = "NBQUNBIS";
        excelFileName = "Regression_Scenario_CAP_22_Test_Datafile";
        excelFilePath = utils.configReader().getExcelFilePath(excelFileName);
        testDataAsMap = utils.excelFileUtil().getExcelDataAsMapWithMultipleValues(excelFilePath, "NBQUNBIS");

        if (!testDataAsMap.get("sanNumber").get(0).equalsIgnoreCase("$$NA$$")) {
            sanNumber = testDataAsMap.get("sanNumber").get(0);
        }
        if (testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("$$NA$$") || testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("Fail")) {
            whileViewing().theCMPLoginPage().theUser_().logsIntoPolicyDecisions(utils.configReader().getURL(), testDataAsMap.get("userNameTxt").get(0), testDataAsMap.get("passwordTxt").get(0));
            whileViewing().theCMPHomePage().theUser_().createNewPolicy(testDataAsMap);
            whileViewing().theCommonInfoPage().theUser_().enterCommonPolicyDetails(testDataAsMap);
            sanNumber = whileViewing().theCommonInfoPage().theUser_().fetchSan();
            whileViewing().theUnderwriterPage().theUser_().enterUnderwriterDetails(testDataAsMap);
            whileViewing().theCapitalAssetPage().theUser_().enterCapitalAssetPolicyDetail(testDataAsMap);
            whileViewing().theCapitalAssetPage().theUser_().addOptionalCovgs(testDataAsMap,"Regression_Scenario_CAP_22");
            whileViewing().theCAPLocationPage().theUser_().addLocations(testDataAsMap);
            whileViewing().theCAPLocationPage().theUser_().addLocationInfo(testDataAsMap);
            whileViewing().theCAPLocationPage().theUser_().addLocationCovgs(testDataAsMap,"Regression_Scenario_CAP_22");
            whileViewing().theRatingPage().theUser_().ratePolicyWithoutVerification(testDataAsMap);
            whileViewing().theIssuingInfo().theUser_().fillIssuingInfoDetails(testDataAsMap, sanNumber, "NBQUNBIS");
            whileViewing().theBillingPage().theUser_().fillBillingPageDetails(testDataAsMap);
            whileViewing().theMiscInformationPage().theUser_().fillMiscInformation(testDataAsMap, sanNumber);
            whileViewing().theIssuingInfo().theUser_().CPconvertToIssue();
            sanNumber = whileViewing().theCommonInfoPage().theUser_().fetchSan();
            whileViewing().theRatingPage().theUser_().ratePolicyWithoutVerification(testDataAsMap);
            whileViewing().thePolicyFormsPage().theUser_().fillsInPolicyForms2(testDataAsMap);
            whileViewing().thePolicyFormsPage().theUser_().formValidation(testDataAsMap);
            whileViewing().theIssuePage().theUser_().issuePolicy();
            whileViewing().theCMPHomePage().theUser_().checkTransactionStatus(sanNumber);
            whileViewing().theCMPLoginPage().theUser_().logOut();
        }
    }
    @Test(enabled = true, priority = 1 , dependsOnMethods = "Regression_Scenario_CAP_22_Transaction_001_NBQUNBIS")
    public void Regression_Scenario_CAP_22_Transaction_002_MANUOFF() throws IOException, InvalidFormatException, ParserConfigurationException, SAXException, InterruptedException
    {
        excelFileName = "Regression_Scenario_CAP_22_Test_Datafile";
        excelFilePath = utils.configReader().getExcelFilePath(excelFileName);
        testDataAsMap = utils.excelFileUtil().getExcelDataAsMapWithMultipleValues(excelFilePath, "MANUOFF");

        if(!testDataAsMap.get("sanNumber").get(0).equalsIgnoreCase("$$NA$$"))
        {
            sanNumber = testDataAsMap.get("sanNumber").get(0);
        }
        if(testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("$$NA$$") || testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("Fail"))
        {
            whileViewing().theCMPLoginPage().theUser_().logsIntoPolicyDecisions(utils.configReader().getURL(), testDataAsMap.get("userNameTxt").get(0), testDataAsMap.get("passwordTxt").get(0));
            sanNumber = whileViewing().theCMPHomePage().theUser_().performActionsOnPolicy(testDataAsMap,"MANU",sanNumber);
            whileViewing().theTransactionInformationPage().theUser_().enterTransactionStopOnOffInformationDetails(testDataAsMap);
            whileViewing().theIssuePage().theUser_().issuePolicy();
            whileViewing().theCMPLoginPage().theUser_().logOut();
        }
    }
    @Test(enabled = true, priority = 2 , dependsOnMethods = "Regression_Scenario_CAP_22_Transaction_002_MANUOFF")
    public void Regression_Scenario_CAP_22_Transaction_003_MANUON() throws IOException, InvalidFormatException, ParserConfigurationException, SAXException, InterruptedException
    {
        excelFileName = "Regression_Scenario_CAP_22_Test_Datafile";
        excelFilePath = utils.configReader().getExcelFilePath(excelFileName);
        testDataAsMap = utils.excelFileUtil().getExcelDataAsMapWithMultipleValues(excelFilePath, "MANUON");

        if(!testDataAsMap.get("sanNumber").get(0).equalsIgnoreCase("$$NA$$"))
        {
            sanNumber = testDataAsMap.get("sanNumber").get(0);
        }
        if(testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("$$NA$$") || testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("Fail"))
        {
            whileViewing().theCMPLoginPage().theUser_().logsIntoPolicyDecisions(utils.configReader().getURL(), testDataAsMap.get("userNameTxt").get(0), testDataAsMap.get("passwordTxt").get(0));
            whileViewing().theCMPHomePage().theUser_().performTransactionON(testDataAsMap,sanNumber);
            whileViewing().theCMPLoginPage().theUser_().logOut();
        }
    }

    @Test(enabled = true, priority = 3 , dependsOnMethods = "Regression_Scenario_CAP_22_Transaction_003_MANUON")
    public void Regression_Scenario_CAP_22_Transaction_004_CANC() throws IOException, InvalidFormatException, ParserConfigurationException, SAXException, InterruptedException
    {
        excelFileName = "Regression_Scenario_CAP_22_Test_Datafile";
        excelFilePath = utils.configReader().getExcelFilePath(excelFileName);
        testDataAsMap = utils.excelFileUtil().getExcelDataAsMapWithMultipleValues(excelFilePath, "CANC");

        if(!testDataAsMap.get("sanNumber").get(0).equalsIgnoreCase("$$NA$$"))
        {
            sanNumber = testDataAsMap.get("sanNumber").get(0);
        }
        if(testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("$$NA$$") || testDataAsMap.get("Transaction_Status").get(0).equalsIgnoreCase("Fail"))
        {
            whileViewing().theCMPLoginPage().theUser_().logsIntoPolicyDecisions(utils.configReader().getURL(), testDataAsMap.get("userNameTxt").get(0), testDataAsMap.get("passwordTxt").get(0).toString());
            whileViewing().theCMPHomePage().theUser_().performCancellation(testDataAsMap, sanNumber);
            whileViewing().thePerformTransactionPage().theUser_().enterDateCANC(testDataAsMap);
            whileViewing().theTransactionInformationPage().theUser_().enterCancellationDetails(testDataAsMap);
            whileViewing().theRatingPage().theUser_().ratePolicyWithoutVerification(testDataAsMap);
            whileViewing().theIssuePage().theUser_().issuePolicy();
            whileViewing().theCMPHomePage().theUser_().reWrCheckTransactionStatus(sanNumber);
            sanNumber = whileViewing().theCMPHomePage().theUser_().fetchSan();
            whileViewing().theCMPLoginPage().theUser_().logOut();
        }
    }

    @AfterMethod()
    public void afterMethod(ITestResult testResult) throws ParserConfigurationException, InvalidFormatException, SAXException, IOException
    {
        boolean transactionStatus;
        String transactionStatusToBeReported;
        String transactionNameAlsoTheTestDataSheetName;
        String scenarioName;
        String previousSPNumber;
        String currentSPNumber;
        String previousSPPolicyOrSANNumber = "";
        String currentSPPolicyOrSANNumber = "";
        String serverRepositoryPathForPolicies;
        String userSpecifiedPathForPolicies;
        String beyondCompareDirectoryPath, pdfCompareDirectoryPath;

        transactionStatus = testResult.isSuccess();
        transactionNameAlsoTheTestDataSheetName = testResult.getMethod().getMethodName().trim();
        transactionNameAlsoTheTestDataSheetName = transactionNameAlsoTheTestDataSheetName.substring(transactionNameAlsoTheTestDataSheetName.indexOf("Transaction")).trim();
        transactionNameAlsoTheTestDataSheetName = transactionNameAlsoTheTestDataSheetName.substring(transactionNameAlsoTheTestDataSheetName.indexOf("_")+1).trim();
        transactionNameAlsoTheTestDataSheetName = transactionNameAlsoTheTestDataSheetName.substring(transactionNameAlsoTheTestDataSheetName.indexOf("_")+1).trim();
        scenarioName = testResult.getMethod().getMethodName().trim();
        scenarioName = scenarioName.replace("Regression_","");
        scenarioName = scenarioName.replace("_00"," ");
        scenarioName = scenarioName.replace("_0"," ");
        scenarioName = scenarioName.replace("_"," ");
        scenarioName = scenarioName.substring(0, scenarioName.indexOf("Transaction")).trim();
        if(scenarioName.charAt(scenarioName.length()-1) == 'A' || scenarioName.charAt(scenarioName.length()-1) == 'B')
        {
            scenarioName = scenarioName.substring(0, scenarioName.length()-2).trim() + scenarioName.charAt(scenarioName.length()-1);
        }
        previousSPNumber = utils.configReader().getConfigInfoForKey("previous_SP");
        currentSPNumber = utils.configReader().getConfigInfoForKey("current_SP");
        serverRepositoryPathForPolicies = utils.configReader().getConfigInfoForKey("serverRepositoryPathForPolicies");
        userSpecifiedPathForPolicies = "\\\\sancfs001\\QA-LT\\Deliverables\\WC Policy Decision\\" + currentSPNumber + "\\" + currentSPNumber + " Deliveries\\Regression Testing\\" + scenarioName + "\\";
        beyondCompareDirectoryPath = userSpecifiedPathForPolicies + "Beyond Compare Scripts\\";
        pdfCompareDirectoryPath = userSpecifiedPathForPolicies + "PDF Compare Reports\\";

        if(transactionStatus)
        {
            // Update transaction Status
            transactionStatusToBeReported = "Pass";
            // Beyond compare script and report to be generated after last automation transaction only
            if(generateBeyondCompareScriptAndReport)
            {
                // Get previous SP policy / SAN number if not retrieved already
                if(previousSPPolicyOrSANNumber.equalsIgnoreCase(""))
                {
                    excelFileName = utils.configReader().getConfigInfoForKey("regression_status_report_file_Name");
                    excelFilePath = userSpecifiedPathForPolicies.replace(scenarioName + "\\", "");
                    previousSPPolicyOrSANNumber = utils.excelFileUtil().getsSPPolicyOrQuoteNumber(excelFilePath + excelFileName, "Sheet1", previousSPNumber, transactionNameAlsoTheTestDataSheetName, scenarioName);
                }
                // Get current SP policy / SAN number if not retrieved already
                if(currentSPPolicyOrSANNumber.equalsIgnoreCase(""))
                {
                    currentSPPolicyOrSANNumber = sanNumber.substring(0, 7);
                }
                // Update policy / SAN number to 8 digits based on transaction name (Remaining transactions will use existing policy / SAN number)
                if(initialTransactionName.equalsIgnoreCase("NBIS"))
                {
                    previousSPPolicyOrSANNumber = previousSPPolicyOrSANNumber.substring(0, 7) + "0";
                    currentSPPolicyOrSANNumber = currentSPPolicyOrSANNumber.substring(0, 7) + "0";
                }
                else if(initialTransactionName.equalsIgnoreCase("RCIS"))
                {
                    previousSPPolicyOrSANNumber = previousSPPolicyOrSANNumber.substring(0, 7) + "1";
                    currentSPPolicyOrSANNumber = currentSPPolicyOrSANNumber.substring(0, 7) + "1";
                }
                else if(initialTransactionName.equalsIgnoreCase("REIS"))
                {
                    previousSPPolicyOrSANNumber = previousSPPolicyOrSANNumber.substring(0, 7) + "1";
                    currentSPPolicyOrSANNumber = currentSPPolicyOrSANNumber.substring(0, 7) + "1";
                }
                // Create Beyond Compare folder
                utils.testAssistUtility().createDirectoryStructure(pdfCompareDirectoryPath);
                // Create beyond compare specification file for HTML report
                utils.testAssistUtility().createBeyondCompareSpecificationFileForReport();
                // Move entire previous Policy / SAN PDF folder to user specified location (Ideally, it is already in place because of copying from previous SP)
                utils.testAssistUtility().movesPolicyOrQuoteDirectoryFromRepositoryToUserLocation(previousSPPolicyOrSANNumber, serverRepositoryPathForPolicies, userSpecifiedPathForPolicies);
                // Move entire current policy PDF folder to user specified location
                utils.testAssistUtility().movesPolicyOrQuoteDirectoryFromRepositoryToUserLocation(currentSPPolicyOrSANNumber, serverRepositoryPathForPolicies, userSpecifiedPathForPolicies);
                // Create beyond compare batch file for comparison
                //utils.testAssistUtility().createBeyondComparePDFComparisonBatchFileAndHTMLReport(previousSPPolicyOrSANNumber, currentSPPolicyOrSANNumber, userSpecifiedPathForPolicies);
                //utils.testAssistUtility().createPDFComparisonHTMLReport(previousSPPolicyOrSANNumber, currentSPPolicyOrSANNumber, userSpecifiedPathForPolicies);
            }
        }
        else
        {
            // Update transaction Status
            transactionStatusToBeReported = "Fail";
        }
        // Write SAN and Transaction Status In Test Data File
        excelFileName = "Regression_Scenario_CAP_22_Test_Datafile";
        excelFilePath = utils.configReader().getExcelFilePath(excelFileName);
        ExcelFile_Util.writeToSpecifiedColumnInExcel(excelFilePath, transactionNameAlsoTheTestDataSheetName, transactionStatusToBeReported, "Transaction_Status");
        ExcelFile_Util.writeToSpecifiedColumnInExcel(excelFilePath, transactionNameAlsoTheTestDataSheetName, sanNumber, "sanNumber");
    }

}