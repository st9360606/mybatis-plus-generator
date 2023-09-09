package com.example.mybatisplusgenerator.serviceImpl;

import com.example.mybatisplusgenerator.entity.User;
import com.example.mybatisplusgenerator.mapper.UserMapper;
import com.example.mybatisplusgenerator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kurt
 * @since 2023-07-28 04:58:59
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
