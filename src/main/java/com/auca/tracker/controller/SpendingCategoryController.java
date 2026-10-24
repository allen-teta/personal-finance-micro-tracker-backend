package com.auca.tracker.controller;

import com.auca.tracker.model.SpendingCategory;
import com.auca.tracker.service.SpendingCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/spending-categories")
public class SpendingCategoryController {
    private final SpendingCategoryService service;

    public SpendingCategoryController(SpendingCategoryService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SpendingCategory x) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(x));
    }

    @GetMapping("/all")
    public List<SpendingCategory> all() {
        return service.getAll();
    }

    @GetMapping("/unused")
    public List<SpendingCategory> unused() {
        return service.getUnused();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {
        Optional<SpendingCategory> found = service.getById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The spending category with that id does not exist");
        }
        return ResponseEntity.ok(found.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody SpendingCategory x) {
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
