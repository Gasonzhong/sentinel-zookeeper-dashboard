
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("systemRuleZookeeperPublisher")
public class SystemRuleZookeeperPublisher extends RuleZkPublisher<SystemRuleEntity> {
    @Value("${sentinel.system.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

}