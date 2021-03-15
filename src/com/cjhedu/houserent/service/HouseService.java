package com.cjhedu.houserent.service;

import com.cjhedu.houserent.domain.House;
import com.cjhedu.houserent.view.HouseView;

public class HouseService {
    //属性
    private House[] houses;//保存数组对象
    private int houseNum = 1;
    private int idCounter =1;

    //创建一个构造器
    public HouseService(int size){
        houses = new House[size];//指定数组的大小
        houses[0] = new House(1,"jack","112","白云区",2000,"未出租");//初始化一个数组便与测试

    }


    //方法

    //add方法，添加对象，返回boolean
    public boolean add(House newHouse){
       //判断是否可以继续添加
       if (houseNum == houses.length){
           System.out.println("数组已经满了，不能在添加了....");
           return false;
       }
       houses[houseNum++]=newHouse;//先用houseNum然后在自增加
       //要实现数组的id自增长
        newHouse.setId(++idCounter);
        return true;
    }



    //del方法，删除一个房屋信息
    public boolean del(int delInt){
        //应当先找到删除的房屋信息对应的下标
        int index = -1;
        for (int i = 0; i < houseNum; i++) {
            if (delInt == houses[i].getId()){
                index = i;
            }
        }

        if (index == -1){//未找到匹配的房屋信息的下标
            return false;
        }
        //如果找到要他的后面的数组向前移动，然后将数组的最后一个置空
        for (int i = index; i < houseNum-1; i++) {
            houses[i] = houses[i+1];
        }

        houses[--houseNum] = null;//将存在的数组最后一位置空然后数组的数量在减一
        return true;

    }


    //编写一个list方法返回数组houses
    public House[] list(){
        return houses;
    }


    //编写一个findById方法,返回house对象
    public House findById(int findId){

        //遍历数组
        for (int i = 0; i < houseNum; i++) {
            if (findId == houses[i].getId() ){
                return houses[i];
            }
        }
        return null;
    }


}
