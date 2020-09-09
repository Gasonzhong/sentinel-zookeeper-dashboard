
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkProvider;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.ZookeeperConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("flowRuleZookeeperProvider")
public class FlowRuleZookeeperProvider extends RuleZkProvider<FlowRuleEntity> {

    @Value("${sentinel.flow.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }
}