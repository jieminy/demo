package com.ur.java.demo.spring.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@Scope("prototype")
@Slf4j
public class TransactionalComponent {
    private final DataSourceTransactionManager dataSourceTransactionManager;
    private TransactionStatus transactionStatus;

    public TransactionalComponent(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    /**
     * 开启事务
     *
     * @return 事务状态
     */
    public void begin() {
        log.info("开启事务");
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    /**
     * 提交事务
     */
    public void commit() {
        log.info("提交事务");
        if (transactionStatus != null && !transactionStatus.isCompleted()) {
            dataSourceTransactionManager.commit(transactionStatus);
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        log.info("回滚事务");
        if (transactionStatus != null && !transactionStatus.isCompleted()) {
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }
}
