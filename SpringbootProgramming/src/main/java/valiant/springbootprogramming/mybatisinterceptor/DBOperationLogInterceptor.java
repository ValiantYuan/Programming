package valiant.springbootprogramming.mybatisinterceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author : yuanqi
 * @since : 2020/12/30
 */

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class DBOperationLogInterceptor implements Interceptor {

    private final DBOperationLogService dbOperationLogService;

    public DBOperationLogInterceptor(DBOperationLogService dbOperationLogService) {
        this.dbOperationLogService = dbOperationLogService;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        String operatorEmail = dbOperationLogService.getOperatorEmail();
        String tableName = null;
        String condition = null;

        // update方法的参数列表中，MappedStatement是第一个
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // update方法的参数列表中，变量对象是第二个
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        Configuration configuration = mappedStatement.getConfiguration();
        // 获取sql语句
        String sql = getSql(configuration, boundSql);
        sql = sql.replaceAll("\'", "").replaceAll("\"", "");
        String splod = sql.toLowerCase().replace("call", "");
        LogEvent logEvent = new LogEvent();
        if (!splod.contains("sys_operation_log")) {
            logEvent.setLogTime(LocalDateTime.now());
            logEvent.setSql(splod);

            String operationType = getOperationType(splod);
            if (operationType.equals("update")) {

            } else if (operationType.equals("insert")) {

            } else if (operationType.equals("delete")) {

            }

        }


        Map<String, Object> result = dbOperationLogService.getOriginRecord(tableName, condition);
        dbOperationLogService.publishEvent(logEvent);
        return null;
    }

    private String getOperationType(String sql) {
        String[] strings = sql.split(" ");
        if (strings.length > 1) {
            return strings[0];
        }
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String getSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping: parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private String getParameterValue(Object obj) {
        String value = null;


        return value;
    }
}
