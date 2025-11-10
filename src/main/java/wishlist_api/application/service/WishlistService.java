package wishlist_api.application.service;

import wishlist_api.application.dto.AddProductRequestDTO;
import wishlist_api.application.dto.WishlistResponseDTO;

import java.util.UUID;

public interface WishlistService {

    WishlistResponseDTO addProduct(UUID clientId, AddProductRequestDTO requestDTO);
    WishlistResponseDTO removeProduct(UUID clientId, String productId);
    WishlistResponseDTO getWishlist(UUID clientId);
}