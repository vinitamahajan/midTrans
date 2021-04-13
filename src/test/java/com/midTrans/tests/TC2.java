package com.midTrans.tests;

import com.google.gson.JsonElement;
import com.midTrans.Common.BaseTest;
import com.midTrans.Utilities.FileReader;
import com.midTrans.Utilities.JSONCompare;
import com.midTrans.Utilities.TestListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

@Listeners({TestListener.class})
public class TC2 extends BaseTest {

    String FilePath1 = "src/main/resources/TestCaseData/File1.xlsx";
    String FilePath2 = "src/main/resources/TestCaseData/File2.xlsx";
    FileReader fileReader = new FileReader();
    JSONCompare jsonCompare = new JSONCompare();
    String equals = "equals";
    String notequals = "notequals";
    boolean flag = false;
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void iterateSimultaneously() throws IOException {

        List<String> i1 = fileReader.getRowsfromFile(FilePath1);
        List<String> i2 = fileReader.getRowsfromFile(FilePath2);

        ListIterator<String> itr1 = i1.listIterator();
        ListIterator<String> itr2 = i2.listIterator();

        while (itr1.hasNext() && itr2.hasNext()) {
            String value1 = itr1.next();
            String value2 = itr2.next();
            if(value1 != "" && value2 !="") {
                String response1 = getRequest(value1);
                String response2 = getRequest(value2);
                flag = jsonCompare.compare(response1, response2);
                if (flag)
                    System.out.println(value1 + " " + equals + " " + value2);
                else
                    System.out.println(value1 + " " + notequals + " " + value2);
            }
        }
    }

    @BeforeAll
    public void setup() {
        RestAssured.baseURI = "";
    }

    public String getRequest(String endpoint) {
        JsonElement json = null;
        RestAssured.baseURI = endpoint;
        Response response = null;
        try {
            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(endpoint)
                    .then()
                    .extract().response();
            softAssert.assertEquals(200, response.statusCode());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return response.getBody().asString();
    }


}
