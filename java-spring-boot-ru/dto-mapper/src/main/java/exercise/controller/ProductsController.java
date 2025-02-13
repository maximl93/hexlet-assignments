package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;

    @GetMapping(path = "")
    public List<ProductDTO> showAll() {
        return productRepository.findAll().stream()
                .map(productMapper::map)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO showOne(@PathVariable long id) {
        return productMapper.map(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id" + id + " not found")));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO dto) {
        return productMapper.map(productRepository.save(productMapper.map(dto)));
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@RequestBody ProductUpdateDTO dto,
                             @PathVariable long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id" + id + " not found"));
        productMapper.update(dto, product);

        return productMapper.map(productRepository.save(product));
    }
    // END
}
