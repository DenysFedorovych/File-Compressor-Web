package knu.project.filecompressorweb.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import knu.project.filecompressorweb.api.model.UserModel;
import knu.project.filecompressorweb.domain.entity.User;
import knu.project.filecompressorweb.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserModel registerUser(UserModel userModel) {
        // Add logic to hash the password before saving
        User converted = objectMapper.convertValue(userModel, User.class);
        converted = userRepository.save(converted);
        userModel = objectMapper.convertValue(converted, UserModel.class);
        return userModel;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<Long> loginUser(String username, String password) {
        Optional<User> converted = userRepository.findByUsernameAndPassword(username, password);
        return converted.map(User::getId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

