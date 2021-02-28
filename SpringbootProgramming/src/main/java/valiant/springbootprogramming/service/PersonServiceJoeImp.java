package valiant.springbootprogramming.service;

import org.springframework.stereotype.Service;

import valiant.springbootprogramming.component.Person;

/**
 * @ClassName PersonServiceImp
 * @Description:
 * @Author: YuanQi
 * @Date: 2020/4/23
 */
@Service
public class PersonServiceJoeImp implements PersonService {
    private Person person;

    public PersonServiceJoeImp(Person person) {
        this.person = person;
    }

    @Override
    public String action() {
        return person.introduce();
    }
}
