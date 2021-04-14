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
 * @time 2021年4月13日 上午10:10:31
 */
@Data
@TableName("`data`")
public class Datas {
	private int d_id;
	private String s_id;
	private String d_class;
}
