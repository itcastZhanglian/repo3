package com.itheima.utils;

public class SQLCreate {
    public static void main(String[] args) {
        String[] strings={"高文相","聂万庆","肖垚","胡锦波","李雪锋","杨哲瑞","成坤","何凯强","姚赛","沈承志","邓超","李新虎","梅帅","马佳","靖德才","马国旭","裴康","陈佳辉","唐梦辉","朱震","俞晋豫","李子凡","冯浩","秦伟","杨鸿靖","翁少旭","代建","葛腾飞","张志城","罗家容","王跃","柯睿","刘森磊","刘涛","蔡安平","高焱","王军","石寒","李明扬","马勇涛","徐磊","杨山","张劲豪","陈承进","程锐","杨杰","张炳元","李琦","李祖杰","岳方方","杨月鑫","肖正","熊家胜","李承浩","郭林力","宋港迎","段光兵","张廉","袁思满","胡成","蔡勇杰","朱恒","姚聪","曾圆","汪小明","余雷","刘俊","李亮","钟凯","阎康","倪明","余晓涵","黄旭","高金帅","吴纪","丁星杰","刘洋","刘威","骆栋栋","常锋","杜思源","杨瑞"};
        for (int i = 0; i < strings.length; i++) {
            if(i==55 || i==80){
                strings[i]="INSERT INTO user VALUES(null,'"+strings[i]+"','女',18,'中国','java','java@itcast.cn');";
                System.out.println(strings[i]);
            }else{
                strings[i]="INSERT INTO user VALUES(null,'"+strings[i]+"','男',18,'中国','java','java@itcast.cn');";
                System.out.println(strings[i]);
            }
        }
    }
}
