import base.BaseTest;
import config.Configuration;
import org.testng.annotations.Test;

public class BankManagerTests extends BaseTest {

    @Test
    public void validateNewCustomerCreation() throws InterruptedException {
        var firstname = "Adam";
        var lastname = "Kowal";
        var postcode = "12345";
        getLoginPage()
                .loginAsBankManager()
                .createNewCustomer(firstname, lastname, postcode)
                .moveToLoginPage()
                .loginAsCustomer(firstname, lastname)
                .validateProperWelcomeMessageDisplayed(firstname, lastname);
    }

    @Test
    public void validateNewAccountCreation() throws InterruptedException {
        var firstname = "Harry";
        var lastname = "Potter";
        var currency = "Pound";
        var accountNumber = "1016";
        getLoginPage()
                .loginAsBankManager()
                .openAccount(firstname, lastname, currency)
                .moveToLoginPage()
                .loginAsCustomer(firstname, lastname)
                .validateBankAccount(accountNumber);
    }

    @Test
    public void validateDeletingCustomer() {
        var firstname = "Harry";
        var lastname = "Potter";
        getLoginPage()
                .loginAsBankManager()
                .searchCustomer(firstname)
                .deleteAccount()
                .moveToLoginPage()
                .validateDeletedCustomer(firstname, lastname);
    }

    @Test
    public void validateNewBankAccountForNewUser() throws InterruptedException {
        var firstname = "Adam";
        var lastname = "Kowal";
        var currency = "Pound";
        var accountNumber = "1016";
        var postcode = "12345";
        getLoginPage()
                .loginAsBankManager()
                .createNewCustomer(firstname, lastname, postcode)
                .openAccount(firstname, lastname, currency)
                .moveToLoginPage()
                .loginAsCustomer(firstname, lastname)
                .validateBankAccount(accountNumber);
    }
}
