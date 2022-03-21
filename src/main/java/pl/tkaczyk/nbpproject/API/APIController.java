package pl.tkaczyk.nbpproject.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.tkaczyk.nbpproject.model.Currency;

import java.math.BigDecimal;

@RestController
public class APIController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String URL = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";

    public BigDecimal getCurrency(String date){
        Currency currency = restTemplate.getForObject(URL + date + "/?format=json", Currency.class);
        return BigDecimal.valueOf(currency.getRates().get(0).getBid());
    }
}
