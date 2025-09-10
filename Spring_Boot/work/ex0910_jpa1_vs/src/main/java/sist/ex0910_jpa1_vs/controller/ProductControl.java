package sist.ex0910_jpa1_vs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sist.ex0910_jpa1_vs.repository.CategoryRepository;
import sist.ex0910_jpa1_vs.repository.ProductRepository;
import sist.ex0910_jpa1_vs.store.Category1JPO;
import sist.ex0910_jpa1_vs.store.ProductJPO;

@RestController
public class ProductControl {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String add(){
        ProductJPO p1 = ProductJPO.builder()
        .pNum(102L)
        .pName("상비")
        .pCompany("tsis")
        .category1(3)
        .build();

        productRepository.save(p1);
        return "KO tresni";
    }

    @GetMapping("/clist")
    public String getClist(){

    List<Category1JPO> list = categoryRepository.findAll();
        StringBuffer sb= new StringBuffer();
        for(Category1JPO cvo : list){
            sb.append(cvo.getIdx());
            sb.append("/");
            sb.append(cvo.getCName());
            sb.append("<br/>");
            List<ProductJPO> plist = cvo.getPList();
            for(ProductJPO pvo: plist){
                sb.append("&nbsp;&nbsp;&nbsp;");
                sb.append(pvo.getPNum());
                sb.append(".");
                sb.append(pvo.getPName());
                sb.append("<br/>");
            }
        }

        return sb.toString();
    }

}
