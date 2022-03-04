package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Base_Util;
import utilities.Browser_Util;
import utilities.LogAndReport_Util;
import utilities.Screenshot_Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Rating_Page {

    private Base_Util baseUtil;
    private Browser_Util browserUtil;
    private LogAndReport_Util log;
    private Screenshot_Util ss;
    private RatingMethods pageMethods;

    public Rating_Page(LogAndReport_Util logAndReport, Screenshot_Util screenshot, Browser_Util browser_Util, Base_Util base_util) {
        this.browserUtil = browser_Util;
        this.baseUtil = base_util;
        this.log = logAndReport;
        this.ss = screenshot;
        this.pageMethods = new RatingMethods();
    }

    public interface IRating
    {
        String pageName = "Rating_Options";

        List rp_rating_button = Arrays.asList(By.id("CoRatingOptionsHTML_cmdStartRating"), pageName, "rating_button");
        List rp_writtenPremium_text = Arrays.asList(By.xpath("//tr/td[4]"), pageName,"Written Premium");
        List rp_billedPremium_text = Arrays.asList(By.xpath("//tr/td[5]"), pageName,"Billed Premium");
        List rp_ratingGroupInfo_CheckBox = Arrays.asList(By.xpath("(//label[text()='Rating Group Info']/preceding::input[@type='checkbox'])[3]"),"","ratingGroupInfo_CheckBox");
        List rp_policyAndStateInfo_CheckBox = Arrays.asList(By.xpath("(//label[text()='Policy And State Info']/preceding::input[@type='checkbox'])[4]"), pageName,"policyAndStateInfo_CheckBox");
        List rp_classCodeInfo_CheckBox = Arrays.asList(By.xpath("(//label[text()='Class Code Info']/preceding::input[@type='checkbox'])[5]"), pageName,"classCodeInfo_CheckBox");
        List cp_Status_text = Arrays.asList(By.xpath("(//td[@title='Status'])[1]"), pageName,"Status");
        List rp_resequenceLocVeh_CheckBox = Arrays.asList(By.xpath("//label[text()='Resequence Locs/Vehs']/preceding::input[@type='checkbox']"), pageName, "resequenceLocVeh_CheckBox");
        List rp_reRateAllLobs_CheckBox = Arrays.asList(By.xpath("//label[text()='Re-rate All Lobs']/preceding::input[@type='checkbox'][1]"), pageName, "reRateAllLobs_CheckBox");
        List cp_termPremium_text = Arrays.asList(By.xpath("(//td[@title='Term Premium'])[1]"), pageName, "Term Premium");
        List cp_termPremium_text_LOB1 = Arrays.asList(By.xpath("(//td[@title='Term Premium'])[1]"), pageName, "Term Premium LOB 1");
        List cp_termPremium_text_LOB2 = Arrays.asList(By.xpath("(//td[@title='Term Premium'])[2]"), pageName, "Term Premium LOB 2");
        List cp_Status_text_LOB1 = Arrays.asList(By.xpath("(//td[@title='Status'])[1]"), pageName, "Status LOB 1");
        List cp_Status_text_LOB2 = Arrays.asList(By.xpath("(//td[@title='Status'])[2]"), pageName, "Status LOB 2");
        List cp_Line_Of_Business_text1 = Arrays.asList(By.xpath("(//td[@title='Line of Business'])[1]"), pageName, "Line Of Business1");
        List cp_Line_Of_Business_text2 = Arrays.asList(By.xpath("(//td[@title='Line of Business'])[2]"), pageName, "Line Of Business2");
        List pes_Common_Link = Arrays.asList(By.xpath("//li[@role='treeitem']/div/button[text()='Common']"), "Policy Explorer", "'Common_Link'");
        List rp_Stat_Coding_CheckBox = Arrays.asList(By.xpath("//label[text()='Stat Coding']/preceding::input[@type='checkbox'][1]"), pageName, "Stat Coding");
        List rp_Statement_of_Values_CheckBox = Arrays.asList(By.xpath("//label[text()='Statement of Values']/preceding::input[@type='checkbox'][1]"), pageName, "Statement of Values");
        List rp_Location_Premium_CheckBox = Arrays.asList(By.xpath("//label[text()='Location Premium']/preceding::input[@type='checkbox'][1]"), pageName, "Location Premium");
        List rp_Location_Schedule_CheckBox = Arrays.asList(By.xpath("//label[text()='Location Schedule']/preceding::input[@type='checkbox'][1]"), pageName, "Location Schedule");

        List gl_GenLiabComposite_Txt = Arrays.asList(By.xpath("//a[@id='dgGroupList_GroupCd_0']"), "Rating Options Page", "Gen Liab Composite");
        List gl_RateOverride_Txt = Arrays.asList(By.id("GlCompositeSummaryDetailHTML_GlGrpCmptRating_CombInputRtFct_FLD"), "Rating Options Page", "Rate Override");
        List gl_save_Btn = Arrays.asList(By.xpath("//div[@class='span12']/button[@title='Save']"), "Rating Options Page", "Save Button");

    }

    public class RatingMethods implements IRating
    {
        public String[] ratePolicy(Map<String,String> map)
        {
            String termPremium;
            String writtenPremium;
            String billedPremium;
            String termPremiumCompare;
            String writtenPremiumCompare;
            String billedPremiumCompare;
            String[] list;

            baseUtil.navigatesToSpecifiedLinkOnPolicyExplorer("RATING OPTIONS");
            browserUtil.switchToDefaultContent();
            //browserUtil.switchToFrame("InsDmain");
            browserUtil.clicksWebElement(rp_rating_button);
            browserUtil.waitUntilPleaseWaitDisappear();

            termPremium = browserUtil.getsTextOfWebElement(cp_termPremium_text);
            writtenPremium = browserUtil.getsTextOfWebElement(rp_writtenPremium_text);
            billedPremium = browserUtil.getsTextOfWebElement(rp_billedPremium_text);

            termPremiumCompare = browserUtil.returnsExactMatchForVisibleText(map.get("verifyTermPremium"),cp_termPremium_text)+"with previous SP";
            writtenPremiumCompare = browserUtil.returnsExactMatchForVisibleText(map.get("verifyWrittenPremium"),rp_writtenPremium_text)+"with previous SP";
            billedPremiumCompare = browserUtil.returnsExactMatchForVisibleText(map.get("verifyBilledPremium"),rp_billedPremium_text)+"with previous SP";

            list = new String[]
                    {
                            map.get("verifyTermPremium"),
                            map.get("verifyWrittenPremium"),
                            map.get("verifyBilledPremium"),
                            termPremium,
                            writtenPremium,
                            billedPremium,
                            termPremiumCompare,
                            writtenPremiumCompare,
                            billedPremiumCompare
                    };
            browserUtil.switchToDefaultContent();

            return list;
        }


        /*public void rateValidation() throws Exception
        {
             final String Append="$";

            String ActualtermPremium = browserUtil.getsTextOfWebElement(cp_termPremium_text);
            System.out.println(ActualtermPremium);
            String ActualStatus = browserUtil.getsTextOfWebElement(cp_Status_text);
            System.out.println(ActualStatus);

            browserUtil.clicksWebElement(pes_Common_Link);
            browserUtil.waitUntilPleaseWaitDisappear();

            String ExpectedtermPremium = browserUtil.getsTextOfWebElement(cp_termPremium_text);
            System.out.println(ExpectedtermPremium);
            String ExpectedStatus = browserUtil.getsTextOfWebElement(cp_Status_text);
            System.out.println(ExpectedStatus);

            String ExpectedtermPremiumnew= Append + ExpectedtermPremium;

            if(ActualtermPremium.equalsIgnoreCase(ExpectedtermPremiumnew))
            {
                browserUtil.writesToLogAndReport("Pass", "Premium is Correct.");
            }
            else
            {
                browserUtil.writesToLogAndReport("Fail", "Premium is incorrect.");
            }

            if(ActualStatus.equalsIgnoreCase(ExpectedStatus))
            {
                browserUtil.writesToLogAndReport("Pass", " Status is Rated.");
            }
            else
            {
                browserUtil.writesToLogAndReport("Fail", "Status is Not Rated .");
            }

        }*/

        //This method works for Monoline type only
        public void rateValidationMonoline() throws Exception {
            final String Append = "$";

            String LOB1 = browserUtil.getsTextOfWebElement2(cp_Line_Of_Business_text1);

            //Actual Results - LOB1
            String ActualTermPremium_LOB1 = browserUtil.getsTextOfWebElement(cp_termPremium_text_LOB1);
            System.out.println(ActualTermPremium_LOB1);

            String ActualStatus_LOB1 = browserUtil.getsTextOfWebElement(cp_Status_text_LOB1);
            System.out.println(ActualStatus_LOB1);

            browserUtil.clicksWebElement(pes_Common_Link);
            browserUtil.waitUntilPleaseWaitDisappear();

            //Expected Results - LOB1
            String ExpectedtermPremium = browserUtil.getsTextOfWebElement(cp_termPremium_text);
            System.out.println(ExpectedtermPremium);

            String ExpectedStatus_LOB1 = browserUtil.getsTextOfWebElement(cp_Status_text);
            System.out.println(ExpectedStatus_LOB1);

            // Expected Term Premium (Add $ Symbol with Premium)
            String ExpectedTermPremiumnew_LOB1= Append + ExpectedtermPremium;

            //Compare with Actual Result and Expected Result - Premium
            if (ActualTermPremium_LOB1.equalsIgnoreCase(ExpectedTermPremiumnew_LOB1)) {
                browserUtil.writesToLogAndReport("Pass", " '" + LOB1 + "'  Premium is Correct.");
                System.out.println(" '" + LOB1 + "'  Premium is Correct.");
            } else {
                browserUtil.writesToLogAndReport("Fail", " '" + LOB1 + "'  Premium is incorrect.");
                System.out.println(" '" + LOB1 + "'  Premium is incorrect.");
            }

            //Compare with Actual Result and Expected Result - Status
            if (ActualStatus_LOB1.equalsIgnoreCase(ExpectedStatus_LOB1)) {
                browserUtil.writesToLogAndReport("Pass", " '" + LOB1 + "'  Status is Rated.");
                System.out.println(" '" + LOB1 + "'  Status is Rated.");
            } else {
                browserUtil.writesToLogAndReport("Fail", " '" + LOB1 + "'  Status is Not Rated.");
                System.out.println(" '" + LOB1 + "'  Status is Not Rated.");
            }
        }

        //This method works for Package type only
        public void rateValidatioPackage() throws Exception {
            final String Append = "$";

            String ActualtermPremium = browserUtil.getsTextOfWebElement(cp_termPremium_text);
            System.out.println(ActualtermPremium);
            String ActualStatus = browserUtil.getsTextOfWebElement(cp_Status_text);
            System.out.println(ActualStatus);
            //String GL = "General Liability";
            //String CP = "Property";
            //String CAP = "Capital Assets";
            String LOB1 = browserUtil.getsTextOfWebElement2(cp_Line_Of_Business_text1);
            String LOB2 = browserUtil.getsTextOfWebElement2(cp_Line_Of_Business_text2);

            //Actual Results - LOB 1
            String ActualTermPremium_LOB1 = browserUtil.getsTextOfWebElement(cp_termPremium_text_LOB1);
            System.out.println(ActualTermPremium_LOB1);

            String ActualStatus_LOB1 = browserUtil.getsTextOfWebElement(cp_Status_text_LOB1);
            System.out.println(ActualStatus_LOB1);

            //Actual Results - LOB 2
            String ActualTermPremium_LOB2 = browserUtil.getsTextOfWebElement(cp_termPremium_text_LOB2);
            System.out.println(ActualTermPremium_LOB2);

            String ActualStatus_LOB2 = browserUtil.getsTextOfWebElement(cp_Status_text_LOB2);
            System.out.println(ActualStatus_LOB2);

            browserUtil.clicksWebElement(pes_Common_Link);
            browserUtil.waitUntilPleaseWaitDisappear();

            String ExpectedtermPremium = browserUtil.getsTextOfWebElement(cp_termPremium_text);
            System.out.println(ExpectedtermPremium);
            String ExpectedStatus = browserUtil.getsTextOfWebElement(cp_Status_text);
            System.out.println(ExpectedStatus);
            //Expected Results - LOB1
            String ExpectedTermPremium_LOB1 = browserUtil.getsTextOfWebElement(cp_termPremium_text_LOB1);
            System.out.println(ExpectedTermPremium_LOB1);

            String ExpectedStatus_LOB1 = browserUtil.getsTextOfWebElement(cp_Status_text_LOB1);
            System.out.println(ExpectedStatus_LOB1);

            //Expected Results - LOB2
            String ExpectedTermPremium_LOB2 = browserUtil.getsTextOfWebElement(cp_termPremium_text_LOB2);
            System.out.println(ExpectedTermPremium_LOB2);

            String ExpectedStatus_LOB2 = browserUtil.getsTextOfWebElement(cp_Status_text_LOB2);
            System.out.println(ExpectedStatus_LOB2);

            String ExpectedtermPremiumnew = Append + ExpectedtermPremium;
            // Expected Term Premium (Add $ Symbol with Premium)
            String ExpectedTermPremiumnew_LOB1 = Append + ExpectedTermPremium_LOB1;
            String ExpectedTermPremiumnew_LOB2 = Append + ExpectedTermPremium_LOB2;

            if (ActualtermPremium.equalsIgnoreCase(ExpectedtermPremiumnew)) {
                browserUtil.writesToLogAndReport("Pass", "Premium is Correct.");
                //Compare with Actual Result and Expected Result - Premium
                if (ActualTermPremium_LOB1.equalsIgnoreCase(ExpectedTermPremiumnew_LOB1)) {
                    browserUtil.writesToLogAndReport("Pass", " '" + LOB1 + "'  Premium is Correct.");
                    System.out.println(" '" + LOB1 + "'  Premium is Correct.");

                } else if (ActualTermPremium_LOB2.equalsIgnoreCase(ExpectedTermPremiumnew_LOB2)) {
                    browserUtil.writesToLogAndReport("Pass", " '" + LOB2 + "'  Premium is Correct.");
                    System.out.println(" '" + LOB2 + "'  Premium is Correct.");

                } else {
                    browserUtil.writesToLogAndReport("Fail", "Premium is incorrect.");

                    System.out.println("Premium is incorrect.");
                }

                if (ActualStatus.equalsIgnoreCase(ExpectedStatus)) {
                    browserUtil.writesToLogAndReport("Pass", " Status is Rated.");
                } else {
                    browserUtil.writesToLogAndReport("Fail", "Status is Not Rated .");

                    //Compare with Actual Result and Expected Result - Status
                    if (ActualStatus_LOB1.equalsIgnoreCase(ExpectedStatus_LOB1)) {
                        browserUtil.writesToLogAndReport("Pass", " '" + LOB1 + "'  Status is Rated.");
                        System.out.println(" '" + LOB1 + "'  Status is Rated.");

                    } else if (ActualStatus_LOB2.equalsIgnoreCase(ExpectedStatus_LOB2)) {
                        browserUtil.writesToLogAndReport("Pass", " '" + LOB2 + "'  Status is Rated.");
                        System.out.println(" '" + LOB2 + "'  Status is Rated.");

                    } else {
                        browserUtil.writesToLogAndReport("Fail", "Status is Not Rated.");
                        System.out.println("Status is Not Rated.");
                    }

                }
            }
        }

                public void ratePolicyWithoutVerification(Map<String, List<String>> map) throws InterruptedException
        {
            if(map.get("RatingPage_Flag").get(0).equalsIgnoreCase("Yes"))
            {
                baseUtil.navigatesToSpecifiedLinkOnPolicyExplorer("RATING OPTIONS");
                browserUtil.waitUntilPleaseWaitDisappear();

                if(map.get("Resequence_LocsVehs_Flag").get(0).equalsIgnoreCase("Yes") || map.get("Resequence_LocsVehs_Flag").get(0).equalsIgnoreCase("Check"))
                {
                    browserUtil.setsCheckBoxTo(true, rp_resequenceLocVeh_CheckBox);
                }
                else if(map.get("Resequence_LocsVehs_Flag").get(0).equalsIgnoreCase("No") || map.get("Resequence_LocsVehs_Flag").get(0).equalsIgnoreCase("Uncheck"))
                {
                    browserUtil.setsCheckBoxTo(false, rp_resequenceLocVeh_CheckBox);
                }
                if(map.get("Rerate_All_Lobs_Flag").get(0).equalsIgnoreCase("Yes") || map.get("Rerate_All_Lobs_Flag").get(0).equalsIgnoreCase("Check"))
                {
                    browserUtil.setsCheckBoxTo(true, rp_reRateAllLobs_CheckBox);
                }
                else if(map.get("Rerate_All_Lobs_Flag").get(0).equalsIgnoreCase("No") || map.get("Rerate_All_Lobs_Flag").get(0).equalsIgnoreCase("Uncheck"))
                {
                    browserUtil.setsCheckBoxTo(false, rp_reRateAllLobs_CheckBox);
                }
                if(map.get("Rating_Group_Info_Flag").get(0).equalsIgnoreCase("Yes") || map.get("Rating_Group_Info_Flag").get(0).equalsIgnoreCase("Check"))
                {
                    browserUtil.setsCheckBoxTo(true, rp_ratingGroupInfo_CheckBox);
                }
                else if(map.get("Rating_Group_Info_Flag").get(0).equalsIgnoreCase("No") || map.get("Rating_Group_Info_Flag").get(0).equalsIgnoreCase("Uncheck"))
                {
                    browserUtil.setsCheckBoxTo(false, rp_ratingGroupInfo_CheckBox);
                }
                if(map.get("Policy_and_State_Info_Flag").get(0).equalsIgnoreCase("Yes") || map.get("Policy_and_State_Info_Flag").get(0).equalsIgnoreCase("Check"))
                {
                    browserUtil.setsCheckBoxTo(true, rp_policyAndStateInfo_CheckBox);
                }
                else if(map.get("Policy_and_State_Info_Flag").get(0).equalsIgnoreCase("No") || map.get("Policy_and_State_Info_Flag").get(0).equalsIgnoreCase("Uncheck"))
                {
                    browserUtil.setsCheckBoxTo(false, rp_policyAndStateInfo_CheckBox);
                }
                if(map.get("Class_Code_Info_Flag").get(0).equalsIgnoreCase("Yes") || map.get("Class_Code_Info_Flag").get(0).equalsIgnoreCase("Check"))
                {
                    browserUtil.setsCheckBoxTo(true, rp_classCodeInfo_CheckBox);
                }
                else if(map.get("Class_Code_Info_Flag").get(0).equalsIgnoreCase("No") || map.get("Class_Code_Info_Flag").get(0).equalsIgnoreCase("Uncheck"))
                {
                    browserUtil.setsCheckBoxTo(false, rp_classCodeInfo_CheckBox);
                }
                this.rateOutputOption();
                browserUtil.clicksWebElement(rp_rating_button);
                browserUtil.waitUntilPleaseWaitDisappear();


            }
        }
        public void rateOutputOption() throws InterruptedException {
            browserUtil.setsCheckBoxTo(true, rp_Stat_Coding_CheckBox);
            if(browserUtil.verifiesWebElementIsPresent(rp_Statement_of_Values_CheckBox)){
                browserUtil.setsCheckBoxTo(true, rp_Statement_of_Values_CheckBox);
            }
            browserUtil.setsCheckBoxTo(true, rp_Location_Premium_CheckBox);
            browserUtil.setsCheckBoxTo(true, rp_Location_Schedule_CheckBox);
            Thread.sleep(1000);
        }

        public void ratePolicyWithoutAnyChangeInOptions() throws InterruptedException
        {
            baseUtil.navigatesToSpecifiedLinkOnPolicyExplorer("RATING OPTIONS");
                browserUtil.switchToFrame("InsDmain");
                browserUtil.clicksWebElement(rp_rating_button);
                browserUtil.switchToDefaultContent();
                Thread.sleep(4000);
        }

        public void Gen_Liab_Composite(Map<String, List<String>> map) throws InterruptedException
        {
            if(map.get("RatingPage_Flag").get(0).equalsIgnoreCase("Yes"))
            {
                baseUtil.navigatesToSpecifiedLinkOnPolicyExplorer("RATING OPTIONS");
                browserUtil.waitUntilPleaseWaitDisappear();
                baseUtil.navigatesToSpecifiedLinkOnPolicyExplorer("Gen Liab Composite");
                browserUtil.waitUntilPleaseWaitDisappear();

                browserUtil.clicksWebElement(gl_GenLiabComposite_Txt);
                browserUtil.waitUntilPleaseWaitDisappear();
                browserUtil.entersTextWithTabIn(gl_RateOverride_Txt, map.get("Rate_Override").get(0));
                browserUtil.clicksWebElement(gl_save_Btn);
            }
        }

}

    public Browser_Util theUser() {
        return this.browserUtil;
    }

    public RatingMethods theUser_() {
        return this.pageMethods;
    }

}