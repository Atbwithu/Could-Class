package com.gbl.service.user;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gbl.entity.*;
import com.gbl.mapper.UserMapper;
import org.springframework.stereotype.Service;
/**
 * @Author: Insight
 * @Description: TODO
 * @Date: 2024/5/1 15:46
 * @Version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    /*@Resource
    UserMapper mapper;

    @Autowired
    StringRedisTemplate redis;


    @Override
    @Transactional
    public Boolean insert(User user) {
        return mapper.insert(user);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public User selectById(int id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    public Boolean update(User user) {
        return mapper.update(user);
    }

    @Override
    public List<User> userList(User user) {
        return mapper.userList(user);
    }

    @Override
    public int countStu() {
        return mapper.countStu();
    }

    @Override
    public int countQuestion() {
        return mapper.countQuestion();
    }

    @Override
    public int countCourse() {
        return mapper.countCourse();
    }

    @Override
    @Transactional
    public Boolean login(String username, String password) {
        User user = mapper.selectByName(username);
        if (user == null) {
            return false;
        }
        boolean checkpw = BCrypt.checkpw(password, user.getPasswordSalt());
        if (checkpw) {
            redis.opsForValue().set("username",  JwtTokenUtil.generateToken(username));
            String salt = BCrypt.gensalt();
            String hashpw = BCrypt.hashpw(user.getPassword(),salt);
            user.setPasswordSalt(salt);
            user.setPassword(hashpw);
            return mapper.update(user);
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean register(User user) {
        String gensalt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw(user.getPassword(), gensalt);
        user.setPassword(hashpw);
        user.setCreateTime(new Date().getTime());
        user.setPasswordSalt(gensalt);
        return true;
    }*/

}
