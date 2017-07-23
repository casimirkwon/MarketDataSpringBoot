package kr.co.koscom.marketdata.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class); 

	public String execute(String urlstring) {
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();
		try {
			
			URL url = new URL(urlstring);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			logger.debug("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null)
				conn.disconnect();
		}

		return sb.toString();
	}
}
