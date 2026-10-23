package com.auca.tracker.controller;

import com.auca.tracker.model.AccountHolder;
import com.auca.tracker.service.AccountHolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/account-holders")
public class AccountHolderController {
    private final AccountHolderService service;

    public AccountHolderController(AccountHolderService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AccountHolder x) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(x));
    }

    @GetMapping("/all")
    public List<AccountHolder> all() {
        return service.getAll();
    }

    @GetMapping("/by-last-name")
    public List<AccountHolder> byLastName(@RequestParam String lastName) {
        return service.getByLastName(lastName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        Optional<AccountHolder> found = service.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The account holder with that id does not exist");
        }
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody AccountHolder x) {
        String message = service.update(id, x);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = service.delete(id);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
}
