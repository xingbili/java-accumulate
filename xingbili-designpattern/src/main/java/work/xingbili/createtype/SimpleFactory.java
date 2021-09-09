/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.createtype;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/9/8 9:08
 */
public class SimpleFactory {
    public IProduct getProduct(String type){
        if(type=="A"){
            return new ProductA();
        }
        if(type=="B"){
            return new ProductB();
        }
        return null;
    }
}

interface IProduct{

}

class ProductA implements IProduct{
    ProductA(){
        System.out.println("productA");
    }
}

class ProductB implements  IProduct{
    ProductB(){
        System.out.println("productB");
    }
}
