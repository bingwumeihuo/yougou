package com.yougou.brand.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.yougou.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yougou.brand.dao.BrandDao;
import com.yougou.brand.pojo.Brand;

/**
 * brand服务层
 * 
 * @author Administrator
 *
 */
@Service
public class BrandService {

	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Brand> findAll() {
		return brandDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Brand> findSearch(Map whereMap, int page, int size) {
		Specification<Brand> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return brandDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Brand> findSearch(Map whereMap) {
		Specification<Brand> specification = createSpecification(whereMap);
		return brandDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Brand findById(Long id) {
		return brandDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param brand
	 */
	public void add(Brand brand) {
		// brand.setId( idWorker.nextId()+"" ); 雪花分布式ID生成器
		brandDao.save(brand);
	}

	/**
	 * 修改
	 * @param brand
	 */
	public void update(Brand brand) {
		brandDao.save(brand);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Long id) {
		brandDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Brand> createSpecification(Map searchMap) {

		return new Specification<Brand>() {

			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 品牌名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 品牌首字母
                if (searchMap.get("firstChar")!=null && !"".equals(searchMap.get("firstChar"))) {
                	predicateList.add(cb.like(root.get("firstChar").as(String.class), "%"+(String)searchMap.get("firstChar")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
