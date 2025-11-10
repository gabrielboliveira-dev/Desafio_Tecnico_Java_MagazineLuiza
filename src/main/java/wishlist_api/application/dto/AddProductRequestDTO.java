package wishlist_api.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddProductRequestDTO {

    @NotBlank(message = "O ID do produto não pode estar em branco.")
    private String productId;
}