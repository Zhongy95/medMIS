package com.group7.bus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Examdoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 检查报告 Mapper 接口
 * </p>
 *
 * @author TT
 * @since 2020-06-20
 */
public interface ExamdocMapper extends BaseMapper<Examdoc> {
    @Select("SELECT bus_examdoc.* FROM bus_examdoc, bus_record WHERE bus_examdoc.record_id = bus_record.record_id AND patient_id = #{pid} ORDER BY createtime DESC ")
    List<Examdoc> getExamdocByPatientId(IPage page, @Param("pid") Integer patientId);
}
