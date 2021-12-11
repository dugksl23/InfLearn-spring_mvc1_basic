package hello.springmvc.requestHeader;


import hello.springmvc.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/springmvc/request")
@Slf4j
public class RequestHeaderController {


    @RequestMapping("/headers")
    public String headerTest(
            HttpServletRequest req,
            HttpServletRequest resp,
            HttpMethod httpMethod,
            Locale local,
            @RequestHeader MultiValueMap<String, Object> header,
            @RequestHeader("host") String host,
            @CookieValue(value = "cookie", required = false) String myCookie,
            @RequestParam(name = "username", required = true, defaultValue = "10") String username,
            @RequestParam Map<String, Object> paramMap,
            @RequestParam MultiValueMap<String, Object> paramMultiValueMapv2) {

        log.info("request = {}", req);
        log.info("response = {}", resp);
        log.info("HttpMethod = {}", httpMethod);
        log.info("Locale = {} 언어_국가, {}, {},{},{}", local, local.getCountry(), local.getDisplayCountry(), local.getLanguage(), local.getUnicodeLocaleKeys());
        log.info("header = {}", header);
        log.info("host = {}", host);
        log.info("host = {}", header.get("host"));
        log.info("myCookie = {}", header.get("cookie"));
        log.info("myCookie = {}", myCookie);

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("a", "a");
        multiValueMap.add("a", "b");
        List<Object> multiValue = multiValueMap.get("a");
        multiValue.stream().forEach(value -> System.out.println("value : " + value));

        log.info("username : {}", username);

        //paramMap.keySet().iterator().forEachRemaining(k -> log.info("key : {}, value : {}", k, paramMap.get(k)));
        paramMultiValueMapv2.keySet().iterator().forEachRemaining(k -> log.info("key : {}, value : {}", k, paramMap.get(k)));
        List<Object> username1 = paramMultiValueMapv2.get("username");
        username1.stream().forEach(System.out::println);


        return "ok";
    }

    @RequestMapping("/modelAttribute")
    public String modelAttribute(@ModelAttribute MemberDto member, Model model) {

        System.out.println("username : " + member.getUsername());
        System.out.println("age : " + member.getAge());
        model.addAttribute("model", model);

        return member.toString();
    }

}
