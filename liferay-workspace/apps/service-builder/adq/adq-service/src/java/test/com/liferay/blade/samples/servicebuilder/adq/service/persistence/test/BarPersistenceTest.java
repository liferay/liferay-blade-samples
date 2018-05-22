/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.samples.servicebuilder.adq.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.blade.samples.servicebuilder.adq.exception.NoSuchBarException;
import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil;
import com.liferay.blade.samples.servicebuilder.adq.service.persistence.BarPersistence;
import com.liferay.blade.samples.servicebuilder.adq.service.persistence.BarUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class BarPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.blade.samples.servicebuilder.adq.service"));

	@Before
	public void setUp() {
		_persistence = BarUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Bar> iterator = _bars.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Bar bar = _persistence.create(pk);

		Assert.assertNotNull(bar);

		Assert.assertEquals(bar.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Bar newBar = addBar();

		_persistence.remove(newBar);

		Bar existingBar = _persistence.fetchByPrimaryKey(newBar.getPrimaryKey());

		Assert.assertNull(existingBar);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBar();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Bar newBar = _persistence.create(pk);

		newBar.setUuid(RandomTestUtil.randomString());

		newBar.setGroupId(RandomTestUtil.nextLong());

		newBar.setCompanyId(RandomTestUtil.nextLong());

		newBar.setUserId(RandomTestUtil.nextLong());

		newBar.setUserName(RandomTestUtil.randomString());

		newBar.setCreateDate(RandomTestUtil.nextDate());

		newBar.setModifiedDate(RandomTestUtil.nextDate());

		newBar.setField1(RandomTestUtil.randomString());

		newBar.setField2(RandomTestUtil.randomBoolean());

		newBar.setField3(RandomTestUtil.nextInt());

		newBar.setField4(RandomTestUtil.nextDate());

		newBar.setField5(RandomTestUtil.randomString());

		_bars.add(_persistence.update(newBar));

		Bar existingBar = _persistence.findByPrimaryKey(newBar.getPrimaryKey());

		Assert.assertEquals(existingBar.getUuid(), newBar.getUuid());
		Assert.assertEquals(existingBar.getBarId(), newBar.getBarId());
		Assert.assertEquals(existingBar.getGroupId(), newBar.getGroupId());
		Assert.assertEquals(existingBar.getCompanyId(), newBar.getCompanyId());
		Assert.assertEquals(existingBar.getUserId(), newBar.getUserId());
		Assert.assertEquals(existingBar.getUserName(), newBar.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(existingBar.getCreateDate()),
			Time.getShortTimestamp(newBar.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingBar.getModifiedDate()),
			Time.getShortTimestamp(newBar.getModifiedDate()));
		Assert.assertEquals(existingBar.getField1(), newBar.getField1());
		Assert.assertEquals(existingBar.isField2(), newBar.isField2());
		Assert.assertEquals(existingBar.getField3(), newBar.getField3());
		Assert.assertEquals(Time.getShortTimestamp(existingBar.getField4()),
			Time.getShortTimestamp(newBar.getField4()));
		Assert.assertEquals(existingBar.getField5(), newBar.getField5());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByField1() throws Exception {
		_persistence.countByField1("");

		_persistence.countByField1("null");

		_persistence.countByField1((String)null);
	}

	@Test
	public void testCountByField2() throws Exception {
		_persistence.countByField2(RandomTestUtil.randomBoolean());

		_persistence.countByField2(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Bar newBar = addBar();

		Bar existingBar = _persistence.findByPrimaryKey(newBar.getPrimaryKey());

		Assert.assertEquals(existingBar, newBar);
	}

	@Test(expected = NoSuchBarException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Bar> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ADQ_Bar", "uuid", true,
			"barId", true, "groupId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"field1", true, "field2", true, "field3", true, "field4", true,
			"field5", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Bar newBar = addBar();

		Bar existingBar = _persistence.fetchByPrimaryKey(newBar.getPrimaryKey());

		Assert.assertEquals(existingBar, newBar);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Bar missingBar = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBar);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Bar newBar1 = addBar();
		Bar newBar2 = addBar();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBar1.getPrimaryKey());
		primaryKeys.add(newBar2.getPrimaryKey());

		Map<Serializable, Bar> bars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, bars.size());
		Assert.assertEquals(newBar1, bars.get(newBar1.getPrimaryKey()));
		Assert.assertEquals(newBar2, bars.get(newBar2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Bar> bars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(bars.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Bar newBar = addBar();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBar.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Bar> bars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, bars.size());
		Assert.assertEquals(newBar, bars.get(newBar.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Bar> bars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(bars.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Bar newBar = addBar();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBar.getPrimaryKey());

		Map<Serializable, Bar> bars = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, bars.size());
		Assert.assertEquals(newBar, bars.get(newBar.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = BarLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Bar>() {
				@Override
				public void performAction(Bar bar) {
					Assert.assertNotNull(bar);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Bar newBar = addBar();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Bar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("barId", newBar.getBarId()));

		List<Bar> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Bar existingBar = result.get(0);

		Assert.assertEquals(existingBar, newBar);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Bar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("barId",
				RandomTestUtil.nextLong()));

		List<Bar> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Bar newBar = addBar();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Bar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("barId"));

		Object newBarId = newBar.getBarId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("barId",
				new Object[] { newBarId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBarId = result.get(0);

		Assert.assertEquals(existingBarId, newBarId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Bar.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("barId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("barId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Bar newBar = addBar();

		_persistence.clearCache();

		Bar existingBar = _persistence.findByPrimaryKey(newBar.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingBar.getUuid(),
				ReflectionTestUtil.invoke(existingBar, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingBar.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingBar, "getOriginalGroupId",
				new Class<?>[0]));
	}

	protected Bar addBar() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Bar bar = _persistence.create(pk);

		bar.setUuid(RandomTestUtil.randomString());

		bar.setGroupId(RandomTestUtil.nextLong());

		bar.setCompanyId(RandomTestUtil.nextLong());

		bar.setUserId(RandomTestUtil.nextLong());

		bar.setUserName(RandomTestUtil.randomString());

		bar.setCreateDate(RandomTestUtil.nextDate());

		bar.setModifiedDate(RandomTestUtil.nextDate());

		bar.setField1(RandomTestUtil.randomString());

		bar.setField2(RandomTestUtil.randomBoolean());

		bar.setField3(RandomTestUtil.nextInt());

		bar.setField4(RandomTestUtil.nextDate());

		bar.setField5(RandomTestUtil.randomString());

		_bars.add(_persistence.update(bar));

		return bar;
	}

	private List<Bar> _bars = new ArrayList<Bar>();
	private BarPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}