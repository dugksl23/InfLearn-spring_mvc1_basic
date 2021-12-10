package hello.springmvc.requestMapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/springmvc/restapi/users")
public class MappingClassController {


    /**
     * 회원 List 조회
     */
    @GetMapping
    public String fetchUserList() {
        log.info("userList get");
        return "got users";
    }

    /**
     * 회원 단일 조회
     */
    @GetMapping("/{userId}")
    public String fetchUser(@PathVariable("userId") Integer userId) {
        log.info("userId : {}", userId);
        return "got user : " + userId;
    }

    /**
     * 회원 수정
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Integer userId) {
        log.info("userId : {}", userId);
        return "updated user : " + userId;
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        log.info("userId : {}", userId);
        return "deleted user : " + userId;
    }


    /**
     * 회원 등록
     */
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@PathVariable("userId") Integer userId) {
        log.info("userId : {}", userId);
        return "registered user : " + userId;
    }




}
