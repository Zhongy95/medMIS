package com.group7.bus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Treattodo;
import com.group7.bus.entity.Treattodo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 医生给患者开具的待检查项目单 Mapper 接口
 * </p>
 *
 * @author TT
 * @since 2020-06-17
 */
public interface TreattodoMapper extends BaseMapper<Treattodo> {
    @Select("SELECT bus_treattodo.* FROM bus_treattodo, bus_record WHERE bus_treattodo.record_id = bus_record.record_id AND patient_id = #{pid} ORDER BY createtime DESC ")
    List<Treattodo> getTreattodoByPatientId(IPage page, @Param("pid") Integer patientId);
}
