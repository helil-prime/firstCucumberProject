package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class ItemsPage {
	
	public ItemsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy (xpath = "//h3[text()='Items']")
	public WebElement itemsPageHeaderText;
	
	@FindBy (xpath = "//button[text()=' Add Item']")
	public WebElement addItemButton;
	
	@FindBy (xpath = "//h3[text()='New Item']")
	public WebElement addItemPageHeaderText;
	
	@FindBy (xpath = "(//input[@type='text'])[2]")
	public WebElement addItemName;
	
	@FindBy (xpath = "//div[text()='Price ']//parent::label//following-sibling::div/input")
	public WebElement addItemPrice;
	
	@FindBy (xpath = "//div[text()='select unit']//preceding::input[1]")
	public WebElement addItemUnit;
	
	@FindBy (xpath = "//span[text()='pc']")
	public WebElement addItem_pc_unit;
	
	@FindBy (name = "description")
	public WebElement addItemDesciption;
	 
	@FindBy (xpath = "//button[text()=' Save Item']")
	public WebElement saveItemButton;
	
	//a[text()='Soccer']
	
	@FindBy (xpath = "//h3[text()='Edit Item']")
	public WebElement editItemHeaderText;
	
	@FindBy (xpath = "//button[text()=' Update Item']")
	public WebElement updateButton;
	
}
