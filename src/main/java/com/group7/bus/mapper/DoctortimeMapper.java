package com.group7.bus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.group7.bus.entity.Doctortime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group7.bus.vo.DoctortimeVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * 医生工作时间 Mapper 接口
 * </p>
 *
 * @author Robin
 * @since 2020-06-11
 */

@Component
public interface DoctortimeMapper extends BaseMapper<Doctortime> {

  @Select("<script>"
          + "SELECT user_name AS doctor_name, dept_name, bus_doctortime.* FROM bus_doctortime "
          + "JOIN sys_user ON doctor_id = user_id \n"
          + "JOIN sys_dept ON sys_dept.dept_id = sys_user.dept_id \n"
          + "<if test='m.pid!=null or m.deptId!=null or m.doctorName!=null'> WHERE </if>"
          + "<if test='m.deptId!=null'> sys_dept.dept_id = #{m.deptId} </if>"
          + "<if test='m.pid!=null'> pid = #{m.pid} </if>\n"
          + "<if test='(m.pid!=null or m.deptId!=null) and m.doctorName!=null'> AND </if>\n"
          + "<if test='m.doctorName!=null'> user_name LIKE concat('%', #{m.doctorName}, '%')</if>\n"
          + "</script>")
  IPage<DoctortimeVo> loadAllDoctortime(Map m, IPage<DoctortimeVo> page);
}
