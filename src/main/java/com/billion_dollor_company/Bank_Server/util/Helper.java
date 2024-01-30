package com.billion_dollor_company.Bank_Server.util;

import com.billion_dollor_company.Bank_Server.models.TransactionRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.sql.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String encodedStr) {
        return Base64.getDecoder().decode(encodedStr);
    }

    public static String getPrettyJson(String uglyJSON) {
        try {

            Object obj = TransactionRequest.class;

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(uglyJSON);
            String prettyJson = objectMapper.writeValueAsString(jsonNode);
            return prettyJson;
        } catch (Exception ignored) {
        }
        return "";
    }

    public static <T> String getPrettyXML(String uglyXML, Class<T> valueType) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            T obj = xmlMapper.readValue(uglyXML, valueType);
            String pretty = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return pretty;
        } catch (Exception ignored) {
        }
        return "";
    }
    public static String getUserPasswordFromDb(String upiId){
        return "123456";
    }


//    public static String getUserPasswordFromDb(String upiId) {
//        Connection connection = null;
//        String hashed_password = null;
//        try {
//
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/be_project";
//            String username = "root";
//            String password = "root123";
//
//            try (Connection conn = DriverManager.getConnection(url, username, password)) {
//                String sql = "SELECT encrypted_password FROM users WHERE upi_id = ?";
//                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//                    stmt.setString(1, upiId);
//                    try (ResultSet rs = stmt.executeQuery()) {
//                        if (rs.next()) {
//                            hashed_password = rs.getString("encrypted_password");
//                        }
//                    }
//                    finally {
//                        stmt.close();
//                    }
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            // below two lines are used for connectivity.
//
//            System.out.println("Value fetched from database: "+ hashed_password);
//
//            connection.close();
//        } catch (Exception exception) {
//            System.out.println(exception);
//        }
//
//        return hashed_password;
//    }

    public static Map<String, String> getUserInfo(String upiID) {

        Map<String, String> map = new HashMap<>();

        if (upiID.equals("akshaybhalerao@oksbi")) {
            map.put(Constants.UserInfo.Response.ACCOUNT_NO, "SBI12345");
            map.put(Constants.UserInfo.Response.FULL_NAME, "AKSHAY AVINASH BHALERAO");
            map.put(Constants.UserInfo.Response.BANK_NAME, "STATE BANK OF INDIA");
            map.put(Constants.UserInfo.Response.UPI_ID, upiID);
        } else if (upiID.equals("anandbhalerao@oksbi")) {
            map.put(Constants.UserInfo.Response.ACCOUNT_NO, "SBI98765");
            map.put(Constants.UserInfo.Response.FULL_NAME, "ANAND AVINASH BHALERAO");
            map.put(Constants.UserInfo.Response.BANK_NAME, "STATE BANK OF INDIA");
            map.put(Constants.UserInfo.Response.UPI_ID, upiID);
        } else {
            return null;
        }
        return map;
    }


}
