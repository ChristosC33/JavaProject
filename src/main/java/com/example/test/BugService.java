package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



import java.util.Optional;

@Service
public class BugService {

    @Autowired
    private BugRepository bugRepository;

    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }

    public Bug updateBug(Long id, Bug bugDetails) {
        Optional<Bug> optionalBug = bugRepository.findById(id);
        if (optionalBug.isPresent()) {
            Bug bug = optionalBug.get();
            bug.setName(bugDetails.getName());
            bug.setDescription(bugDetails.getDescription());
            bug.setReporter(bugDetails.getReporter());
            bug.setDateAdded(bugDetails.getDateAdded());
            bug.setResolved(bugDetails.isResolved());
            bug.setDateResolved(bugDetails.getDateResolved());
            bug.setSeverity(bugDetails.getSeverity());
            return bugRepository.save(bug);
        }
        return null;
    }

    public Bug resolveBug(Long id) {
        Optional<Bug> optionalBug = bugRepository.findById(id);
        if (optionalBug.isPresent()) {
            Bug bug = optionalBug.get();
            bug.setResolved(true);
            bug.setDateResolved(LocalDateTime.now());
            return bugRepository.save(bug);
        }
        return null;
    }

    public List<Bug> listBugs(boolean resolved) {
        return bugRepository.findByResolved(resolved);
    }
}
