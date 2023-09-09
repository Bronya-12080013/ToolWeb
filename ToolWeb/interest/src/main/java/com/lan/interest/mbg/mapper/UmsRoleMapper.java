package com.lan.interest.mbg.mapper;


import com.lan.interest.mbg.model.UmsRole;
import com.lan.interest.mbg.model.UmsRoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UmsRoleMapper {
    int countByExample(UmsRoleExample example);

    int deleteByExample(UmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(UmsRole record);

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);
}