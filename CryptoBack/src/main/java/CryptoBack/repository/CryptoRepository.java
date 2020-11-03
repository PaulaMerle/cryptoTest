package CryptoBack.repository;


import CryptoBack.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CryptoRepository extends JpaRepository<Crypto, Integer> {

}
