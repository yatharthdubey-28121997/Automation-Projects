package com.qa.managers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qa.global.GlobalParams;

public class PropertiesManager {
	private static Properties properties = new Properties();

	public Properties getProps() throws IOException {
		InputStream is = null;
		String propsFileName = GlobalParams.propertyFilePath;

		if (properties.isEmpty()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader (propsFileName));
				properties = new Properties();
				try {
					properties.load(reader);
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return properties;
	}
}
