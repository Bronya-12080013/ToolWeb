package com.lan.interest.dao;


import com.lan.interest.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * 这个是自定义mapper接口
 * 不是service 不写impl 直接在/mapper写自定义的mapper.xml
 */
@Component
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
