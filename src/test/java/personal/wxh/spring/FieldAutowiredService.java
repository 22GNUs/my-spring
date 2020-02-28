package personal.wxh.spring;

/**
 * 测试属性注入的Service
 * @author wangxinhua
 * @since 1.0
 */
public class FieldAutowiredService {
    // 待注入属性
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
