
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.auth;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("authorityRuleZookeeperPublisher")
public class AuthorityRuleZookeeperPublisher extends RuleZkPublisher<AuthorityRuleEntity> {
    @Value("${sentinel.auth.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

}