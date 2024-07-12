package com.example.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    @Autowired
    private BugService bugService;

    @PostMapping
    public ResponseEntity<Bug> addBug(@RequestBody Bug bug) {
        return new ResponseEntity<>(bugService.addBug(bug), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bug> updateBug(@PathVariable Long id, @RequestBody Bug bugDetails) {
        return ResponseEntity.ok(bugService.updateBug(id, bugDetails));
    }

    @PutMapping("/resolve/{id}")
    public ResponseEntity<Bug> resolveBug(@PathVariable Long id) {
        return ResponseEntity.ok(bugService.resolveBug(id));
    }

    @GetMapping
    public List<Bug> listBugs(@RequestParam(required = false) Boolean resolved) {
        if (resolved == null) {
            return bugService.listBugs(false);
        }
        return bugService.listBugs(resolved);
    }

    // Additional endpoints for advanced features
}

