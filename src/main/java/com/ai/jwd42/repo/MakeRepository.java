package com.ai.jwd42.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ai.jwd42.dto.Make;


@Repository
public class MakeRepository extends BaseRepoistory {

	
	public List<Make> findAllMake() {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from make ;");
			ResultSet rs = ps.executeQuery();
			List<Make> makes = new ArrayList<>();
			while (rs.next()) {
				Make make = new Make();
				make.setId(rs.getInt(1));
				make.setName(rs.getString(2));
				
				makes.add(make);
			}
			return makes;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
