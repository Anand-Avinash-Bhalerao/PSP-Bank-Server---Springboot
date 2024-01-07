package com.billion_dollor_company.Bank_Server.util;

public class Constants {

    public static class Keys {
        public static final String NPCI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzlJDxZyYybLotiDimedygowDUGf7x6suqbpULbkGk/YtI+q+6yRWPhYnNeSiBrxgR7xev7Afz7a38FvZbXyUlriPqk0trzacWEyEKbJAogTtc/uKdD4tZ/8FI7qEVpP4SCUjbHxhigQzHR7rfYbzjMMRghk+CS4f0WdJ2LuHBxaIOTYFiaLu/JOKxRbFhStUwG6bbuaBzEdyPIWzt9DnotemcTVpmkgblF1W1dQ3uPf2yRCXQf5YEfvQUxYTQ36tKj9yclyLCgn/8o02SiyBRmRQ1mvbV1k4pLibZByfRWh6/xbPEGQFyqRpQT6FI28rWPh6K9Ehn7gXtjjsRgjcwIDAQAB";
        public static final String BANK_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDWotkCByVEZ36gKXAMPrF8ASlKIDTXLHB8CN3uCtSbkfUBw2AJTn+74tVL6efi69+tw0b5FVhcgymcHpqFu0Gn3T1fEM8hhgMLhctq7rAa/kD7WjBBGBRGT0O9FRet33XcKyUGJPXot0FpE2NToQ7LbkQVXrJAso1Qm52F0LF/1mZsGm8iFrYRiVLg72RT6d3LZnTkrkPflvFI0bsuUYtnlY7S/cUBakpSDH5di2BuY2Ky55DZyqm9DAguQHxEvZgKI0OBPTjToUC09xOIySJjZHObYIfsp+xyB9fbQLbjlot0ZYk8Toy4EQli03X5mPtjdyNxSOh/W5Wx5izC+YmLAgMBAAECggEAUxMY11YYsI82TZt8SOEDJTH130Dz8sy6hOeRro72l3g40oPiafe+LOSxFf7kx8nv+3t3vvTqDHSf6FNYD6bY1LYnCOKiqSELkKwqfasA8pGyaRcRUkCVqFkJqOe/EIdLQPpaDDTGEVrMsHTWnwnZhheZtoO+jpgNZkO1BpQt/4IxB6FzpDfyqlrzM2lZAGlsb4fHAEIs/1Izk2/pg1Lj4QgpQxWedObYO4IrK/FK+wAI8F5CGOG6+1rt5v4RKfwVZFwLQH5qSJnMuQmCA5RV9zvQGOB6ILLHV2ZpLUmIUz27DV/SX3683cUvrVLHJPjGUWFHJ+geA675fhQGSUNPwQKBgQDvRypcfO5ScBdxRaoLPoTvhAf5OGthBOHCL/YIVDggMVqL+vk47VK1TrL2KTpsEKWaki0kn0BEYzRg7z07U3+EesiNUFARCi7A3N9kH6KS7HASBwSLwy1/dLhfpI9kr1tBDFLfMKy/WxgxFEt2VUqZIcVHYkjtZ0KAtI2uWkeG2wKBgQDlotLaX8SB4leGWUhAGPuZeV0qbg+a11Mos8eGVSj9NQTfkaVNGPvbeT5L2gGPQlIOxP9tyqO1aXInJ/ZarVRJIAriyx5/qPMKhfrKYu4mOh7qqmpJIkNh2Ilp2GdZTRktBuVaQ1R97ppJQQscxUSup0Rp8ZQI8VVxJDSpoZRPEQKBgG4m2s99C9GTnJcetOs8OsJwi6MlX/ln3+4fWin3a2cWkVKDlRup1Tt2IVjFHgoFl9cSmAmV7mYNLV26hPu8sqVwSMcVQ+a0Q1Wx2H0lOxhcFDOYW3Vjwpw8RWxyOzjkqSPG4pewtrXY38goSZ0OIqtgKot0KZJwyOKrO9YZ5XelAoGAEB41no5KRLOWYR+zKoyWXFdgarH+9mdWtuynfI5Nqjgmd2rJp3exdd40uS48HcoR6Gd7ZkFq7DjSgl0TtZmk6N0O5oxLP7qkFIEC8WGwRY6ezOnZO/jjseJ5Aqzl1CbNNZPeVU+UhqJQwgOGvCY8g+XJr4X9VgNac+6Oo7lrp4ECgYEA51KIJ5yeRrVj88hCCtVu+USSVmnWdOFFg8QwZiBaiGxQKuPqkXi5RiI3nn01h14l8Hv2x74m3kjSsquuNETFjGa85kZGGG/uxw2PCg7bEGQX2rGSnjLWTNP6glwOaH6YHs2iLadAqtkjzcHl1vsWwFHji8r2CVeY4q/k8/u6jzE=";
    }

    public static class TransactionRequest{
        public static final String PAYEE_ID = "payeeID";
        public static final String PAYER_ID = "payerID";
        public static final String BANK_NAME = "bankName";
        public static final String ENCRYPTED_STRING = "encryptedString";
        public static final String AMOUNT = "amount";
    }




    public static class Servers {

        public static class NPCI_Server {
            private static final String BASE_URL = "http://localhost:8080/npci";
            private static final String START_TRANSACTION = "/transaction";

            public static String getTransactionURL() {
                return BASE_URL + START_TRANSACTION;
            }
        }
    }
}
