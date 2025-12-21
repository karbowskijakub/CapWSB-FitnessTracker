package pl.wsb.fitnesstracker.user.internal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<User> findUsersOlderThanDate(LocalDate date) {
        return userRepository.findByBirthdateBefore(date);
    }

    @Override
    @Transactional
    public User updateUser(Long userId, User userUpdate) {
        log.info("Updating User with ID {}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User ID: " + userId));

        User updatedUser = new User(
                userUpdate.getFirstName() != null ? userUpdate.getFirstName() : existingUser.getFirstName(),
                userUpdate.getLastName() != null ? userUpdate.getLastName() : existingUser.getLastName(),
                userUpdate.getBirthdate() != null ? userUpdate.getBirthdate() : existingUser.getBirthdate(),
                userUpdate.getEmail() != null ? userUpdate.getEmail() : existingUser.getEmail()
        );

        userRepository.deleteById(userId);
        userRepository.flush();

        return userRepository.save(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }
}