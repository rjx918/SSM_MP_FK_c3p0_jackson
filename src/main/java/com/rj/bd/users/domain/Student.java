package com.rj.bd.users.domain;


import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.*;

@Table(name = "student")
@Data
public class Student {

    @Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@KeySql（\@C
    private Integer sid;
    private String name;
    private String sex;
    private Classroom classroom;
    @ColumnType(column = "class_id")
    //@Column(name = "class_id")
    private Integer classId;


}
