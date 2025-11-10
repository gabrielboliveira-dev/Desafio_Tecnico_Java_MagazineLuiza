package wishlist_api.application.service.impl;

import wishlist_api.application.dto.AddProductRequestDTO;
import wishlist_api.application.dto.WishlistResponseDTO;
import wishlist_api.application.exception.BusinessException;
import wishlist_api.application.exception.ResourceNotFoundException;
import wishlist_api.application.service.WishlistService;
import wishlist_api.domain.entity.Client;
import wishlist_api.domain.entity.Wishlist;
import wishlist_api.domain.repository.ClientRepository;
import wishlist_api.domain.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public WishlistResponseDTO addProduct(UUID clientId, AddProductRequestDTO requestDTO) {

        Wishlist wishlist = findOrCreateWishlistByClient(clientId);

        if (wishlist.getProductIds().contains(requestDTO.getProductId())) {
            throw new BusinessException("Produto já existe na wishlist.");
        }

        if (wishlist.getProductIds().size() >= 20) {
            throw new BusinessException("Wishlist cheia. Limite de 20 produtos atingido.");
        }

        wishlist.getProductIds().add(requestDTO.getProductId());
        wishlistRepository.save(wishlist);

        return mapToWishlistResponseDTO(wishlist);
    }

    @Override
    @Transactional
    public WishlistResponseDTO removeProduct(UUID clientId, String productId) {

        Wishlist wishlist = findWishlistByClientId(clientId);

        if (!wishlist.getProductIds().contains(productId)) {
            throw new BusinessException("Produto não encontrado na wishlist para remoção.");
        }

        wishlist.getProductIds().remove(productId);
        wishlistRepository.save(wishlist);

        return mapToWishlistResponseDTO(wishlist);
    }

    @Override
    @Transactional(readOnly = true)
    public WishlistResponseDTO getWishlist(UUID clientId) {
        Wishlist wishlist = findWishlistByClientId(clientId);
        return mapToWishlistResponseDTO(wishlist);
    }

    private Wishlist findWishlistByClientId(UUID clientId) {
        return wishlistRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Wishlist não encontrada para o cliente ID: " + clientId));
    }

    private Wishlist findOrCreateWishlistByClient(UUID clientId) {

        return wishlistRepository.findByClientId(clientId)
                .orElseGet(() -> {

                    Client client = clientRepository.findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Cliente ID: " + clientId + " não encontrado."));

                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setClient(client);
                    newWishlist.setProductIds(new HashSet<>());

                    return wishlistRepository.save(newWishlist);
                });
    }

    private WishlistResponseDTO mapToWishlistResponseDTO(Wishlist wishlist) {
        return WishlistResponseDTO.builder()
                .clientId(wishlist.getClient().getId())
                .clientName(wishlist.getClient().getName())
                .productIds(wishlist.getProductIds())
                .build();
    }
}