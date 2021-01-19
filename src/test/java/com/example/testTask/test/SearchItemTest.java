package com.example.testTask.test;

import com.example.testTask.entity.Item;
import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchItemTest extends TestBase {

    @Test
    public void testItem() throws InterruptedException {
        app.getMainPageHelper().goToYandexMarket(By.cssSelector("[data-id='market']"));


        app.getMarketItemHelper().switchToNextTab();
        app.getMarketHeaderHelper().selectComputerDepartment(By.cssSelector("[href*='/catalog--kompiuternaia-tekhnika']"));
        app.getMarketItemHelper().filterItem(new Item()
                .setItemType("planshety")
                .setPriceFrom("20000")
                .setPriceTo("35000")
                .setBrand("Apple"));

        app.getMarketItemHelper().pause(3000);

        String item = app.getMarketItemHelper().getItemNameFromListByOrder(2);
        System.out.println(item);

        String itemName = app.getMarketItemHelper().getItemNameFromListByOrder(2);
        app.getMarketItemHelper().searchItemFromSearchBox(itemName);
        app.getMarketItemHelper().pause(3000);

        String foundItemName = app.getMarketItemHelper().getItemNameFromListByOrder(1);

        Assert.assertEquals(foundItemName,itemName);

    }




}
