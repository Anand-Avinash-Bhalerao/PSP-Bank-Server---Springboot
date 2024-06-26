package com.billion_dollor_company.Bank_Server.util;

public class Constants {

    public static class Keys {

        public static final String NPCI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzlJDxZyYybLotiDimedygowDUGf7x6suqbpULbkGk/YtI+q+6yRWPhYnNeSiBrxgR7xev7Afz7a38FvZbXyUlriPqk0trzacWEyEKbJAogTtc/uKdD4tZ/8FI7qEVpP4SCUjbHxhigQzHR7rfYbzjMMRghk+CS4f0WdJ2LuHBxaIOTYFiaLu/JOKxRbFhStUwG6bbuaBzEdyPIWzt9DnotemcTVpmkgblF1W1dQ3uPf2yRCXQf5YEfvQUxYTQ36tKj9yclyLCgn/8o02SiyBRmRQ1mvbV1k4pLibZByfRWh6/xbPEGQFyqRpQT6FI28rWPh6K9Ehn7gXtjjsRgjcwIDAQAB";

        public static final String BANK_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDWotkCByVEZ36gKXAMPrF8ASlKIDTXLHB8CN3uCtSbkfUBw2AJTn+74tVL6efi69+tw0b5FVhcgymcHpqFu0Gn3T1fEM8hhgMLhctq7rAa/kD7WjBBGBRGT0O9FRet33XcKyUGJPXot0FpE2NToQ7LbkQVXrJAso1Qm52F0LF/1mZsGm8iFrYRiVLg72RT6d3LZnTkrkPflvFI0bsuUYtnlY7S/cUBakpSDH5di2BuY2Ky55DZyqm9DAguQHxEvZgKI0OBPTjToUC09xOIySJjZHObYIfsp+xyB9fbQLbjlot0ZYk8Toy4EQli03X5mPtjdyNxSOh/W5Wx5izC+YmLAgMBAAECggEAUxMY11YYsI82TZt8SOEDJTH130Dz8sy6hOeRro72l3g40oPiafe+LOSxFf7kx8nv+3t3vvTqDHSf6FNYD6bY1LYnCOKiqSELkKwqfasA8pGyaRcRUkCVqFkJqOe/EIdLQPpaDDTGEVrMsHTWnwnZhheZtoO+jpgNZkO1BpQt/4IxB6FzpDfyqlrzM2lZAGlsb4fHAEIs/1Izk2/pg1Lj4QgpQxWedObYO4IrK/FK+wAI8F5CGOG6+1rt5v4RKfwVZFwLQH5qSJnMuQmCA5RV9zvQGOB6ILLHV2ZpLUmIUz27DV/SX3683cUvrVLHJPjGUWFHJ+geA675fhQGSUNPwQKBgQDvRypcfO5ScBdxRaoLPoTvhAf5OGthBOHCL/YIVDggMVqL+vk47VK1TrL2KTpsEKWaki0kn0BEYzRg7z07U3+EesiNUFARCi7A3N9kH6KS7HASBwSLwy1/dLhfpI9kr1tBDFLfMKy/WxgxFEt2VUqZIcVHYkjtZ0KAtI2uWkeG2wKBgQDlotLaX8SB4leGWUhAGPuZeV0qbg+a11Mos8eGVSj9NQTfkaVNGPvbeT5L2gGPQlIOxP9tyqO1aXInJ/ZarVRJIAriyx5/qPMKhfrKYu4mOh7qqmpJIkNh2Ilp2GdZTRktBuVaQ1R97ppJQQscxUSup0Rp8ZQI8VVxJDSpoZRPEQKBgG4m2s99C9GTnJcetOs8OsJwi6MlX/ln3+4fWin3a2cWkVKDlRup1Tt2IVjFHgoFl9cSmAmV7mYNLV26hPu8sqVwSMcVQ+a0Q1Wx2H0lOxhcFDOYW3Vjwpw8RWxyOzjkqSPG4pewtrXY38goSZ0OIqtgKot0KZJwyOKrO9YZ5XelAoGAEB41no5KRLOWYR+zKoyWXFdgarH+9mdWtuynfI5Nqjgmd2rJp3exdd40uS48HcoR6Gd7ZkFq7DjSgl0TtZmk6N0O5oxLP7qkFIEC8WGwRY6ezOnZO/jjseJ5Aqzl1CbNNZPeVU+UhqJQwgOGvCY8g+XJr4X9VgNac+6Oo7lrp4ECgYEA51KIJ5yeRrVj88hCCtVu+USSVmnWdOFFg8QwZiBaiGxQKuPqkXi5RiI3nn01h14l8Hv2x74m3kjSsquuNETFjGa85kZGGG/uxw2PCg7bEGQX2rGSnjLWTNP6glwOaH6YHs2iLadAqtkjzcHl1vsWwFHji8r2CVeY4q/k8/u6jzE=";
    }

    public static class DatabaseTables {
        public static final String USERS_TABLE = "users";
        public static final String PASSWORDS_TABLE = "passwords";

    }

    public static class Status {

        public static final String SUCCESS = "Success";
        public static final String FAILED = "Failed";
    }

    public static class MessagePrinter {
        public static class Server {
            public static final String PSP = "PSP";
            public static final String BANK = "BANK";
        }
        public static class MethodType {
            public static final String AccountInfo = "AccountInfo";
            public static final String InitiateTransaction = "InitiateTransaction";
            public static final String Registration = "Registration";
            public static final String FetchKeys = "FetchKeys";
            public static final String CheckBalance = "CheckBalance";
        }
    }

    public static class Messages {
        public static final String NO_PAYER_ACCOUNT_FOUND = "No payer Account found";
        public static final String NO_PAYEE_ACCOUNT_FOUND = "No payee Account found";
        public static final String NO_ACCOUNT_FOUND = "No Account found for the upiID: ";
        public static final String NOT_ENOUGH_BALANCE = "Not enough account balance";
        public static final String INCORRECT_PASSWORD = "The pin entered is incorrect";
        public static final String SUCCESSFUL_PAYMENT = "Payment Successful";
        public static final String SUCCESSFUL_CHECK_BALANCE = "Balance Fetch Successful";
        public static final String SOME_ERROR_OCCURRED = "An unknown error occurred";
    }

    public static class Servers {

        public static class NPCI_Server {
            private static final String NPCI_SERVER_PORT = "17000";

            private static final String BASE_URL = "http://localhost:" + NPCI_SERVER_PORT + "/npci";
            private static final String START_TRANSACTION = "/transaction";
            private static final String CHECK_BALANCE = "/checkBalance";
            private static final String REGISTRATION = "/registration";
            private static final String FETCH_KEYS = "/fetchKeys";

            public static String getTransactionURL() {
                return BASE_URL + START_TRANSACTION;
            }

            public static String getAccountBalanceURL() {
                return BASE_URL + CHECK_BALANCE;
            }

            public static String getRegistrationURL(){
                return BASE_URL + REGISTRATION;
            }
            public static String getFetchKeysURL(){
                return BASE_URL + FETCH_KEYS;
            }


        }
    }
}
