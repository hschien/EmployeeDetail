package Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.ucanaccess.jdbc.JackcessOpenerInterface;

public class EmployeeDAO {
	private Connection connDB;
	protected void connect() throws SQLException{
		if(connDB==null||connDB.isClosed()) {
			try {
				Class.forName("net.ucanaccess.jbdc.UcanaccessDriver");
			}catch(ClassNotFoundException e) {
				throw new SQLException(e);
			}
			String database="jdbc:ucanaccess://C:/Users/ªY»ö/Desktop/case study/Employee-Detail1.accdb";
			connDB=DriverManager.getConnection(database);
		}
	}
	protected void disconnect() throws SQLException{
		if(connDB!=null&&!connDB.isClosed()) {
			connDB.close();
		}
	}
	public boolean insertEmployee(Employee employee) throws SQLException{
		String sql="INSERT INTO employee (id, name, designation, salary, email) VALUES (?, ?, ?, ?, ?)";
		
		connect();
		
		PreparedStatement statement=connDB.prepareStatement(sql);
		statement.setInt(1, employee.getId());
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
		String sql="SELECT * FROM employee";
		
		connect();
		
		Statement statement=connDB.createStatement();
		ResultSet resultSet=statement.executeQuery(sql);
		
		while(resultSet.next()) {
			int id=resultSet.getInt("id");
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
		String sql="DELETE FROM employee where id=?";
		
		connect();
		
		PreparedStatement statement=connDB.prepareStatement(sql);
		statement.setInt(1, employee.getId());
		
		boolean rowDeleted=statement.executeUpdate()>0;
		statement.close();
		disconnect();
		return rowDeleted;
	}
	
	public boolean updateEmployee(Employee employee) throws SQLException{
		String sql="UPDATE employee SET name=?,designation=?,salary=?,email=?";
		sql+=" WHERE id=?";
		connect();
		
		PreparedStatement statement=connDB.prepareStatement(sql);
		statement.setString(1, employee.getName());
        statement.setString(2, employee.getDesignation());
        statement.setInt(3, employee.getSalary());
        statement.setString(4, employee.getEmail());
        statement.setInt(5, employee.getId());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
	}
	
	public Employee getEmployee(int id) throws SQLException {
		Employee employee = null;
        String sql = "SELECT * FROM employee WHERE id = ?";
         
        connect();
         
        PreparedStatement statement = connDB.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String designation = resultSet.getString("designation");
            int salary = resultSet.getInt("salary");
            String email = resultSet.getString("email");
             
            employee = new Employee(id, name, designation, salary, email);
        }
         
        resultSet.close();
        statement.close();
         
        return employee;
    }

}
