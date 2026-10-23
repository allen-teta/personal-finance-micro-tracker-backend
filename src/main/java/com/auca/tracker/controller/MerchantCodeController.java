package com.auca.tracker.controller;

import com.auca.tracker.model.MerchantCode;
import com.auca.tracker.service.MerchantCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/merchant-codes")
public class MerchantCodeController {
    private final MerchantCodeService service;

    public MerchantCodeController(MerchantCodeService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MerchantCode x) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(x));
    }

    @GetMapping("/all")
    public List<MerchantCode> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        Optional<MerchantCode> found = service.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The merchant code with that id does not exist");
        }
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody MerchantCode x) {
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
