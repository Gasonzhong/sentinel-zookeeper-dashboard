
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("apiRuleZookeeperProvider")
public class ApiRuleZookeeperProvider extends RuleZkProvider<ApiDefinitionEntity> {

    @Value("${sentinel.api.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }
}