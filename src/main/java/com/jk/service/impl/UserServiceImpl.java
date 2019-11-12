package com.jk.service.impl;


import com.jk.dao.UserDao;
import com.jk.model.RoleBean;
import com.jk.model.TreeBean;
import com.jk.model.UserBean;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：wdd
 * create time:2019/10/14
 * email：
 * tel：
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<TreeBean> queryTree(Integer userid) {
        List<TreeBean> list = new ArrayList<TreeBean>();

        int pid = 0;
        //查询一级节点
        //提取公共方法的快捷键：alt+shift+m
        list = queryNodes(pid,userid);
        return list;
    }

    public HashMap<String, Object> queryUserPage(Integer page, Integer rows, UserBean user) {
        //查询总条数
        int total = userDao.queryCount(user);
        int start = (page-1)*rows;
        //查询每页返回的数据 list
        List<UserBean> list = userDao.queryUserPage(start,rows,user);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    @Override
    public List<String> queryPowerByUserid(Integer id) {
        return userDao.queryPowerByUserid(id);
    }

    /** <pre>queryNodes(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午9:49:30
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午9:49:30
     * 修改备注：
     * @param pid
     * @return</pre>
     */
    private List<TreeBean> queryNodes(int pid,int userid) {
        List<TreeBean> list = userDao.queryTreeByPidAndUserid(pid,userid);
        for (TreeBean treeBean : list) {
            Integer id = treeBean.getId();
            //查询对应的子节点
            List<TreeBean> nodes = queryNodes(id,userid);//递归：自己调自己
            treeBean.setChildren(nodes);
        }
        return list;
    }

    public void addUser(UserBean user) {
        Integer id = user.getUserId();
        if(id!=null){//修改
            //修改用户
            userDao.updateUser(user);
            //修改用户角色中间表 1、删除 2、新增
            //删除：根据用户id，用户角色中间删除
            userDao.deleteUserRole(id);
        }else{//新增
            //新增用户
            userDao.addUser(user);
        }
        //新增用户角色中间表：批量新增
        String roleids = user.getRolesId();
        String[] arrId = roleids.split(",");
        userDao.addUserRole(arrId,user.getUserId());
    }

    public UserBean queryUserById(Integer userId) {
        return userDao.queryUserById(userId);
    }

    public List<RoleBean> queryRole() {
        return userDao.queryRole();
    }


    public void deletePowerById(Integer powerid) {
        userDao.deletePowerById(powerid);
    }

    public void addPower(TreeBean power) {
        userDao.addPower(power);
    }

    public void updatePowerById(TreeBean power) {
        userDao.updatePowerById(power);
    }

    public List<TreeBean> queryPowerTree(Integer roleid) {
        //根据角色id查询对应的权限
        List<TreeBean> rolePower = userDao.queryPowerTreeByRoleid(roleid);

        int pid = -1;
        //查询一级节点
        //提取公共方法的快捷键：alt+shift+m
        List<TreeBean> list = queryPowerNodes(pid,rolePower);

        //添加虚拟的根节点
        TreeBean tree = new TreeBean();
        tree.setId(-1);
        tree.setPid(-2);
        tree.setText("根节点");
        tree.setChildren(list);

        ArrayList<TreeBean> list2 = new ArrayList<TreeBean>();
        list2.add(tree);
        return list2;
    }

    private List<TreeBean> queryPowerNodes(int pid, List<TreeBean> rolePower) {
        List<TreeBean> list = userDao.queryTreeByPid(pid);

        for (TreeBean treeBean : list) {
            Integer id = treeBean.getId();
            //查询对应的子节点
            List<TreeBean> nodes = queryPowerNodes(id,rolePower);//递归：自己调自己
            treeBean.setChildren(nodes);

            //把所有的权限，跟当前角色拥有的权限比较：checked属性：true
            for (TreeBean treeBean2 : rolePower) {
                Integer id3 = treeBean2.getId();
                if(nodes.size()<=0 && id==id3){//是子节点 并且 有权限
                    treeBean.setChecked(true);
                }
            }
        }
        return list;
    }

    public void saveRolePower(Integer roleid, String[] ids) {
        //删除
        userDao.deleteByRoleid(roleid);
        //新增
        if(ids.length>0){
            userDao.addRolePower(roleid,ids);
        }
    }

    public UserBean queryUserName(String loginNumber) {
        return userDao.queryUserName(loginNumber);
    }

    @Override
    public List<UserBean> findUser() {
        return userDao.findUser();
    }

    @Override
    public void addUSers(ArrayList<UserBean> list) {
        userDao.addUSers(list);
    }

}
