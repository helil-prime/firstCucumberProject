package step_definitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CraterCommonPage;
import pages.ItemsPage;
import pages.LogInPage;
import utilities.BrowserUtils;
import utilities.DataReader;
import utilities.Driver;

public class ItemsManagement {
	
	LogInPage loginpage = new LogInPage();
	ItemsPage itemsPage = new ItemsPage();
	CraterCommonPage commonPage = new CraterCommonPage();
	BrowserUtils utils = new BrowserUtils();
	
	static String itemName;
	
	@Given("As an entity user, I am logged in")
	public void as_an_entity_user_i_am_logged_in() {
		Driver.getDriver().get(DataReader.getProperty("appUrl"));
		loginpage.login();
	}
	@Given("I navigate to Items tab")
	public void i_navigate_to_items_tab() {
		commonPage.itemsLink.click();
		Assert.assertTrue(itemsPage.itemsPageHeaderText.isDisplayed());
	}
	@When("I click on the Add Item button")
	public void i_click_on_the_add_item_button() {
		itemsPage.addItemButton.click();
	}
	@Then("I should be on item input page")
	public void i_should_be_on_item_input_page() {
		Assert.assertTrue(itemsPage.addItemPageHeaderText.isDisplayed());
	}
	@When("I provide item information name {string}, price {int}, unit {string}, and description {string}")
	public void i_provide_item_information_name_price_unit_and_description(String name, Integer price, String unit, String description) {
		itemName = name + utils.randomNumber();
		itemsPage.addItemName.sendKeys(itemName);
		itemsPage.addItemPrice.sendKeys(price.toString());
		itemsPage.addItemUnit.click();
		utils.waitUntilElementVisible(itemsPage.addItem_pc_unit);
		Driver.getDriver().findElement(By.xpath("//span[text()='"+unit+"']")).click();
		
		itemsPage.addItemDesciption.sendKeys(description);
	}
	@When("I click Save Item button")
	public void i_click_save_item_button() {
	   itemsPage.saveItemButton.click();
	}
	@Then("The Item is added to the Item list table")
	public void the_item_is_added_to_the_item_list_table() {
		Assert.assertTrue(
				Driver.getDriver().findElement(By.xpath("//a[text()='"+itemName+"']")).isDisplayed());
	}
	
	// update item scenario steps
	
	@When("I select the item {string}")
	public void i_select_the_item(String name) {
		Driver.getDriver().findElement(By.xpath("//a[text()='"+itemName+"']")).click();
	}
	@When("I should be on item details page")
	public void i_should_be_on_item_details_page() {
	    Assert.assertTrue(itemsPage.editItemHeaderText.isDisplayed());
	}
	@When("I update the item price to {int} dollars")
	public void i_update_the_item_price_to_dollars(Integer price) {
		itemsPage.addItemPrice.clear();
		itemsPage.addItemPrice.sendKeys(price.toString());
	}
	@When("I click Update Item button")
	public void i_click_update_item_button() {
	    itemsPage.updateButton.click();
	}
	@Then("the Item price is updated to {int} dollars")
	public void the_item_price_is_updated_to_dollars(Integer updatedPrice) {
		String itemXpath = "(//a[text()='"+itemName+"']//parent::td//following-sibling::td)[2]//span";
	    String itemPrice = Driver.getDriver().findElement(By.xpath(itemXpath)).getText();
	    System.out.println(itemPrice); //$ 800.00
	    String trimmedPrice = itemPrice.substring(2);
	    Assert.assertEquals(trimmedPrice, updatedPrice + ".00");
	}
	
	
	// data table item create steps
	
	@When("I provide item information to the fields")
	public void i_provide_item_information_to_the_fields(DataTable dataTable) {
		List<String> itemInfo = dataTable.asList();
		for ( String info : itemInfo) {
			System.out.println(info);
		}
		itemName = itemInfo.get(0);
		itemsPage.addItemName.sendKeys(itemInfo.get(0));
		itemsPage.addItemPrice.sendKeys(itemInfo.get(1));
		itemsPage.addItemUnit.click();
		utils.waitUntilElementVisible(itemsPage.addItem_pc_unit);
		Driver.getDriver()
		.findElement(By.xpath("//span[text()='"+ itemInfo.get(2) +"']")).click();
		itemsPage.addItemDesciption.sendKeys(itemInfo.get(3));
	}

}
