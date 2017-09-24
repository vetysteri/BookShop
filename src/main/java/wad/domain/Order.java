package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "order_table")
public class Order extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    @Length(min = 3, max = 30)
    private String name;
    @NotBlank
    @Length(min = 3, max = 30)
    private String address;
    @NotBlank
    @Length(min = 3, max = 30)
    private String postNumber;
    @NotBlank
    @Length(min = 3, max = 30)
    private String city;
    private Double totalPrice;
    private Long bookCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }
    
    
    
    
    
    

}
