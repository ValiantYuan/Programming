package valiant.springbootprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yuanqi
 * @since : 2021/3/30
 * 还缺乏一些抽象，比如获取':"获取',',判断是否结束
 */
public class JsonParser {

    int index = 1;

    public Map<String, Object> parse(String jsonStr) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (jsonStr == null || jsonStr.length() == 0) {
            return resultMap;
        }

        if (jsonStr.charAt(0) != '{') {
            throw new Exception("invalid json string");
        }

        if (jsonStr.charAt(jsonStr.length() - 1) != '}') {
            throw new Exception("invalid json string");
        }
        // ','还没有处理
        while(jsonStr.charAt(index) != '}') {
            // 函数返回后，index指向上一个键值对的逗号后一位
            String keyName = getKeyName(jsonStr);
            // 函数返回后，index指向上一个键值对的逗号后一位
            Object value = getValue(jsonStr);
            resultMap.put(keyName, value);
        }
        return resultMap;
    }

    /**
     * 从当前位置开始获取key的名字
     */
    public String getKeyName(String jsonStr) throws Exception {
        // trim
        while (jsonStr.charAt(index) == '\n'
            || jsonStr.charAt(index) == ' '
            || jsonStr.charAt(index) == '\t') {
            index++;
        }

        if (jsonStr.charAt(index) == '"') {
            StringBuilder keyName = new StringBuilder();
            index++;
            while (jsonStr.charAt(index) != '"') {
                keyName.append(jsonStr.charAt(index));
                index++;
            }
            // 此时index指向'"'，要指向下一个':'
            while(jsonStr.charAt(index) != ':') {
                index++;
            }
            // 函数返回时index指向':'，继续后移
            index++;
            return keyName.toString();
        }
        throw new Exception("invalid json string");
    }


    public Object getValue(String jsonStr) throws Exception {
        // trim
        while (jsonStr.charAt(index) == '\n'
            || jsonStr.charAt(index) == ' '
            || jsonStr.charAt(index) == '\t') {
            index++;
        }
        // string类型
        if (jsonStr.charAt(index) == '"') {
            index++;
            StringBuilder value = new StringBuilder();
            while (jsonStr.charAt(index) != '"') {
                value.append(jsonStr.charAt(index));
                index++;
            }
            while(jsonStr.charAt(index) != ',') {
                if (jsonStr.charAt(index) == '{') {
                    return Boolean.valueOf(value.toString());
                }
                index++;
            }
            // 函数返回时index指向',',继续后移
            index++;
            return value.toString();
        }
        // boolean类型
        else if (jsonStr.charAt(index) == 't'
            || jsonStr.charAt(index) == 'f') {
            StringBuilder value = new StringBuilder();
            if (jsonStr.charAt(index) == 't') {
                for (int i = 0; i < 4; i++) {
                    value.append(jsonStr.charAt(index));
                    index++;
                }
            } else if (jsonStr.charAt(index) == 'f') {
                for (int i = 0; i < 5; i++) {
                    value.append(jsonStr.charAt(index));
                    index++;
                }
            }
            while(jsonStr.charAt(index) != ',') {
                if (jsonStr.charAt(index) == '}') {
                    return Boolean.valueOf(value.toString());
                }
                index++;
            }
            // 函数返回时index指向',',继续后移
            index++;
            return Boolean.valueOf(value.toString());
        }
        // 整数类型
        else if (jsonStr.charAt(index) - '0' >= 0 && jsonStr.charAt(index) - '9' <= 0) {
            StringBuilder value = new StringBuilder();
            while (jsonStr.charAt(index) - '0' >= 0 && jsonStr.charAt(index) - '9' <= 0) {
                value.append(jsonStr.charAt(index));
                index++;
            }
            while(jsonStr.charAt(index) != ',') {
                if (jsonStr.charAt(index) == '{') {
                    return Boolean.valueOf(value.toString());
                }
                index++;
            }
            // 函数返回时index指向',',继续后移
            index++;
            return Integer.parseInt(value.toString());
        } else {
            throw new Exception("invalid json string");
        }
    }

    public static void main(String[] args) throws Exception {
        String jsonStr = "{\n" +
            "    \"name\" :    \"yuanqi\"   ,\n" +
            "    \"age\":   31,\n" +
            "    \"merried\" : true\n" +
            "}";

        System.out.println(new JsonParser().parse(jsonStr));
    }

}
