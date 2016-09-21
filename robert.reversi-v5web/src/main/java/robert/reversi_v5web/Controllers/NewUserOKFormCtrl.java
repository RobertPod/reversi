package robert.reversi_v5web.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * uzupełnić
 */
@Controller
public class NewUserOKFormCtrl {
	@RequestMapping(value = "/newUserOKForm", method = RequestMethod.GET)
	public String newUserOKFormGET(HttpSession session) {
		if (session.isNew()) {
			return "redirect:/LogginPageForm";
		}

		return "newUserOKForm";
	}

	@RequestMapping(value = "/newUserOKForm", method = RequestMethod.POST)
	public String newUserOKFormPOST(HttpSession session) {
		return "redirect:/LogginPageForm";
	}
}
