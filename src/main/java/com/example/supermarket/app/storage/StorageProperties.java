package com.example.supermarket.app.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Service
@ConfigurationProperties("storage")
public class StorageProperties {
	
	/**
	 * Folder location for storing files
	 */
	private String location = "upload-dir";
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
}
