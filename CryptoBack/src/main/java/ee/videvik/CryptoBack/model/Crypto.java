package ee.videvik.CryptoBack.model;

import javax.persistence.*;

@Entity
@Table(name = "cryptos")
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private int amount;

    @Column(name = "location")
    private String location;

    @Column(name = "date")
    private String date;

    @Transient
    private double value;


    public Crypto() {
    }

    public Crypto(int id, String currency, int amount, String location, String date, double value) {
        this.id = id;
        this.currency = currency;
        this.amount = amount;
        this.location = location;
        this.date = date;
        this.value = value;

    }

    public Crypto(String currency, int amount, String location, String date, double value) {
        this.currency = currency;
        this.amount = amount;
        this.location = location;
        this.date = date;
        this.value = value;
    }

    public Crypto(String currency, int amount, String location, String date) {
        this.currency = currency;
        this.amount = amount;
        this.location = location;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
