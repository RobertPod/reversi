package robert.reversi_v5web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * uzupełnić
 */
@Controller
public class NewUserOKFormCtrl {
	@RequestMapping(value = "/newUserOKForm", method = RequestMethod.GET)
	public String newUserOKFormGET() {
		return "newUserOKForm";
	}

	@RequestMapping(value = "/newUserOKForm", method = RequestMethod.POST)
	public String newUserOKFormPOST() {
		return "redirect:/startForm";
	}
}
