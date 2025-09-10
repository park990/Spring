package sist.ex0910_jpa1_vs.store;

import java.util.List;

import org.hibernate.mapping.OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "category1_t")
@Getter@Setter@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category1JPO {
    
    @Id
    @GeneratedValue
    private int idx;
    private String cName;
    private String desc;
    private int status;

    @jakarta.persistence.OneToMany
    @JoinColumn(name = "category1")
    private List<ProductJPO> pList;
}
