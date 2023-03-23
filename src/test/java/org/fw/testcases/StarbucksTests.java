package org.fw.testcases;

import org.fw.pages.StarbucksPage;
import org.fw.utils.ExcelReader;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class StarbucksTests extends BaseTest {

    String currentWorkDir = System.getProperty("user.dir");
    String path = currentWorkDir + "/src/test/resources/";
    private final ExcelReader excelReaderUtils = new ExcelReader(path + "sb_usa_small.xlsx");
    int LOCATION_COL_INDEX = 1;
    int CITY_COL_INDEX = 2;
    int PRICE_COL_INDEX = 4;

    @Test
    public void executeTest() throws IOException {
        StarbucksPage starbucksPage = new StarbucksPage();

        LinkedHashMap<Integer, List<String>> locations = excelReaderUtils.read();

//        starbucksPage.tryAllowNotification();

        // TODO: check this later
        // starbucksPage.enterSignInScreen();
        // starbucksPage.logIn("john.harling.90@gmail.com", "John1990!");

        // will have some steps before you can reach to next line (clickOrderMenuButton)
        // may be you need to implement some additional steps to proceed to next step

        starbucksPage.clickOrderMenuButton();
        starbucksPage.clickSearchButton();
        starbucksPage.enterLocation("Anchorage");
        starbucksPage.clickSearchResult("Anchorage");
        starbucksPage.chooseStore("Anchorage");
        starbucksPage.clickOrderHereButton();
        starbucksPage.clickMenuCard();
        starbucksPage.chooseHotCoffee();
        starbucksPage.chooseCoffeeLatte();
        starbucksPage.chooseSize();
        starbucksPage.addToOrder();
        starbucksPage.clickCart();
        starbucksPage.closePopUp();

        for (int i = 1; i < locations.size(); i++) {

            if (!locations.get(i).get(PRICE_COL_INDEX).equals(" ")) {
                System.out.println("Already have price, next store");
                continue;
            }

            starbucksPage.clickPickupStoreDropdown();
            starbucksPage.denyLocation();
            starbucksPage.clickSearchButton();
            starbucksPage.enterLocation(locations.get(i).get(LOCATION_COL_INDEX) + "," + locations.get(i).get(CITY_COL_INDEX));
            starbucksPage.clickSearchResult(locations.get(i).get(LOCATION_COL_INDEX));
            starbucksPage.chooseStore(locations.get(i).get(LOCATION_COL_INDEX) + "," + locations.get(i).get(CITY_COL_INDEX));
        }
    }
}
