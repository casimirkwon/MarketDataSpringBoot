package kr.co.koscom.marketdata.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class); 

	public String execute(String urlstring) {
		HttpURLConnection conn = null;
		StringBuilder sb = new StringBuilder();
		try {
			StringBuilder urlBuilder = new StringBuilder(
					"https://sandbox-apigw.koscom.co.kr/v2/market/stocks/{marketcode}/{issuecode}/price"
							.replace("{marketcode}", URLEncoder.encode("kospi", "UTF-8"))
							.replace("{issuecode}", URLEncoder.encode("005930", "UTF-8")));
			urlBuilder.append("?");
			urlBuilder.append(URLEncoder.encode("apikey", "UTF-8") + "="
					+ URLEncoder.encode("l7xx230ef2235e34448c982eb192ac98e206", "UTF-8"));
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
