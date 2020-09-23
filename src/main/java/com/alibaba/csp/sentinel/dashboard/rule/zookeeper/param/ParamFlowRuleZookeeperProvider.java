package com.alibaba.csp.sentinel.dashboard.rule.zookeeper.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleZkProvider;
import com.alibaba.csp.sentinel.dashboard.rule.zookeeper.ZookeeperConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("paramFlowRuleZookeeperProvider")
public class ParamFlowRuleZookeeperProvider extends RuleZkProvider<ParamFlowRuleEntity> {

    @Value("${sentinel.param.prex}")
    private String type;

    @Override
    public String getDataIdPostfix() {
        return type;
    }

    @Autowired
    private Converter<String, List<ParamFlowRuleCorrectEntity>> converterParam;

    @Override
    public List<ParamFlowRuleEntity> getRules(String appName) throws Exception {

        String zkPath = ZookeeperConfigUtil.getPath(appName, getDataIdPostfix());
        Stat stat = zkClient.checkExists().forPath(zkPath);
        if (stat == null) {
            return new ArrayList<>(0);
        }
        byte[] bytes = zkClient.getData().forPath(zkPath);
        if (null == bytes || bytes.length == 0) {
            return new ArrayList<>();
        }
        String s = new String(bytes);
        List<ParamFlowRuleCorrectEntity> list = converterParam.convert(s);
        //转换
        return list.stream().map(rule -> {

            ParamFlowRule paramFlowRule = new ParamFlowRule();
            BeanUtils.copyProperties(rule, paramFlowRule);
            ParamFlowRuleEntity entity = ParamFlowRuleEntity.fromAuthorityRule(rule.getApp(), rule.getIp(), rule.getPort(), paramFlowRule);
            entity.setId(rule.getId());
            entity.setGmtCreate(rule.getGmtCreate());
            return entity;
        }).collect(Collectors.toList());
    }
}