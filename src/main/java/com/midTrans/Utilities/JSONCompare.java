package com.midTrans.Utilities;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class JSONCompare {
    ObjectMapper objectMapper;
    JsonNode jsonNode1;
    JsonNode jsonNode2;
    SoftAssert softAssert = new SoftAssert();
    boolean flag = false;

    public boolean compare(String Object1, String Object2) throws IOException {
        objectMapper = new ObjectMapper();
        try {
            jsonNode1 = objectMapper.readTree(Object1);
            jsonNode2 = objectMapper.readTree(Object2);

            softAssert.assertTrue(jsonNode1.equals(jsonNode2));
        }
        catch (Exception e)
        {
            System.out.println("Failed due to null value to compare");
            return false;
        }
        return jsonNode1.equals(jsonNode2);
    }
}
