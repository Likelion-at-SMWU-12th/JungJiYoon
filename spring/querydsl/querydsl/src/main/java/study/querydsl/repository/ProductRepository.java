package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.querydsl.entity.Product;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 필요한 경우 커스텀 메서드를 추가할 수 있음
    // 쿼리 메서드 또는 @Query

    //인기있는 상품 Top10을 반환
    //1. 쿼리 메소드
    List<Product> findTop10ByOrderByPopularityDesc();

    //2. @Query 사용
    @Query("SELECT p FROM Product p ORDER BY p.popularity DESC")
    List<Product> findTop10ByPopularity(Pageable pageable);

    //최근 등록된 상품 Top10을 반환
    //1. 쿼리 메소드
    List<Product> findTop10ByOrderByIdDesc();

    //2. 쿼리 메소드
    @Query("SELECT p FROM Product p ORDER BY p.id DESC")
    List<Product> findTop10ByIdDesc(Pageable pageable);

}
