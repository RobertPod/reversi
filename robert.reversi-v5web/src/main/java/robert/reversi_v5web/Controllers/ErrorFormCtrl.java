package robert.reversi_v5web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorFormCtrl {
	@RequestMapping("/errorform")
	public String errorPage(Model model) {
		return ("/errorform");
	}
}
