package com.neo.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

@Configuration
public class AtomikosJtaPlatform extends AbstractJtaPlatform {

	@Override
	@Bean(name = "atomikosTransactionManager")
	protected TransactionManager locateTransactionManager() {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(true);
		return userTransactionManager;
	}

	@Override
	@Bean(name = "userTransaction")
	protected UserTransaction locateUserTransaction() {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		try {
			userTransactionImp.setTransactionTimeout(300);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return userTransactionImp;
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
	public PlatformTransactionManager transactionManager() throws SystemException {
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		jtaTransactionManager.setTransactionManager(locateTransactionManager());
		jtaTransactionManager.setUserTransaction(locateUserTransaction());
		return jtaTransactionManager;
	}
}
