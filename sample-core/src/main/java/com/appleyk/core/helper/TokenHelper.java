package com.appleyk.core.helper;

import com.appleyk.core.common.ex.CommonException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>用户令牌帮助类 -- 生成和解析</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 1:24 2019-4-28
 */
public class TokenHelper {


    private static final String TYPE = "JWT";
    private static final String ALGORITHM = "Appleyk521";
    private static final String ISSUER = "https://blog.csdn.net/appleyk";
    private static final String USER_ID = "uid";

    /**
     * 服务器端秘钥
     */
    private static final String SECRET = "USER-AUTH-SERVER-KEY";


    /**
     * 创建令牌
     * @param userId 用户ID
     * @return GxSessionInfo
     */
    public synchronized static String  createToken(Long userId) throws CommonException {

        Long systemTime = System.currentTimeMillis();
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        //JWT签名头部
        Map<String,Object> header = new HashMap<>(2);
        header.put("typ", TYPE);
        header.put("alg", ALGORITHM);

        //生成token
        return JWT.create()
                .withClaim(USER_ID,userId)
                .withHeader(header)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date(systemTime))
                .sign(algorithm);
    }

    /**
     * 验证令牌是否合法
     * @param token 用户令牌
     */
    public synchronized static Long verifyToken(String token) throws CommonException {

        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim(USER_ID);
        if (!claim.isNull()) {
            return claim.asLong();
        }
        return 0L;

    }

    /**
     * 根据标签反解令牌信息
     */
    public static Long decodeToken(String token, String name) {

        if (StringUtils.isNotBlank(token)) {
            try {
                DecodedJWT jwt = JWT.decode(token);
                Claim claim = jwt.getClaim(name);
                if (!claim.isNull()) {
                    return claim.asLong();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }
}
