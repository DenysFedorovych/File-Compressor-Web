package knu.project.filecompressorweb.api.controllers;

import knu.project.filecompressorweb.api.model.UserModel;
import knu.project.filecompressorweb.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel userModel) {
        UserModel registeredUserModel = userService.registerUser(userModel);
        return ResponseEntity.ok(registeredUserModel);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> loginUser(@RequestBody UserModel userModel) {
        Optional<Long> loggedInUserId = userService.loginUser(userModel.getUsername(), userModel.getPassword());
        return loggedInUserId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}

