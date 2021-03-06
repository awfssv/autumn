package cn.org.autumn.modules.sms.service;

import cn.org.autumn.table.TableInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.org.autumn.utils.PageUtils;
import cn.org.autumn.utils.Query;

import cn.org.autumn.modules.sms.dao.AliyunSmsSendDao;
import cn.org.autumn.modules.sms.entity.AliyunSmsSendEntity;

import javax.annotation.PostConstruct;
import cn.org.autumn.modules.sys.entity.SysMenuEntity;
import cn.org.autumn.modules.sys.service.SysMenuService;

/**
 * 发送验证码控制器
 *
 * @author Shaohua
 * @email henryxm@163.com
 * @date 2018-10
 */

@Service("aliyunSmsSendService")
public class AliyunSmsSendService extends ServiceImpl<AliyunSmsSendDao, AliyunSmsSendEntity> {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private TableInit tableInit;

    public PageUtils queryPage(Map<String, Object> params) {
        Page<AliyunSmsSendEntity> page = this.selectPage(
                new Query<AliyunSmsSendEntity>(params).getPage(),
                new EntityWrapper<AliyunSmsSendEntity>()
        );

        return new PageUtils(page);
    }

    /**
    * need implement it in the subclass.
    * @return
    */
    public int menuOrder(){
        return 0;
    }

    /**
    * need implement it in the subclass.
    * @return
    */
    public int parentMenu(){
        return 1;
    }

    private String order(){
        return String.valueOf(menuOrder());
    }

    private String parent(){
        return String.valueOf(parentMenu());
    }

    /**
    * Remove the comment for @PostConstruct, it will automaticlly to init data when startup.
    * Please do it in the subclass, since the code is generated.
    * */
    //@PostConstruct
    public void init() {
        if (!tableInit.init)
            return;
        Long id = 0L;
        String[] _m = new String[]
                {null, parent(), "发送验证码", "modules/sms/aliyunsmssend.html", "sms:aliyunsmssend:list,sms:aliyunsmssend:info,sms:aliyunsmssend:save,sms:aliyunsmssend:update,sms:aliyunsmssend:delete", "1", "fa fa-file-code-o", order()};
        SysMenuEntity sysMenu = sysMenuService.from(_m);
        SysMenuEntity entity = sysMenuService.get(sysMenu);
        if (null == entity) {
            int ret = sysMenuService.put(sysMenu);
            if (1 == ret)
                id = sysMenu.getMenuId();
        } else
            id = entity.getMenuId();
        String[][] menus = new String[][]{
                {null, id + "", "查看", null, "sms:aliyunsmssend:list,sms:aliyunsmssend:info", "2", null, order()},
                {null, id + "", "新增", null, "sms:aliyunsmssend:save", "2", null, order()},
                {null, id + "", "修改", null, "sms:aliyunsmssend:update", "2", null, order()},
                {null, id + "", "删除", null, "sms:aliyunsmssend:delete", "2", null, order()},
        };
        for (String[] menu : menus) {
            sysMenu = sysMenuService.from(menu);
            entity = sysMenuService.get(sysMenu);
            if (null == entity) {
                sysMenuService.put(sysMenu);
            }
        }
    }
}
