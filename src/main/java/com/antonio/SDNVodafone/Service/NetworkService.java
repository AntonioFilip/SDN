package com.antonio.SDNVodafone.Service;

import com.antonio.SDNVodafone.Entities.City;
import com.antonio.SDNVodafone.Repositories.CityRepository;
import com.antonio.SDNVodafone.Entities.Network;
import com.antonio.SDNVodafone.Repositories.NetworkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NetworkService {

    private final CityRepository cityRepository;
    private final NetworkRepository networkRepository;

    public NetworkService(CityRepository cityRepository, NetworkRepository networkRepository) {
        this.cityRepository = cityRepository;
        this.networkRepository = networkRepository;
    }

    public List<Network> fetchRequestedNetworks(String address){
        return networkRepository.findAll().stream().map( currentNetwork -> currentNetwork.retrieveNetwork(address)).collect(Collectors.toList());
    }

    public City createInstance(City c1){
        selectNetworks(c1);
        return cityRepository.save(c1);
    }

    private void selectNetworks(City c1){
        c1.getRegionList().forEach(e -> {
            e.getNetworkList().forEach(o -> {
                o.setNetworkInfoList(o.getNetworkInfoList().stream().distinct()
                        .filter(obj -> obj.isValidIp())
                        .collect(Collectors.toList()));
            });
        });
    }
}

