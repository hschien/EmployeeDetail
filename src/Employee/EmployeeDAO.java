package Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private String jdbcName;
	private String jdbcPwd;
	private String jdbcurl;
	private Connection connDB;
	public EmployeeDAO(String name, String pwd, String url) {
		this.jdbcName = name;
		this.jdbcPwd = pwd;
		this.jdbcurl = url;
	}
	protected void connect() throws SQLException{
		if(connDB==null||connDB.isClosed()) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connDB=DriverManager.getConnection(this.jdbcurl, this.jdbcName, this.jdbcPwd);
		}
	}
	protected void disconnect() throws SQLException{
		if(connDB!=null&&!connDB.isClosed()) {
			connDB.close();
		}
	}
	public boolean insertEmployee(Employee employee) throws SQLException{
		String sql="INSERT INTO EmployeeDetail.dbo.EmployeeDetail (employeeid, name, designation, salary, email) VALUES (?, ?, ?, ?, ?)";
		
		connect();
		
		PreparedStatement statement=connDB.prepareStatement(sql);
		statement.setInt(1, employee.getEmployeeId());
        statement.setString(2, employee.getName());
        statement.setString(3, employee.getDesignation());
        statement.setInt(4, employee.getSalary());
        statement.setString(5, employee.getEmail());
        
        boolean rowInserted=statement.executeUpdate()>0;
        statement.close();
        disconnect();
        return rowInserted;
	}
	
	public List<Employee> listAllEmployees() throws SQLException{
		List<Employee> listEmployee=new ArrayList<>();
		String sql="SELECT * FROM EmployeeDetail.dbo.EmployeeDetail";
		
		connect();
		
		Statement statement=connDB.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		
		while(resultSet.next()) {
			int id=resultSet.getInt("employeeid");
			String name=resultSet.getString("name");
			String designation=resultSet.getString("designation");
			int salary=resultSet.getInt("salary");
			String email=resultSet.getString("email");
			
			Employee employee=new Employee(id,name,designation,salary,email);
			listEmployee.add(employee);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listEmployee;
	}
	
	public boolean deleteEmployee(Employee employee) throws SQLException{
		String sql="DELETE FROM EmployeeDetail.dbo.EmployeeDetail where employeeid=?";
		
		connect();
		
		PreparedStatement statement=connDB.prepareStatement(sql);
		statement.setInt(1, employee.getEmployeeId());
		
		boolean rowDeleted=statement.executeUpdate()>0;
		statement.close();
		disconnect();
		return rowDeleted;
	}
	
	public boolean updateEmployee(Employee employee) throws SQLException{
		String sql="UPDATE EmployeeDetail.dbo.EmployeeDetail SET name=?,designation=?,salary=?,email=?";
		sql+=" WHERE employeeid=?";
		connect();
		
		PreparedStatement statement=connDB.prepareStatement(sql);
		statement.setString(1, employee.getName());
        statement.setString(2, employee.getDesignation());
        statement.setInt(3, employee.getSalary());
        statement.setString(4, employee.getEmail());
        statement.setInt(5, employee.getEmployeeId());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
	}
	
	public Employee getEmployee(int id) throws SQLException {
		Employee employee = null;
        String sql = "SELECT * FROM EmployeeDetail.dbo.EmployeeDetail WHERE employeeid = ?";
         
        connect();
         
        PreparedStatement statement = connDB.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	int employeeid = resultSet.getInt("employeeid");
            String name = resultSet.getString("name");
            String designation = resultSet.getString("designation");
            int salary = resultSet.getInt("salary");
            String email = resultSet.getString("email");
             
            employee = new Employee(employeeid, name, designation, salary, email);
        }
         
        resultSet.close();
        statement.close();
         
        return employee;
    }

}
