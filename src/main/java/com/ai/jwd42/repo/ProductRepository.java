package com.ai.jwd42.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.jwd42.dto.Product;

@Repository
public class ProductRepository extends BaseRepoistory {


	public void insertProduct(Product product) {
		Connection con = getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into products(model,maker_id) value(?,?);");
			ps.setString(1, product.getModel());
			ps.setInt(2, product.getMaker_id());
			ps.executeUpdate();

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	
	
	
	public List<Product> findAllProduct() {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select m.name,p.model,p.id,p.maker_id from products p inner join make m on p.maker_id=m.id ;");
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<>();
			while (rs.next()) {
				Product product = new Product();
				product.setMake(rs.getString(1));
				product.setModel(rs.getString(2));
				product.setId(rs.getInt(3));
				product.setMaker_id(rs.getInt(4));
				
				products.add(product);
			}
			return products;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<Product> findProductWithModelandMaker_Id(String model , int maker_id) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from products where model= ? and maker_id= ?;");
			ps.setString(1, model);
			ps.setInt(2, maker_id);
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<>();
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setMaker_id(rs.getInt(2));
				product.setModel(rs.getString(3));
				products.add(product);
				return products;
			}
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	




	public void updateProduct(Product product) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update products set model=? , maker_id=? where id=?");
			ps.setString(1, product.getModel());
			ps.setInt(2, product.getMaker_id());
			ps.setInt(3, product.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void deleteProduct(int id) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from products where id=?;");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
