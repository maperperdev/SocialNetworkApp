package controller;

import java.util.List;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import dao.DaoUser;
import model.User;
import utils.Validate;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServletUsuarioInsertar
 */
@WebServlet("/ServletInsertUser")
public class ServletInsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/SocialNetworkApp")
	private DataSource myPool;
	private User newUser;
	private DaoUser daoUser;
	private User foundUser;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			insertUserDB(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void insertUserDB(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> errorsList = new ArrayList<>();
		daoUser = new DaoUser(myPool);
		String nick = "";
		String email = "";
		String dateBirthSTR = "";
		Date dateBirth = null;
		String password = "";
		String repeatedPassword = "";
		Date dateRegister = Date.valueOf(LocalDate.now());
		String bio = "";

		nick = request.getParameter("nick");
		email = request.getParameter("email");
		dateBirthSTR = request.getParameter("dateBirth");
		password = request.getParameter("password");
		repeatedPassword = request.getParameter("repeatedPassword");
		bio = request.getParameter("bio");

		if (nick.length() == 0) {
			errorsList.add("The name cannot be empty.");
		} else {
			request.setAttribute("nick", nick);
		}


		if (dateBirthSTR.length() == 0) {
			errorsList.add("Date of birth cannot be empty");
		} else {
			dateBirth = Validate.convertDates(dateBirthSTR);
			if (dateBirth == null) {
				errorsList.add("Date of birth is not correct.");
			}
		}

		if (email.length() == 0) {
			errorsList.add("The email cannot be empty");
		}

		if (email.length() != 0 && !Validate.isEmail(email)) {
			errorsList.add("The email has not a correct format.");
		} else {
			request.setAttribute("email", email);
		}

		foundUser = daoUser.getUserByEmail(email);

		if (foundUser != null) {
			errorsList.add("You cannot register with an registed email.");
		}

		if (password.length() == 0) {
			errorsList.add("The password cannot be empty");
		} else {
			if (!password.equals(repeatedPassword)) {
				errorsList.add("The passwords are not equal.");
			}
		}

		if (errorsList.size() == 0) {
			newUser = new User(nick, email, dateBirth, dateRegister, password, bio);
			System.out.println(newUser);
			daoUser.insert(newUser);
			request.getSession().setAttribute("loggedUser", newUser);

			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("errorsList", errorsList);
			request.getRequestDispatcher("views/insert.jsp").forward(request, response);
		}
	}



}
