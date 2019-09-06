package space.maganda.superiosign.aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 声明式事务切面
 */
@Configuration
public class Transaction {

  /**
   * 提供配置指定名称规则方法的传播特性以及事务级别的配置对象
   * 
   * @return 配置对象
   */
  @Bean("txSource")
  public TransactionAttributeSource transactionAttributeSource() {
    // 用于配置名称规则的资源对象
    NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

    /*
     * 事务的几种传播特性
     * 1.PROPAGATION_REQUIRED: 如果存在一个事务，则支持当前事务。如果没有事务则开启
     * 2.PROPAGATION_SUPPORTS: 如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行
     * 3.PROPAGATION_MANDATORY: 如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。
     * 4.PROPAGATION_REQUIRES_NEW: 总是开启一个新的事务。如果一个事务已经存在，则将这个存在的事务挂起。
     * 5.PROPAGATION_NOT_SUPPORTED: 总是非事务地执行，并挂起任何存在的事务。
     * 6.PROPAGATION_NEVER: 总是非事务地执行，如果存在一个活动事务，则抛出异常
     * 7.PROPAGATION_NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行
     */

    // 非事务属性(只读)
    RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
    readOnlyTx.setReadOnly(true);
    readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

    // 普通事务
    RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(
      TransactionDefinition.PROPAGATION_REQUIRED,
      Collections.singletonList(new RollbackRuleAttribute(Exception.class))
    );
    requiredTx.setTimeout(5);

    // 声明指定命名规则方法对应的事务类型map
    Map<String, TransactionAttribute> ruleMap = new HashMap<String, TransactionAttribute>();
    // 更新动作相关
    ruleMap.put("add*", requiredTx);
    ruleMap.put("save*", requiredTx);
    ruleMap.put("update*", requiredTx);
    ruleMap.put("delete*", requiredTx);
    // 查询动作相关
    ruleMap.put("get*", readOnlyTx);
    ruleMap.put("list*", readOnlyTx);
    ruleMap.put("query*", readOnlyTx);

    // 设置规则到资源对象
    source.setNameMap(ruleMap);

    return source;
  }

  /**
   * 切入点规则配置
   * 
   * @param transactionInterceptor 事务拦截器
   *
   * @return 配置对象
   */
  @Bean
  public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor transactionInterceptor) {

    // 创建切入点通知对象
    AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
    pointcutAdvisor.setAdvice(transactionInterceptor);
    pointcutAdvisor.setExpression("execution (* space.maganda.superiosign..service.*.*(..))");

    return pointcutAdvisor;
  }

  /**
   * 应用配置到事务拦截器
   *
   *  @param transactionManager 事务管理对象
   *
   * @return 拦截器对象
   */
  @Bean("txInterceptor")
  public TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager transactionManager) {
    return new TransactionInterceptor(
      transactionManager,
      transactionAttributeSource()
    );
  }
}