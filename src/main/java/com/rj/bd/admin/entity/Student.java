/**
 * 
 */
package com.rj.bd.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 上午10:14:27
 */

@Data
public class Student {
	@TableId
	private String s_id;
	private String s_student;

	
	
}
