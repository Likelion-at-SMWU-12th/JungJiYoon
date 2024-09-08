package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 필요한 경우 커스텀 메서드를 추가할 수 있음
    // 쿼리 메서드 또는 @Query
}
