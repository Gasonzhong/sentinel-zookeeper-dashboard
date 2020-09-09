
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("degradeRuleZookeeperPublisher")
public class DegradeRuleZookeeperPublisher extends RuleZkPublisher<DegradeRuleEntity> {
    @Value("${sentinel.degrade.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

}