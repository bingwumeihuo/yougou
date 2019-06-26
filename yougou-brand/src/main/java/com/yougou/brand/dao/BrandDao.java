package com.yougou.brand.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yougou.brand.pojo.Brand;
/**
 * brand数据访问接口
 * @author Administrator
 *
 */
public interface BrandDao extends JpaRepository<Brand,Long>,JpaSpecificationExecutor<Brand>{
	
}
