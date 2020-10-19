package ins.webeye.project.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import ins.webeye.framework.config.WebEyeConfig;
import ins.webeye.framework.web.controller.BaseController;
import ins.webeye.project.eye.report.service.EyeReportService;
import ins.webeye.project.eye.report.vo.IndexReportVo;
import ins.webeye.project.system.config.service.IConfigService;
import ins.webeye.project.system.menu.domain.Menu;
import ins.webeye.project.system.menu.service.IMenuService;
import ins.webeye.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author webeye
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private WebEyeConfig ruoYiConfig;

	@Autowired
	private EyeReportService reportService;
    
    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
	{
		IndexReportVo reportVo = reportService.getIndexReport();
		mmap.put("version",ruoYiConfig.getVersion());
		mmap.put("reportVo",reportVo);
		return "main";
	}
}
