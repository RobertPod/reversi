package robert.reversi_v5web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * uzupełnić
 */
@Controller
public class NewUserOKFormCtrl {
	@RequestMapping(value = "/newuserokform", method = RequestMethod.GET)
	public String newUserOKFormGET() {
		return "newuserokform";
	}

	@RequestMapping(value = "/newuserokform", method = RequestMethod.POST)
	public String newUserOKFormPOST() {
		return "redirect:/startform";
	}
}
