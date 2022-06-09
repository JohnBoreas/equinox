package com.equinox.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.equinox.sentinel.constants.SentinelConstants;
import com.equinox.sentinel.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author boreas
 * @create 2022-06-09 13:33
 */
@Slf4j
@Configuration
public class SentinelConfiguration {

    private static final int FLOW_GRADE_QPS = 1;

    @PostConstruct
    public void postConstruct() {
        DegradeRuleManager.loadRules(
                Arrays.asList(this.rtDegradeRule(),
                        this.ratioDegradeRule(),
                        this.countDegradeRule())
        );
        FlowRuleManager.loadRules(Arrays.asList(
                this.exceptionSentinelFlowRule(),
                this.annotationSentinelFlowRule()));
        log.info("配置限流熔断规则成功");
    }

    @Bean("exceptionSentinelFlowRule")
    @Qualifier("exceptionSentinelFlowRule")
    public FlowRule exceptionSentinelFlowRule() {
        return this.getFlowRule(SentinelConstants.Resource.EXCEPTION_SENTINEL, 1, "default");
    }

    @Bean("annotationSentinelFlowRule")
    @Qualifier("annotationSentinelFlowRule")
    public FlowRule annotationSentinelFlowRule() {
        return this.getFlowRule(SentinelConstants.Resource.ANNOTATION_SENTINEL, 1, "default");
    }

    @Bean("rtDegradeRule")
    @Qualifier("rtDegradeRule")
    public DegradeRule rtDegradeRule() {
        return this.getDegradeRule(SentinelConstants.Resource.DEGRADE_GRADE_RT, 10, 30, RuleConstant.DEGRADE_GRADE_RT);
    }

    @Bean("ratioDegradeRule")
    @Qualifier("ratioDegradeRule")
    public DegradeRule ratioDegradeRule() {
        return this.getDegradeRule(SentinelConstants.Resource.DEGRADE_GRADE_EXCEPTION_RATIO, 20, 30, RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);
    }

    @Bean("countDegradeRule")
    @Qualifier("countDegradeRule")
    public DegradeRule countDegradeRule() {
        return this.getDegradeRule(SentinelConstants.Resource.DEGRADE_GRADE_EXCEPTION_COUNT, 10, 60, RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
    }

    /**
     * FlowRule
     * @param resource 资源名称
     * @param count 熔断条件
     * @param limitApp applicationName
     * @return
     */
    private FlowRule getFlowRule(String resource, int count, String limitApp) {
        // resource：资源名，即限流规则的作用对象
        // limitApp：流控针对的调用来源，若为default则不区分调用来源
        // grade：限流阈值类型，即QPS还是并发线程数，0代表线程数，1代表QPS
        // count：限流阈值
        // strategy：调用关系限流策略
        // controlBehavior：流量控制效果（快速失败，Warm Up，排队等待）
        // clusterMode：是否集群
        FlowRule flowRule = new FlowRule(resource);
        flowRule.setCount(Math.max(count, 1));
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);// FLOW_GRADE_THREAD = 0;  FLOW_GRADE_QPS = 1;
        if (StringUtils.isNotEmpty(limitApp)) {
            flowRule.setLimitApp(limitApp);
        }
        return flowRule;
    }

    /**
     * 降级熔断，创建rule
     * @param resource    资源名称
     * @param count       熔断条件
     * @param degradeTime 熔断时间
     * @return 熔断规则
     */
    private DegradeRule getDegradeRule(String resource, int count, int degradeTime, int ruleConstant) {
        // resource 资源名，即规则的作⽤对象
        // grade 熔断策略，⽀持慢调⽤⽐例/异常⽐例/异常数策略
        // count 慢调⽤⽐例模式下为慢调⽤临界 RT（超出该值计为慢调⽤）；异常⽐例/异常数模式下为对应的阈值
        // timeWindow 熔断时长，单位为 s
        // minRequestAmount 熔断触发的最⼩请求数，请求数⼩于该值时即使异常⽐率超出阈值也不会熔断（1.7.0 引⼊）
        // statIntervalMs 统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引⼊）
        // slowRatioThresh 慢调⽤⽐例阈值，仅慢调⽤⽐例模式有效（1.8.0 引⼊）
        DegradeRule degradeRule = new DegradeRule(resource)
                // DEGRADE_GRADE_EXCEPTION_COUNT: 当资源近 1 分钟的异常数目超过阈值之后会进行熔断。
                .setGrade(ruleConstant)
                .setCount(count)
                .setTimeWindow(degradeTime);
        degradeRule.setLimitApp("applicationName");
        return degradeRule;
    }

}
