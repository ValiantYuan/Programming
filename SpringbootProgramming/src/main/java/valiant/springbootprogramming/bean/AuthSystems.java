package valiant.springbootprogramming.bean;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AuthSystems
 * @Description:
 * @Author: YuanQi
 * @Date: 2020/4/23
 */
@ConfigurationProperties(prefix = "auth")
@Component
@Data
public class AuthSystems {
    List<System> systems = new ArrayList<>();
}
