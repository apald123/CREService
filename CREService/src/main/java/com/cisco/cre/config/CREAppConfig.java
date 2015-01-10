package com.cisco.cre.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:Elasticsearch.properties")
public class CREAppConfig {
	
	@Autowired
	private static Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public static String getProperty(String key) {
		if (env == null) return "****************************";
		return env.getProperty(key);
	}
}

/*
public class CREAppConfig extends PropertyPlaceholderConfigurer  {

	private static Map<String, String> propertyMap = null;

    @Override
    public void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        propertyMap = new HashMap();
        for (Entry entry : props.entrySet()) {
            propertyMap.put((String) entry.getKey(), (String) entry.getValue());
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, String> getPropertys() {
               System.out.println("Inside configproeperty");
        return propertyMap;
    }

}
*/