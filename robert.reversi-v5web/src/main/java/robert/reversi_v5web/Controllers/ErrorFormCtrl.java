package robert.reversi_v5web.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorFormCtrl {
	@RequestMapping("/errorForm")
	public String errorPage(HttpSession session, Model model) {
		if (session.isNew()) {
			return "redirect:/startForm";
		}
		return ("/errorForm");
	}
}
