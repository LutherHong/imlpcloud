package cn.how2j.imlpcloud.service;

import cn.how2j.imlpcloud.client.ProductClientRibbon;
import cn.how2j.imlpcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductClientRibbon productClientRibbon;

    public List<Product> listProducts(){
        return productClientRibbon.listProdcuts();
    }
}
