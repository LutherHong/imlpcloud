package cn.how2j.imlpcloud.service;

import cn.how2j.imlpcloud.client.ProductClientFeign;
import cn.how2j.imlpcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductClientFeign productClientFeign;

    public List<Product> listProducts(){
        return productClientFeign.listProdcuts();
    }
}
