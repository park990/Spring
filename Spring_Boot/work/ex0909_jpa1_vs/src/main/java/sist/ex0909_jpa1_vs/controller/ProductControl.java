package sist.ex0909_jpa1_vs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sist.ex0909_jpa1_vs.repository.ProductRepository;
import sist.ex0909_jpa1_vs.store.ProductJPO;

@RestController
public class ProductControl {
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/t1")
    public String test1(){
        ProductJPO product = new ProductJPO();
        product.setPName("빈센트 아몬드나무");
        product.setPCompany("Art company");
        
        productRepository.save(product);
        return "test1";
    }

    @GetMapping("/t2")
    public String test2(){
       ProductJPO product =  ProductJPO.builder()
       .pName("빈센트 반고흐")
       .pCompany("Art company")
       .build();
  
        
        productRepository.save(product);
        return "test2";
    }

    @GetMapping("/t3")
    public String test3(){
        List<ProductJPO> list = productRepository.findAll();
        StringBuffer sb = new StringBuffer();
        for(ProductJPO product :list){
            sb.append(product.getPNum());
            sb.append("/");
            sb.append(product.getPName());
            sb.append("/");
            sb.append(product.getPCompany());
            sb.append("/");
            sb.append(product.getCvo1().getCName());
            sb.append("/");
            sb.append(product.getCvo1().getDesc());
            sb.append("<br/>");
        }
        return sb.toString();
    }
}
