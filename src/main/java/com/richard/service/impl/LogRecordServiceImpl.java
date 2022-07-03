package com.richard.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.richard.entity.LogRecord;
import com.richard.mapper.LogRecordMapper;
import com.richard.service.LogRecordService;
import org.springframework.stereotype.Service;

/**
 * @author Richard
 * @version 1.0
 * @description: 日志接口实现类
 * @date 2021/12/30 14:42
 */
@Service
public class LogRecordServiceImpl extends ServiceImpl<LogRecordMapper, LogRecord> implements LogRecordService {

}
