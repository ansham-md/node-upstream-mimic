package com.amos.nodeupstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class NodeUpstreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeUpstreamApplication.class, args);
	}

}
