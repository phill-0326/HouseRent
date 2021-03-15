package com.cjhedu.houserent.view;

import com.cjhedu.houserent.domain.House;
import com.cjhedu.houserent.service.HouseService;
import com.cjhedu.houserent.untils.Utility;

/**
 * 1.显示界面
 * 2.接受用户的输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {
    private boolean loop = true;
    private char key = ' ';
    HouseService houseService = new HouseService(2);//设置数组大小为10

    //编写listHouse()显示房屋列表
    public void listHouse() {
        System.out.println("==========房屋列表==========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(出租/未出租)");
        //调用list()方法
        House[] house = houseService.list();
        //遍历数组
        for (int i = 0; i < house.length; i++) {
            //判断是否为空，若为空则直接退出，否则就打印输出
            if (house[i] == null) {
                break;
            }
            System.out.println(house[i]);
        }
        System.out.println("=========房屋列表显示完毕========");
    }


    //编写addHouse() 接受输入，创建House对象，调用add方法
    public void addHouse() {
        System.out.println("============添加房屋===========");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(11);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);

        //创建一个新的House对象，注意id是系统分配的
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("房屋添加成功！");
        } else {
            System.out.println("房屋添加失败！");
        }
    }

    //编写delHouse,接受输入的id，调用Service的del方法
    public void delHouse() {
        System.out.println("============删除房屋============");
        System.out.println("请选择待删除的房屋编号（-1退出）：");
        int delInt = Utility.readInt();
        if (delInt == -1) {
            System.out.println("========放弃删除房屋信息========");
            return;
        }
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {
            if (houseService.del(delInt)) {
                System.out.println("========删除房屋成功========");
            } else {
                System.out.println("========房屋编号不存在，删除失败！========");
            }
        } else {
            System.out.println("========放弃删除房屋信息========");
        }
    }

    //编写修改的方旭信息的方法
    public void update() {
        System.out.println("=============修改房屋信息============");
        System.out.println("请选择待修改房屋信息编号(-1表示退出)");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("=============你放弃修改房屋信息============");
            return;
        }
        //根据输入的updateId,查找对象
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("=============修改房屋信息编号不存在..============");
            return;
        }
        System.out.println("姓名(" + house.getName() + "): ");
        String name = Utility.readString(8, "");
        if (!"".equals(name)) {
            house.setName(name);
        }

        System.out.print("电话(" + house.getPhone() + "): ");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }

        System.out.print("地址(" + house.getAddress() + "): ");
        String address = Utility.readString(16, "");
        if (!"".equals(address)) {
            house.setAddress(address);
        }

        System.out.print("月租(" + house.getRent() + "): ");
        int rent = Utility.readInt(-1);
        if (rent != -1) {
            house.setRent(rent);
        }

        System.out.print("状态(" + house.getState() + "): ");
        String state = Utility.readString(3, "");
        if (!"".equals(state)) {
            house.setState(state);
        }
        System.out.println("============修改房屋信息成功=============");


    }


    //编写exit()退出方法
    public void exit() {
        //这里使用Utility
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    //根据id查找房屋信息
    public void findHouse() {
        System.out.println("============查找房屋信息===========");
        int findId = Utility.readInt();
        //调用find方法
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("===========查找房屋信息失败，id不存在===========");
        }

    }


    //编写mainMenu方法，可以显示主菜单
    public void mainView() {
        do {
            System.out.println("============房屋出租系统============");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退       出");
            System.out.println("请输入选择(1-6): ");
            key = Utility.readChar();
            switch (key) {
                case '1'://要和key的数据类型对应
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    update();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while (loop);


    }
}
