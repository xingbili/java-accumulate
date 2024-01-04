/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.persondemo.service;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xing
 * @date: 2024/1/2 13:59
 */
@Service
public class ProductService {
//    private final ProductRepository productRepository;
//
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Cacheable(value = "products", key = "#id")
//    public Product getProduct(Long id) {
//        // 模拟数据库查询
//        return productRepository.findById(id);
//    }
//
//    @CacheEvict(value = "products", key = "#product.id")
//    public void updateProduct(Product product) {
//        // 模拟数据库更新
//        productRepository.update(product);
//    }
//
//    // 缓存预热方法
//    public void cacheWarmUp() {
//        List<Product> products = productRepository.findAll();
//        for (Product product : products) {
//            getProduct(product.getId());
//        }
//    }

}
