package hello.springmvc.view;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc/response/view")
@Slf4j
public class ResponseViewController {


    /**
     * @RestController -> @Controller + @ResponseBody
     * @param model
     * @param mv
     * @return
     * @RestController는 디스패처에게 viewResolver를 호출하지 않고,Http messageBody data를 그대로 넣고 반환하도록 하는 어노테이션이다.
     */
    @RequestMapping("/v1")
    public ModelAndView responseView(Model model, ModelAndView mv) {
        log.info("come");
        model.addAttribute("data", "data");
        mv.setViewName("/public/hello");
        mv.addObject("data","data");
        return mv;
    }

    /**
     * @Controller
     * @param model
     * @return viewResolver 호출 및 view를 반환
     * @Controller는 디스패처에게 viewResolver를 호출하도록 하는 어노테이션이다.
     */
    @RequestMapping("/v2")
    public String responseString(Model model) {

        model.addAttribute("data", "data");

        return "/public/hello";
    }





}
