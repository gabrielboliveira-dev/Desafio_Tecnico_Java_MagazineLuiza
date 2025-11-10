package wishlist_api.domain.repository;

import wishlist_api.domain.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, UUID> {

    Optional<Wishlist> findByClientId(UUID clientId);
}