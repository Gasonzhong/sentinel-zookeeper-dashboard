
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("gatewayFlowRuleZookeeperPublisher")
public class GatewayFlowRuleZookeeperPublisher extends RuleZkPublisher<GatewayFlowRuleEntity> {
    @Value("${sentinel.gateway.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

}