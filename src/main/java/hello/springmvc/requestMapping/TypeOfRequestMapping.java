package hello.springmvc.requestMapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/springmvc/type/request")
@Slf4j
public class TypeOfRequestMapping {


    /**
     * 다중 경로 요청
     */
    @RequestMapping({"", "/normal"})
    public String normalRequestMapping() {
        return "ok";
    }

    @GetMapping({"/getRequest"})
    public String GetMapping() {
        return "ok";
    }

    @PostMapping({"/postRequest"})
    public String postMapping(String username) {
        log.info("username : {}", username);
        return "ok";
    }

    /**
     * @param userId
     * @return
     * @pathVariable + post 요청
     */
    @PostMapping({"/post/{userId}"})
    public String pathVariablePostTest(@PathVariable("userId") String username, String userId) {
        log.info("username : {}", username);
        log.info("userid : {}", userId);

        return "ok" + userId;
    }


    /**
     * @param userId
     * @return
     * @pathVariable + get 요청
     */
    @GetMapping({"/get/{userId}"})
    public String pathVariablePostTest(@PathVariable("userId") String userId) {
        log.info("username : {}", userId);

        return "ok" + userId;
    }

    /**
     * @param userId
     * @param orderId
     * @return
     * @PathVariable 다중 요청
     */

    @GetMapping({"/user/{userId}/order/{orderId}"})
    public String multiPathVariableTest(@PathVariable("userId") String userId, @PathVariable Integer orderId) {
        log.info("username : {}", userId);
        log.info("orderId : {}", orderId);

        return "ok" + userId;
    }


    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/param-test/{userId}", params = "userId=1")
    public String paramRequestTest(@PathVariable("userId") String userId) {
        log.info("userid : {}", userId);
        return userId;
    }


    @GetMapping(value = "/header-test/{userId}", headers = "userId=1")
    public String headerRequestTest(@PathVariable("userId") String userId) {
        log.info("userid : {}", userId);
        return userId;
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @GetMapping(value = "/contentType/consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String contentTypeConsumeTest(String userId) {
        log.info("userId : {}", userId);
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type * produces = "text/html"
     * produces = "!text/html" * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/accept/produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }


}

