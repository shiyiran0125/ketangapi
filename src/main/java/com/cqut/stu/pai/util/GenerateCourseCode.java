package com.cqut.stu.pai.util;

/**
 * @author 石益然
 * @program: pai
 * @description: 生成加课码的工具类
 * @date 2020-11-18 13:32:23
 */
public class GenerateCourseCode {
   public static String  generateCourseCode(){
       char[] one = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
       ,'0','1','2','3','4','5','6','7','8','9'};
       char[] codes= new char[6];
       codes[0] = one[(int)(Math.random()*37)];
       create(one,codes[0],codes,1);
       return String.valueOf(codes);
   }

   public static void create(char[] src, char c, char[] obj, int index){
       char[] src1 = new char[src.length-1];
       int indexSrc1 = 0;
       for (int i = 0; i < src.length; i++) {
           if (src[i]!=c){
               src1[indexSrc1++] = src[i];
           }
       }
       obj[index++] = src1[(int)(Math.random()*indexSrc1)];
       if (index==6){
           return;
       }
       create(src1,obj[index-1],obj,index);
   }
}