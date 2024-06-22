package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.repository.BannedNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannedNamesService {
    @Autowired
    private BannedNamesRepository bannedNamesRepository;
    public boolean existInBannedNames(String name) {
        return bannedNamesRepository.existsInNamesArray(name);
    }
}
