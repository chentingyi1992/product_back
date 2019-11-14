package com.jk.controller.cty;


import com.jk.model.CommBean;
import com.jk.model.RoleBean;
import com.jk.model.TreeBean;
import com.jk.model.UserBean;
import com.jk.service.UserService;
import com.jk.utils.ExcelImEx;
import com.jk.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * author：wdd
 * create time:2019/10/14
 * email：
 * tel：
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Bean(value = "userService")
    public UserService getUserService() {
        return userService;
    }

    //导入
    @RequestMapping("importExcel")
    @ResponseBody
    @RequiresPermissions("user:importExcel")
    public String importExcel(MultipartFile file){
        try{
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();//获取上传的文件名称
            boolean xls = ExcelUtils.isXls(filename);
            //创建excel工作薄
            Workbook wrkbook = null;
            if(xls){//03
                wrkbook = new HSSFWorkbook(inputStream);
            }else{//07
                wrkbook = new XSSFWorkbook(inputStream);
            }
            //获取sheet页
            Sheet sheet = wrkbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            ArrayList<UserBean> list = new ArrayList<>();
            //遍历行
            for (int i = 0; i < rowNum; i++) {
                Row row = sheet.getRow(i + 1);
                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String pwd = row.getCell(2).getStringCellValue();
                //String id = row.getCell(3).getStringCellValue();
                UserBean userBean = new UserBean();
                userBean.setUserName(name);
                userBean.setUserPwd(pwd);
                list.add(userBean);
            }

            //批量新增
            userService.addUSers(list);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //导出excel
    @RequestMapping("exportExcel")
    @ResponseBody
    @RequiresPermissions("user:exportExcel")
    public void exportExcel(HttpServletResponse response,String filename) throws IOException, IllegalAccessException {
        //从数据库查询用户列表
        List<UserBean> list = userService.findUser();
        String[] titles = {"id","用户名","密码","角色"};
        ExcelImEx.exportExcel(response,filename, Collections.singletonList(list),titles,"用户信息",UserBean.class);
    }

    //导出excel
    @RequestMapping("exportExcel2")
    @ResponseBody
    @RequiresPermissions("user:exportExcel2")
    public void exportExcel2(HttpServletResponse response,String filename) throws IOException, IllegalAccessException {
        //从数据库查询用户列表
        List<UserBean> list = userService.findUser();
        boolean xls = ExcelUtils.isXls(filename);
        //创建excel工作薄
        Workbook workBook = null;
        if(xls){//.xls  03
            workBook = new HSSFWorkbook();
        }else{//.xlsx  07
            workBook = new XSSFWorkbook();
        }
        //创建sheet页
        Sheet sheet = workBook.createSheet("用户信息");
        String[] titles = {"id","用户名","密码","角色"};
        //创建行：标题
        Row row1 = sheet.createRow(0);
        for (int i = 0; i <titles.length ; i++) {
            row1.createCell(i).setCellValue(titles[i]);
        }

        //获取类的属性数值：用java反射机制
        Field[] fields = UserBean.class.getDeclaredFields();
        for (int i = 0; i < list.size(); i++) {
            UserBean user = list.get(i);
            //创建行
            Row row = sheet.createRow(i+1);
            for (int j = 0; j <fields.length ; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                Object o = field.get(user);
                if(o!=null){
                    row.createCell(j).setCellValue(o.toString());
                }
            }
/*            //创建列
            Cell cell = row.createCell(0);
            //给cell赋值
            cell.setCellValue(user.getId());

            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getPassword());
            row.createCell(3).setCellValue(user.getRoleName());*/
        }

        //设置头信息
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workBook.write(outputStream);
        outputStream.close();//关闭流
    }

    /**
     * <pre>queryTree(查询同步树)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午9:42:35
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午9:42:35
     * 修改备注：
     * @return</pre>
     */
    @RequestMapping("queryTree")
    @ResponseBody
    public List<TreeBean> queryTree(){
        Subject subject = SecurityUtils.getSubject();
        UserBean user = (UserBean) subject.getPrincipal();
        return userService.queryTree(user.getUserId());
    }

    /**
     * <pre>queryUserPage(分页查询用户信息)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午10:23:30
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午10:23:30
     * 修改备注：
     * @param page
     * @param rows
     * @param user
     * @return</pre>
     */
    @RequestMapping("queryUserPage")
    @ResponseBody
    @RequiresPermissions("user:queryUserPage")
    public HashMap<String, Object> queryUserPage(Integer page, Integer rows, UserBean user){
        return userService.queryUserPage(page,rows,user);
    }

    /**
     * <pre>addUser(新增用户)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午11:33:14
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午11:33:14
     * 修改备注：
     * @param user</pre>
     */
    @RequestMapping("addUser")
    @ResponseBody
    @RequiresPermissions("user:addUser")
    public void addUser(UserBean user){
       userService.addUser(user);
    }

    /**
     * <pre>queryUserById(修改：回显)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午11:51:48
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午11:51:48
     * 修改备注：
     * @param userId
     * @return</pre>
     */
    @RequestMapping("queryUserById")
    @ResponseBody
    public UserBean queryUserById(Integer userId){
        return userService.queryUserById(userId);
    }


    /**
     * <pre>queryRole(查询所有的角色信息)
     * 创建人：wdd
     * 创建时间：2019年9月3日 下午4:44:06
     * 修改人：wdd
     * 修改时间：2019年9月3日 下午4:44:06
     * 修改备注：
     * @return</pre>
     */
    @RequestMapping("queryRole")
    @ResponseBody
    public List<RoleBean> queryRole(){
        return userService.queryRole();
    }

    /**
     * <pre>deletePowerById(删除权限节点)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午10:54:45
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午10:54:45
     * 修改备注：
     * @param powerid</pre>
     */
    @RequestMapping("deletePowerById")
    @ResponseBody
    public void deletePowerById(Integer powerid){
        userService.deletePowerById(powerid);
    }

    /**
     * <pre>addPower(新增权限节点)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午11:05:21
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午11:05:21
     * 修改备注：
     * @param power</pre>
     */
    @RequestMapping("addPower")
    @ResponseBody
    public void addPower(TreeBean power){
        userService.addPower(power);
    }

    /**
     * <pre>updatePowerById(修改权限节点)
     * 创建人：wdd
     * 创建时间：2019年9月4日 上午11:26:14
     * 修改人：wdd
     * 修改时间：2019年9月4日 上午11:26:14
     * 修改备注：
     * @param power</pre>
     */
    @RequestMapping("updatePowerById")
    @ResponseBody
    public void updatePowerById(TreeBean power){
        userService.updatePowerById(power);
    }

    /**
     * <pre>saveRolePower(修改角色权限)
     * 创建人：wdd
     * 创建时间：2019年9月5日 上午9:42:38
     * 修改人：wdd
     * 修改时间：2019年9月5日 上午9:42:38
     * 修改备注：
     * @param roleid
     * @param ids</pre>
     */
    @RequestMapping("saveRolePower")
    @ResponseBody
    public void saveRolePower(Integer roleid,String[] ids){
        userService.saveRolePower(roleid,ids);
    }

    /**
     * <pre>queryTree(查询权限树)
     * 创建人：wdd
     * 创建时间：2019年9月3日 上午9:42:35
     * 修改人：wdd
     * 修改时间：2019年9月3日 上午9:42:35
     * 修改备注：
     * @return</pre>
     */
    @RequestMapping("queryPowerTree")
    @ResponseBody
    public List<TreeBean> queryPowerTree(Integer roleid){
        return userService.queryPowerTree(roleid);
    }


    @RequestMapping("queryTable")
    @ResponseBody
    public List<UserBean> queryTable(){
        return userService.queryTable();
    }

    @RequestMapping("detelePower")
    @ResponseBody
    public void detelePower(Integer id){
     userService.detelePower(id);
    }


    @RequestMapping("saveUser")
    @ResponseBody
    public void saveUser(UserBean userBean){
        userService.saveUser(userBean);
    }

    @RequestMapping("saveRoles")
    @ResponseBody
    public void saveRoles(RoleBean roleBean){
        userService.saveRoles(roleBean);
    }

    @RequestMapping("deteleById")
    @ResponseBody
    public void deteleById(Integer id){
        userService.deteleById(id);
    }

    @RequestMapping("queryXiaoQu")
    @ResponseBody
    public HashMap<String, Object> queryXiaoQu(Integer page, Integer rows,Integer max){
       return userService.XiaoQuTable(page,rows,max);
    }


    @RequestMapping("deteleByIds")
    @ResponseBody
    public void deteleByIds(Integer id){
        userService.deteleByIds(id);
    }

    @RequestMapping("saveComm")
    @ResponseBody
    public void saveComm(CommBean commBean){
        commBean.setStastu(2);
        userService.saveComm(commBean);
    }

    @RequestMapping("updateById")
    @ResponseBody
    public void updateById(Integer id){

        userService.updateById(id);
    }
}
