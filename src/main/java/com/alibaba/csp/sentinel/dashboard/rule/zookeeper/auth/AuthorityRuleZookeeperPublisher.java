
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.auth;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.ZookeeperConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.util.AssertUtil;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component("authorityRuleZookeeperPublisher")
public class AuthorityRuleZookeeperPublisher extends RuleZkPublisher<AuthorityRuleEntity> {
    @Value("${sentinel.auth.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }
    @Autowired
    private Converter<List<AuthorityRuleCorrectEntity>, String> converterAuth;
    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        //  转换
        List<AuthorityRuleCorrectEntity> list = rules.stream().map(rule -> {
            AuthorityRuleCorrectEntity entity = new AuthorityRuleCorrectEntity();
            BeanUtils.copyProperties(rule,entity);
            return entity;
        }).collect(Collectors.toList());
        AssertUtil.notEmpty(app, "app name cannot be empty");

        String path = ZookeeperConfigUtil.getPath(app, getDataIdPostfix());
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        byte[] data = CollectionUtils.isEmpty(rules) ? "[]".getBytes() : converterAuth.convert(list).getBytes();
        zkClient.setData().forPath(path, data);
    }
}