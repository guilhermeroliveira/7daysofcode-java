package br.com.guilhermeroliveira.alura.sevendaysofcode.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

import javax.naming.ServiceUnavailableException;

public class IMDbHttpService {
	private static final String BASE_URL = "https://imdb-api.com/pt-br/API/";
	private static final String API_KEY;
	private static final HttpClient httpClient = HttpClient.newHttpClient();

	static {
		String propertiesFileName = "config.properties";
		String key = "";

		try (InputStream is = IMDbHttpService.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
			Properties properties = new Properties();

			if (is != null) {
				properties.load(is);
			} else {
				throw new FileNotFoundException("File '" + propertiesFileName + "' not found in the classpath");
			}

			key = (String) properties.get("IMDB_API_KEY");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		API_KEY = key;
	}

	public static String getTop250Movies() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(BASE_URL + "Top250Movies/" + API_KEY))
				.GET()
				.build();

		try {
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

			return response.body();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		throw new RuntimeException(new ServiceUnavailableException("Error on external service: IMDb"));
	}
}
