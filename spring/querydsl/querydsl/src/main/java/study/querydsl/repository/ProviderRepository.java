package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
