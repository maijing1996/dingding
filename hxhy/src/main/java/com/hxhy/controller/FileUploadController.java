package com.hxhy.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.model.po.HxhyUserExtraMoney;
import com.hxhy.service.HxhyManagerService;
import com.hxhy.service.HxhyUserExtraMoneyService;
import com.hxhy.util.DateUtil;
import com.hxhy.util.StringUtil;
 
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private HxhyManagerService hxhyManagerService;
    @Autowired
    private HxhyUserExtraMoneyService hxhyUserExtraMoneyService;
    
    /**
     *
     *  异步上传图片
     *
     * @param multipartRequest
     * @return
     * @throws URISyntaxException
     */
    @ResponseBody
    @RequestMapping(value = "/file", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String UploadAction(MultipartHttpServletRequest multipartRequest) {

    	Map<String, String> map = new HashMap<String, String>();
        MultipartFile file = multipartRequest.getFile("file");
        if (StringUtil.isNotNullOrEmpty(file)) {
            try {
            	//获得所有的在职员工信息
            	HxhyManager hxhyManager = new HxhyManager();
            	hxhyManager.setIs_work(1);
            	List<HxhyManager> userList = hxhyManagerService.getAll(hxhyManager, null);
            	List<HxhyUserExtraMoney> saveList = new ArrayList<HxhyUserExtraMoney>();
            	
            	byte[] byt = file.getBytes();
            	InputStream inputStream = new ByteArrayInputStream(byt);
            	// 将返回的输入流转换成字符串
    			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
    			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    			
    			String str = null;
    			while ((str = bufferedReader.readLine()) != null) {
    				String[] arr = str.split(",");
    				
					if(arr.length > 1) {
						for(HxhyManager hxhyManager2 : userList) {
    						if(arr[0].equals(hxhyManager2.getName())) {
    							Double money = StringUtil.strToDouble(arr[1]);
    							if(money != null) {
    								
    								Date now = new Date();
    								HxhyUserExtraMoney hxhyUserExtraMoney = new HxhyUserExtraMoney();
    								hxhyUserExtraMoney.setAdd_date(now);
    								hxhyUserExtraMoney.setDescription("餐费");
    								hxhyUserExtraMoney.setExtra_type(2);
    								hxhyUserExtraMoney.setMoney(money);
    								hxhyUserExtraMoney.setMonthy(DateUtil.format(now, DateUtil.FORMAT_YYYYMM));
    								hxhyUserExtraMoney.setState(2);
    								hxhyUserExtraMoney.setTitle("餐费");
    								hxhyUserExtraMoney.setType(1);
    								hxhyUserExtraMoney.setUse_date(now);
    								hxhyUserExtraMoney.setUser_id(hxhyManager2.getUser_id());
    								
    								saveList.add(hxhyUserExtraMoney);
    								break;
    							}
    						}
						}
					}
				}
    			
                
    			hxhyUserExtraMoneyService.insertList(saveList);
    			map.put("code", "200");
            	map.put("msg", "上传成功");
                return JSONObject.toJSONString(map);
            } catch (Exception e) {
                logger.error("上传失败"+e);
                map.put("code", "403");
            	map.put("msg", "上传失败，请设置文件为UTF-8编码再上传");
            	return JSONObject.toJSONString(map);
            }
        } else {
        	map.put("code", "403");
        	map.put("msg", "上传失败，请设置文件为UTF-8编码再上传");
        	return JSONObject.toJSONString(map);
        }
    }
}
