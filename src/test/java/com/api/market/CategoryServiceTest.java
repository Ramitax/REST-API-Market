package com.api.market;

import com.api.market.models.CategoryModel;
import com.api.market.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testSave() {
        CategoryModel categotyExample = new CategoryModel();
        categotyExample.setName("Example");
        assert categotyExample == categoryService.save(categotyExample);
        categoryService.delete(categotyExample.getIdCategory());
    }

    @Test
    public void testGetAll(){
        //assert 8 == categoryService.getAll().size();
    }
}
