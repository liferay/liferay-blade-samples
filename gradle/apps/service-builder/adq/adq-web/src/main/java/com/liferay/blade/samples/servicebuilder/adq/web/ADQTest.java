package com.liferay.blade.samples.servicebuilder.adq.web;

import java.util.Date;

import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

public class ADQTest {
	
	public final String TEST = "test";
	public final Date DATE = new Date();

	public void testMassUpdate() throws PortalException {
		
		// Set up
		// Only field 3 is relevant as far as the mass update test is concerned.
		Bar bar1 = BarLocalServiceUtil.createBar(CounterLocalServiceUtil.increment());
		
		bar1.setField1(TEST);
		bar1.setField2(false);
		bar1.setField3(0);
		bar1.setField4(DATE);
		bar1.setField5(TEST);
		
		bar1 = BarLocalServiceUtil.addBar(bar1);
		
		Bar bar2 = BarLocalServiceUtil.createBar(CounterLocalServiceUtil.increment());
		
		bar2.setField1(TEST);
		bar2.setField2(false);
		bar2.setField3(99);
		bar2.setField4(DATE);
		bar2.setField5(TEST);
		
		bar2 = BarLocalServiceUtil.addBar(bar2);
		
		Bar bar3 = BarLocalServiceUtil.createBar(CounterLocalServiceUtil.increment());
		
		bar3.setField1(TEST);
		bar3.setField2(false);
		bar3.setField3(100);
		bar3.setField4(DATE);
		bar3.setField5(TEST);
		
		bar3 = BarLocalServiceUtil.addBar(bar3);
		
		Bar bar4 = BarLocalServiceUtil.createBar(CounterLocalServiceUtil.increment());
		
		bar4.setField1(TEST);
		bar4.setField2(false);
		bar4.setField3(101);
		bar4.setField4(DATE);
		bar4.setField5(TEST);
		
		bar4 = BarLocalServiceUtil.addBar(bar4);
		
		Bar bar5 = BarLocalServiceUtil.createBar(CounterLocalServiceUtil.increment());
		
		bar5.setField1(TEST);
		bar5.setField2(false);
		bar5.setField3(200);
		bar5.setField4(DATE);
		bar5.setField5(TEST);
		
		bar5 = BarLocalServiceUtil.addBar(bar5);
		
		// Perform 1st mass update and make assertions
		BarLocalServiceUtil.massUpdate();
		
		Assert.assertTrue(
				"Expected " + 1 + ", but saw " + bar1.getField3(),
				bar1.getField3() == 1);
		Assert.assertTrue(
				"Expected " + 100 + ", but saw " + bar2.getField3(),
				bar2.getField3() == 100);
		Assert.assertTrue(
				"Expected " + 100 + ", but saw " + bar3.getField3(),
				bar3.getField3() == 100);
		Assert.assertTrue(
				"Expected " + 101 + ", but saw " + bar4.getField3(),
				bar4.getField3() == 101);
		Assert.assertTrue(
				"Expected " + 200 + ", but saw " + bar5.getField3(),
				bar5.getField3() == 200);
		
		// Step 3: Perform 2nd mass update and make assertions
		BarLocalServiceUtil.massUpdate();
		
		Assert.assertTrue(
				"Expected " + 2 + ", but saw " + bar1.getField3(),
				bar1.getField3() == 2);
		Assert.assertTrue(
				"Expected " + 100 + ", but saw " + bar2.getField3(),
				bar2.getField3() == 100);
		Assert.assertTrue(
				"Expected " + 100 + ", but saw " + bar3.getField3(),
				bar3.getField3() == 100);
		Assert.assertTrue(
				"Expected " + 101 + ", but saw " + bar4.getField3(),
				bar4.getField3() == 101);
		Assert.assertTrue(
				"Expected " + 200 + ", but saw " + bar5.getField3(),
				bar5.getField3() == 200);
	}

}
