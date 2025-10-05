package br.com.snowproject.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO create(UserCreateRequestDTO userDTO) {

        var userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setName(userDTO.getName());
        userModel.setPassword(userDTO.getPassword());

        this.validateUser(userModel);

        String hashedPassword = this.hashPassword(userModel.getPassword());
        userModel.setPassword(hashedPassword);

        var userSaved = this.userRepository.save(userModel);

        return new UserResponseDTO(userSaved);
    }

    private void validateUser(UserModel userModel) {
        var existingUser = this.userRepository.findByUsername(userModel.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Usuário já existe");
        }
    }

    private String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}