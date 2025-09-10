package sist.ex0910_jpa1_vs.store;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product_t")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductJPO {

    

    @Id
    @GeneratedValue
    private long pNum;
    private String pName;
    private String pCompany;
    private LocalDate regDate;

    @Column(name = "category1")
    private int category1;

    private int category2;
    private int category3;

    @ManyToOne
    @JoinColumn(name="category1",insertable = false,updatable = false)
    private Category1JPO cvo1;
    
}
