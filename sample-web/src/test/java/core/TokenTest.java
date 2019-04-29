package core;

import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.helper.TokenHelper;
import org.junit.Test;

/**
 * <p>用户令牌测试</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 1:57 2019-4-28
 */
public class TokenTest {

    @Test
    public  void A() throws CommonException{

        // 创建token
        Long userId = 123456L;
        String token = TokenHelper.createToken(userId);
        System.out.println("根据用户ID创建token："+token);

        // 解析token，拿到用户ID
        userId = TokenHelper.verifyToken(token);
        System.out.println("根据用户token拿到用户ID："+userId);

    }

}
