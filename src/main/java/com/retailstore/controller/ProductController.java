package com.retailstore.controller;

import com.retailstore.dto.ProductDto;
import com.retailstore.service.IProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kranthi on 6/23/2016.
 */
@RestController
@RequestMapping(value = "products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody final ProductDto productVo) {
        productService.addProduct(productVo);
    }

    @RequestMapping(value = "/searchbyIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProductsByTypeAndName(@RequestParam(value = "productIds", required = true) final String productIds) {
        String[] stringIds = productIds.split(",");
        Long[] ids = new Long[stringIds.length];   
		for (int i = 0; i < stringIds.length; i++) {   
    		ids[i] = Long.parseLong(stringIds[i]);   
		}
		long[] idLongs = new long[ids.length];
		for(int j=0;j<ids.length;j++){
		    idLongs[j] = ids[j];
		}
        return productService.getProductsByIds(idLongs);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable final Long id) {
        productService.deleteProduct(id);
    }
}
