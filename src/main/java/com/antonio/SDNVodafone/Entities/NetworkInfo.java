package com.antonio.SDNVodafone.Entities;

import org.apache.http.conn.util.InetAddressUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class NetworkInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private boolean available;
    private String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    public NetworkInfo() {
    }

    public NetworkInfo(String address, boolean available, String timeStamp) {
        this.address = address;
        this.available = available;
        this.timeStamp = timeStamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isValidIp() {
        return InetAddressUtils.isIPv4Address(this.address) || InetAddressUtils.isIPv6Address(this.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.address, this.available, this.timeStamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkInfo)) {
            return false;
        }

        NetworkInfo network = (NetworkInfo) obj;
        return Objects.equals(this.address, network.address) && Objects.equals(this.available, network.available)
                && Objects.equals(this.timeStamp, network.timeStamp);
    }

    @Override
    public String toString() {
        return "Network{" + "address=" + this.address + ", available='" + this.available + '\'' + ", last_used='" + this.timeStamp + '\'' + '}';
    }
}
