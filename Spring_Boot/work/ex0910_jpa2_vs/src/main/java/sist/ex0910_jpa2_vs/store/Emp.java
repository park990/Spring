package sist.ex0910_jpa2_vs.store;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "emp")
@Getter@Setter@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    
    @Id
    @GeneratedValue
    private Long empno;
    private String ename, job;
    private BigDecimal sal;
    private LocalDate hiredate;

    @Column(name = "deptno", insertable = false, updatable = false)
    private String deptno;

    @ManyToOne(fetch=FetchType.LAZY) // Emp객체를 조회할 때 Dept객체는 필요할 때까지 로딩 안함.
    @JoinColumn(name = "deptno", referencedColumnName = "deptno")
    private Dept dept;
}
