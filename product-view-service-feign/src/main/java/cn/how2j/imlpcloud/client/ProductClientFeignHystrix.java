package cn.how2j.imlpcloud.client;

import cn.how2j.imlpcloud.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imlp
 * @date 2021/2/19 15:30
 */
@Component
public class ProductClientFeignHystrix implements ProductClientFeign {
    public List<Product> listProdcuts() {
        List<Product> result = new ArrayList<>();
        result.add(new Product(0,"产品数据微服务不可用",0));
        return result;
    }
}
