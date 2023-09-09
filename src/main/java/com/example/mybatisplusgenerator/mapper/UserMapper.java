package com.example.mybatisplusgenerator.mapper;

import com.example.mybatisplusgenerator.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kurt
 * @since 2023-07-28 04:58:59
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
