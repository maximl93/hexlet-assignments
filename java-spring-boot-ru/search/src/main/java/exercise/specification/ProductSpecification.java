package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withTitleCont(params.getTitleCont()))
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()));
    }

    public Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("title"), titleCont);
    }

    public Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThan(root.get("price"), priceLt);
    }

    public Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("price"), priceGt);
    }

    public Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }
}
// END
