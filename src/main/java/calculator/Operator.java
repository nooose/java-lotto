package calculator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Operator {
    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    private static final Map<String, Operator> CACHE = new ConcurrentHashMap<>();

    static {
        CACHE.put(ADD, new Add());
        CACHE.put(SUBTRACT, new Subtract());
        CACHE.put(MULTIPLY, new Multiply());
        CACHE.put(DIVIDE, new Divide());
    }

    public static Operator mapping(String operator) {
        Operator result = CACHE.get(operator);
        if (result == null) {
            throw new IllegalArgumentException(operator + "은(는) 지원하지 않는 연산자입니다.");
        }
        return result;
    }

    abstract int operate(int num1, int num2);
}
