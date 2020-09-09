
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("paramFlowRuleZookeeperPublisher")
public class ParamFlowRuleZookeeperPublisher extends RuleZkPublisher<ParamFlowRuleEntity> {
    @Value("${sentinel.param.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

}