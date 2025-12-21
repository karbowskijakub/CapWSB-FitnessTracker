package pl.wsb.fitnesstracker.user.internal;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving, creating, updating, and deleting users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Retrieves a list of all users in the system containing full details.
     *
     * @return a list of {@link UserDto} representing all users.
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a simplified list of all users, containing only basic information.
     *
     * @return a list of {@link UserSimpleDto} representing all users with limited details.
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    /**
     * Retrieves a specific user by their unique identifier.
     *
     * @param id the unique ID of the user to retrieve.
     * @return the {@link UserDto} corresponding to the provided ID.
     * @throws IllegalArgumentException if the user with the given ID is not found.
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Error id: " + id));
    }

    /**
     * Creates a new user in the system.
     *
     * @param userDto the data transfer object containing the new user's information.
     * @return the created {@link UserDto} with generated fields (e.g., ID).
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param userId the unique ID of the user to delete.
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Searches for users based on their email address.
     *
     * @param email the email address (or fragment) to search for.
     * @return a list of {@link UserEmailDto} matching the search criteria.
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userService.findUsersByEmail(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Retrieves users who are older than the specified date.
     *
     * @param time the cutoff date; users born before this date will be returned.
     * @return a list of {@link UserDto} representing users older than the provided date.
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userService.findUsersOlderThanDate(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Updates an existing user's information.
     *
     * @param userId  the unique ID of the user to update.
     * @param userDto the data transfer object containing the updated user information.
     * @return the updated {@link UserDto}.
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(userId, user);
        return userMapper.toDto(updatedUser);
    }
}