package top.idwangmo.whitebird.commoncore.util;

/**
 * bean 拷贝.
 *
 * @author idwangmo
 */
@FunctionalInterface
public interface BeanCopyUtilFunction <S, T>{

   /**
    * 定义默认返回方法.
    *
    * @param t T
    * @param s S
    */
   void callBack(S t, T s);
}
