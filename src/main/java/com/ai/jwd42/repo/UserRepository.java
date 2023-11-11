package com.ai.jwd42.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.ai.jwd42.dto.User;

@Repository
public class UserRepository extends BaseRepoistory {

	public User findUserByEmail(String email) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user  where email=?;");

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				return user;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public User findUserByPhoneNumber(String phone_number) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user  where phone_number=?;");

			ps.setString(1, phone_number);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				return user;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<User> findAllUser() {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				users.add(user);
			}
			return users;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public User findUserById(int id) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNumber(rs.getString(5));
				return user;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int insertUser(User user) {
		Connection con = getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con
					.prepareStatement("insert into user(name,email,password,phone_number) value(?,?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhoneNumber());
			result = ps.executeUpdate();

			return result;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;

	}

	public void updateUser(User user) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("update user set phone_number = ? , name = ? , email = ? where id = ?;");
			ps.setString(1, user.getPhoneNumber());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void deleteUser(int id) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from user where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void changePassword(int id, String password) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update user set password = ? where id = ?;");
			ps.setInt(2, id);
			ps.setString(1, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
