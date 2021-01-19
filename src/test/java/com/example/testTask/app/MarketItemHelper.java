package com.example.testTask.app;

import com.example.testTask.entity.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class MarketItemHelper extends BaseHelper {

    public MarketItemHelper(WebDriver wd) {
        super(wd);
    }

    public void filterItem(Item item) {

        clickOnElement(By.cssSelector("[href*='/catalog--" + item.getItemType() + "']"));

        Actions actions = new Actions(wd);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        fillInForm(By.cssSelector("#glpricefrom"), item.getPriceFrom());
        fillInForm(By.cssSelector("#glpriceto"), item.getPriceTo());
        clickOnElement(By.xpath("//*[text()='"+ item.getBrand() +"']"));
    }

    public void switchToNextTab() {
        List<String> availableTabs = new ArrayList<>(wd.getWindowHandles());
        if(!availableTabs.isEmpty()){
            wd.switchTo().window(availableTabs.get(1));
        }
    }

    public String getItemNameFromListByOrder(Integer number) {
        return wd.findElement(By.xpath("//*[@data-autotest-id='product-snippet']["+number+"]//h3")).getText();
    }

    public void searchItemFromSearchBox(String itemName) {
        fillInForm(By.cssSelector("#header-search"), itemName);
        clickOnElement(By.cssSelector("[type=submit]"));
    }
}
