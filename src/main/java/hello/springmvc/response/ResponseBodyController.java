package hello.springmvc.response;


import hello.springmvc.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Member;

@Controller
@RequestMapping("/springmvc/response/body")
public class ResponseBodyController {


    @GetMapping("/servlet")
    public void servletResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("return");
    }

    @GetMapping("/stringReturn")
    @ResponseBody
    public String stringReturn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "stringReturn";
    }

    @GetMapping("/responseEntityToString")
    public ResponseEntity responseEntityToString(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return new ResponseEntity("{\"username\":\"dd\",\"age\":10}", HttpStatus.OK); // String 반환
    }

    @GetMapping("/responseEntityToJson")
    public ResponseEntity responseEntityToJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        MemberDto memberDto = new MemberDto();
        memberDto.setAge(10);
        memberDto.setUsername("dd");
        return new ResponseEntity(memberDto, HttpStatus.OK); // json 반환
    }

}
