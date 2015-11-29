package org.dayatang.datasource4saas.tenantservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ThreadLocalTenantServiceTest {
	
	private ThreadLocalTenantService instance;

	@Before
	public void setUp() throws Exception {
		ThreadLocalTenantHolder.removeTenant();
		instance = new ThreadLocalTenantService();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		assertNull(instance.getTenant());
		String tenant = "abc";
		ThreadLocalTenantHolder.setTenant(tenant);
		assertEquals(tenant, instance.getTenant());
		ThreadLocalTenantHolder.removeTenant();
		assertNull(instance.getTenant());
	}

}
