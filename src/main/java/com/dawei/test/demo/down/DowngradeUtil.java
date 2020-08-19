package com.dawei.test.demo.down;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dawei.test.demo.function.SuperSupplier;


import lombok.extern.slf4j.Slf4j;

/**
 * 限流降级规则
 *
 * @author sinbad on 2020/07/02.
 */
@Slf4j
public class DowngradeUtil<T> implements SuperSupplier<T> {

    /**
     * 名字主要用来标记统计并对照配置
     */
    private final String resourceName;

    private final SuperSupplier<T> executorApi;
    private final SuperSupplier<T> fallBackApi;
    private final SuperSupplier<T> errorApi;

    public DowngradeUtil(String resourceName, SuperSupplier<T> executorApi,
                         SuperSupplier<T> fallBackApi, SuperSupplier<T> errorApi) {
        this.resourceName = resourceName;
        this.executorApi = executorApi;
        this.fallBackApi = fallBackApi;
        this.errorApi = errorApi;
    }

    @Override
    public T get() throws Throwable {
        DownConfigLoaderUtil.loadDownStrategyConfig();
        return runTask(resourceName, executorApi, fallBackApi, errorApi);
    }

    /**
     * 尝试执行
     *
     * @param resourceName 资源名称
     * @param executorApi  任务API
     * @param fallBackApi  限流降级逻辑
     */
    private T runTask(String resourceName, SuperSupplier<T> executorApi, SuperSupplier<T> fallBackApi,
                      SuperSupplier<T> errorApi) throws Throwable {
        Entry entry = null;
        try {
            entry = SphU.entry(resourceName);
            return executorApi.get();
        } catch (BlockException blockException) {
            blockException.fillInStackTrace();
            log.error("On call {} failed been down", resourceName, blockException);
            if (fallBackApi != null) {
                return fallBackApi.get();
            } else {
                throw blockException;
            }
        } catch (Exception throwable) {
            throwable.fillInStackTrace();
            log.error("On call {} error ", resourceName);
            if (errorApi != null) {
                return errorApi.get();
            } else {
                throw throwable;
            }
        } finally {
            entry.exit();
        }
    }
}
