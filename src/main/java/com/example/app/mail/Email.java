package com.example.app.mail;

public class Email {


    public static String getHtmlEmailTemplate(String token, int code) {
        String url = "http://localhost:8080/verify?token=" + token + "&code=" + code;

        return "<!DOCTYPE html>\n" +
                "<html lang='en'>\n" +
                "    <head>\n" +
                "        <meta charset='UTF-8'>\n" +
                "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "        <style>\n" +
                "            @import url('https://fonts.googleapis.com/css2?family=Comfortaa:wght@600&display=swap');\n" +
                "\n" +
                "            * {\n" +
                "                box-sizing: border-box;\n" +
                "                font-family: 'Comfortaa', cursive;\n" +
                "            }\n" +
                "\n" +
                "            body {\n" +
                "                height: 100vh;\n" +
                "                background-color: rgb(212, 222, 230);\n" +
                "                display: flex;\n" +
                "                align-items: center;\n" +
                "                justify-content: center;\n" +
                "            }\n" +
                "\n" +
                "            .wrapper {\n" +
                "                width: 550px;\n" +
                "                height: auto;\n" +
                "                padding: 15px;\n" +
                "                background-color: #fff;\n" +
                "                border-radius: 7px;\n" +
                "                text-align: center;\n" +
                "            }\n" +
                "\n" +
                "            .email-msg-header {\n" +
                "                font-size: 1.6em;\n" +
                "            }\n" +
                "\n" +
                "            .company-name {\n" +
                "                width: 100%;\n" +
                "                font-size: 35px;\n" +
                "                color: gray;\n" +
                "            }\n" +
                "\n" +
                "            .verify-account-btn {\n" +
                "                padding: 15px;\n" +
                "                background-color: rgb(0, 109, 252);\n" +
                "                text-decoration: none;\n" +
                "                color: #fff;\n" +
                "                border-radius: 5px;\n" +
                "            }\n" +
                "\n" +
                "            .copy-right {\n" +
                "                padding: 15px;\n" +
                "                color: gray;\n" +
                "                font-size: 14px;\n" +
                "                margin: 20px;\n" +
                "            }\n" +
                "        </style>\n" +
                "        <title>Dashboard</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div class='wrapper'>\n" +
                "            <h2 class='email-msg-header'>\n" +
                "                Welcome and Thank You for Choosing\n" +
                "            </h2>\n" +
                "            <div class='company-name'>Easy Way Bank</div>\n" +
                "\n" +
                "            <hr>\n" +
                "\n" +
                "            <div class='welcome-text'>\n" +
                "                Your Account has been successfully registered, please click below to verify your account.\n" +
                "            </div>\n" +
                "\n" +
                "            <br><br>\n" +
                "\n" +
                "            <a href='" + url + "' class='verify-account-btn' role='button'>Verify Account</a>\n" +
                "\n" +
                "            <div class='copy-right'>\n" +
                "                &copy; Copy Right 2023. All Rights Reserved.\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";
    }
}
