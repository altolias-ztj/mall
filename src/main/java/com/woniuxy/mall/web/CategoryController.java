package com.woniuxy.mall.web;

import com.woniuxy.mall.entity.Category;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.CategoryService;
import com.woniuxy.mall.vo.CategoryVO;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseResult<List<CategoryVO>> list() {
        List<CategoryVO> categoryVOS = new ArrayList<>();
        List<Category> categories = categoryService.list();
        for (Category category : categories) {
            if (category.getPid() == null) {
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category, categoryVO);
                for (Category subCategory : categories) {
                    if (category.getId().equals(subCategory.getPid())) {
                        CategoryVO subCategoryVO = new CategoryVO();
                        BeanUtils.copyProperties(subCategory, subCategoryVO);
                        subCategoryVO.setSubCategories(null);
                        categoryVO.getSubCategories().add(subCategoryVO);
                    }
                }
                if (categoryVO.getSubCategories().size() == 0) {
                    categoryVO.setSubCategories(null);
                }
                categoryVOS.add(categoryVO);
            }
        }
        return new ResponseResult<>(ResponseCode.SUCCESS, categoryVOS);
    }

    @PostMapping("add")
    public ResponseResult<Void> add(@RequestBody Category category){
        categoryService.save(category);
        return ResponseResult.ok();
    }
}
