package com.yougou.brand.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yougou.brand.pojo.Brand;
import com.yougou.brand.service.BrandService;

import com.yougou.common.entity.PageResult;
import com.yougou.common.entity.Result;
import com.yougou.common.entity.StatusCode;
/**
 * brand控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	@RequestMapping(value="/{module}/{module2}/{name}")
	public String commonController(@PathVariable String module,@PathVariable String module2, @PathVariable String name) {
		return module+"/"+module2+"/"+name;
	}
	@RequestMapping(value="/{module}/{name}")
	public String commonController2(@PathVariable String module, @PathVariable String name) {
		return module+"/"+name;
	}
	@RequestMapping(value="/{name}")
	public String commonController(@PathVariable String name) {

		return name;
	}

	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",brandService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable Long id){
		return new Result(true,StatusCode.OK,"查询成功",brandService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Brand> pageList = brandService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Brand>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",brandService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param brand
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Brand brand  ){
		brandService.add(brand);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param brand
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Brand brand, @PathVariable Long id ){
		brand.setId(id);
		brandService.update(brand);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable Long id){
		brandService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
