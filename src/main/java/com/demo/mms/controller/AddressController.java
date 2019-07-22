package com.demo.mms.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.AddressKey;
import com.demo.mms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/member")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("/addressList")
    @ResponseBody
    public Object getaddressList(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("userId");
        JSONObject jsonObjectR = new JSONObject();
        ArrayList<JSONObject> resultArrayList = new ArrayList<>(20);
        int numberOfAddress = addressService.getNumberOfAddress(id);
        AddressKey tempAddress;
        if (numberOfAddress == 0) {
            try {
                jsonObjectR.put("success", false);
                jsonObjectR.put("message", "无可用地址");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < numberOfAddress; i++) {
                JSONObject tempJObject = new JSONObject();
                tempAddress = addressService.getAddressItem(i, id);
                tempJObject.put("name", tempAddress.getName());
                tempJObject.put("address", tempAddress.getAddress());
                tempJObject.put("phonenum", tempAddress.getPhonenum());
                resultArrayList.add(tempJObject);
            }
            try {
                jsonObjectR.put("success", true);
                jsonObjectR.put("message", null);
                jsonObjectR.put("result", resultArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectR;
    }

    @RequestMapping("/updateAddress")
    @ResponseBody
    public Object toupdate(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String oldName = jsonObject.getString("oldName");
        String oldAddress = jsonObject.getString("oldAddress");
        String oldPhoneNum = jsonObject.getString("oldPhoneNum");
        String newName = jsonObject.getString("newName");
        String newAddress = jsonObject.getString("newAddress");
        String newPhoneNum = jsonObject.getString("newPhoneNum");
        JSONObject jsonObjectR = new JSONObject();
        AddressKey address = addressService.findAddress(userId, oldName, oldAddress, oldPhoneNum);
        if (address == null) {
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "旧地址不存在");
        } else {
            AddressKey address2 = addressService.findAddress(userId, newName, newAddress, newPhoneNum);
            if (address2 != null){
                jsonObjectR.put("success", false);
                jsonObjectR.put("message", "新地址已经存在");
            } else {
                addressService.update(userId, oldName, oldAddress, oldPhoneNum, newName, newAddress, newPhoneNum);
                jsonObjectR.put("success", true);
                jsonObjectR.put("message", "修改成功");
            }
        }
        return jsonObjectR;
    }

    @RequestMapping("/addAddress")
    @ResponseBody
    public Object addAddress(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String newName = jsonObject.getString("newName");
        String newAddress = jsonObject.getString("newAddress");
        String newPhoneNum = jsonObject.getString("newPhoneNum");
        JSONObject jsonObjectR = new JSONObject();
        if(addressService.findAddress(userId,newName,newAddress,newPhoneNum) == null){
            addressService.addAddress(userId,newName,newAddress,newPhoneNum);
            try {
                jsonObjectR.put("success", true);
                jsonObjectR.put("message", "添加成功");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            try {
                jsonObjectR.put("success", false);
                jsonObjectR.put("message", "该地址已经存在");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectR;
    }

    @RequestMapping("/delAddress")
    @ResponseBody
    public Object delAddress(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String phoneNum = jsonObject.getString("phoneNum");
        JSONObject jsonObjectR = new JSONObject();
        if (addressService.findAddress(userId, name, address, phoneNum) == null){
            try {
                jsonObjectR.put("success", false);
                jsonObjectR.put("message", "该地址不存在");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            addressService.delAddress(userId, name, address, phoneNum);
            try {
                jsonObjectR.put("success", true);
                jsonObjectR.put("message", "删除成功");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjectR;
    }
}
