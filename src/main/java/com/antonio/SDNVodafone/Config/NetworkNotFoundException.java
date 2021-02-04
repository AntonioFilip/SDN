package com.antonio.SDNVodafone.Config;

public class NetworkNotFoundException extends RuntimeException{

    NetworkNotFoundException(String address) {
        super("Network" + address + " not found" );
    }
}
