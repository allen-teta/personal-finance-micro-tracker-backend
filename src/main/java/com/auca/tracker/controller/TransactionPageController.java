package com.auca.tracker.controller;

import com.auca.tracker.service.TransactionPageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionPageController {
    private final TransactionPageService service;

    public TransactionPageController(TransactionPageService service) {
        this.service = service;
    }

    @GetMapping("/page")
    public ResponseEntity<Object> page(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "transactionDate,desc") String sort) {
        if (page < 0 || size < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("page must be 0 or more and size must be at least 1");
        }
        String[] parts = sort.split(",");
        List<String> allowed = List.of("transactionDate", "amount", "status", "rawLine");
        if (!allowed.contains(parts[0])) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot sort by " + parts[0]);
        }
        Sort.Direction direction = parts.length > 1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, parts[0]));
        return ResponseEntity.ok(service.getPage(pageable));
    }
}
