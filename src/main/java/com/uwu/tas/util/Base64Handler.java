package com.uwu.tas.util;

import com.uwu.tas.exception.CustomServiceException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Base64Handler {

    public byte[] getByteArrayFromBase64(String base64File) {
        if (base64File == null || !base64File.startsWith("data:"))
            throw new CustomServiceException("Invalid base64 data.");
        String b64 = base64File.split(",", 2)[1];
        byte[] file = Base64.getDecoder().decode(b64.getBytes(StandardCharsets.UTF_8));
        if (file.length > 15000000) throw new CustomServiceException("File size is larger than 15Mb!");
        return file;
    }
}
