package com.bootcampstorageult.bootcampstorageult.controller;

import com.bootcampstorageult.bootcampstorageult.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    // Muestra el formulario de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String registerForm() {
        return "register";  // Retorna la vista del formulario de registro
    }

    // Maneja el registro del usuario
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, @RequestParam String email) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";

        if (!password.matches(regex)) {
            return "redirect:/register?error=true";  // Redirige con un error si las contraseñas no coinciden
        }
        // Verifica si las contraseñas coinciden
        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error=true";  // Redirige con un error si las contraseñas no coinciden
        }

        // Llama al servicio para crear el usuario
        userService.createUser(username, password, email);

        // Redirige al login después de registrar al usuario
        return "redirect:/login";
    }

    // Página de bienvenida, donde se muestra el nombre del usuario
    @GetMapping("/welcome")
    public String welcome(Model model) {
        // Obtener el usuario autenticado
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        // Pasar el nombre de usuario al modelo para mostrarlo en la vista
        model.addAttribute("username", username);

        // Listar los documentos del usuario
        return "welcome"; // La vista de bienvenida mostrará los documentos ya asociados
    }
}
