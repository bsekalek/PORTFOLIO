import base.BaseTest;
import org.testng.annotations.Test;

public class CustomerTests extends BaseTest {

    @Test
    public void validateDepositFunctionality() throws InterruptedException {
        var firstname = "Albus";
        var lastname = "Dumbledore";
        var amount = 560;

        getLoginPage()
                .loginAsCustomer(firstname, lastname)
                .depositMoney(amount)
                .validateBalanceAmount(amount)
                .openTransactions()
                .validateLatestTransaction(amount, "Credit");
    }

    @Test
    public void validateWithdrawFunctionality() throws InterruptedException {
        var firstname = "Albus";
        var lastname = "Dumbledore";
        var withdrawl = 40;
        var amount = 600;

        getLoginPage()
                .loginAsCustomer(firstname, lastname)
                .depositMoney(amount)
                .moveToLoginPage()
                .loginAsCustomer(firstname, lastname)
                .withdrawlMoney(withdrawl)
                .validateWithdrawlMoney(amount, withdrawl);
    }

    @Test
    public void validateTransactionHistory() throws InterruptedException {
        var firstname = "Albus";
        var lastname = "Dumbledore";
        var amount = 100;
        getLoginPage()
                .loginAsCustomer(firstname, lastname)
                .depositMoney(amount)
                .openTransactions()
                .validateLatestTransaction(amount, "Credit")
                .clearTransactionHistory();
    }

    @Test
    public void validateTransactionsForNewCustomers() throws InterruptedException {
        var firstname = "Adam";
        var lastname = "Kowal";
        var postcode = "12345";
        var currency = "Dollar";
        var amount = 1003;
        var withdrawl = 123;
        getLoginPage()
                .loginAsBankManager()
                .createNewCustomer(firstname, lastname, postcode)
                .moveToLoginPage()
                .loginAsBankManager()
                .openAccount(firstname, lastname, currency)
                .moveToLoginPage()
                .loginAsCustomer(firstname, lastname)
                .depositMoney(amount)
                .withdrawlMoney(withdrawl)
                .openTransactions()
                .validateLatestTransaction(amount, "Credit");
    }

    @Test
    public void validateTransactionReset() throws InterruptedException {
        var firstname = "Albus";
        var lastname = "Dumbledore";
        var amount = 560;

        getLoginPage()
                .loginAsCustomer(firstname, lastname)
                .depositMoney(amount)
                .openTransactions()
                .clearTransactionHistory()
                .validateBalanceReset();
    }

    @Test
    public void validateWithdrawWarning() throws InterruptedException {
        var firstname = "Albus";
        var lastname = "Dumbledore";
        var withdrawl = 40;

        getLoginPage()
                .loginAsCustomer(firstname, lastname)
                .withdrawlMoney(withdrawl)
                .validateWithdrawlMoney(withdrawl);
    }
}
