package cn.zy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.zy.entity.ZJW_landlord;

public interface ZJW_landlordDao extends
		PagingAndSortingRepository<ZJW_landlord, Long> {
	@Query(value = "select * from zjw_landlord where true_name = ?1", nativeQuery = true)
	ZJW_landlord findByTruename(String string);
}
