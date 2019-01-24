package com.example.design_pattern.proxy;

/**
 * 代理类也要同样实现IShop接口，并且要持有被代理者。
 * 在buy()方法中调用了被代理者的buy()方法。
 *
 * 这是静态代理，在代码运行前，就已经存在了代理类的class编译文件；
 * 而动态代理则是在代码运行时通过反射来动态地生成代理类的对象，并确定到底来代理谁。
 * 也就是我们在编码时无须知道代理谁，代理谁将会在运行时决定。
 */
public class Purchasing implements IShop{
    private IShop mShop;
    public Purchasing(IShop shop){
        mShop=shop;
    }
    @Override
    public void buy() {
        mShop.buy();
    }
}
