package com.ata.flo.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ata.flo.model.Product;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

    	Product product = new Product(
                rs.getString("shoppingid"),
                rs.getString("name"),
                rs.getDouble("quantity"),
                rs.getDouble("price"),
                rs.getString("invoice")
        );

        return product;

    }
}
