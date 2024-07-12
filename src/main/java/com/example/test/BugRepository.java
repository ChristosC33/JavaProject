package com.example.test;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface BugRepository extends MongoRepository<Bug, Long> {
    List<Bug> findByResolved(boolean resolved);
}

