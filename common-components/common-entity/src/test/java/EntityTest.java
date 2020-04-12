import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityTest {

    @Test
    public void phone()
    {
        String phone = "15001390311";
        String regex = "^[1][3,4,5,7,8][0-9]{9}$";

        Pattern pattern = Pattern.compile(regex);    // 编译正则表达式
        Matcher matcher = pattern.matcher(phone);    // 创建给定输入模式的匹配器
        boolean bool = matcher.matches();
        if(bool) { // 如果验证通过
            System.out.println("输入的电话号码格式正确。");
        } else {
            System.out.println("输入的电话号码无效，格式不正确。");
        }
    }

}
