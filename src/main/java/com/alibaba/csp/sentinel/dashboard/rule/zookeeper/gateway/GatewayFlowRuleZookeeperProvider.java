
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("gatewayFlowRuleZookeeperProvider")
public class GatewayFlowRuleZookeeperProvider extends RuleZkProvider<GatewayFlowRuleEntity> {

    @Value("${sentinel.gateway.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }
}