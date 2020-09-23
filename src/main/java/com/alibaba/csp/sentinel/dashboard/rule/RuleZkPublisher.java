package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.ZookeeperConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2020/9/4 11:13
 * @description:
 */
public abstract class RuleZkPublisher<T extends RuleEntity> implements DynamicRulePublisher<List<T>> {
    @Autowired
    protected CuratorFramework zkClient;
    @Autowired
    protected Converter<List<T>, String> converter;

    @Override
    public void publish(String app, List<T> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");

        String path = ZookeeperConfigUtil.getPath(app, getDataIdPostfix());
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        byte[] data = CollectionUtils.isEmpty(rules) ? "[]".getBytes() : converter.convert(rules).getBytes();
        zkClient.setData().forPath(path, data);
    }

    public abstract String getDataIdPostfix();
}
