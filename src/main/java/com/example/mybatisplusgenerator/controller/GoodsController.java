package com.example.mybatisplusgenerator.controller;

import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mybatisplusgenerator.service.GoodsService;
import com.example.mybatisplusgenerator.entity.Goods;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kurt
 * @since 2023-07-28 04:58:59
 */
@RestController
@RequestMapping("/goods")
    public class GoodsController {

@Resource
private GoodsService goodsService;

        //新增或者更新
        @PostMapping("/saveOrUpdate")
        public boolean save(@RequestBody Goods goods){
                return goodsService.saveOrUpdate(goods);
        }

        //删除单个
        @DeleteMapping("/del/{id}")
        public boolean delete(@PathVariable Integer id){
                return goodsService.removeById(id);
        }

        //批量删除
        @DeleteMapping("/dels/{ids}")
        public boolean delete(@PathVariable List<Goods> ids){
                return goodsService.removeByIds(ids);
        }

        //修改
        @PostMapping("/update")
        public boolean update(@RequestBody Goods goods){
                return goodsService.update(goods,null);
        }

        //查找全部
        @GetMapping("/findAll")
        public List<Goods> findAll(){
                return goodsService.list();
        }

        //单个查找
        @GetMapping("/findOne")
        public Goods findOne(@PathVariable Integer id){
                return goodsService.getById(id);
        }

        //分页
        @GetMapping("/page")
        public Page<Goods> findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize){
                QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
                return goodsService.page(new Page<>(pageNum,pageSize),queryWrapper);
        }
}
