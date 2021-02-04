package com.antonio.SDNVodafone;

import com.antonio.SDNVodafone.Config.NetworkController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

	//SmokeTest - Asserting that the context creates the controller

	@Autowired
	private NetworkController controller;

	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
