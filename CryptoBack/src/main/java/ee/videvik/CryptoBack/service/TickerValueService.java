package ee.videvik.CryptoBack.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.google.gson.JsonParser.parseReader;


public class TickerValueService {

    // api connection setup with java.urlConnection
    public static String connectUrl(String url) throws IOException {
        JsonObject obj = new JsonObject();
        URL connectTo = new URL(url);
        URLConnection request = connectTo.openConnection();
        request.connect();

        try {
            JsonElement element = parseReader(new InputStreamReader((InputStream) request.getContent()));
            obj = element.getAsJsonObject();
        } catch (Exception e) {
            obj.addProperty("default", "Something went wrong");
        }

        return obj.toString();
    }

    // EUR rate query for Ripple
    public static double getEurRate() throws IOException {
        String url = "https://www.freeforexapi.com/api/live?pairs=USDEUR";
        double eurRate = Double.parseDouble(connectUrl(url).replaceAll(".*?([\\d.]+).*",
                "$1"));

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
        // here the api call receives 403 forbidden [error 1010], meaning
        // The owner of this website has banned access based on your browser's signature
        String uri = "https://api-pub.bitfinex.com/v2/ticker/" + symbol;
        /* RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        headers.add("Accept", "*/*");
        /* String[] cryptoTicker = restTemplate.getForObject(uri, String[].class, headers);
        double lastPrice = Double.parseDouble(cryptoTicker[6]);
        if (currency.equals("Ripple")) {
            lastPrice = lastPrice * TickerValueService.getEurRate();
        } */
        double marketValue = 123.45; // amount * lastPrice;

        return marketValue;
    }

}
