package com.rj.bd.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rj.bd.admin.entity.Condition;
import com.rj.bd.admin.entity.Message;
import com.rj.bd.admin.entity.Money;
import com.rj.bd.admin.entity.Query;
import com.rj.bd.admin.entity.User;
import com.rj.bd.admin.service.IIndexService;
import com.rj.bd.admin.service.ILoginService;
import com.rj.bd.admin.service.IStudentlistService;
import com.rj.bd.admin.service.IStudnetSevice;
import com.rj.bd.admin.utils.PutFileUtils;
import com.rj.bd.admin.utils.Putdata;

@Controller
//@ResponseBody // 返回文本内容
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IIndexService iindexService;

	@Autowired
	private IStudentlistService studentlistService;
	
	
	@Autowired
	private IStudnetSevice iStudnetSevice;
	
	

	@RequestMapping("/login")
	public String loginAdmin(User u, HttpServletRequest request,Model model) {
		System.out.println("登录页面");
		System.out.println(u);
		if (u != null) {
			if (u.getName() != null && u.getPassword() != null) {
				User userinfo = loginService.Login(u);
				if (userinfo != null) {
					String token = userinfo.getName() + userinfo.getPassword() + request.getSession().getId();
					// 吧token 存入数据库中
					userinfo.setToken(token);
					boolean ret = loginService.putToken(userinfo);
					if (ret) {
						User retuser = new User();
						retuser.setName(u.getName());
						retuser.setToken(token);
						retuser.setImgs(userinfo.getImgs());
						return Putdata.printf(200, "登录成功", retuser,model);
					}
				}
			}
		}
		return Putdata.printf(-1, "登录失败", null,model);
	}

	// 首页数据
	@RequestMapping("/index") // api地址
	public String  indexinfo(User u,Model model) {
		// 1.判断穿过来的参数是否为空
		if (u != null) {
			// 2. 判断有没token字段
			if (u.getToken() != null) {
				// 3.验证token
				User tokeninfo = loginService.verifyToken(u);
				// 4.判断tokeninfo是否为空
				if (tokeninfo != null) {
					// 这里已经验证成功了
					System.out.println("验证成功");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("cardsum", iindexService.getcardsum());
					map.put("cardlose", iindexService.getcardlose());
					map.put("grossmoney", iindexService.getgrossmoney());
					map.put("studentsum", iindexService.getstudentsum());
					return Putdata.printf(200, "获取成功", map,model);
				} else {
					return Putdata.printf(-3, "token失效", null,model);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null,model);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}
	}

	// 查询数据
		@RequestMapping("/studentlist") // api地址
		public String studentlistinfo(User u,Integer page,Integer size,Model model) {
			// 1.判断穿过来的参数是否为空
			if (u != null) {
				// 2. 判断有没token字段
				if (u.getToken() != null) {
					// 3.验证token
					User tokeninfo = loginService.verifyToken(u);
					// 4.判断tokeninfo是否为空
					if (tokeninfo != null) {
						// 这里已经验证成功了
						System.out.println("验证成功");
						if (page ==null) {
							page = 0;
						}
						
						if (size ==null) {
							size = 0;
						}
						
						if(size ==0){
							size = 10;
						}
						page = page*size;
						return Putdata.printf(200, "获取成功", studentlistService.query( page, size),model);
					} else {
						return Putdata.printf(-3, "token失效", null,model);
					}
				} else {
					return Putdata.printf(-2, "没有token字段", null,model);
				}
			} else {
				return Putdata.printf(-1, "参数为空", null,model);
			}
		}
	
	
	
	
	
	
	
	
	
	
	// 搜索数据
	@RequestMapping("/studentsearch") // api地址
	public String studentsearchinfo(User u,String search,Model model) {
		// 1.判断穿过来的参数是否为空
		if (u != null) {
			// 2. 判断有没token字段
			if (u.getToken() != null) {
				// 3.验证token
				User tokeninfo = loginService.verifyToken(u);
				// 4.判断tokeninfo是否为空
				if (tokeninfo != null) {
					// 这里已经验证成功了
					System.out.println("验证成功");
					return Putdata.printf(200, "获取成功", studentlistService.studentsearch(search),model);
				} else {
					return Putdata.printf(-3, "token失效", null,model);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null,model);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}
	}
	
	
	// 增加学生
	@RequestMapping("/studentadd") // api地址
	public String studentadd(User u,String snumber,String sclassesid,Model model) {
		// 1.判断穿过来的参数是否为空
		if (u != null) {
			// 2. 判断有没token字段
			if (u.getToken() != null && u.getName()!=null && snumber!=null && sclassesid != null) {
				// 3.验证token
				User tokeninfo = loginService.verifyToken(u);
				// 4.判断tokeninfo是否为空
				if (tokeninfo != null) {
					// 这里已经验证成功了
					System.out.println("验证成功");
					return Putdata.printf(200,studentlistService.studentadd(u.getName(),snumber,sclassesid) , null,model);
				} else {
					return Putdata.printf(-3, "token失效", null,model);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null,model);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}
	}
	
	
	
	// 查询所有的班级
	@RequestMapping("/classesinfo") // api地址
	public String classesinfo(User u,Model model) {
		// 1.判断穿过来的参数是否为空
		if (u != null) {
			// 2. 判断有没token字段
			if (u.getToken() != null) {
				// 3.验证token
				User tokeninfo = loginService.verifyToken(u);
				// 4.判断tokeninfo是否为空
				if (tokeninfo != null) {
					// 这里已经验证成功了
					System.out.println("验证成功");
					return Putdata.printf(200,"获取成功" , studentlistService.classesinfo(),model);
				} else {
					return Putdata.printf(-3, "token失效", null,model);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null,model);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}
	}
	
	
	/**
	 * @Desc 增加餐卡 1. 认证管理员是否登录 2. 更具学生id查询是否已经有该餐卡 3. 认证是否增加成功
	 * @param token
	 *            管理员token
	 * @param sid
	 *            学生的id
	 * @return
	 */
	@RequestMapping("/addmeal")
	public String addmeal(String token, String sid,Model model) {

		// 参数认证
		if (sid != null) { // 认证参数是否为空

			if (token != null) { // 验证是否登录
				User user = new User();
				user.setToken(token);
				User tokeninfo = loginService.verifyToken(user);
				if (tokeninfo != null) { // token存在
					// 查询是否有该学生
					Map<String, Object> studnetMap = iStudnetSevice.queryStuById(sid);

					if (studnetMap == null) { // 是否是本校的学生
						return Putdata.printf(-2, "不是本校学生", null,model);
					} else {
						// 查询是否有卡
						Map<String, Object> msgMap = iStudnetSevice.queyMsgById(sid);
						System.out.println(msgMap);
						String M_number = msgMap.get("mNumber").toString().replaceAll(" ", "");
						System.out.println(M_number == null);
						System.out.println("1"+M_number+"1");
						if (!M_number.equals("")) { // 是否已经办卡
							return Putdata.printf(-3, "您已经办过卡了", null,model);
						} else {
							Message msg = new Message();
							msg.setM_id(msgMap.get("mId").toString());
							msg.setM_studentnum(msgMap.get("mStudentnum").toString());
							msg.setS_id(msgMap.get("sId").toString());
							String cardid = "ka" + UUID.randomUUID();
							msg.setM_number(cardid);
							//添加挂失信息
							Condition condition = new Condition();
							
							condition.setC_id(0);
							condition.setC_condition("0");
							condition.setM_id(msgMap.get("mId").toString());
							
							
							
							//添加余额信息
							
							Money money = new Money();
							money.setM_id(msgMap.get("mId").toString());	
							money.setM_money("0");
							
							iStudnetSevice.updateMsg(msg,condition,money);
							
							Map<String, Object> data = new HashMap<String, Object>();
							data.put("cardid", cardid);
							return Putdata.printf(200, "请求成功", data,model);
						}
					}
				} else {
					return Putdata.printf(-1, "请重新登录", null,model);
				}
			} else {
				return Putdata.printf(-1, "请先登录", null,model);
			}

		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}

		
	}

	@RequestMapping("/reportcard")
	public String reportcard(String token, String cardid,Model model) {
		// 1.判断穿过来的参数是否为空
		if (cardid != null) { // 参数是否为空
			if (token != null) { // 验证是否登录
				User user = new User();
				user.setToken(token);
				User tokeninfo = loginService.verifyToken(user);
				if (tokeninfo != null) { // token存在
					Map<String, Object> conditionMap = iStudnetSevice.queryConditionById(cardid);
					System.out.println(conditionMap);
					if (conditionMap == null) { // 卡号是否存在
						return Putdata.printf(-1, "请您先去办卡", null,model);
					} else {
						Condition condition = new Condition();
						// 更新状态
						String state = conditionMap.get("cCondition").toString();
						condition.setC_condition(state.equals("0") ? "1" : "0");
						condition.setM_id(conditionMap.get("mId").toString());
						condition.setC_id(Integer.parseInt(conditionMap.get("cId").toString()));
						iStudnetSevice.update(condition);
						String msg = state.equals("0") ? "挂失成功":"解挂成功" ;
						return Putdata.printf(200, msg, null,model);
					}

				} else {

					return Putdata.printf(-1, "请重新登录", null,model);
				}
			}else{
				return Putdata.printf(-1, "请重新登录", null,model);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}


	}
	
	
	

	@RequestMapping("/topup")
	public String topup(String token, String cardid, String money,Model model) {
		// 1.判断穿过来的参数是否为空
		if (cardid != null) { // 参数是否为空
			if (token != null) { // 验证是否登录
				User user = new User();
				user.setToken(token);
				User tokeninfo = loginService.verifyToken(user);
				if (tokeninfo != null) { // token存在
					Map<String, Object> conditionMap = iStudnetSevice.queryConditionById(cardid);
					System.out.println(conditionMap);
					if (conditionMap!=null) {
						String state = conditionMap.get("cCondition").toString();
						if (state.equals("1")) {
							return Putdata.printf(-3, "该卡以挂失，请先解挂", null,model);
						}else{
							Map<String, String> monMap = iStudnetSevice.getMonById(conditionMap.get("mId").toString());
							System.out.println(monMap);	
							String mMoney = monMap.get("mMoney");
							int myMMoney = Integer.parseInt(mMoney);
							int youMMoney = Integer.parseInt(money);
							 int mm = myMMoney+youMMoney;
							iStudnetSevice.updateMonById(conditionMap.get("mId").toString(),mm+"" );
							return Putdata.printf(200, "充值成功", null,model);
						}
					}else{
						return Putdata.printf(-2, "没有该卡信息", null,model);
					}
				}else {
					return Putdata.printf(-1, "请重新登录", null,model);
				}
			}else {
				return Putdata.printf(-1, "请重新登录", null,model);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null,model);
		}


	}
		

	@RequestMapping("/excel")
	public Map<String, Object> Excel(HttpServletResponse response) throws UnsupportedEncodingException {
		// 固定写法 复制就行 这里写导出excel的名字 设置下载在浏览器端，等用户下载
		String fileName = "xinxi.xls";
		
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("UTF-8"), "utf-8"));// 设置文件头编码格式
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
		response.setHeader("Cache-Control", "no-cache");// 设置头
		response.setDateHeader("Expires", 0);// 设置日期头
		// 获取数据库查询的所有数据
		List<Query> list = studentlistService.query(0,10000);
		// 将查询结果带到页面 回显函数使用
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		// 创建导出表格的对象
		Workbook wb = new HSSFWorkbook();
		
		
		// 创建表
		Sheet sheet = wb.createSheet("sheet1");
		// 获取表的第一行元素，也就是0行
		Row row = sheet.createRow(0);
		// 创建存放列的数组
		Cell[] cell = new HSSFCell[8];
		for (int i = 0; i < cell.length; i++) {
			// 吧每一列放到数组中
			cell[i] = row.createCell(i);
		}
		// 这个是写的标题头
		// 给第0行第一列元素赋值
		cell[0].setCellValue("序号");
		cell[1].setCellValue("学生ID");
		// 给第0行第二列元素赋值
		cell[2].setCellValue("姓名");
		// 给第0行第三列元素赋值
		cell[3].setCellValue("班级");
		// 给第0行第四列元素赋值
		cell[4].setCellValue("卡号");
		// 给第0行第五列元素赋值
		cell[5].setCellValue("学号");
		// 给第0行第六列元素赋值
		cell[6].setCellValue("状态");
		// 给第0行第七列元素赋值
		cell[7].setCellValue("余额");
		
		
		try {
			// 循环获取从数据库中的集合每个pojo对象的数据
			for (int i = 0; i < list.size(); i++) {
				// 查询的每个对象的数据
				Query query = list.get(i);
				// 设置要插入的行为i+1(就是标题下的第一行)
				Row row1 = sheet.createRow(i + 1);
				// 创建存放列的数组
				Cell[] cell2 = new HSSFCell[8];
				for (int j = 0; j < cell.length; j++) {
					// 吧每一列放到数组中
					cell2[j] = row1.createCell(j);
				}
				cell2[0].setCellValue(i + 1);
				cell2[1].setCellValue(query.getS_id());
				cell2[2].setCellValue(query.getM_id());
				cell2[3].setCellValue(query.getS_student());
				cell2[4].setCellValue(query.getD_class());
				cell2[5].setCellValue(query.getM_number());
				cell2[6].setCellValue(query.getC_condition());
				cell2[7].setCellValue(query.getM_money());
			}
			// 输出到下载人的电脑上
			wb.write(response.getOutputStream());
			// 刷新
			response.getOutputStream().flush();
			// 关闭
			response.getOutputStream().close();
		} catch (IOException e) {

		} finally {
			try {
				if (wb != null) {
					// 关闭流
					wb.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	
	/**
	 * @desc 上传图片
	 * @param files
	 *            文件
	 * @param userId
	 *            用户id
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	// 前端先上传图片预览生成图片id 给图片右上角一个删除图标 点击时remove(id) 压缩或者不压缩放入到一个数组里统一上传file
	@RequestMapping("/uploadFile") // 同时上传多张文件前端数组存储之后点保存 统一上传 集修改删除于一体
	public String save(@RequestParam(value = "file", required = false) MultipartFile[] files,
			String name,Model model) throws IllegalStateException, IOException {

		for (MultipartFile file : files) {
			//文件名称
			String fileName = file.getOriginalFilename();
			//文件后缀
			String fileType = fileName.substring(fileName.indexOf("."), fileName.length());
			// 文件InputStream
			InputStream fileInputStream  = file.getInputStream();
			String url = PutFileUtils.Putimgs(fileInputStream, fileType);

			if (url!=null) {// 如果文件存在
				User user = new User() ;
				user.setName(name);
				user.setImgs(url);
				if(loginService.SetImg(user)){
					return Putdata.printf(200, "上传成功", url,model);
				}else{
					return Putdata.printf(200, "用户不存在", null,model);
				}
				
			}else{
				return Putdata.printf(-1, "上传失败", null,model);
			}
			
		}
		return Putdata.printf(-5, "上传失败", null,model);
	}
}
