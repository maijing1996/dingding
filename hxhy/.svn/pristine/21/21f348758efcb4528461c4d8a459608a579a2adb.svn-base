package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.ExtraMoney;
import com.hxhy.model.po.HxhyUserExtraMoney;
import com.hxhy.util.MyMapper;

public interface HxhyUserExtraMoneyMapper extends MyMapper<HxhyUserExtraMoney> {

	List<ExtraMoney> listInfo(@Param("state") Integer state, @Param("name") String name,
			@Param("monthy") String monthy, @Param("extraType") Integer extraType,
			@Param("type") Integer type);
	
	List<String> getMonthy();
}
