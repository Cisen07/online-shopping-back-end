package com.demo.mms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.demo.mms.common.domain.Good;
import com.demo.mms.common.domain.Shoppingcart;
import com.demo.mms.common.domain.User;
import com.demo.mms.common.utils.IDGenerator;
import com.demo.mms.service.GoodsService;
import com.demo.mms.service.ShoppingcartService;
import com.demo.mms.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingcartService shoppingcartService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody JSONObject jsonObject) {
        String typeInName = jsonObject.getString("typeInName");
        String typeInPassword = jsonObject.getString("typeInPassword");
        JSONObject jsonObjectR = new JSONObject();
        User user = userService.findUserByName(typeInName);
        String msg;
        Boolean success = false;
        if (user == null){
            msg = "用户名不存在";
        } else if (!user.getPassword().equals(typeInPassword)){
            msg = "密码错误";
        } else {
            success = true;
            jsonObjectR.put("id", user.getUserid());
            msg = "密码正确";
        }
        jsonObjectR.put("success", success);
        jsonObjectR.put("message", msg);
        return jsonObjectR;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Object register(@RequestBody JSONObject jsonObject){
        String typeInName = jsonObject.getString("typeInName");
        String typeInPassword = jsonObject.getString("typeInPassword");
        String typeInPhoneNumber = jsonObject.getString("typeInPhoneNumber");
        User user = userService.findUserByName(typeInName);
        String msg;
        Boolean success = false;
        JSONObject jsonObjectR = new JSONObject();
        if (user != null){
            msg = "用户名已存在";
        } else if (typeInPassword == null){
            msg = "密码不能为空";
        } else if (typeInPhoneNumber == null){
            msg = "电话号码不能为空";
        } else {
            User newUser = new User();
            newUser.setUsername(typeInName);
            newUser.setUserpassword(typeInPassword);
            newUser.setUserid(IDGenerator.getIdInt9());
            newUser.setPhonenumber(typeInPhoneNumber);
            userService.addNewUser(newUser);
            success = true;
            jsonObjectR.put("id", newUser.getUserid());
            msg = "注册成功";
        }
        jsonObjectR.put("success", success);
        jsonObjectR.put("message", msg);
        return jsonObjectR;
    }

    @RequestMapping("/cartList")
    @ResponseBody
    public Object getCartList(@RequestBody JSONObject jsonObject){
        String wantUserID = jsonObject.getString("userID");
        String[] productIDList = shoppingcartService.getAllProductIDByUserID(wantUserID);
        String[] productNumList = shoppingcartService.getAllProductNumByUserID(wantUserID);
        JSONObject jsonObjectR = new JSONObject();
        int num = productIDList.length;
        if (num == 0){
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "该用户购物车为空");
            jsonObjectR.put("result", null);
        } else {
            ArrayList<JSONObject> tempArrayList = new ArrayList<JSONObject>();
            for (int i = 0; i < num; i++) {
                JSONObject tempJSONObject = new JSONObject();
                Good tempGooder = goodsService.getGoodByID(productIDList[i]);
                tempJSONObject.put("productId", productIDList[i]);
                tempJSONObject.put("productName", tempGooder.getGoodname());
                tempJSONObject.put("subTitle", tempGooder.getDescription());
                tempJSONObject.put("salePrice", tempGooder.getPrice());
                tempJSONObject.put("productImg", tempGooder.getPicture());
                tempJSONObject.put("productNum", productNumList[i]);
                tempArrayList.add(tempJSONObject);
            }
            jsonObjectR.put("success",true);
            jsonObjectR.put("message", "成功得到购物车列表");
            jsonObjectR.put("result", tempArrayList);
        }
        return jsonObjectR;
    }

    @RequestMapping("/addCart")
    @ResponseBody
    public Object addProductToCart(@RequestBody JSONObject jsonObject){
        String wantUserID = jsonObject.getString("userId");
        String wantProductID = jsonObject.getString("productId");
        String wantProductNum = jsonObject.getString("productNum");
        JSONObject jsonObjectR = new JSONObject();
        Shoppingcart tempShoppingcart = shoppingcartService.getProductBy2IDs(wantUserID, wantProductID);
        if (tempShoppingcart == null){  //购物车中原本无此种产品
            User tempUser = userService.getUserByID(wantUserID);
            if (tempUser == null){
                jsonObjectR.put("success", false);
                jsonObjectR.put("message", "该用户不存在");
                return jsonObjectR;
            } else {
                Good tempGood = goodsService.getGoodByID(wantProductID);
                if (tempGood == null){
                    jsonObjectR.put("success", false);
                    jsonObjectR.put("message", "该产品不存在");
                    return jsonObjectR;
                } else {
                    Shoppingcart insertShoppingcart = new Shoppingcart();
                    int wantUserIDInt = 0;
                    int wantProductIDInt = 0;
                    int wantProductNumInt = 0;
                    try {
                        wantUserIDInt = Integer.valueOf(wantUserID).intValue();
                        wantProductIDInt = Integer.valueOf(wantProductID).intValue();
                        wantProductNumInt = Integer.valueOf(wantProductNum).intValue();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    insertShoppingcart.setGoodid(wantProductIDInt);
                    insertShoppingcart.setUserid(wantUserIDInt);
                    insertShoppingcart.setGoodnum(wantProductNumInt);
                    shoppingcartService.insert(insertShoppingcart);
                    jsonObjectR.put("success", true);
                    jsonObjectR.put("message", "已经添加该商品到购物车");
                    return jsonObjectR;
                }
            }
        } else {    //增加购物车已有产品的数量
            Shoppingcart updateShoppingcart = new Shoppingcart();
            int wantUserIDInt = 0;
            int wantProductIDInt = 0;
            int wantProductNumInt = 0;
            try {
                wantUserIDInt = Integer.valueOf(wantUserID).intValue();
                wantProductIDInt = Integer.valueOf(wantProductID).intValue();
                wantProductNumInt = Integer.valueOf(wantProductNum).intValue();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            updateShoppingcart.setGoodid(wantProductIDInt);
            updateShoppingcart.setUserid(wantUserIDInt);
            updateShoppingcart.setGoodnum(wantProductNumInt + tempShoppingcart.getGoodnum());
            shoppingcartService.update(updateShoppingcart);
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "已经增加购物车中该产品的数量");
        }
        return jsonObjectR;
    }

    @RequestMapping("/cartDel")
    @ResponseBody
    public Object deleteCart(@RequestBody JSONObject jsonObject){
        JSONObject jsonObjectR = new JSONObject();
        String wantUserID = jsonObject.getString("userId");
        String wantProductID = jsonObject.getString("productId");
        Shoppingcart tempShoppingcart = shoppingcartService.getProductBy2IDs(wantUserID, wantProductID);
        if (tempShoppingcart == null) {
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "该购物车不存在");
        } else {
            shoppingcartService.delete(wantUserID, wantProductID);
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "删除成功");
        }
        return jsonObjectR;
    }

    @RequestMapping("/cartEdit")
    @ResponseBody
    public Object editCart(@RequestBody JSONObject jsonObject){
        String wantUserID = jsonObject.getString("userId");
        String wantProductID = jsonObject.getString("productId");
        String wantProductNum = jsonObject.getString("productNum");
        JSONObject jsonObjectR = new JSONObject();
        Shoppingcart tempShoppingcart = shoppingcartService.getProductBy2IDs(wantUserID, wantProductID);
        if (tempShoppingcart == null) {
            jsonObjectR.put("success", false);
            jsonObjectR.put("message", "购物车中不存在该产品");
            return jsonObjectR;
        } else {
            Shoppingcart updateShoppingcart = new Shoppingcart();
            int wantUserIDInt = 0;
            int wantProductIDInt = 0;
            int wantProductNumInt = 0;
            try {
                wantUserIDInt = Integer.valueOf(wantUserID).intValue();
                wantProductIDInt = Integer.valueOf(wantProductID).intValue();
                wantProductNumInt = Integer.valueOf(wantProductNum).intValue();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            updateShoppingcart.setGoodid(wantProductIDInt);
            updateShoppingcart.setUserid(wantUserIDInt);
            updateShoppingcart.setGoodnum(wantProductNumInt);
            shoppingcartService.update(updateShoppingcart);
            jsonObjectR.put("success", true);
            jsonObjectR.put("message", "已经改变购物车中该产品的数量");
            return jsonObjectR;
        }
    }


}
//获取参数可以使用bean，也就是使用Admin admin，再利用方法
//        //也可以直接获取，这里就是，注意要用包装类，不用基本类型，比如要用Integer而不是int，这样在输入空值时不会报错
//        //@DateTimeFormat(pattern = "MM-dd")Date time
//        //根据用户名查找对象