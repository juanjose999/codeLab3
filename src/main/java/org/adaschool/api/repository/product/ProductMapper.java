package org.adaschool.api.repository.product;

public class ProductMapper {

    public static ProductResponseDto productToProductResponseDto(Product product){
        return new ProductResponseDto(
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getTags(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public static Product productDtoToProduct(ProductDto productDto){
        return new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getCategory(),
                productDto.getTags(),
                productDto.getPrice(),
                productDto.getImageUrl()
        );
    }
}
