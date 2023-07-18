package com.poly.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/admin/manage/order")
    public String order() {

        return "admin/manage/order";
    }
}
