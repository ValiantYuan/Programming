package valiant.springbootprogramming.mybatisinterceptor;

import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author : yuanqi
 * @since : 2020/12/30
 */
public interface DBOperationLogService {
    String getOperatorEmail();
    Map<String, Object> getOriginRecord(String tableName, String condition);
    void publishEvent(LogEvent event);

}
