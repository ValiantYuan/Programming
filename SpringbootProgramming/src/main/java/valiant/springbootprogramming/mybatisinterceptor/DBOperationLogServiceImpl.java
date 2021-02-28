package valiant.springbootprogramming.mybatisinterceptor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : yuanqi
 * @since : 2020/12/30
 */
@Service
public class DBOperationLogServiceImpl implements DBOperationLogService {
    private final ApplicationContext applicationContext;

    public DBOperationLogServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String getOperatorEmail() {
        return null;
    }

    @Override
    public Map<String, Object> getOriginRecord(String tableName, String condition) {
        return null;
    }

    @Override
    public void publishEvent(LogEvent event) {
        applicationContext.publishEvent(new LogEvent());
    }
}
