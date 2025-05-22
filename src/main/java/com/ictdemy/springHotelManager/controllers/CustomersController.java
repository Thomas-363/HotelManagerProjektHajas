package com.ictdemy.springHotelManager.controllers;


import com.ictdemy.springHotelManager.data.entities.RoomEntity;
import com.ictdemy.springHotelManager.models.dto.CustomerDTO;
import com.ictdemy.springHotelManager.models.dto.RoomDTO;
import com.ictdemy.springHotelManager.models.dto.mappers.CustomerMapper;
import com.ictdemy.springHotelManager.models.exceptions.DuplicateEmailException;
import com.ictdemy.springHotelManager.models.exceptions.NoAvailableRoomsException;
import com.ictdemy.springHotelManager.models.services.AccommodateService;
import com.ictdemy.springHotelManager.models.services.CustomerService;
import com.ictdemy.springHotelManager.models.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AccommodateService accommodateService;


    @GetMapping("/accommodate")
    public String renderCustomerCreateForm(@ModelAttribute("customer") CustomerDTO customer, Model model) {
        List<RoomEntity> freeRoomsForGuests;
        try {
            freeRoomsForGuests = roomService.getFreeRooms(1);
        } catch (NoAvailableRoomsException e) {
            model.addAttribute("error", "There are not enough rooms for the selected guests");
            return "pages/customers/accommodate";
        }

        model.addAttribute("freeRooms",freeRoomsForGuests);
        model.addAttribute("customer", customer);

        List<RoomDTO> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);

        return "pages/customers/accommodate";
    }

    @PostMapping("accommodate")
    public String accommodateCustomer(@Valid @ModelAttribute("customer") CustomerDTO customer,
                                        BindingResult result, Model model,
                                        RedirectAttributes redirectAttributes)
    {

        if (result.hasErrors())
            return renderCustomerCreateForm(customer, model);

        try {
            boolean accommodated = accommodateService.accommodateCustomer(customer);

            if (!accommodated) {
                return renderCustomerCreateForm(customer, model);
            }
            redirectAttributes.addFlashAttribute("toastMessage", "Customer was added successfully!");
            redirectAttributes.addFlashAttribute("toastType", "success");
        }catch (DuplicateEmailException e){
            result.rejectValue("email", "error", "Email already in use.");
            return renderCustomerCreateForm(customer, model);
        }
        return "redirect:/customers/index";
    }
    @GetMapping("/index")
    public String renderCustomers(Model model) {
        List<CustomerDTO> customers = customerService.getAll();
        model.addAttribute("customers", customers);

        List<RoomDTO> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);


        return "pages/customers/index";
    }

    @GetMapping("/checkout")
    public String renderCheckout(Model model) {
        List<CustomerDTO> customers = customerService.getAll();
        model.addAttribute("customers", customers);

        List<RoomDTO> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);

        return "/pages/customers/checkout";
    }

    @GetMapping("checkout/{customerId}")
    public String checkoutCustomer(@PathVariable("customerId") Long customerId,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        Optional<CustomerDTO> customerDTO = customerService.findById(customerId);
        customerService.remove(customerId);
        RoomEntity editRoom = roomService.findRoomByNumber(customerDTO.get().getRoomNumber());
        editRoom.setOccupied(editRoom.getOccupied() - 1);
        roomService.saveRoom(editRoom);


        List<CustomerDTO> customers = customerService.getAll();
        model.addAttribute("customers", customers);

        List<RoomDTO> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);

        redirectAttributes.addFlashAttribute("toastMessage", "Customer was checked out successfully.");
        redirectAttributes.addFlashAttribute("toastType", "success");

        return "redirect:/customers/checkout";
    }

    @GetMapping("index/edit/{customerId}")
    public String renderEditCustomer(@PathVariable("customerId") Long customerId, Model model) {
        List<RoomEntity> freeRoomsForGuests = List.of();
        Optional<CustomerDTO> customerDTO = customerService.findById(customerId);
        try {
            if(customerDTO.isPresent()){
                freeRoomsForGuests = roomService.getFreeRoomsIncludingCurrent(1, customerDTO.get().getRoomNumber());
            }
        } catch (NoAvailableRoomsException e) {
            model.addAttribute("error", "There are not enough rooms for the selected guests");
            return "pages/customers/accommodate";
        }

        if (customerDTO.isEmpty()) {
            model.addAttribute("error", "Customer not found");
            return "customers/index";
        }
        CustomerDTO customer = customerDTO.get();
        customer.setPreviousRoomNumber(customer.getRoomNumber());


        model.addAttribute("customer", customerDTO.get());
        model.addAttribute("freeRooms",freeRoomsForGuests);


        List<RoomDTO> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);

        return "pages/customers/edit";
    }

    @PostMapping("edit")
    public String editCustomer(@Valid @ModelAttribute("customer") CustomerDTO customer,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            List<RoomDTO> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
            return "pages/customers/edit";
        }
        try{
            boolean success = accommodateService.editCustomer(customer);

            if (!success) {
                return renderEditCustomer(customer.getCustomerId(), model);
            }
            redirectAttributes.addFlashAttribute("toastMessage", "Customer updated successfully.");
            redirectAttributes.addFlashAttribute("toastType", "success");

        } catch (DuplicateEmailException e){
            result.rejectValue("email", "error", "Email already in use.");
            return renderEditCustomer(customer.getCustomerId(), model);
        }
        return "redirect:/customers/index";

    }


}