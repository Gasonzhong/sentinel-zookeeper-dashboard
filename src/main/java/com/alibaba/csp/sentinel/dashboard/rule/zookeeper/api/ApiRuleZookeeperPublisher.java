
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("apiRuleZookeeperPublisher")
public class ApiRuleZookeeperPublisher extends RuleZkPublisher<ApiDefinitionEntity> {
    @Value("${sentinel.api.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

}