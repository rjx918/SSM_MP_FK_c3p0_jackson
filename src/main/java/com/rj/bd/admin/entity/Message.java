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
 * @time 2021年4月13日 上午10:12:26
 */


@Data
public class Message {
	@TableId
	private String m_id;
	private String m_number;
	private String m_studentnum;
	private String s_id;
}
