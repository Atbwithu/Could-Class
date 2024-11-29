package com.gbl.service;

import com.gbl.utils.ToKenUtil;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/7/17 1:09
 * @Version: 1.0
 */
@Configuration
public class OutLog {
    public Boolean outLog(String token){
        return ToKenUtil.destroyToken(token);
    }
}
