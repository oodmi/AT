package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/view")
public class ViewController {

    @RequestMapping(value = "/book/", method = RequestMethod.GET)
    public ModelAndView getBookView(){
        return new ModelAndView("book");
    }

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ModelAndView getUserView(){
        return new ModelAndView("user");
    }

}
