package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
public class LogTestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/log-test")
    public ResponseEntity<?> test(@RequestParam("username") String username) {
        log.trace("username, {}" + username);
        log.debug("username, {}", username); // 개발서버
        log.info("username, {}", username);  // 운용서버
        log.warn("username, {}", username);
        log.error("username, {}", username); // try-catch exception

        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
    }
}
