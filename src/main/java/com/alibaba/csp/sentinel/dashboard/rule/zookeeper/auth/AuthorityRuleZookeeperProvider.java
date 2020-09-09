
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.auth;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authorityRuleZookeeperProvider")
public class AuthorityRuleZookeeperProvider extends RuleZkProvider<AuthorityRuleEntity> {

    @Value("${sentinel.auth.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }
}