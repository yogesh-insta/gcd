package com.ynm;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;
import com.ynm.repository.GCDRepositoryStub;

public class GCDRestServiceTest {
	static GCDRepository ps;

	@BeforeClass
	public static void testParameterService() throws Exception {
		ps = new GCDRepositoryStub();
		Parameters params = new Parameters();
		params.setParam1(10);
		params.setParam1(20);
		ps.persistParameters(params);
	}

	@Test
	public void testFetch() {
		List<Parameters> paramList = ps.getAllParameters("TODO");
		assertNotNull(paramList);
	}
}