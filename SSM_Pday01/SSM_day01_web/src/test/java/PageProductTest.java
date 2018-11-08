import com.github.pagehelper.PageHelper;
import com.itheima.pojo.Product;
import com.itheima.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class PageProductTest {
    @Autowired
    private ProductService productService;
    @Test
    public void testPageHelper(){

        /*
            设置分页数据
            pageNum:页码
            pageSize:当前页显示的条数
         */

        PageHelper.startPage(2,2);

        List<Product> all = productService.findAll();
        for (Product product : all) {
            System.out.println(product.getId());
        }
    }
}
