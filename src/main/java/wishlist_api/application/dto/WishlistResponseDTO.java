package wishlist_api.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class WishlistResponseDTO {

    private UUID clientId;
    private String clientName;
    private Set<String> productIds;

}
