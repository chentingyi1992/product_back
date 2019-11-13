package com.jk.service;

import com.jk.model.RoleBean;
import com.jk.model.TreeBean;
import com.jk.model.UserBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author：wdd
 * create time:2019/10/14
 * email：
 * tel：
 */
public interface UserService {
    List<TreeBean> queryTree(Integer id);

    HashMap<String, Object> queryUserPage(Integer page, Integer rows, UserBean user);

    List<String> queryPowerByUserid(Integer id);

    /** <pre>addUser(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午11:33:33
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午11:33:33
     * 修改备注：
     * @param user</pre>
     */
    void addUser(UserBean user);

    /** <pre>queryUserById(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午11:52:04
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午11:52:04
     * 修改备注：
     * @param userId
     * @return</pre>
     */
    UserBean queryUserById(Integer userId);

    /** <pre>queryRole(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 下午4:44:23
     * 修改人：wdd
     * 修改时间：2019年9月3日 下午4:44:23
     * 修改备注：
     * @return</pre>
     */
    List<RoleBean> queryRole();

    /** <pre>deletePowerById(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午10:54:58
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午10:54:58
     * 修改备注：
     * @param powerid</pre>
     */
    void deletePowerById(Integer powerid);

    /** <pre>addPower(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午11:05:37
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午11:05:37
     * 修改备注：
     * @param power</pre>
     */
    void addPower(TreeBean power);

    /** <pre>updatePowerById(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午11:26:22
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午11:26:22
     * 修改备注：
     * @param power</pre>
     */
    void updatePowerById(TreeBean power);

    /** <pre>queryPowerTree(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 下午5:09:59
     * 修改人：wdd
     * 修改时间：2019年9月4日 下午5:09:59
     * 修改备注：
     * @param roleid
     * @return</pre>
     */
    List<TreeBean> queryPowerTree(Integer roleid);

    /** <pre>saveRolePower(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午9:42:59
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午9:42:59
     * 修改备注：
     * @param roleid
     * @param ids</pre>
     */
    void saveRolePower(Integer roleid, String[] ids);

    /** <pre>queryUserName(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午10:05:38
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午10:05:38
     * 修改备注：
     * @param loginNumber
     * @return</pre>
     */
    UserBean queryUserName(String loginNumber);


    List<UserBean> findUser();

    void addUSers(ArrayList<UserBean> list);

    List<UserBean> queryTable();

    void detelePower(Integer id);

    void saveUser(UserBean userBean);

    void saveRoles(RoleBean roleBean);

    void deteleById(Integer id);
}
