package com.example.userservice.controller;

import com.example.userservice.dto.DeliveryPersonDTO;
import com.example.userservice.dto.InventoryKeeperDTO;
import com.example.userservice.dto.LoginDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.DeliveryPerson;
import com.example.userservice.entity.InventoryKeeper;
import com.example.userservice.entity.User;
import com.example.userservice.enums.UserRole;
import com.example.userservice.exception.NotFoundException;
import com.example.userservice.repository.DeliveryPersonRepository;
import com.example.userservice.repository.InventoryKeeperRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.security.JwtTokenProvider;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userservice;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private  UserRepository userRepository;


    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private InventoryKeeperRepository inventoryKeeperRepository;

    @Autowired
    public UserController(UserService userservice, JwtTokenProvider jwtTokenProvider) {
        this.userservice = userservice;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private boolean isValidEmail(String email) {
        //  email format validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidTelephone(String telephone) {
        //  telephone number format validation: exactly 10 digits
        String telephoneRegex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(telephoneRegex);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        // Password format validation: at least 5 characters, mix of uppercase, lowercase, and digits
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{5,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidFullName(String fullName) {
        //  name format validation: only letters
        String fullNameRegex = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(fullNameRegex);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    private boolean isValidAreaCode(String areaCode) {
        // Area code format validation: exactly 5 digits
        String areaCodeRegex = "^[0-9]{5}$";
        Pattern pattern = Pattern.compile(areaCodeRegex);
        Matcher matcher = pattern.matcher(areaCode);
        return matcher.matches();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        // Validate email format
        if (!isValidEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format");
        }

        // Validate telephone number format
        if (!isValidTelephone(userDTO.getTelephone())) {
            return ResponseEntity.badRequest().body("Invalid telephone number format. It should contain exactly 10 digits.");
        }

        // Set the default role to customer if it's not already set
        if (userDTO.getRole() == null) {
            userDTO.setRole(UserRole.CUSTOMER);
        }

        // Validate password format
        if (!isValidPassword(userDTO.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password format. It should be at least 5 characters long and contain a mix of uppercase letters, lowercase letters, and digits.");
        }

        // Validate full name format
        if (!isValidFullName(userDTO.getFullName())) {
            return ResponseEntity.badRequest().body("Invalid full name format. It should contain only letters.");
        }

        // Save the user
        User savedUser = userservice.saveUser(userDTO);

        return ResponseEntity.ok(savedUser);
    }

    //deliver person registration
    @PostMapping("/register_DelveryPerson")
    public ResponseEntity<?> createDelveryPerson(@RequestBody DeliveryPersonDTO deliveryPersonDTO) {

        // Validate email format
        if (!isValidEmail(deliveryPersonDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format");
        }

        // Validate telephone number format
        if (!isValidTelephone(deliveryPersonDTO.getTelephone())) {
            return ResponseEntity.badRequest().body("Invalid telephone number format. It should contain exactly 10 digits.");
        }

        // Validate area code format
        if (!isValidAreaCode(deliveryPersonDTO.getAreaCode())) {
            return ResponseEntity.badRequest().body("Invalid area code format. It should contain exactly 5 digits.");
        }

        if (deliveryPersonDTO.getRole() == null) {
            deliveryPersonDTO.setRole(UserRole.DELIVERY_PERSON);
        }

        // Validate password format
        if (!isValidPassword(deliveryPersonDTO.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password format. It should be at least 5 characters long and contain a mix of uppercase letters, lowercase letters, and digits.");
        }

        // Validate full name format
        if (!isValidFullName(deliveryPersonDTO.getFullName())) {
            return ResponseEntity.badRequest().body("Invalid full name format. It should contain only letters.");
        }

        // Save the user
        DeliveryPerson savedDeliveryPerson = userservice.saveDelveryPerson(deliveryPersonDTO);

        return ResponseEntity.ok(savedDeliveryPerson);
    }


    //Inventory Keeper registration
    @PostMapping("/register_InventoryKeeper")
    public ResponseEntity<?> createInventoryKeeper(@Valid @RequestBody InventoryKeeperDTO inventoryKeeperDTO) {

        // Validate email format
        if (!isValidEmail(inventoryKeeperDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format");
        }

        // Validate telephone number format
        if (!isValidTelephone(inventoryKeeperDTO.getTelephone())) {
            return ResponseEntity.badRequest().body("Invalid telephone number format. It should contain exactly 10 digits.");
        }

        if (inventoryKeeperDTO.getRole() == null) {
            inventoryKeeperDTO.setRole(UserRole.INVENTORY_MANAGER);
        }

        // Validate password format
        if (!isValidPassword(inventoryKeeperDTO.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password format. It should be at least 5 characters long and contain a mix of uppercase letters, lowercase letters, and digits.");
        }

        // Validate full name format
        if (!isValidFullName(inventoryKeeperDTO.getFullName())) {
            return ResponseEntity.badRequest().body("Invalid full name format. It should contain only letters.");
        }

        // Save the user
        InventoryKeeper savedInventoryKeeper = userservice.saveInventoryKeeper(inventoryKeeperDTO);

        return ResponseEntity.ok(savedInventoryKeeper);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Object authenticatedUser = userservice.loginUser(loginDTO.getEmail(), loginDTO.getPassword());

        if (authenticatedUser != null) {
            if (authenticatedUser instanceof User) {
                String token = jwtTokenProvider.generateToken(((User) authenticatedUser).getEmail(), String.valueOf(((User) authenticatedUser).getRole()),((User) authenticatedUser).getId());
                return ResponseEntity.ok(token);
            } else if (authenticatedUser instanceof InventoryKeeper) {
                String token = jwtTokenProvider.generateToken(((InventoryKeeper) authenticatedUser).getEmail(), String.valueOf(((InventoryKeeper) authenticatedUser).getRole()),((InventoryKeeper) authenticatedUser).getId());
                return ResponseEntity.ok(token);
            } else if (authenticatedUser instanceof DeliveryPerson) {
                String token = jwtTokenProvider.generateToken(((DeliveryPerson) authenticatedUser).getEmail(), String.valueOf(((DeliveryPerson) authenticatedUser).getRole()),((DeliveryPerson) authenticatedUser).getId());
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.badRequest().body("Invalid email or password");
    }


    @GetMapping("/validateToken/{token}")
    public ResponseEntity<String> validateTokenAndGetEmail(@PathVariable String token) {
        boolean isValid = jwtTokenProvider.validateToken(token);
        if (isValid) {
            String email = jwtTokenProvider.getEmailFromToken(token);
            return ResponseEntity.ok("Valid Token. Email: " + email);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }

    //delete cutomer record  with given id
    @DeleteMapping("/deleteCustomer/{id}")
    String deleteCustomer(@PathVariable Long id){
        if (!userRepository.existsById(id)){
            throw new NotFoundException(("Customer not found with id: " + id));
        }
        userRepository.deleteById(id);
        return "Customer with id " +id+ "has been deleted";
    }

    //delete delivery person record  with given id
    @DeleteMapping("/deleteDeliveryPerson/{id}")
    String deleteDeliveryPerson(@PathVariable Long id){
        if (!deliveryPersonRepository.existsById(id)){
            throw new NotFoundException(("Delivery Person not found with id: " + id));
        }
        deliveryPersonRepository.deleteById(id);
        return "Delivery Person with id " +id+ "has been deleted";
    }

    //delete Inventory Keeper record  with given id
    @DeleteMapping("/deleteInventoryKeeper/{id}")
    String deleteInventoryKeeper(@PathVariable Long id){
        if (!inventoryKeeperRepository.existsById(id)){
            throw new NotFoundException(("Inventory Keeper not found with id: " + id));
        }
        inventoryKeeperRepository.deleteById(id);
        return "Inventory Keeper with id " +id+ "has been deleted";
    }

    //update customer
        /*
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User updatedUser = userservice.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
 */

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userservice.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }


    //update delivery person
    @PutMapping("/updateDeliveryPerson/{id}")
    public ResponseEntity<DeliveryPerson> updateDeliveryPerson(@PathVariable("id") Long id, @RequestBody DeliveryPersonDTO deliveryPersonDTO) {
        DeliveryPerson updatedDeliveryPerson = userservice.updateDeliveryPerson(id, deliveryPersonDTO);
        return ResponseEntity.ok(updatedDeliveryPerson);
    }

    //update inventory keeper
    @PutMapping("/updateInventoryKeeper/{id}")
    public ResponseEntity<InventoryKeeper> updateInventoryKeeper(@PathVariable("id") Long id, @RequestBody InventoryKeeperDTO inventoryKeeperDTO) {
        InventoryKeeper updatedInventoryKeeper = userservice.updateInventoryKeeper(id, inventoryKeeperDTO);
        return ResponseEntity.ok(updatedInventoryKeeper);
    }


}

