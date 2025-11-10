package wishlist_api.presentation.controller;

import wishlist_api.application.dto.AddProductRequestDTO;
import wishlist_api.application.dto.WishlistResponseDTO;
import wishlist_api.application.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/{clientId}/products")
    public ResponseEntity<WishlistResponseDTO> addProduct(
            @PathVariable UUID clientId,
            @Valid @RequestBody AddProductRequestDTO requestDTO) {

        WishlistResponseDTO response = wishlistService.addProduct(clientId, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{clientId}/products/{productId}")
    public ResponseEntity<WishlistResponseDTO> removeProduct(
            @PathVariable UUID clientId,
            @PathVariable String productId) {

        WishlistResponseDTO response = wishlistService.removeProduct(clientId, productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<WishlistResponseDTO> getWishlist(@PathVariable UUID clientId) {
        WishlistResponseDTO response = wishlistService.getWishlist(clientId);
        return ResponseEntity.ok(response);
    }
}
