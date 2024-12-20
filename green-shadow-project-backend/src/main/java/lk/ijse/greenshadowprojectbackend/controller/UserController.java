package lk.ijse.greenshadowprojectbackend.controller;

import lk.ijse.greenshadowprojectbackend.dto.impl.StaffDto;
import lk.ijse.greenshadowprojectbackend.dto.impl.UserDto;
import lk.ijse.greenshadowprojectbackend.service.StaffService;
import lk.ijse.greenshadowprojectbackend.service.UserService;
import lk.ijse.greenshadowprojectbackend.util.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StaffService staffService;

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        try {
            // Check if a staff member exists with the given email
            Optional<StaffDto> existingStaff = staffService.findByEmail(userDto.getEmail());
            if (!existingStaff.isPresent()) {
                // Save new staff member if none exists
                StaffDto newStaff = new StaffDto();
                newStaff.setEmail(userDto.getEmail());
                newStaff.setRole(userDto.getRole());
                // Additional staff fields, if needed
                newStaff = staffService.save(newStaff);
                // Set the saved staff ID to the user DTO
                userDto.setStaffId(newStaff.getStaffId());
            } else {
                // Link to the existing staff member
                userDto.setStaffId(existingStaff.get().getStaffId());
            }
            // Save the user
            userService.save(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable("email") String email){
        //validate email
        if (!Pattern.matches(String.valueOf(Regex.getEmailPattern()),email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid email format
        }
        // Fetch user by email
        Optional<UserDto> userDtoOptional = userService.findByEmail(email);
        if (userDtoOptional.isPresent()) {
            // Get the user's ID
            String userId = userDtoOptional.get().getId();
            userService.delete(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User with email not found
        }
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("email") String email, @RequestBody UserDto userDto) {
        // Validate email format
        if (!Pattern.matches(String.valueOf(Regex.getEmailPattern()), email)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid email format
        }
        // Check if the user exists
        Optional<UserDto> userDtoOptional = userService.findByEmail(email);
        if (userDtoOptional.isPresent()) {
            UserDto user = userDtoOptional.get();
            // Update fields in userEntity based on userDto
            user.setEmail(userDto.getEmail());  // Assuming email is allowed to be updated
            user.setPassword(userDto.getPassword()); // Update password
            user.setRole(userDto.getRole()); // Update role if necessary
            // Save the updated entity back to the database
            UserDto updatedUser = userService.update(user.getId(),user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // Success
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User with email not found
        }
    }
}