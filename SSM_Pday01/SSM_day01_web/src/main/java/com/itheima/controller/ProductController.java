package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Product;
import com.itheima.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;

@Secured({"ROLE_admin","ROLE_user"})
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Product> productlist = productService.findAll();

        for (Product product : productlist) {
            //为了让前端页面显示字符串类型的日期，我们多设置一个属性，用来将事件转换为字符串类型
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String departureTimeStr = format.format(product.getDepartureTime());
            product.setDepartureTimeStr(departureTimeStr);
        }

        ModelAndView modelAndView = new ModelAndView();

      /*  if (productlist!=null){
        for (Product product : productlist) {
            System.out.println(product);
        }}
        else {
            System.out.println("kong");
        }*/

        modelAndView.addObject("productlist",productlist);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @RequestMapping("/findByPageInfo")
    public ModelAndView findByPageInfo( @RequestParam(value = "pageNum",
            required = false,defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "pageSize",
                                            required = false,defaultValue = "2")Integer pageSize){

        PageInfo<Product> pageInfo = productService.findByPageHelper(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list-pageinfo");
        return modelAndView;
    }
    @RequestMapping("/findByPage")
    public ModelAndView findByPage( @RequestParam(value = "pageNum",
            required = false,defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "pageSize",
                                            required = false,defaultValue = "5")Integer pageSize){

        PageBean<Product> pageBean = productService.findByPage(pageNum,pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageBean",pageBean);
        modelAndView.setViewName("product-list-page");
        return modelAndView;
    }

    @RequestMapping("save")
    public  String save(Product product){
        productService.save(product);
        return "redirect:/product/findAll";
    }

    /**
     * 数据回显
     * @param id
     * @return
     */
    @RequestMapping("findByid")
    public  ModelAndView update(Long id){
       Product product= productService.findById(id);

       //为了让前端页面显示字符串类型的日期，我们多设置一个属性，用来将事件转换为字符串类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String departureTimeStr = format.format(product.getDepartureTime());
        product.setDepartureTimeStr(departureTimeStr);

        //返回数据与视图
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product-update");

        return modelAndView;
    }

    /**
     * 修改数据
     * @param product
     * @return
     */
    @RequestMapping("update")
    public  String update(Product product){
         productService.update(product);
        return "redirect:/product/findAll";
    }


    /**
     * 删除单个数据
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    public  String deleteById(Long id){
        productService.deleteById(id);
        return "redirect:/product/findAll";
    }

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @RequestMapping("delSelected")
    public  String deleteAll(Long[] ids){
       productService.delSelected(ids);
        return "redirect:/product/findAll";
    }

}
