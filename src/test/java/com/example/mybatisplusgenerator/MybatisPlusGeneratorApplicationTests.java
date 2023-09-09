package com.example.mybatisplusgenerator;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplusgenerator.entity.User;
import com.example.mybatisplusgenerator.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisPlusGeneratorApplicationTests {

    @Autowired
    private UserService userService;

    //TODO =============================Mybatis Plus 新增 ======================================
    @Test
    void testInsert() {
        // 新增一條用戶數據
        User newUser = new User();
        newUser.setId(6L);
        newUser.setName("Lucy");
        newUser.setAge(22);
        newUser.setEmail("test6@baomidou.com");
        userService.save(newUser);
    }

    //TODO =============================Mybatis Plus 刪除 ======================================
    @Test
    void testRemoveWithWrapper() {
        // 根據 queryWrapper 設置的條件，刪除記錄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Sandy");

        boolean success = userService.remove(queryWrapper);
        System.out.println("Remove success: " + success);
    }

    @Test
    void testRemoveById() {
        // 根據 ID 刪除
        Long idToRemove = 2L; // 要刪除的數據的id
        boolean success = userService.removeById(idToRemove);
        System.out.println("Remove success: " + success);
    }

    @Test
    void testRemoveByMap() {
        // 根據 columnMap 條件，刪除記錄
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Billie");

        boolean success = userService.removeByMap(columnMap);
        System.out.println("Remove success: " + success);
    }

    @Test
    void testRemoveBatchIds() {
        // 刪除（根據ID 批量刪除）
        List<Long> idsToRemove = Arrays.asList(3L, 4L); // 要刪除的數據的id列表
        boolean success = userService.removeByIds(idsToRemove);
        System.out.println("Remove success: " + success);
    }

    //TODO =============================Mybatis Plus 修改 ======================================
    @Test
    void testUpdateWithUpdateWrapper() {
        // 根據 UpdateWrapper 條件，更新記錄 需要設置 sqlset
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", "Lucy123")
                .eq("name", "Lucy"); // 將name是"Lucy"的記錄更新為"Lucy123"

        boolean success = userService.update(updateWrapper);
        System.out.println("Update success: " + success);
    }

    @Test
    void testUpdateWithWhereWrapper() {
        // 根據 whereWrapper 條件，更新記錄
        User updateEntity = new User();
        updateEntity.setName("Lucy456");

        QueryWrapper<User> whereWrapper = new QueryWrapper<>();
        whereWrapper.eq("name", "Lucy123"); // 將name是"Lucy123"的記錄更新為"Lucy456"

        boolean success = userService.update(updateEntity, whereWrapper);
        System.out.println("Update success: " + success);
    }

    @Test
    void testUpdateById() {
        // 根據 ID 選擇修改
        Long idToUpdate = 1L; // 要更新的數據的id
        User updateEntity = new User();
        updateEntity.setId(idToUpdate);
        updateEntity.setName("Lucy789");

        boolean success = userService.updateById(updateEntity);
        System.out.println("Update success: " + success);
    }

    @Test
    void testUpdateBatchById() {
        // 根據ID 批量更新
        List<User> entityListToUpdate = new ArrayList<>();

        // 添加要更新的實體對象
        User user1 = new User();
        user1.setId(3L);
        user1.setName("Tom1");
        entityListToUpdate.add(user1);

        User user2 = new User();
        user2.setId(4L);
        user2.setName("Sandy1");
        entityListToUpdate.add(user2);

        boolean success = userService.updateBatchById(entityListToUpdate);
        System.out.println("Update success: " + success);
    }

    @Test
    void testUpdateBatchByIdWithBatchSize() {
        // 根據ID 批量更新，指定批次大小
        List<User> entityListToUpdate = new ArrayList<>();
        // 添加要更新的實體對象，省略代碼...

        int batchSize = 2; // 指定批次大小
        boolean success = userService.updateBatchById(entityListToUpdate, batchSize);
        System.out.println("Update success: " + success);
    }

    //TODO =============================Mybatis Plus 查詢_Select ======================================
    @Test
    void testSelectById() {
        // 根據 ID 查詢
        Long idToFind = 1L; // 要查詢的數據的id
        User user = userService.getById(idToFind);
        System.out.println(user.toString());
    }

    @Test
    void testSelectOneWithWrapper() {
        // 根據 entity 條件，查詢一條記錄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        User user = userService.getOne(queryWrapper);
        System.out.println(user.toString());
    }

    @Test
    void testSelectBatchIds() {
        // 查詢（根據ID 批量查詢）
        List<Long> idsToFind = Arrays.asList(1L, 2L); // 要查詢的數據的id列表
        List<User> users = userService.listByIds(idsToFind);
        System.out.println(users.toString());
    }

    @Test
    void testSelectListWithWrapper() {
        // 根據 entity 條件，查詢全部記錄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<User> users = userService.list(queryWrapper);
        System.out.println(users.toString());
    }

    @Test
    void testSelectByMap() {
        // 查詢（根據 columnMap 條件）
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Lucy");
        List<User> users = userService.listByMap(columnMap);
        System.out.println(users.toString());
    }

    @Test
    void testSelectMaps() {
        // 根據 Wrapper 條件，查詢全部記錄，無序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<Map<String, Object>> resultMapList = userService.listMaps(queryWrapper);
        System.out.println(resultMapList.toString());
    }

    @Test
    void testSelectObjs() {
        // 根據 Wrapper 條件，查詢全部記錄。注意： 只返回第一個字段的值
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<Object> objList = userService.listObjs(queryWrapper);
        System.out.println(objList);
    }

    //TODO =============================Mybatis Plus 查詢_Get ======================================
    @Test
    void testGetById() {
        // 根據 ID 查詢
        Long idToFind = 1L; // 要查詢的數據的id
        User user = userService.getById(idToFind);
        System.out.println(user);
    }

    @Test
    void testGetOneWithWrapper() {
        // 根據 Wrapper，查詢一條記錄。結果集，如果是多個會拋出異常，隨機取一條加上限制條件 wrapper.last("LIMIT 1")
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    void testGetOneWithWrapperAndThrowEx() {
        // 根據 Wrapper，查詢一條記錄，並決定是否拋出異常
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        User user = userService.getOne(queryWrapper, true);
        System.out.println(user);
    }

    @Test
    void testGetMapWithWrapper() {
        // 根據 Wrapper，查詢一條記錄，並返回 Map，無序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        Map<String, Object> resultMap = userService.getMap(queryWrapper);
        System.out.println(resultMap);
    }

    @Test
    void testGetObjWithWrapperAndMapper() {
        // 根據 Wrapper，查詢一條記錄，並使用自定義類型的 mapper 函數處理結果
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        String userName = userService.getObj(queryWrapper, obj -> {
            if (obj instanceof User) {
                return ((User) obj).getName();
            }
            return null;
        });
        System.out.println(userName);
    }

    //TODO =============================Mybatis Plus 查詢_List ======================================
    @Test
    void testList() {
        // 查詢所有
        List<User> list = userService.list();
        System.out.println(list.toString());
    }

    @Test
    void testListWithWrapper() {
        // 查詢列表（根據 Wrapper 條件）
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<User> list = userService.list(queryWrapper);
        System.out.println(list.toString());
    }

    @Test
    void testListByIds() {
        // 查詢（根據ID 批量查詢）
        Collection<Long> idList = Arrays.asList(1L, 2L);
        Collection<User> list = userService.listByIds(idList);
        System.out.println(list.toString());
    }

    @Test
    void testListByMap() {
        // 查詢（根據 columnMap 條件）
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Lucy");
        Collection<User> list = userService.listByMap(columnMap);
        System.out.println(list.toString());
    }

    @Test
    void testListMaps() {
        // 查詢所有列表，返回 Map，無序
        List<Map<String, Object>> list = userService.listMaps();
        System.out.println(list.toString());
    }

    @Test
    void testListMapsWithWrapper() {
        // 查詢列表（根據 Wrapper 條件），返回 Map
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<Map<String, Object>> list = userService.listMaps(queryWrapper);
        System.out.println(list.toString());
    }

    @Test
    void testListObjs() {
        // 查詢全部記錄，返回 Object 列表
        List<Object> list = userService.listObjs();
        System.out.println(list);
    }

    @Test
    void testListObjsWithWrapper() {
        // 根據 Wrapper 條件，查詢全部記錄，返回 Object 列表，注意： 只返回第一個字段的值
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<Object> list = userService.listObjs(queryWrapper);
        System.out.println(list.toString());
    }

    @Test
    void testListObjsWithWrapperAndMapper() {
        // 根據 Wrapper 條件，查詢全部記錄，返回自定義類型列表
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");
        List<String> userNames = userService.listObjs(queryWrapper, obj -> {
            if (obj instanceof User) {
                return ((User) obj).getName();
            }
            return null;
        });
        System.out.println(userNames.toString());
    }

    //TODO =============================Mybatis Plus Count_計數 ======================================
    @Test
    void testCount() {
        // 查詢總記錄數
        long totalRecords = userService.count();
        System.out.println("Total records: " + totalRecords);
    }

    @Test
    void testCountWithWrapper() {
        // 根據 Wrapper 條件，查詢總記錄數
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Lucy");

        long totalRecords = userService.count(queryWrapper);
        System.out.println("Total records: " + totalRecords);
    }



    /**
     * SELECT id,name,age,email,role_id FROM user
     * WHERE ( 1 = 1 ) AND ( ( name = ? AND age = ? ) OR ( name = ? AND age = ? ) )
     */
    @Test
    public void testSql() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("1", 1))
                .nested(i ->
                        i.and(j -> j.eq("name", "a").eq("age", 2))
                                .or(j -> j.eq("name", "b").eq("age", 2)));
        userService.list(wrapper);
    }

    /**
     * SELECT id,name FROM user
     * WHERE (age BETWEEN ? AND ?) ORDER BY role_id ASC,id ASC
     */
    @Test
    public void testSelect() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","age","email").between("age",20,25)
                .orderByAsc("age","id");
        List<User> plainUsers = userService.list(wrapper);

        LambdaQueryWrapper<User> lwq = new LambdaQueryWrapper<>();
        lwq.select(User::getId,User::getName).between(User::getAge,20,25)
                .orderByAsc(User::getId);
        List<User> lambdaUsers = userService.list(lwq);

        System.out.print(plainUsers.toString());
        Assertions.assertEquals(plainUsers.size(), lambdaUsers.size());
    }

}
