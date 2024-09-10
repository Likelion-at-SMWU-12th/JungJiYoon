package study.querydsl.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.entity.Product;
import study.querydsl.entity.Provider;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void cascadeTest(){
        Provider provider = savedProvider("새로운 공급 업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 1500);
        Product product3 = savedProduct("상품3", 750, 750);

        // 연관관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        // 여기서 영속성 전이가 일어남
        providerRepository.save(provider);

    }

    private Provider savedProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

}