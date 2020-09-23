package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.ZookeeperConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @auther: zhongjias
 * @date: 2020/9/4 11:12
 * @description:
 */
public abstract class RuleZkProvider<T extends RuleEntity> implements DynamicRuleProvider<List<T>> {
    @Autowired
    protected CuratorFramework zkClient;
    @Autowired
    protected Converter<String, List<T>> converter;

    @Override
    public List<T> getRules(String appName) throws Exception {
        String zkPath = ZookeeperConfigUtil.getPath(appName,getDataIdPostfix());
        Stat stat = zkClient.checkExists().forPath(zkPath);
        if(stat == null){
            return new ArrayList<>(0);
        }
        byte[] bytes = zkClient.getData().forPath(zkPath);
        if (null == bytes || bytes.length == 0) {
            return new ArrayList<>();
        }
        String s = new String(bytes);

        return converter.convert(s);
    }

    public abstract String getDataIdPostfix();
}
