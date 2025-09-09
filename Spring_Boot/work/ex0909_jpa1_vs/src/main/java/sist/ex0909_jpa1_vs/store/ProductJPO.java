package sist.ex0909_jpa1_vs.store;

import java.time.LocalDate;

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

@Entity(name="product_t")
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductJPO {

    @Id
    @GeneratedValue
    private Long pNum;
    private String pName;
    private String pCompany;
    private LocalDate reg_date;

    @Column(name="category1")
    private int category1;


    private int category2;
    private int category3;

    @ManyToOne
    @JoinColumn(name="category1",insertable = false,updatable = false)
    // jpa가 해당컬럼(category)을 삽입하거나 수정하지 못하도록
    private category1JPO cvo1;

}
