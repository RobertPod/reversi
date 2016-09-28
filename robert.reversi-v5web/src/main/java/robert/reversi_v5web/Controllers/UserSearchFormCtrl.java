package robert.reversi_v5web.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import robert.reversi_v5web.impl.SprDataUserDAO;
import robert.reversi_v5web.impl.UserSprDataImpl;

@Controller
public class UserSearchFormCtrl {
	@Autowired
	protected SprDataUserDAO userDaoSpr;

	@RequestMapping(value = "/UserSearchForm", method = RequestMethod.GET)
	public String userSearchGET(HttpSession session, Model model) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		model.addAttribute("message1", "Wyszukiwanie użytkowników");
		model.addAttribute("message2",
				"Wprowadź <strong>ID</strong> lub <strong>email</strong> szukanego użytkownika.<br />"
						+ "W pierwszej kolejności szukamy po ID a jeżeli to jest puste to szukamy na podstawie adresu email.<br />"
						+ "Jeżeli wynik wyszukiwania jest pusty lub nie wypełnino pól wyświetlana jest pusta lista.");
		model.addAttribute("message3", "<br />&nbsp;<br />");

		return "/UserSearchForm";
	}

	@RequestMapping(value = "/UserSearchForm", method = RequestMethod.POST, params = { "submit" })
	public String userSearchPOST(HttpSession session,
			@RequestParam(required = false, defaultValue = "") String searchID,
			@RequestParam(required = false, defaultValue = "") String searchEmail, Model model,
			@ModelAttribute("formlist") List<UserSprDataImpl> formlist) {

		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

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
			UserSprDataImpl searchRes;
			searchRes = userDaoSpr.findByUserId(longID);
			if (searchRes != null) {
				formlist.add(searchRes);
				model.addAttribute("message3", "<br />&nbsp;<br />Wynik wyszukiwania wg. ID: " + searchID);
			} else {
				UserSprDataImpl userDAOTmp = new UserSprDataImpl();
				userDAOTmp.setUserId(0l);
				formlist.add(userDAOTmp);
				model.addAttribute("message3", "<br />&nbsp;<br />Nie znaleziono użytkownika o ID: " + searchID);
			}
		} else if (!searchEmail.isEmpty()) {
			List<UserSprDataImpl> searchResList = userDaoSpr.findByEmailLikeOrderByUserId(searchEmail);
			if (!searchResList.isEmpty()) {
				formlist.addAll(searchResList);
				model.addAttribute("message3", "<br />&nbsp;<br />Wynik wyszukiwania wg. adresu email: " + searchEmail);
			} else {
				UserSprDataImpl userDAOTmp = new UserSprDataImpl();
				userDAOTmp.setUserId(0l);
				formlist.add(userDAOTmp);
				model.addAttribute("message3", "<br />&nbsp;<br />Nie znaleziono użytkownika o email: " + searchEmail);
			}
		}
		return "/UserSearchForm";
	}

	@RequestMapping(value = "/UserSearchForm", method = RequestMethod.POST, params = { "cancel" })
	public String userSearchPOSTCancel(HttpSession session, Model model) {
		return "redirect:/LogginPageForm";

	}

	@ModelAttribute("formlist")
	public List<UserSprDataImpl> getFormsList() {
		List<UserSprDataImpl> userList = new ArrayList<UserSprDataImpl>();
		return userList;
	}
}
