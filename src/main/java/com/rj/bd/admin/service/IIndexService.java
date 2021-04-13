/**
 * 
 */
package com.rj.bd.admin.service;

/**
 * @desc   获取首页数据的接口
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 下午2:23:54
 */
public interface IIndexService {
	
	
	
	/**
	 * @desc 1.查询总卡数
	 * @return
	 */
	public int getcardsum();
	
	/**
	 * @desc 2.查询挂失数量
	 * @return
	 */
	public int getcardlose();
	/**
	 * @desc 3.查询总余额
	 * @return
	 */
	public Float getgrossmoney();
	/**
	 * @desc 4.查询总学生数量
	 * @return
	 */
	public int getstudentsum();
	
	
}
