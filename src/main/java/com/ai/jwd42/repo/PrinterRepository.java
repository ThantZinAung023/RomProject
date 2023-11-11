package com.ai.jwd42.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.ai.jwd42.dto.Printer;


@Repository
public class PrinterRepository extends BaseRepoistory {


	public void insertPrinter(Printer printer) {
		Connection con = getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into printer(product_id,color,price) value(?,?,?);");
			ps.setInt(1, printer.getProduct_id());
			ps.setString(2, printer.getColor());
			ps.setDouble(3, printer.getPrice());
			ps.executeUpdate();

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	
	
	
	public List<Printer> findAllPrinter() {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select pr.id,pr.color,p.model,m.name,pr.price,p.id from printer pr inner join products p on p.id=pr.product_id inner join make m on m.id=p.maker_id;");
			ResultSet rs = ps.executeQuery();
			List<Printer> printers = new ArrayList<>();
			while (rs.next()) {
				Printer printer = new Printer();
				printer.setId(rs.getInt(1));
				printer.setColor(rs.getString(2));
				printer.setModel(rs.getString(3));
				printer.setMake(rs.getString(4));
				printer.setPrice(rs.getDouble(5));
				printer.setProduct_id(rs.getInt(6));
				
				printers.add(printer);
			}
			return printers;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<Printer> findPrinterWithAllSpec(int productId, String color, double Price) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select product_id,color,price from printer where product_id=? and color=? and price=?;");
			ps.setInt(1, productId);
			ps.setString(2, color);
			ps.setDouble(3, Price);
			ResultSet rs = ps.executeQuery();
			List<Printer> printers = new ArrayList<>();
			if(rs.next()) {
				Printer printer = new Printer();
				printer.setProduct_id(rs.getInt(1));
				printer.setColor(rs.getString(2));
				printer.setPrice(rs.getDouble(3));
				
				printers.add(printer);
				return printers;
			}
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	




	public void updatePrinter(Printer printer) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update printer set color=? , product_id=? , price=? where id=?");
			ps.setString(1, printer.getColor());
			ps.setInt(2, printer.getProduct_id());
			ps.setDouble(3, printer.getPrice());
			ps.setInt(4, printer.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void deletePrinter(int id) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from printer where id=?;");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
