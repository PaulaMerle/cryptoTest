package ee.videvik.CryptoBack.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TickerValueService {

    public static String connectApi(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
            connection.setRequestProperty("Accept", "*/*");
            connection.setReadTimeout(10000);
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                System.out.println("Error reading API");
            }
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String response = inputReader.readLine();
        inputReader.close();
        return response;
    }

    // EUR rate query for Ripple
    public static double getEurRate() throws IOException {
        URL url = new URL("https://www.freeforexapi.com/api/live?pairs=USDEUR");
        double eurRate = Double.parseDouble(connectApi(url).replaceAll(".*?([\\d.]+).*",
                "$1"));
        System.out.println(eurRate);

        return eurRate;
    }

    // rounding the crypto price to 2 decimal points
    public static double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // retrieve latest price for cryptocurrencies from Bitfinex api
    public static double getMarketValue(int amount, String currency) throws IOException {
        String symbol = "";

        // assign ticker symbol to crypto
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

        // connect to Bitfinex API
        URL url = new URL("https://api-pub.bitfinex.com/v2/ticker/" + symbol);
        String[] cryptoValue = connectApi(url).split(",");
        double lastPrice = Double.parseDouble(cryptoValue[6]);

        // calculate EUR value for Ripple since Bitfinex doesn't give any
        if (currency.equals("Ripple")) {
            lastPrice = lastPrice * TickerValueService.getEurRate();
        }
        double marketValue = amount * lastPrice;

        return round(marketValue);
    }
}
