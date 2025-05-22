package com.ictdemy.springHotelManager.controllers;


import com.ictdemy.springHotelManager.models.services.CustomerService;
import com.ictdemy.springHotelManager.models.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class HomeController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;


    @GetMapping("/")
    public String renderIndex(Model model) {
        LocalDate today = LocalDate.now();
        model.addAttribute("todayDate", today.format(DateTimeFormatter.ofPattern("d. MMMM yyyy", new Locale("en"))));
        model.addAttribute("guestsToday", customerService.getCountOfAccommodateCustomers());
        model.addAttribute("availableRooms", roomService.getCountOfAvailableRooms());

        return "pages/home/index";
    }
}
