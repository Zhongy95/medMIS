package com.group7.bus.service.impl;

import com.group7.bus.entity.Record;
import com.group7.bus.mapper.RecordMapper;
import com.group7.bus.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 病历记录 服务实现类
 * </p>
 *
 * @author Robin
 * @since 2020-06-17
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

}
