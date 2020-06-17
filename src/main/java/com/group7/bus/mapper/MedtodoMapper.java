package com.group7.bus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Medtodo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 医生给患者开具的药物单 Mapper 接口
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
public interface MedtodoMapper extends BaseMapper<Medtodo> {
    @Select("SELECT bus_medtodo.* FROM bus_medtodo, bus_record WHERE bus_medtodo.record_id = bus_record.record_id AND patient_id = #{pid} ORDER BY createtime DESC ")
    List<Medtodo> getMedtodoByPatientId(IPage page, @Param("pid") Integer patientId);
}
