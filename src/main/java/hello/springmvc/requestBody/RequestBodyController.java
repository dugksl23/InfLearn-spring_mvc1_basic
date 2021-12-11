package hello.springmvc.requestBody;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/springmvc/requestBody")
@Slf4j
public class RequestBodyController {


    @PostMapping("/v1")
    public String httpBodyTest(HttpServletRequest req, MemberDto member) throws IOException {

        ServletInputStream inputStream = req.getInputStream();// Stream은 bytecode로 되어있다.
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// byte-code를 문자로 받을 때는 반드시 charset 인코딩이 필요하다.
        MemberDto member1 = new ObjectMapper().readValue(s, MemberDto.class);
        System.out.println(member1.toString());
        log.info("s : {}", s);

        return member1.toString();
    }


    @PostMapping("/v2")
    public String httpBodyTest(InputStream inputStream, MemberDto member) throws IOException {

        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// byte-code를 문자로 받을 때는 반드시 charset 인코딩이 필요하다.
        MemberDto member1 = new ObjectMapper().readValue(s, MemberDto.class);
        System.out.println(member1.toString());
        log.info("s : {}", s);

        return member1.toString();
    }

    @PostMapping("/v3")
    public HttpEntity httpBodyTest(HttpEntity<String> httpEntity, MemberDto member) throws IOException {

        String body = httpEntity.getBody();
        log.info(body); // json 타입
        MemberDto member1 = new ObjectMapper().readValue(body, MemberDto.class);// toString
        System.out.println(member1.toString());
        //httpEntity body에 담아서 보낸다.
        return new HttpEntity(member1);
    }

    @PostMapping("/v4")
    public ResponseEntity httpBodyTest(@RequestBody MemberDto member) throws IOException {

        //@RequestBody
        // 메세지 컨버터를 통해서,      MemberDto member1 = new ObjectMapper().readValue(body, MemberDto.class);// toString
        // 상기의 코드를 거친 후에 변환시킨다.

        log.info(member.toString()); // json 타입

        return new ResponseEntity<String>(String.valueOf(member), HttpStatus.OK);
    }

    @PostMapping("/v5")
    @ResponseBody // HttpEntity message body 에 담고 view를 거치지 않고, 반환한다.
    public String requestBodyAndResponseBodyTest(@RequestBody MemberDto member) throws IOException {

        log.info(member.toString()); // json 타입

        return member.toString();
    }

    @PostMapping("/v6/text")
    @ResponseBody
    public String requestBodyStringTest(@RequestBody String member) throws IOException {

        log.info(member); // txt

        return member.toString();
    }


}
