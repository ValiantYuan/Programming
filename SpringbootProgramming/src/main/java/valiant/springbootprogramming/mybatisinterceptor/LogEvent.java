package valiant.springbootprogramming.mybatisinterceptor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : yuanqi
 * @since : 2020/12/30
 */
@Data
public class LogEvent {
    private LocalDateTime logTime;
    private String sql;
}
