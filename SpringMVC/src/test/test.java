package test;
import spring.mvc.annotation.Controller;
import spring.mvc.annotation.RequestMapping;
import spring.mvc.servlet.ModelAndView;
@Controller
public class test {
	@RequestMapping("/hello")
	public ModelAndView hello(ModelAndView mdv) {
		ModelAndView mav=mdv;
		mav.setViewName("test");
		mav.addObject("name", mav.getMap("name"));
		mav.addObject("pas", mav.getMap("pas"));
		return mav;
	}
	@RequestMapping("/hello2")
	public ModelAndView hello2(ModelAndView mdv) {
		ModelAndView mav =mdv;
		mav.setViewName("test");
		return mav;
	}
}
