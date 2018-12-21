package com.hxhy.mapper;

import java.util.List;

import com.hxhy.model.dto.RevenueInfo;
import com.hxhy.model.po.HxhyRevenue;
import com.hxhy.util.MyMapper;

public interface HxhyRevenueMapper extends MyMapper<HxhyRevenue> {

	List<RevenueInfo> listInfo();

}
