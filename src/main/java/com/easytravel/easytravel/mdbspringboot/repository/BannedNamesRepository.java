package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.BannedNames;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannedNamesRepository extends MongoRepository <BannedNames, Integer>{
    @Query("{ 'names' : ?0 }")
    List<BannedNames> findByNameInNamesArray(String name);

    default boolean existsInNamesArray(String name) {
        return !findByNameInNamesArray(name).isEmpty();
    }
}

