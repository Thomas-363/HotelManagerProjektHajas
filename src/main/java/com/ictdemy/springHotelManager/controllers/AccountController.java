package com.ictdemy.springHotelManager.controllers;


import com.ictdemy.springHotelManager.data.entities.UserEntity;
import com.ictdemy.springHotelManager.data.repositories.UserRepository;
import com.ictdemy.springHotelManager.models.dto.CustomerDTO;
import com.ictdemy.springHotelManager.models.dto.UserDTO;
import com.ictdemy.springHotelManager.models.dto.mappers.UserMapper;
import com.ictdemy.springHotelManager.models.exceptions.DuplicateEmailException;
import com.ictdemy.springHotelManager.models.exceptions.PasswordsDoNotEqualException;
import com.ictdemy.springHotelManager.models.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public  String renderLogin() {

        return "pages/account/login";
    }

    @GetMapping("/register")
    public String renderRegister(@ModelAttribute("user") UserDTO user){

        return "pages/account/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "pages/account/register";
        }

        try {
            userService.create(user);
        } catch (DuplicateEmailException e){
            result.rejectValue("email", "error", "Email already in use.");
            return "pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "The password do not match.");
            result.rejectValue("confirmPassword", "error", "The password do not match.");
            return ("pages/account/register");
        }

        redirectAttributes.addFlashAttribute("toastMessage", "User was registered successfully!");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/";
    }

    @GetMapping("/remove")
    public String renderRemoveUser() {

        return "pages/account/remove";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) {
        Optional<UserEntity> user = userService.findByEmail(email);

        if (user.isEmpty()) {
            redirectAttributes.addFlashAttribute("toastMessage", "User not found");
            redirectAttributes.addFlashAttribute("toastType", "error");
            model.addAttribute("email", email);
            return "redirect:/account/remove";
        }

        userService.remove(email);
        redirectAttributes.addFlashAttribute("toastMessage", "User was deleted");
        redirectAttributes.addFlashAttribute("toastType", "error");
        return ("redirect:/account/remove");
    }

    @GetMapping("/index")
    public String renderUsers(Model model) {
        List<UserDTO> users = userService.getall();
        model.addAttribute("users", users);

        return "pages/account/index";
    }

    @GetMapping ("index/edit/{userId}")
    public String renderEditUser(@PathVariable("userId") Long userId, Model model) {
        Optional<UserDTO> userDTO = userService.findById(userId);
        if (userDTO.isEmpty()){
            model.addAttribute("error", "User not found");
            return "redirect:/account/index";
        }

        UserDTO user = userDTO.get();

        model.addAttribute("user", user);
        return "pages/account/edit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute("user") UserDTO user,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            userService.update(user);
            redirectAttributes.addFlashAttribute("toastMessage", "User updated successfully.");
            redirectAttributes.addFlashAttribute("toastType", "success");
        } catch (DuplicateEmailException e){
            result.rejectValue("email", "error", "Email already in use.");
            return "pages/account/edit";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "The password do not match.");
            result.rejectValue("confirmPassword", "error", "The password do not match.");
            return ("pages/account/edit");
        }

        return "redirect:/account/index";
    }



}
