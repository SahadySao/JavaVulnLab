package vulnlab.nomvue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/** new ModelAndView("pages/" + page) : ../ ou prétraitement Thymeleaf. */
@Controller
public class NomVueModelAndView {
    @GetMapping("/nomvue/mav")
    public ModelAndView show(@RequestParam String page) {
        return new ModelAndView("pages/" + page);
    }
}
