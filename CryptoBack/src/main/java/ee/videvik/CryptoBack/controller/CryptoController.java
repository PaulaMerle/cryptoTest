package ee.videvik.CryptoBack.controller;

import ee.videvik.CryptoBack.model.Crypto;
import ee.videvik.CryptoBack.repository.CryptoRepository;
import ee.videvik.CryptoBack.service.TickerValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8082") // see on dev frondi port
@RestController
@RequestMapping("/api")
public class CryptoController {

    @Autowired
    CryptoRepository cryptoRepository;

    private void getCryptoValue(Crypto crypto) throws IOException {

        double currentMarketValue = Math.round(TickerValueService.getMarketValue
                (crypto.getAmount(), crypto.getCurrency()) * 100) / 100.0;
        try {
            crypto.setValue(currentMarketValue);
        } catch (Exception e) {
            System.out.println("Viga 1: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/cryptos")
    public ResponseEntity<List<Crypto>> getAllCryptos() throws IOException {

        try {
            List<Crypto> cryptos = cryptoRepository.findAll();
            if (cryptos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            cryptos.forEach(crypto -> {
                try {
                    getCryptoValue(crypto);
                } catch (IOException e) {
                    System.out.println("Viga 2: " + e.getMessage());
                    e.printStackTrace();
                }
            });
            return new ResponseEntity<>(cryptos, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Viga 3: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> getCryptoById(@PathVariable("id") int id) {
        Optional<Crypto> cryptoData = cryptoRepository.findById(id);

        if (cryptoData.isPresent()) {
            return new ResponseEntity<>(cryptoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cryptos")
    public ResponseEntity<Crypto> createCrypto(@RequestBody Crypto crypto) {
        try {
            Crypto _crypto = cryptoRepository.save(new Crypto(crypto.getCurrency(), crypto.getAmount(),
                    crypto.getLocation(), crypto.getDate()));
            return new ResponseEntity<>(_crypto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> updateCrypto(@PathVariable("id") int id, @RequestBody Crypto crypto) {
        Optional<Crypto> cryptoData = cryptoRepository.findById(id);

        if (cryptoData.isPresent()) {
            Crypto _crypto = cryptoData.get();
            _crypto.setCurrency(crypto.getCurrency());
            _crypto.setAmount(crypto.getAmount());
            _crypto.setLocation(crypto.getLocation());
            _crypto.setDate(crypto.getDate());
            return new ResponseEntity<>(cryptoRepository.save(_crypto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cryptos/{id}")
    public ResponseEntity<HttpStatus> deleteCrypto(@PathVariable("id") int id) {
        try {
            cryptoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cryptos")
    public ResponseEntity<HttpStatus> deleteAllCryptos() {
        try {
            cryptoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
