package com.spingboot.hashid.request;


import com.spingboot.hashid.hash.Hashids;

public record StudentRequest(@Hashids(salt = "abc", minHashLength = 8, alphabet = "abcdefghijklmnopqrstuvwxyz") Long id) {
}
