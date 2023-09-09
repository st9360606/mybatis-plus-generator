package com.example.mybatisplusgenerator.controller;

import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mybatisplusgenerator.service.UserService;
import com.example.mybatisplusgenerator.entity.User;

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
@RequestMapping("/user")
    public class UserController {

@Resource
private UserService userService;

        //新增或者更新
        @PostMapping("/saveOrUpdate")
        public boolean save(@RequestBody User user){
                return userService.saveOrUpdate(user);
        }

        //删除单个
        @DeleteMapping("/del/{id}")
        public boolean delete(@PathVariable Integer id){
                return userService.removeById(id);
        }

        //批量删除
        @DeleteMapping("/dels/{ids}")
        public boolean delete(@PathVariable List<User> ids){
                return userService.removeByIds(ids);
        }

        //修改
        @PostMapping("/update")
        public boolean update(@RequestBody User user){
                return userService.update(user,null);
        }

        //查找全部
        @GetMapping("/findAll")
        public List<User> findAll(){
                return userService.list();
        }

        //单个查找
        @GetMapping("/findOne")
        public User findOne(@PathVariable Integer id){
                return userService.getById(id);
        }

        //分页
        @GetMapping("/page")
        public Page<User> findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize){
                QueryWrapper<User> queryWrapper=new QueryWrapper<>();
                return userService.page(new Page<>(pageNum,pageSize),queryWrapper);
        }
}
