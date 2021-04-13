package com.rj.bd.users.domain;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "administrator")
@Data
public class Administrator{
	 @Id
	private Integer id;
	private String name;
	private String password;
	private String image;
	private String nickname;
}

