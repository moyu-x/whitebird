package top.idwangmo.whitebird.commoncore.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * bean copy util.
 *
 * @author idwangmo
 */
public class BeanCopyUtil extends BeanUtils {

    /**
     * 列表拷贝.
     *
     * @param sources 数据源
     * @param target 目标类
     * @return 拷贝完的数据
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 列表拷贝（带函数方法）.
     *
     * @param sources 数据源
     * @param target 目标类
     * @param callback 回调函数
     * @return 拷贝完的数据
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target,
                                                    BeanCopyUtilFunction<S, T> callback) {
        List<T> list = new ArrayList<>(sources.size());
        sources.forEach(source -> {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (Objects.nonNull(callback)) {
                callback.callBack(source, t);
            }
        });

        return list;
    }
}
