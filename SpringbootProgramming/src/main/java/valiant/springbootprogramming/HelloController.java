package valiant.springbootprogramming;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import valiant.springbootprogramming.bean.AuthSystems;
import valiant.springbootprogramming.service.AcceptService;
import valiant.springbootprogramming.service.PersonService;

/**
 * @ClassName HelloController
 * @Description: TODO
 * @Author: YuanQi
 * @Date: 2019/11/26
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    private PersonService personService;
    private AuthSystems authSystems;
    private AcceptService acceptService;

    public HelloController(PersonService personService,
                           AuthSystems authSystems,
                           AcceptService acceptService) {
        this.personService = personService;
        this.authSystems = authSystems;
        this.acceptService = acceptService;
    }

    @RequestMapping("/index")
    public String index() {
        System.out.println();
        try {
            return personService.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping("/retry-test")
    public String retry() throws Exception {
        return String.valueOf(acceptService.retryMethod());
    }



}
