package com.auca.tracker.controller;

import com.auca.tracker.model.ReceiptLine;
import com.auca.tracker.service.ReceiptLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/receipt-lines")
public class ReceiptLineController {
    private final ReceiptLineService service;

    public ReceiptLineController(ReceiptLineService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID transactionId, @RequestBody ReceiptLine x) {
        String message = service.save(transactionId, x);
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.contains("only for REVIEWED")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<ReceiptLine> all() {
        return service.getAll();
    }

    @GetMapping("/account-holder/{accountNumber}")
    public List<ReceiptLine> byAccountHolder(@PathVariable String accountNumber) {
        return service.getByAccountNumber(accountNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        Optional<ReceiptLine> found = service.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The receipt line with that id does not exist");
        }
        return ResponseEntity.ok(found.get());
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
