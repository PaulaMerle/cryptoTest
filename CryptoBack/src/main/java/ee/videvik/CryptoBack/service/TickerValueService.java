package ee.videvik.CryptoBack.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.google.gson.JsonParser.parseReader;


public class TickerValueService {


    // EUR rate query for Ripple
    public static double getEurRate() throws IOException {
        String url = "https://www.freeforexapi.com/api/live?pairs=USDEUR";
        double eurRate = Double.parseDouble(getUrlData(url).replaceAll(".*?([\\d.]+).*", "$1"));

        return eurRate;
    }

    // retrieve latest price for cryptocurrencies from Bitfinex api
    public static double getMarketValue(int amount, String currency) throws IOException {
        String symbol = "";
        switch (currency) {
            case ("Bitcoin"):
                symbol = "tBTCEUR";
                break;
            case ("Ethereum"):
                symbol = "tETHEUR";
                break;
            case ("Ripple"):
                symbol = "tXRPUSD";
                break;
            default:
                System.out.println("Currency not found");
        }
        System.out.println("Probla 1: ");
        String uri = "https://api-pub.bitfinex.com/v2/ticker/" + symbol;
        System.out.println("Probla 2: ");

        RestTemplate restTemplate = new RestTemplate();
        String[] cryptoTicker = restTemplate.getForObject(uri, String[].class);
        System.out.println("Probla 3: ");
        double lastPrice = Double.parseDouble(cryptoTicker[6]);
        System.out.println("Probla 4: ");
        if (currency.equals("Ripple")) {
            System.out.println("Probla 5: ");
            lastPrice = lastPrice * TickerValueService.getEurRate();
            System.out.println("Probla 6: ");
        }
        double marketValue = amount * lastPrice;
        System.out.println("Probla 7: ");

        return marketValue;
    }

    // api connection setup with java.urlConnection
    public static String getUrlData(String url) throws IOException {
        JsonObject obj = new JsonObject();
        URL connectTo = new URL(url);
        URLConnection request = connectTo.openConnection();
        request.connect();


        try {
            JsonElement element = parseReader(new InputStreamReader((InputStream) request.getContent()));
            obj = element.getAsJsonObject();
        } catch (Exception e) {
            obj.addProperty("default", "0");
        }

        return obj.toString();
    }

}
