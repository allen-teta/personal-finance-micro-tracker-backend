package com.auca.tracker.controller;

import com.auca.tracker.service.TransactionReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionReportController {
    private final TransactionReportService service;

    public TransactionReportController(TransactionReportService service) {
        this.service = service;
    }

    @GetMapping("/stats/by-status")
    public List<Object[]> statsByStatus() {
        return service.statsByStatus();
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelDay(@RequestParam UUID merchantId, @RequestParam String date) {
        return ResponseEntity.ok(service.cancelDay(merchantId, LocalDate.parse(date)));
    }
}
