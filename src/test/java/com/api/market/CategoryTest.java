package com.api.market;

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
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testGetAll(){
        assert 5 == categoryService.getAll().size();
    }

}
