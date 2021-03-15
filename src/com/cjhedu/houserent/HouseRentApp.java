package com.cjhedu.houserent;

import com.cjhedu.houserent.view.HouseView;

/**
 * 房屋出租系统
 * 实现基于文本界面的《房屋出租软件》
 * 能够实现对房屋信息的添加、修改和删除（用数组实现），并且能够打印房屋明细表
 * @author：phil
 * @Date 2021/03/15
 */
public class HouseRentApp {
    public static void main(String[] args) {
        new HouseView().mainView();
        System.out.println("======你已经退出房屋出租系统======");
    }
}
