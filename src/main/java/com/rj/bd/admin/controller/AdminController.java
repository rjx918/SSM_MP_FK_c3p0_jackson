package com.rj.bd.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rj.bd.admin.entity.Condition;
import com.rj.bd.admin.entity.Message;
import com.rj.bd.admin.entity.Money;
import com.rj.bd.admin.entity.Student;
import com.rj.bd.admin.entity.User;
import com.rj.bd.admin.service.IIndexService;
import com.rj.bd.admin.service.ILoginService;
import com.rj.bd.admin.service.IStudentlistService;
import com.rj.bd.admin.service.IStudnetSevice;
import com.rj.bd.admin.utils.Putdata;
import com.sun.org.apache.xpath.internal.operations.And;

import sun.print.resources.serviceui;

@Controller
@ResponseBody // 返回文本内容
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
	public Map<String, Object> loginAdmin(User u, HttpServletRequest request) {
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
						return Putdata.printf(200, "登录成功", retuser);
					}
				}
			}
		}
		return Putdata.printf(-1, "登录失败", null);
	}

	// 首页数据

	@RequestMapping("/index") // api地址
	public Map<String, Object> indexinfo(User u) {
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
					return Putdata.printf(200, "获取成功", map);
				} else {
					return Putdata.printf(-3, "token失效", null);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
		}
	}

	// 查询数据
	@RequestMapping("/studentlist") // api地址
	public Map<String, Object> studentlistinfo(User u) {
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
					return Putdata.printf(200, "获取成功", studentlistService.query());
				} else {
					return Putdata.printf(-3, "token失效", null);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
		}
	}
	
	
	// 搜索数据
	@RequestMapping("/studentsearch") // api地址
	public Map<String, Object> studentsearchinfo(User u,String search) {
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
					return Putdata.printf(200, "获取成功", studentlistService.studentsearch(search));
				} else {
					return Putdata.printf(-3, "token失效", null);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
		}
	}
	
	
	// 增加学生
	@RequestMapping("/studentadd") // api地址
	public Map<String, Object> studentadd(User u,String snumber,String sclassesid) {
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
					return Putdata.printf(200,studentlistService.studentadd(u.getName(),snumber,sclassesid) , null);
				} else {
					return Putdata.printf(-3, "token失效", null);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
		}
	}
	
	
	
	// 查询所有的班级
	@RequestMapping("/classesinfo") // api地址
	public Map<String, Object> classesinfo(User u) {
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
					return Putdata.printf(200,"获取成功" , studentlistService.classesinfo());
				} else {
					return Putdata.printf(-3, "token失效", null);
				}
			} else {
				return Putdata.printf(-2, "没有token字段", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
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
	public Map<String, Object> addmeal(String token, String sid) {

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
						return Putdata.printf(-2, "不是本校学生", null);
					} else {
						// 查询是否有卡
						Map<String, Object> msgMap = iStudnetSevice.queyMsgById(sid);
						System.out.println(msgMap);
						String M_number = msgMap.get("mNumber").toString().replaceAll(" ", "");
						System.out.println(M_number == null);
						System.out.println("1"+M_number+"1");
						if (!M_number.equals("")) { // 是否已经办卡
							return Putdata.printf(-3, "您已经办过卡了", null);
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
							return Putdata.printf(200, "请求成功", data);
						}
					}
				} else {
					return Putdata.printf(-1, "请重新登录", null);
				}
			} else {
				return Putdata.printf(-1, "请先登录", null);
			}

		} else {
			return Putdata.printf(-1, "参数为空", null);
		}

		
	}

	@RequestMapping("/reportcard")
	public Map<String, Object> reportcard(String token, String cardid) {
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
						return Putdata.printf(-1, "请您先去办卡", null);
					} else {
						Condition condition = new Condition();
						// 更新状态
						String state = conditionMap.get("cCondition").toString();
						condition.setC_condition(state.equals("0") ? "1" : "0");
						condition.setM_id(conditionMap.get("mId").toString());
						condition.setC_id(Integer.parseInt(conditionMap.get("cId").toString()));
						iStudnetSevice.update(condition);
						String msg = state.equals("0") ? "挂失成功":"解挂成功" ;
						return Putdata.printf(200, msg, null);
					}

				} else {

					return Putdata.printf(-1, "请重新登录", null);
				}
			}else{
				return Putdata.printf(-1, "请重新登录", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
		}


	}

	@RequestMapping("/topup")
	public Map<String, Object> topup(String token, String cardid, String money) {
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
							return Putdata.printf(-3, "该卡以挂失，请先解挂", null);
						}else{
							Map<String, String> monMap = iStudnetSevice.getMonById(conditionMap.get("mId").toString());
							System.out.println(monMap);	
							String mMoney = monMap.get("mMoney");
							int myMMoney = Integer.parseInt(mMoney);
							int youMMoney = Integer.parseInt(money);
							 int mm = myMMoney+youMMoney;
							iStudnetSevice.updateMonById(conditionMap.get("mId").toString(),mm+"" );
							return Putdata.printf(200, "充值成功", null);
						}
					}else{
						return Putdata.printf(-2, "没有该卡信息", null);
					}
				}else {
					return Putdata.printf(-1, "请重新登录", null);
				}
			}else {
				return Putdata.printf(-1, "请重新登录", null);
			}
		} else {
			return Putdata.printf(-1, "参数为空", null);
		}


	}
		

	
	
	
	
	
	

}
