
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("systemRuleZookeeperProvider")
public class SystemRuleZookeeperProvider extends RuleZkProvider<SystemRuleEntity> {

    @Value("${sentinel.system.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }
}