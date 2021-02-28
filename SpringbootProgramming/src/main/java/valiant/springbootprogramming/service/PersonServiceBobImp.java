package valiant.springbootprogramming.service;

import org.springframework.stereotype.Service;

import valiant.springbootprogramming.bean.Person;

/**
 * @ClassName PersonServiceImp
 * @Description:
 * @Author: YuanQi
 * @Date: 2020/4/23
 */
@Service("personServiceBobImp")
public class PersonServiceBobImp {
    private Person person;

    public PersonServiceBobImp(Person person) {
        this.person = person;
    }

//    @Override
    public String action() {
        return person.introduce();
    }
}
