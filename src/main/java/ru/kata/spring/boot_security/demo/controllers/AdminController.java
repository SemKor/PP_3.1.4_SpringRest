package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok(userService.showAllUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.showUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody @Valid User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        User user = userService.showUser(id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
