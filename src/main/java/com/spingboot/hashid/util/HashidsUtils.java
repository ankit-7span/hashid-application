package com.spingboot.hashid.util;

import org.hashids.Hashids;

import java.util.List;

public class HashidsUtils {

    private static final Hashids hashids = new Hashids("k3G7QAe51FCsPW92uEOyq4Bg6Sp8YzVTmnU0liwDdHXLajZrfxNhobJIRcMvKt", 6,"0123456789abcdefghijklmnopqrstuvwxyz");

    private HashidsUtils() {
        // Private constructor to prevent instantiation
    }

    public static String encode(Long number) {
        return hashids.encode(number);
    }

    public static long decode(String hash) {
        if (hashids.decode(hash).length>0){
            return hashids.decode(hash)[0];
        }else {
            throw new RuntimeException("No value found for given hashId "+hash);
        }
    }
}
