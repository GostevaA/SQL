package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import data.DBHelper;
import data.DataHelper;
import page.LoginPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DBHelper.cleanDB;
public class AuthorizationTest {
    @AfterAll
    static void setDown() { cleanDB(); }

    @Test
    void shouldSuccessfulLogin() {
        LoginPage loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfoWithTestData();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPage();
        DataHelper.VerificationCode verificationCode = DBHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}