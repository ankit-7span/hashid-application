package com.spingboot.hashid.service;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
public class HashidsService {

    private final Hashids hashID;

    public HashidsService(Hashids hashids) {
        this.hashID = hashids;
    }

    public String encode(Long number) {
        return hashID.encode(number);
    }

    public long decode(String hash) {
        if (hashID.decode(hash).length>0){
            return hashID.decode(hash)[0];
        }else {
            throw new RuntimeException("No number found for given hash "+hash);
        }
    }
}
