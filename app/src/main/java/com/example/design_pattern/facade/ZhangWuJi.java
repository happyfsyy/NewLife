package com.example.design_pattern.facade;

/**
 * 外观类，他负责将自己的招式、内功和经脉通过不同的情况合理地运用。
 */
public class ZhangWuJi {
    private JingMai jingMai;
    private NeiGong neiGong;
    private ZhaoShi zhaoShi;
    public ZhangWuJi(){
        jingMai=new JingMai();
        neiGong=new NeiGong();
        zhaoShi=new ZhaoShi();
    }
    public void QianKun(){
        jingMai.jingmai();
        neiGong.QianKun();
    }
    public void QiShang(){
        jingMai.jingmai();
        neiGong.JiuYang();
        zhaoShi.QiShangQuan();
    }
}
