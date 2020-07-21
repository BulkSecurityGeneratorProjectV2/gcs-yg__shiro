package com.zxh.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zxh.dao.SysRoleMenuDao;
import com.zxh.entity.SysRoleMenuEntity;
import com.zxh.service.SysRoleMenuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 *
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity>
        implements SysRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if (menuIdList.size() == 0) {
            return;
        }
        // 先删除角色与菜单关系
        baseMapper.deleteNoMapper(roleId);

        // 保存角色与菜单关系
        Map<String, Object> map = new HashMap<>(2);
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        baseMapper.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

}
