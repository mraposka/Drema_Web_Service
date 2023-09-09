package com.drema.service.controller;

import com.drema.service.model.Ruya_User_Model;
import com.drema.service.model.Ruya_Tabir_Model;
import com.drema.service.model.Ruya_User_Token_Model;
import com.drema.service.repository.RuyaUserRepository;
import com.drema.service.repository.RuyaTabirRepository;
import com.drema.service.repository.RuyaUserTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private RuyaUserRepository ruyaUserRepository;

    @Autowired
    private RuyaTabirRepository ruyaTabirRepository;

    @Autowired
    private RuyaUserTokenRepository ruyaUserTokenRepository;

    // RuyaUser için CRUD işlemleri
    @GetMapping(path = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Ruya_User_Model> getAllUsers() {
        return ruyaUserRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Ruya_User_Model> getUserById(@PathVariable Long id) {
        Ruya_User_Model user = ruyaUserRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/login")
    public ResponseEntity<Ruya_User_Model> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<Ruya_User_Model> user = ruyaUserRepository.findByUserNameAndUserPass(loginRequest.getUserName(),
                loginRequest.getUserPass());

        return user.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/user")
    public Ruya_User_Model createUser(@RequestBody Ruya_User_Model newUser) {
        return ruyaUserRepository.save(newUser);
    }

    @PutMapping("/user/{id}")
    public Ruya_User_Model updateUser(@PathVariable Long id, @RequestBody Ruya_User_Model updatedUser) {
        if (!ruyaUserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        updatedUser.setUserId(id);
        return ruyaUserRepository.save(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!ruyaUserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        ruyaUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // RuyaTabir için CRUD işlemleri
    @GetMapping("/tabir")
    public List<Ruya_Tabir_Model> getAllTabirler() {
        return ruyaTabirRepository.findAll();
    }

    @GetMapping("/tabir/{id}")
    public ResponseEntity<Ruya_Tabir_Model> getTabirById(@PathVariable Long id) {
        Ruya_Tabir_Model tabir = ruyaTabirRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tabir not found"));
        return ResponseEntity.ok(tabir);
    }

    @GetMapping("/tabir/user/{userId}")
    public ResponseEntity<List<Ruya_Tabir_Model>> getTabirByUserId(@PathVariable Long userId) {
        List<Ruya_Tabir_Model> tabirList = ruyaTabirRepository.findByUserUserId(userId);
        if (tabirList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tabirs found for this user");
        }
        return ResponseEntity.ok(tabirList);
    }

    @PostMapping("/tabir")
    public ResponseEntity<Ruya_Tabir_Model> createTabir(@RequestBody Ruya_Tabir_Model tabir, @RequestParam Long userId) {
        Ruya_User_Model user = ruyaUserRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        tabir.setUser(user);
        Ruya_Tabir_Model savedTabir = ruyaTabirRepository.save(tabir);
        return ResponseEntity.ok(savedTabir);
    }

    @PutMapping("/tabir/{id}")
    public ResponseEntity<Ruya_Tabir_Model> updateTabir(@PathVariable Long id, @RequestBody Ruya_Tabir_Model updatedTabir) {
        if (!ruyaTabirRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tabir not found");
        }
        updatedTabir.setRuyaId(id);
        Ruya_Tabir_Model savedTabir = ruyaTabirRepository.save(updatedTabir);
        return ResponseEntity.ok(savedTabir);
    }

    @DeleteMapping("/tabir/{id}")
    public ResponseEntity<Void> deleteTabir(@PathVariable Long id) {
        if (!ruyaTabirRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tabir not found");
        }
        ruyaTabirRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // RuyaUserToken için CRUD işlemleri
    @GetMapping("/tokens")
    public List<Ruya_User_Token_Model> getAllTokens() {
        return ruyaUserTokenRepository.findAll();
    }

    @GetMapping("/tokens/{id}")
    public ResponseEntity<Ruya_User_Token_Model> getTokenById(@PathVariable Long id) {
        Ruya_User_Token_Model token = ruyaUserTokenRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found"));
        return ResponseEntity.ok(token);
    }

    @PostMapping("/tokens")
    public ResponseEntity<Ruya_User_Token_Model> createToken(@RequestBody Ruya_User_Token_Model token) {
        if (token.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId cannot be null!");
        }
        Ruya_User_Token_Model savedToken = ruyaUserTokenRepository.save(token);
        return ResponseEntity.ok(savedToken);
    }

    @PutMapping("/tokens/{id}")
    public ResponseEntity<Ruya_User_Token_Model> updateToken(@PathVariable Long id, @RequestBody Ruya_User_Token_Model updatedToken) {
        if (!ruyaUserTokenRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }
        updatedToken.setUserId(id);
        Ruya_User_Token_Model savedToken = ruyaUserTokenRepository.save(updatedToken);
        return ResponseEntity.ok(savedToken);
    }

    @DeleteMapping("/tokens/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable Long id) {
        if (!ruyaUserTokenRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found");
        }
        ruyaUserTokenRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

class LoginRequest {
    private String userName;
    private String userPass;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	} 
}
