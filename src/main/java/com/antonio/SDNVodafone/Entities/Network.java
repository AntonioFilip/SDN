package com.antonio.SDNVodafone.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Network {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subnetMask;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "network_info_id")
    private List<NetworkInfo> networkInfoList;

    public Network() {
    }

    public Network(String subnetMask) {
        this.subnetMask = subnetMask;
        this.networkInfoList = new ArrayList<>();
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public List<NetworkInfo> getNetworkInfoList() {
        return networkInfoList;
    }

    public void setNetworkInfoList(List<NetworkInfo> networkInfoList) {
        this.networkInfoList = networkInfoList;
    }

    public boolean addNetworkInfo(String address, boolean available, String timeStamp) {
        NetworkInfo info = new NetworkInfo(address, available, timeStamp);
        if(info.isValidIp()) {
            return networkInfoList.add(info);
        }
        return false;
    }

    public boolean addNetworkInfo(NetworkInfo networkInfo) {
        if(networkInfo.isValidIp()) {
            return networkInfoList.add(networkInfo);
        }
        return false;
    }

    public Network retrieveNetwork(String address){
            for(NetworkInfo currentNetworkInfo : this.networkInfoList){
                if(currentNetworkInfo.getAddress().equals(address)){
                    return this;
                }
            }
            return null;
    }

/*    public boolean findNetwork(String address){
        List<NetworkInfo> elements = null;
        elements = this.networkInfoList.stream().filter( networkInfoInstance ->
                networkInfoInstance.getAddress().equals(address)
        ).collect(Collectors.toList());
        if(elements!=null){
            return true;
        }
        return false;
    }*/


}
