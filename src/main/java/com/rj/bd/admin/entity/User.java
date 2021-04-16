/**
 * 
 */
package com.rj.bd.admin.entity;

import lombok.Data;

/**
 * @desc 
 * @author qiufeng
 * @version 1.0
 * @time 2021年4月13日 上午9:46:38
 */
@Data
public class User {
	private String id;
	private String name;
	private String password;
	private String token;
	private String imgs;
}
