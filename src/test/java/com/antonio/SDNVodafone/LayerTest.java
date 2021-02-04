package com.antonio.SDNVodafone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.antonio.SDNVodafone.Entities.City;
import com.antonio.SDNVodafone.Entities.Network;
import com.antonio.SDNVodafone.Entities.NetworkInfo;
import com.antonio.SDNVodafone.Entities.Region;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // entire context instantiated without starting the server
public class LayerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper jackson = new ObjectMapper();

    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/getNetworks").param("address", "192.168.0.0/24")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void postTest() throws Exception {
        this.mockMvc.perform(post("/insertNetwork").contentType(MediaType.APPLICATION_JSON).content(jackson.writeValueAsString(aCity()))).andDo(print()).andExpect(status().isOk());
    }

    private City aCity(){
        NetworkInfo x1 = new NetworkInfo("255.255.255.0", true, "30/01/20 17:00:00");
        NetworkInfo x2 = new NetworkInfo("192.168.0.4", true, "30/01/20 17:00:00");
        NetworkInfo x3 = new NetworkInfo("192.168.0.2", false, "30/01/20 17:00:00");

        Network y1 = new Network("192.168.0.0/24");
        Network y2 = new Network("192.168.10.0/24");

        y1.addNetworkInfo(x1);
        y1.addNetworkInfo(x2);
        y2.addNetworkInfo(x3);
        y2.addNetworkInfo(x1);

        Region z1 = new Region("LON-1", 1);
        z1.addNetwork(y1);
        z1.addNetwork(y2);
        City c1 = new City("London");
        c1.addRegion(z1);

        return c1;
    }
}
