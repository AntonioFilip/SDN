package com.antonio.SDNVodafone.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "region_id")

    private List<Region> regionList;

    public City(String cityName) {
        this.cityName = cityName;
        this.regionList = new ArrayList<>();
    }

    public City() {
    }

    public String getCityName() {
        return cityName;
    }

    public List<Region> getRegionList() {
        return regionList;
    }

    public boolean addRegion(String name, int securityLevel) {
        return regionList.add(new Region(name,securityLevel));
    }

    public boolean addRegion(Region region) {
        return regionList.add(region);
    }

/*    public List<Network> retrieveNetworks(String address) {
        List<Network> networkList = new ArrayList<>();
        for(Region currentRegion : this.regionList){
            if(currentRegion.retrieveNetworks(address)!=null){
                currentRegion.retrieveNetworks(address).stream().map(network -> networkList.add(network));
            }
        }
        return networkList;
    }*/
}
