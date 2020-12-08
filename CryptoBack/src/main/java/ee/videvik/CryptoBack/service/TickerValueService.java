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

    // EUR rate query for Ripple
    public static double getEurRate() throws IOException {
        URL url = new URL("https://www.freeforexapi.com/api/live?pairs=USDEUR");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10000);

        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        double eurRate = Double.parseDouble(inputReader.readLine().replaceAll(".*?([\\d.]+).*",
                "$1"));
        System.out.println(eurRate);

        inputReader.close();

        return eurRate;
    }

    // rounding the crypto price to 2 decimal points
    private static double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // retrieve latest price for cryptocurrencies from Bitfinex api
    public static double getMarketValue(int amount, String currency) throws IOException {

        double marketValue = 0;
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
        try {
            URL url = new URL("https://api-pub.bitfinex.com/v2/ticker/" + symbol);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setReadTimeout(10000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                System.out.println("Error reading API");
            }

            // read the last price, value of 6th index in response array
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String response = inputReader.readLine();
            String[] cryptoValue = response.split(",");
            double lastPrice = Double.parseDouble(cryptoValue[6]);

            // calculate EUR value for Ripple since Bitfinex doesn't give any
            if (currency.equals("Ripple")) {
                lastPrice = lastPrice * TickerValueService.getEurRate();
            }

            marketValue = amount * lastPrice;

            inputReader.close();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return round(marketValue);
    }

}
