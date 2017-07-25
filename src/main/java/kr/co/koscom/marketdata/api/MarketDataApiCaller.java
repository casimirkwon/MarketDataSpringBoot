package kr.co.koscom.marketdata.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.model.Debt;
import kr.co.koscom.marketdata.model.Price;
import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class MarketDataApiCaller {

	private static String URI_PREFIX = "https://sandbox-apigw.koscom.co.kr/v2/market/stocks/kospi/";
	private static String URI_POSTFIX = "/price";
	
	private static String APIKEY = "l7xx230ef2235e34448c982eb192ac98e206";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public Price getPrice(String issueCode) {
		
		try {
			String jsonStr = httpClientUtil.execute(URI_PREFIX + issueCode + URI_POSTFIX + "?apikey=" + APIKEY);
			JsonNode node = objectMapper.readTree(jsonStr);
			
			Price price = objectMapper.readValue(node.findValue("result").toString(), Price.class);
			
			return price;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Debt getDebt(String issueCode) {
		// TODO [실습 3-04] 종목 코드를 입력 받아 Fabot의 재무정보 API를 이용하여 해당 종목(기업)의 부채율 정보를 화면에 json형태로 출력한다.
		return null;
	}
}
