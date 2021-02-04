package com.antonio.SDNVodafone.Config;

import com.antonio.SDNVodafone.Entities.City;
import com.antonio.SDNVodafone.Entities.Network;
import com.antonio.SDNVodafone.Service.NetworkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NetworkController {
    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @GetMapping("/getNetworks")
    public List<Network> getNetworks(@RequestParam String address){

        return networkService.fetchRequestedNetworks(address);
    }

    @PostMapping("/insertNetwork")
    public City newNetwork(@RequestBody City newCity){
        return networkService.createInstance(newCity);
    }

}
