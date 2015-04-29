package cn.zy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.zy.entity.ZJW_user;

public interface ZJW_userDao extends PagingAndSortingRepository<ZJW_user, Long> {
	@Query(value = "select * from zjw_user where last_name = ?1", nativeQuery = true)
	ZJW_user findByLastname(String string);
}
