package robert.reversi_v5web.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import robert.reversi_v5web.api.UserDAO;
import robert.reversi_v5web.impl.HibDBUserDAOImpl;
import robert.reversi_v5web.impl.UserDAOImpl;

@Controller
public class UserSearchFormCtrl {
	@Autowired
	private HibDBUserDAOImpl hibDBUserDaoImpl;

	@RequestMapping(value = "/userSearchForm", method = RequestMethod.GET)
	public String userSearchGET(Model model) {
		model.addAttribute("message1", "Wyszukiwanie użytkowników");
		model.addAttribute("message2",
				"Wprowadź <strong>ID</strong> lub <strong>email</strong> szukanego użytkownika.<br />"
						+ "W pierwszej kolejności szukamy po ID a jeżeli to jest puste to szukamy na podstawie adresu email.<br />"
						+ "Jeżeli wynik wyszukiwania jest pusty lub nie wypełnino pól wyświetlana jest pusta lista.");
		model.addAttribute("message3", "<br />&nbsp;<br />");

		return "/userSearchForm";
	}

	@RequestMapping(value = "/userSearchForm", method = RequestMethod.POST, params = { "submit" })
	public String userSearchPOST(@RequestParam(required = false, defaultValue = "") String searchID,
			@RequestParam(required = false, defaultValue = "") String searchEmail, Model model,
			@ModelAttribute("formlist") List<UserDAOImpl> formlist) {
		model.addAttribute("message1", "Wyszukiwanie użytkowników");
		model.addAttribute("message2",
				"Wprowadź <strong>ID</strong> lub <strong>email</strong> szukanego użytkownika.<br />"
						+ "W pierwszej kolejności szukamy po <strong>ID</strong> a jeżeli to jest puste to szukamy na podstawie adresu <strong>email</strong>.<br />"
						+ "Jeżeli wynik wyszukiwania jest pusty lub nie wypełnino pól wyświetlana jest pusta lista.");
		if (!searchID.isEmpty()) {
			long longID;
			try {
				longID = Long.parseLong(searchID, 10);
			} catch (NumberFormatException nfe) {
				longID = 0l;
			}
			UserDAO searchRes;
			searchRes = hibDBUserDaoImpl.getUser(longID);
			if (searchRes != null) {
				// form.copyUserObj(searchRes);
				formlist.add((UserDAOImpl) searchRes);
				model.addAttribute("message3", "<br />&nbsp;<br />Wynik wyszukiwania wg. ID: " + searchID);
			} else {
				// form.setUserID(0l);
				UserDAO userDAOTmp = new UserDAOImpl();
				userDAOTmp.setUserID(0l);
				formlist.add((UserDAOImpl) userDAOTmp);
				model.addAttribute("message3", "<br />&nbsp;<br />Nie znaleziono użytkownika o ID: " + searchID);
			}
		} else if (!searchEmail.isEmpty()) {
			// UserDAO searchRes =
			// hibDBUserDaoImpl.searchFirstUserByEmail(searchEmail); -
			// wyszukanie jednego elementu
			List<? extends Object> searchResList = hibDBUserDaoImpl.searchUserByEmail(searchEmail);
			// UserDAO searchRes =
			// hibDBUserDaoImpl.searchFirstUserByEmail(searchEmail); -

			if (!searchResList.isEmpty()) {
				formlist.addAll((Collection<? extends UserDAOImpl>) searchResList); // add((UserDAOImpl)
																					// searchRes);
				model.addAttribute("message3", "<br />&nbsp;<br />Wynik wyszukiwania wg. adresu email: " + searchEmail);
			} else {
				UserDAO userDAOTmp = new UserDAOImpl();
				userDAOTmp.setUserID(0l);
				formlist.add((UserDAOImpl) userDAOTmp);
				model.addAttribute("message3", "<br />&nbsp;<br />Nie znaleziono użytkownika o email: " + searchEmail);
			}
		}
		return "/userSearchForm";
	}

	@RequestMapping(value = "/userSearchForm", method = RequestMethod.POST, params = { "cancel" })
	public String userSearchPOSTCancel(Model model) {
		return "redirect:/startform";

	}

	@ModelAttribute("form")
	public UserDAOImpl getFormularz() {
		return new UserDAOImpl();
	}

	@ModelAttribute("formlist")
	public List<UserDAOImpl> getFormsList() {
		List<UserDAOImpl> userList = new ArrayList<UserDAOImpl>();
		return userList;
	}
}
