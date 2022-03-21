package pl.tkaczyk.nbpproject.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Entity
@Table(name = "computer")
@XmlRootElement(name = "komputer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComputerModel {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private long id;

    @Column(name = "nazwa")
    @XmlElement(name = "nazwa")
    private String name;
    @Column(name = "data_ksiegowania")
    @XmlElement(name = "data_ksiegowania")
    private String date;
    @Column(name = "koszt_USD")
    @XmlElement(name = "koszt_USD")
    private BigDecimal price_USD;
    @Column(name = "koszt_PLN")
    @XmlElement(name = "koszt_PLN")
    private BigDecimal price_PLN;

    public ComputerModel(long id, String name, String date, BigDecimal price_USD, BigDecimal price_PLN) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price_USD = price_USD;
        this.price_PLN = price_PLN;
    }

    public ComputerModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice_USD() {
        return price_USD;
    }

    public void setPrice_USD(BigDecimal price_USD) {
        this.price_USD = price_USD;
    }

    public BigDecimal getPrice_PLN() {
        return price_PLN;
    }

    public void setPrice_PLN(BigDecimal price_PLN) {
        this.price_PLN = price_PLN;
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price_USD=" + price_USD +
                ", price_PLN=" + price_PLN +
                '}';
    }
}
