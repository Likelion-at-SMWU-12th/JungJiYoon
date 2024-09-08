package study.querydsl.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import study.querydsl.entity.Product;
import study.querydsl.entity.QProduct;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    // @BeforeEach를 사용하여 각 테스트 전에 더미 데이터 삽입
    @BeforeEach
    void setUp() {
        productRepository.save(new Product("펜1", 1000, 100, 15));
        productRepository.save(new Product("펜2", 500, 200, 14));
        productRepository.save(new Product("펜3", 2000, 150, 13));
        productRepository.save(new Product("펜4", 300, 300, 12));
        productRepository.save(new Product("펜5", 700, 250, 11));
        productRepository.save(new Product("펜6", 1000, 100, 10));
        productRepository.save(new Product("펜7", 1000, 100, 9));
        productRepository.save(new Product("펜8", 1000, 100, 8));
        productRepository.save(new Product("펜9", 1000, 100, 7));
        productRepository.save(new Product("펜10", 1000, 100, 6));
        productRepository.save(new Product("펜11", 1000, 100, 5));
        productRepository.save(new Product("펜12", 1000, 100, 4));
        productRepository.save(new Product("펜13", 1000, 100, 3));
        productRepository.save(new Product("펜14", 1000, 100, 2));
        productRepository.save(new Product("펜15", 1000, 100, 1));
    }

    @Test
    void queryDslTest(){
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList =
                query.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                        .orderBy(qProduct.price.asc())
                        .fetch();

        for (Product product : productList) {
            System.out.println("--------------------");
            System.out.println("Product Number : " + product.getId());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println("--------------------");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product);
            System.out.println("--------------------");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple product : tupleList){
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Price : " + product.get(qProduct.price));
            System.out.println("--------------------");
        }
    }

    // 쿼리 메소드 방식 테스트
    @Test
    void popularTest(){
        List<Product> popularProducts = productRepository.findTop10ByOrderByPopularityDesc();

        for (Product product : popularProducts) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Popularity : " + product.getPopularity());
            System.out.println("--------------------");
        }

    }

    @Test
    void popularTest2(){
        List<Product> popularProducts = productRepository.findTop10ByPopularity(PageRequest.of(0, 10));

        for (Product product : popularProducts) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Popularity : " + product.getPopularity());
            System.out.println("--------------------");
        }

    }

    @Test
    void popularTest3(){
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Tuple> productList = query
                .select(qProduct.name, qProduct.popularity)
                .from(qProduct)
                .orderBy(qProduct.popularity.desc())
                .limit(10)
                .fetch();

        for (Tuple product : productList) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Popularity : " + product.get(qProduct.popularity));
            System.out.println("--------------------");
        }
    }

    @Test
    void idTest(){
        List<Product> popularProducts = productRepository.findTop10ByOrderByIdDesc();

        for (Product product : popularProducts) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Id : " + product.getId());
            System.out.println("--------------------");
        }

    }

    @Test
    void idTest2(){
        List<Product> popularProducts = productRepository.findTop10ByIdDesc(PageRequest.of(0, 10));

        for (Product product : popularProducts) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Id : " + product.getId());
            System.out.println("--------------------");
        }
    }

    @Test
    void idTest3(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Tuple> productList = jpaQueryFactory
                .select(qProduct.name, qProduct.id)
                .from(qProduct)
                .orderBy(qProduct.id.desc())
                .limit(5)
                .fetch();

        for (Tuple product : productList) {
            System.out.println("--------------------");
            System.out.println("Product Name : " + product.get(qProduct.name));
            System.out.println("Product Id : " + product.get(qProduct.id));
            System.out.println("--------------------");
        }
    }
}