package controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUser;
import model.User;
import utils.Validate;


@WebServlet("/ServletLoginUser")
public class ServletLoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/SocialNetworkApp")
	private DataSource miPool;
	private DaoUser daoUser;
	private User newUser;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			login(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> errorsList = new ArrayList<>();
		daoUser = new DaoUser(miPool);
		String email = "";
		String password = "";
		boolean foundPassword = false;

		email = request.getParameter("email");
		password = request.getParameter("password");
		
		if (email.length() == 0) {
			errorsList.add("The email field cannot be empty");
		}

		if (email.length() != 0 && !Validate.isEmail(email)) {
			errorsList.add("The email doesn't have the required format");
		}
		newUser = daoUser.getUserByEmail(email);
		if (newUser == null) {
			errorsList.add("This email doesn't exist.");
		} 

		if (password.length() == 0) {
			errorsList.add("The password cannot be empty.");
		}
		
		if (newUser != null) {
			if (password.equals(newUser.getPassword())) {
				foundPassword = true;
			} else {
				foundPassword= false;
				errorsList.add("Incorrect password");
			}
		}

		if (foundPassword) {
			request.getSession().setAttribute("loggedUser", newUser);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.setAttribute("errorsList", errorsList);
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		}
	}
}

