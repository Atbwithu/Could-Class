package com.gbl.service.ask;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.Ask;
import com.gbl.mapper.AskMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: Insight
 * @Description: 问答
 * @Date: 2024/5/1 15:37
 * @Version: 1.0
 */
@Service
public class AskServiceImpl extends ServiceImpl<AskMapper, Ask> implements IAskService {
}
