package com.yougou.brand.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * brand实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_brand")
public class Brand implements Serializable{

	@Id
	private Long id;


    //品牌名称
	private String name;
    //品牌首字母
	private String firstChar;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}


	
}
