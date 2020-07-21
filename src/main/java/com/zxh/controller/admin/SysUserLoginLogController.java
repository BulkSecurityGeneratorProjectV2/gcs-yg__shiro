package com.zxh.controller.admin;

import com.baomidou.mybatisplus.plugins.Page;
import com.zxh.entity.SysUserLoginLogEntity;
import com.zxh.service.SysUserLoginLogService;
import com.zxh.util.PageUtils;
import com.zxh.util.R;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统用户登录日志
 *
 */
@RestController
@RequestMapping("/admin/sys/log")
public class SysUserLoginLogController extends AbstractController {

    @Resource
    private SysUserLoginLogService sysUserLoginLogService;

    @RequestMapping("/list")
    @ResponseBody
    public R list(Integer offset, Integer limit, String sort, String order,
                  @RequestParam(name = "search", required = false) String loginIp, HttpServletRequest request) {
        offset = (offset / limit) + 1;
        // 排序逻辑
        Boolean flag = null;
        if (StringUtils.isNoneBlank(order)) {
            if ("asc".equalsIgnoreCase(order)) {
                flag = true;
            } else {
                flag = false;
            }
        }
        Page<SysUserLoginLogEntity> self = sysUserLoginLogService.getSelf(offset, limit, getAdminId(), loginIp, sort,
                flag);
        PageUtils pageUtil = new PageUtils(self.getRecords(), self.getTotal(), self.getSize(), self.getCurrent());
        return R.ok().put("page", pageUtil);
    }
}
