package lqc.com.pis.repository;

import lqc.com.pis.entity.InvalidAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidAccessTokenRepository extends JpaRepository<InvalidAccessToken, Long> {
    boolean existsByTokenUuid(String tokenUuid);
}
