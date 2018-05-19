package Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;
    
    public void init() {
    	String name = getServletContext().getInitParameter("jdbcName");
		String pwd = getServletContext().getInitParameter("jdbcPwd");
		String url = getServletContext().getInitParameter("jdbcUrl");
    	employeeDAO = new EmployeeDAO(name, pwd, url);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            if(action.equals("/Employee/new")) {
            	showNewForm(request, response);
            }else if(action.equals("/Employee/insert")) {
            	insertEmployee(request, response);
            }else if(action.equals("/Employee/delete")) {
            	deleteEmployee(request, response);
            }else if(action.equals("/Employee/edit")) {
            	showEditForm(request, response);
            }else if(action.equals("/Employee/update")) {
            	updateEmployee(request, response);
            }else {
            	listEmployee(request, response);
            }
        }catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDAO.listAllEmployees();
        request.setAttribute("listEmployees", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeeDAO.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
    }
    
    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	int employeeid = Integer.parseInt(request.getParameter("employeeid"));
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        int salary = Integer.parseInt(request.getParameter("salary"));
        String email = request.getParameter("email");
 
        Employee newEmployee = new Employee(employeeid, name, designation, salary, email);
        employeeDAO.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }
    
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int employeeid = Integer.parseInt(request.getParameter("employeeid"));
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        int salary = Integer.parseInt(request.getParameter("salary"));
        String email = request.getParameter("email");
 
        Employee employee = new Employee(id, employeeid, name, designation, salary, email);
        employeeDAO.updateEmployee(employee);
        response.sendRedirect("list");
    }
    
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Employee employee = new Employee(id);
        employeeDAO.deleteEmployee(employee);
        response.sendRedirect("list");
    }
}
