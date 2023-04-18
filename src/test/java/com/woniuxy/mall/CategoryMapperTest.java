package com.woniuxy.mall;

import com.woniuxy.mall.entity.Category;
import com.woniuxy.mall.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
public class CategoryMapperTest {
    @Resource
    private CategoryMapper categoryMapper;

    @Test
    public void testAdd() {
        Category category = new Category();
        category.setName("家电");
        category.setShow("y");
        category.setSort(1);
        category.setIsDel("n");
        category.setLastUpdatetime(LocalDateTime.now());
        categoryMapper.insert(category);
    }
}
