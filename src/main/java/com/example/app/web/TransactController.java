package com.example.app.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transact")
public class TransactController {

    @PostMapping("/deposit")
String deposit(@RequestParam("deposit_amount") String depositAmount,
               @RequestParam("account_id") String accountId,
               HttpSession session,
               RedirectAttributes attributes) {
        return "";
    }

}
