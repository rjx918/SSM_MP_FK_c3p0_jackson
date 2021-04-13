/**
 * 
 */
package com.rj.bd.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 上午10:09:46
 */

@Data
@TableName("`condition`")
public class Condition {
	private String c_id;
	private String c_condition;
	private String m_id;
}
