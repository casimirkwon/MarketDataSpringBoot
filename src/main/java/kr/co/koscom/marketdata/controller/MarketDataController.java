package kr.co.koscom.marketdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.koscom.marketdata.api.MarketDataApiCaller;
import kr.co.koscom.marketdata.model.Debt;
import kr.co.koscom.marketdata.model.Price;

@Controller
public class MarketDataController {
	
	@Autowired
	private MarketDataApiCaller marketDataApiCaller;
	
    @RequestMapping(path = "/marketdata/price/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody Price priceJson(@PathVariable String issueCode) {
        return marketDataApiCaller.getPrice(issueCode);
    }
    
    
    @RequestMapping(path = "/marketdata/graph/price/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String priceJson(@PathVariable String issueCode, Model model) {
        
    	model.addAttribute("issueCode", issueCode);
    	
    	// return view
    	return "graph";
    }

    
    // TODO [실습 3-04] 종목 코드를 입력 받아 Fabot의 재무정보 API를 이용하여 해당 종목(기업)의 부채율 정보를 화면에 json형태로 출력한다.
    
    /*
      hint
      
      1) 종목부채율 정보 데이터를 모델 클래스로 만들어 처리하는 경우 (recommended)
         
          Debt 모델 클래스 작성 => API 응답 json 데이터를 참고
      
		  @RequestMapping(path = "/marketdata/debt/{issueCode}",
			method = { RequestMethod.GET, RequestMethod.POST } )
	    public @ResponseBody Debt debt(@PathVariable String issueCode) {
	        return marketDataApiCaller.getDebt(issueCode);
	    }
      
      2) json 데이터를 그대로 출력하는 경우
      
          @RequestMapping(path = "/marketdata/debt/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    	 public @ResponseBody Debt debt(@PathVariable String issueCode) {
      		String jsonStr = httpClientUtil.execute(URI_PREFIX + issueCode + URI_POSTFIX + "?apikey=" + APIKEY);
			JsonNode node = objectMapper.readTree(jsonStr);
			return node.toString();
    	}
      
     */
}
