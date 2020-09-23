
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.ZookeeperConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component("paramFlowRuleZookeeperPublisher")
public class ParamFlowRuleZookeeperPublisher extends RuleZkPublisher<ParamFlowRuleEntity> {
    @Value("${sentinel.param.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

    @Autowired
    private Converter<List<ParamFlowRuleCorrectEntity>, String> converterParam;

    @Override
    public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
        //  转换
        List<ParamFlowRuleCorrectEntity> list = rules.stream().map(rule -> {
            ParamFlowRuleCorrectEntity entity = new ParamFlowRuleCorrectEntity();
            BeanUtils.copyProperties(rule,entity);
            return entity;
        }).collect(Collectors.toList());
        AssertUtil.notEmpty(app, "app name cannot be empty");

        String path = ZookeeperConfigUtil.getPath(app, getDataIdPostfix());
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        byte[] data = CollectionUtils.isEmpty(rules) ? "[]".getBytes() : converterParam.convert(list).getBytes();
        zkClient.setData().forPath(path, data);
    }
}