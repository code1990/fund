package com.dao;

import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public interface UserDao extends JpaRepository<User,Integer> {
}

