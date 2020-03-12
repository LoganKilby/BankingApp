package daoclass;



import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import connections.ConnectionUtil;
import daoapi.Dao;
import domain.Account;
import domain.User;

public class UserDao implements Dao<User> {
	
	private User user;

	@Override
	public User get() {
		System.out.println(user);
		return user;
	}
	
	public String getFullName() {
		return user.getFirst_name() + " " + user.getLast_name();
	}
	
	public User logIn(String usernameInput, String passwordInput) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM CUSTOMERS WHERE username = ? AND password = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, usernameInput);
			stmt.setString(2, passwordInput);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				user = new User(customer_id, first_name,
						last_name,   email,
						username,    password);
			
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("An account with that username and password could not be found.");
		}
		
		return null;
	}

	@Override
	public User employeeLogIn(String usernameInput, String passwordInput) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEES WHERE username = ? AND password = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, usernameInput);
			stmt.setString(2, passwordInput);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				int salary = rs.getInt("salary");
				String title = rs.getString("title");
				
				user = new User(id, 		first_name,
						 		last_name,  username,
						 		password,   email,
						 		salary, 	title);
				
				return user;
			} else {
				System.out.println("something went wrong");
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); //TODO Log this
			System.out.println("An account with that username and password could not be found.");
		}
		
		return null;

	}

	@Override
	public List<Account> findAccounts(int customer_id) {
		List<Account> accounts = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNTS WHERE owner = ? AND account_status = 'active'";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, customer_id);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int balance = rs.getInt("balance");
				String account_type = rs.getString("account_type");
				String account_status = rs.getString("account_status");
				int owner = rs.getInt("owner");
				int joint_owner = rs.getInt("joint_owner");
				
				Account account = new Account(id, balance, account_type, account_status, owner, joint_owner);
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace(); //TODO Log this
			System.out.println("An active account with that username and password could not be found.");
		}
		
		return null;
	}
	
	@Override
	public User findCustomerByName(String fName, String lName) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM CUSTOMERS WHERE first_name = ? AND last_name = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, fName);
			stmt.setString(2,  lName);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("customer_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User temp_user = new User(id, first_name, last_name, email, username, password);
				return temp_user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); //TODO Log this
			System.out.println("An account with that username and password could not be found.");
		}
		
		return null;
	}

	@Override
	public int getCustomerId() {
		return user.getId();
	}

	@Override
	public int withdraw(int amount, int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{ call withdraw(?, ?) }";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1,  amount);
			stmt.setInt(2,  id);
			
			stmt.executeQuery();
			
			System.out.println("Withdraw complete.");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deposit(int amount, int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{ call deposit(?, ?) }";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1,  amount);
			stmt.setInt(2,  id);
			
			stmt.executeQuery();
			
			System.out.println("Deposit successful");
						
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void transferFunds(int sourceID, int targetID, int amount) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "{ call transfer_funds(?, ?, ?) }";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1,  sourceID);
			stmt.setInt(2,  targetID);
			stmt.setInt(3,  amount);
			
			stmt.executeQuery();
			
			System.out.println("Transfer complete");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void applyForAccount(String accountType, int owner1) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO ACCOUNTS (account_type, account_status, owner) VALUES (?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,  accountType);
			stmt.setString(2,  "pending");
			stmt.setInt(3,  owner1);
			
			stmt.executeQuery();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void applyForJointAccount(String accountType, int owner1, int owner2) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO accounts (account_type, account_status, owner, JOINT_OWNER) VALUES (?, 'pending', ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,  accountType);
			stmt.setInt(2,  owner1);
			stmt.setInt(3, owner2);
			
			stmt.executeQuery();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Account> getPendingAccounts() {
		List<Account> accounts = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNTS WHERE account_status = 'pending'";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int balance = rs.getInt("balance");
				String account_type = rs.getString("account_type");
				String account_status = rs.getString("account_status");
				int owner = rs.getInt("owner");
				int joint_owner = rs.getInt("joint_owner");
				
				Account account = new Account(id, balance, account_type, account_status, owner, joint_owner);
				accounts.add(account);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace(); //TODO Log this
			System.out.println("An active account with that username and password could not be found.");
		}
		
		return null;
	}

	@Override
	public void reviewAccounts(int id, int appChoice) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts SET account_status = 'active' WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			stmt.execute();
			
			String sql2 = "UPDATE accounts SET balance = 0 WHERE id = ?";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			
			stmt2.setInt(1,  id);
			stmt2.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int lookupBalance(int sourceId) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT balance FROM ACCOUNTS where ID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, sourceId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int balance = rs.getInt("balance");
				return balance;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void cancelAccount(int Id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE accounts SET account_status = 'cancelled' WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, Id);
			stmt.executeQuery();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void newCustomer(String first_name, String last_name, String email, String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO CUSTOMERS (FIRST_NAME, LAST_NAME, EMAIL, USERNAME, PASSWORD) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, first_name);
			stmt.setString(2, last_name);
			stmt.setString(3, email);
			stmt.setString(4, username);
			stmt.setString(5, password);
			
			stmt.executeQuery();
			
			System.out.println("New customer account created.Thanks for signing up, " + first_name + "!\n"
							 + "You can now apply to open a new bank account at the main menu.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}