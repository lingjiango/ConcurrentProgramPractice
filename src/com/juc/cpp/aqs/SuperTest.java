package com.juc.cpp.aqs;

public class SuperTest {
    public static void main(String[] args) {
        Child c = new Child();
        c.add();
    }
}

class Parent{
    public final void add(){
        System.out.println("A add...");
        System.out.println(this.getClass().getName()); // 打印出来Child 你难道还会不明白么？
        addAll();
    }

    public void addAll(){
//        System.out.println("A add ALL...");
        //加上下面这句话 你的世界就变的清晰了  肯定就明白了
        System.out.println(this.getClass().getName()); // 打印出来Child 你难道还会不明白么？
//        this.add();
    }
}

class Child extends Parent{


    @Override
    public void addAll() {
        // TODO Auto-generated method stub
        System.out.println("B add ALL...");
//        super.addAll();
    }

}
