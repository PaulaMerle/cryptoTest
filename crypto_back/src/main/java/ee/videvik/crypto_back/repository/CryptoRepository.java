package ee.videvik.crypto_back.repository;


import ee.videvik.crypto_back.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CryptoRepository extends JpaRepository<Crypto, Integer> {

}
