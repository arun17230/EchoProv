package Utilities;

import org.testng.annotations.DataProvider;

public class NegativeTestData 
{

    @DataProvider(name = "NegativeTinScenarios")
    public Object[][] provideNegativeTinData() 
    {
        return new Object[][] {{
                "All fields empty",
                "", "", "", "",
                new String[] 
                		{
                    MessageReader.get("affiliation.required"),
                    MessageReader.get("tin.required"),
                    MessageReader.get("draft.number.required"),
                    MessageReader.get("draft.amount.required")}},
            {
                "Only affiliation selected",
                "Principal", "", "", "",
                new String[] {
                    MessageReader.get("tin.required"),
                    MessageReader.get("draft.number.required"),
                    MessageReader.get("draft.amount.required")}},
            {
                "Invalid TIN format",
                "Principal", "@@##$$", "100", "500",
                new String[] {
                    MessageReader.get("invalid.tin")}},
            {
                "Duplicate TIN",
                "Principal", "1234567890", "999", "500",
                new String[] {MessageReader.get("duplicate.tin")}}};
    }
}
