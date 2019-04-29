package core;

import com.appleyk.core.dict.UserRoleEnum;
import com.appleyk.core.model.GUser;
import com.appleyk.core.utils.JsonUtils;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 1:59 2019-4-28
 */
public class UserTest {

    @Test
    public void getUserJson(){
        GUser user = new GUser();
        user.setId(123456L);
        user.setName("appleyk");
        user.setRole(UserRoleEnum.SUPER_ADMIN);
        user.setAvatar("https://img-blog.csdnimg.cn/20181106145416961.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FwcGxleWs=,size_16,color_FFFFFF,t_70");
        System.out.println(JsonUtils.objectToJson(user));
    }

    @Test
    public void getUser(){
        String json = "{\n" +
                "\t\"id\": 123456,\n" +
                "\t\"name\": \"appleyk\",\n" +
                "\t\"avatar\": \"https://img-blog.csdnimg.cn/20181106145416961.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0FwcGxleWs=,size_16,color_FFFFFF,t_70\",\n" +
                "\t\"telPhone\": null,\n" +
                "\t\"role\": 0,\n" +
                "\t\"cTime\": \"2019-04-28 14:02:11\"\n" +
                "}";
        GUser user = JsonUtils.jsonToPojo(json, GUser.class);
        System.out.println(user == null ? "" : user.getName());
    }

}
