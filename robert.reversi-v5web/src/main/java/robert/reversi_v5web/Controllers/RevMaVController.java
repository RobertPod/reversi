package robert.reversi_v5web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class RevMaVController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView helloWorld() {

		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world ==");

		return model;
	}

	/*
	 * public ModelAndView handleRequestInternal() { return new
	 * ModelAndView("HelloWorldPage", "msg", "Hello Robert!"); }
	 */
}