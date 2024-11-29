package com.gbl.service.study;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.*;
import com.gbl.mapper.AskMapper;
import com.gbl.mapper.KindsMapper;
import com.gbl.mapper.StudyMapper;
import com.gbl.service.kind.IKindsService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:49
 * @Version: 1.0
 */
@Service
public class StudyServiceImpl extends ServiceImpl<StudyMapper, Study> implements IStudyService {

}
