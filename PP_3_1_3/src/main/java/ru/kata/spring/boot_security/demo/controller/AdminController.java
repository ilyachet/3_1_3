package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("aUser", user);
        model.addAttribute("userList", userService.getAllUser());
        model.addAttribute("newUser", new User());
        return "index";
    }

    @GetMapping("/add")
    public String newUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.getAllRole());
        return "/new";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute("newUser") User user) {
//        if (bindingResult.hasErrors()) {
//            return "/new";
//        }

        userService.save(user, null);
        return "redirect:/admin";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @ModelAttribute
    public void addRoles(Model model) {
        model.addAttribute("roles", userService.getAllRole());
    }
}
