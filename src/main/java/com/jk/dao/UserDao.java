package com.jk.dao;

import com.jk.model.CommBean;
import com.jk.model.RoleBean;
import com.jk.model.TreeBean;
import com.jk.model.UserBean;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * author：wdd
 * create time:2019/10/14
 * email：
 * tel：
 */
@Repository
@Mapper
public interface UserDao {

    /** <pre>queryTreeByPid(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午9:45:43
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午9:45:43
     * 修改备注：
     * @param pid
     * @return</pre>
     */
    List<TreeBean> queryTreeByPid(int pid);

    /** <pre>queryCount(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午10:26:43
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午10:26:43
     * 修改备注：
     * @param user
     * @return</pre>
     */
    int queryCount(@Param("user") UserBean user);

    /** <pre>queryUserPage(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午10:26:47
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午10:26:47
     * 修改备注：
     * @param start
     * @param rows
     * @param user
     * @return</pre>
     */
    List<UserBean> queryUserPage(@Param("start") int start, @Param("rows") Integer rows, @Param("user") UserBean user);




    /** <pre>addUser(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午11:33:59
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午11:33:59
     * 修改备注：
     * @param user</pre>
     */
    void addUser(UserBean user);

    /** <pre>queryUserById(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午11:52:40
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午11:52:40
     * 修改备注：
     * @param userId
     * @return</pre>
     */
    UserBean queryUserById(Integer userId);

    /** <pre>updateUser(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 下午2:05:40
     * 修改人：wdd
     * 修改时间：2019年9月3日 下午2:05:40
     * 修改备注：
     * @param user</pre>
     */
    void updateUser(UserBean user);

    /** <pre>queryRole(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 下午4:44:39
     * 修改人：wdd
     * 修改时间：2019年9月3日 下午4:44:39
     * 修改备注：
     * @return</pre>
     */
    List<RoleBean> queryRole();

    /** <pre>addUserRole(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 下午5:07:15
     * 修改人：wdd
     * 修改时间：2019年9月3日 下午5:07:15
     * 修改备注：
     * @param arrId
     */
    void addUserRole(@Param("arrId") String[] arrId, @Param("userid") Integer userid);

    /** <pre>deleteUserRole(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月3日 下午5:24:45
     * 修改人：wdd
     * 修改时间：2019年9月3日 下午5:24:45
     * 修改备注：
     * @param id</pre>
     */
    void deleteUserRole(Integer id);

    /** <pre>deletePowerById(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午10:55:20
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午10:55:20
     * 修改备注：
     * @param powerid</pre>
     */
    void deletePowerById(Integer powerid);

    /** <pre>addPower(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午11:06:03
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午11:06:03
     * 修改备注：
     * @param power</pre>
     */
    void addPower(TreeBean power);

    /** <pre>updatePowerById(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午11:26:43
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午11:26:43
     * 修改备注：
     * @param power</pre>
     */
    void updatePowerById(TreeBean power);

    /** <pre>queryPowerTreeByRoleid(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月4日 下午5:30:29
     * 修改人：wdd
     * 修改时间：2019年9月4日 下午5:30:29
     * 修改备注：
     * @param roleid
     * @return</pre>
     */
    List<TreeBean> queryPowerTreeByRoleid(Integer roleid);

    /** <pre>deleteByRoleid(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午9:44:16
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午9:44:16
     * 修改备注：
     * @param roleid</pre>
     */
    void deleteByRoleid(Integer roleid);

    /** <pre>addRolePower(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午9:45:07
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午9:45:07
     * 修改备注：
     * @param roleid
     * @param ids</pre>
     */
    void addRolePower(@Param("roleid") Integer roleid, @Param("ids") String[] ids);

    /** <pre>queryUserName(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午10:06:03
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午10:06:03
     * 修改备注：
     * @param loginNumber
     * @return</pre>
     */
    UserBean queryUserName(String loginNumber);

    /** <pre>zhuceuser(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午10:34:12
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午10:34:12
     * 修改备注：
     * @param u</pre>
     */
    void zhuceuser(UserBean u);

    /** <pre>queryTreeByPidAndUserid(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 下午3:08:34
     * 修改人：wdd
     * 修改时间：2019年9月5日 下午3:08:34
     * 修改备注：
     * @param pid
     * @param userid
     * @return</pre>
     */
    List<TreeBean> queryTreeByPidAndUserid(@Param("pid") int pid, @Param("userid") int userid);

    /** <pre>queryPowerByUserid(这里用一句话描述这个方法的作用)
     * 创建人：wdd
     * 创建时间：2019年9月5日 下午3:38:27
     * 修改人：wdd
     * 修改时间：2019年9月5日 下午3:38:27
     * 修改备注：
     * @param userid
     * @return</pre>
     */
    List<String> queryPowerByUserid(Integer userid);

    List<UserBean> findUser();

    void addUSers(ArrayList<UserBean> list);

    @Select("select * from tt_user")
    List<UserBean> queryTable();

    @Delete("delete  from tt_user  where userId =#{value}")
    void detelePower(Integer id);

    @Insert("INSERT INTO tt_user(userName,userPwd) values(#{userName},#{userPwd})")
    void saveUser(UserBean userBean);
    @Insert("INSERT INTO t_role(rolesName,rolesInfo) values(#{rolesName},#{rolesInfo})")
    void saveRoles(RoleBean roleBean);
    @Delete("delete  from t_role  where rolesId =#{value}")
    void deteleById(Integer id);
    @Select("select count(*) from t_community")
    int queryCounts();
    @Select("select * from t_community  where stastu =1  limit #{start},#{rows}")
    List<CommBean> queryUserPages(@Param("start") int start, @Param("rows")Integer rows );
    @Delete("delete  from t_community  where commId =#{value}")
    void deteleByIds(Integer id);

    @Insert("INSERT INTO t_community(commName,townCommName,huanjing,fujin,sheshi,stastu) values(#{commName},#{townCommName},#{huanjing},#{fujin},#{sheshi},#{stastu},)")
    void saveComm(CommBean commBean);
}