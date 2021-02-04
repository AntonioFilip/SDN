package com.antonio.SDNVodafone.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String regionName;
    private int securityLevel;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "network_id")
    private List<Network> networkList;

    public Region() {
    }

    public Region(String regionName,int securityLevel) {
        this.regionName = regionName;
        this.securityLevel = securityLevel;
        this.networkList = new ArrayList<>();
    }

    public String getRegionName() {
        return regionName;
    }

    public List<Network> getNetworkList() {
        return networkList;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    public boolean addNetwork(String subnetMask) {
        return networkList.add(new Network(subnetMask));
    }

    public boolean addNetwork(Network network) {
        return networkList.add(network);
    }

    public String findNetwork(String subnetMask) {
        for (Network currentNetwork : networkList) {
            if (subnetMask.equals(currentNetwork.getSubnetMask())) {
                return subnetMask;
            }
        }
        return null;
    }

/*   public List<Network> retrieveNetworks(String address){
        List<Network> targetNetworks = new ArrayList<>();
        targetNetworks = this.networkList.stream().filter( currentNetwork ->
            currentNetwork.findNetwork(address)==true
        ).collect(Collectors.toList());
        return targetNetworks;
    }*/
}
