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

package com.liferay.blade.workflow.basic.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blade.workflow.basic.exception.NoSuchBazException;
import com.liferay.blade.workflow.basic.model.Baz;
import com.liferay.blade.workflow.basic.service.BazLocalServiceUtil;
import com.liferay.blade.workflow.basic.service.persistence.BazPersistence;
import com.liferay.blade.workflow.basic.service.persistence.BazUtil;
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class BazPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.blade.workflow.basic.service"));

	@Before
	public void setUp() {
		_persistence = BazUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Baz> iterator = _bazs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Baz baz = _persistence.create(pk);

		Assert.assertNotNull(baz);

		Assert.assertEquals(baz.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Baz newBaz = addBaz();

		_persistence.remove(newBaz);

		Baz existingBaz = _persistence.fetchByPrimaryKey(
			newBaz.getPrimaryKey());

		Assert.assertNull(existingBaz);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBaz();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Baz newBaz = _persistence.create(pk);

		newBaz.setUuid(RandomTestUtil.randomString());

		newBaz.setGroupId(RandomTestUtil.nextLong());

		newBaz.setCompanyId(RandomTestUtil.nextLong());

		newBaz.setUserId(RandomTestUtil.nextLong());

		newBaz.setUserName(RandomTestUtil.randomString());

		newBaz.setCreateDate(RandomTestUtil.nextDate());

		newBaz.setModifiedDate(RandomTestUtil.nextDate());

		newBaz.setStatus(RandomTestUtil.nextInt());

		newBaz.setStatusByUserId(RandomTestUtil.nextLong());

		newBaz.setStatusByUserName(RandomTestUtil.randomString());

		newBaz.setStatusDate(RandomTestUtil.nextDate());

		_bazs.add(_persistence.update(newBaz));

		Baz existingBaz = _persistence.findByPrimaryKey(newBaz.getPrimaryKey());

		Assert.assertEquals(existingBaz.getUuid(), newBaz.getUuid());
		Assert.assertEquals(existingBaz.getBazId(), newBaz.getBazId());
		Assert.assertEquals(existingBaz.getGroupId(), newBaz.getGroupId());
		Assert.assertEquals(existingBaz.getCompanyId(), newBaz.getCompanyId());
		Assert.assertEquals(existingBaz.getUserId(), newBaz.getUserId());
		Assert.assertEquals(existingBaz.getUserName(), newBaz.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBaz.getCreateDate()),
			Time.getShortTimestamp(newBaz.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingBaz.getModifiedDate()),
			Time.getShortTimestamp(newBaz.getModifiedDate()));
		Assert.assertEquals(existingBaz.getStatus(), newBaz.getStatus());
		Assert.assertEquals(
			existingBaz.getStatusByUserId(), newBaz.getStatusByUserId());
		Assert.assertEquals(
			existingBaz.getStatusByUserName(), newBaz.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingBaz.getStatusDate()),
			Time.getShortTimestamp(newBaz.getStatusDate()));
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
	public void testFindByPrimaryKeyExisting() throws Exception {
		Baz newBaz = addBaz();

		Baz existingBaz = _persistence.findByPrimaryKey(newBaz.getPrimaryKey());

		Assert.assertEquals(existingBaz, newBaz);
	}

	@Test(expected = NoSuchBazException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Baz> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"Workflow_Baz", "uuid", true, "bazId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "status", true, "statusByUserId", true,
			"statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Baz newBaz = addBaz();

		Baz existingBaz = _persistence.fetchByPrimaryKey(
			newBaz.getPrimaryKey());

		Assert.assertEquals(existingBaz, newBaz);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Baz missingBaz = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBaz);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Baz newBaz1 = addBaz();
		Baz newBaz2 = addBaz();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBaz1.getPrimaryKey());
		primaryKeys.add(newBaz2.getPrimaryKey());

		Map<Serializable, Baz> bazs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, bazs.size());
		Assert.assertEquals(newBaz1, bazs.get(newBaz1.getPrimaryKey()));
		Assert.assertEquals(newBaz2, bazs.get(newBaz2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Baz> bazs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(bazs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Baz newBaz = addBaz();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBaz.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Baz> bazs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, bazs.size());
		Assert.assertEquals(newBaz, bazs.get(newBaz.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Baz> bazs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(bazs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Baz newBaz = addBaz();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBaz.getPrimaryKey());

		Map<Serializable, Baz> bazs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, bazs.size());
		Assert.assertEquals(newBaz, bazs.get(newBaz.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			BazLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Baz>() {

				@Override
				public void performAction(Baz baz) {
					Assert.assertNotNull(baz);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Baz newBaz = addBaz();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Baz.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("bazId", newBaz.getBazId()));

		List<Baz> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Baz existingBaz = result.get(0);

		Assert.assertEquals(existingBaz, newBaz);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Baz.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("bazId", RandomTestUtil.nextLong()));

		List<Baz> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Baz newBaz = addBaz();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Baz.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("bazId"));

		Object newBazId = newBaz.getBazId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("bazId", new Object[] {newBazId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBazId = result.get(0);

		Assert.assertEquals(existingBazId, newBazId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Baz.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("bazId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"bazId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Baz newBaz = addBaz();

		_persistence.clearCache();

		Baz existingBaz = _persistence.findByPrimaryKey(newBaz.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingBaz.getUuid(),
				ReflectionTestUtil.invoke(
					existingBaz, "getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingBaz.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingBaz, "getOriginalGroupId", new Class<?>[0]));
	}

	protected Baz addBaz() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Baz baz = _persistence.create(pk);

		baz.setUuid(RandomTestUtil.randomString());

		baz.setGroupId(RandomTestUtil.nextLong());

		baz.setCompanyId(RandomTestUtil.nextLong());

		baz.setUserId(RandomTestUtil.nextLong());

		baz.setUserName(RandomTestUtil.randomString());

		baz.setCreateDate(RandomTestUtil.nextDate());

		baz.setModifiedDate(RandomTestUtil.nextDate());

		baz.setStatus(RandomTestUtil.nextInt());

		baz.setStatusByUserId(RandomTestUtil.nextLong());

		baz.setStatusByUserName(RandomTestUtil.randomString());

		baz.setStatusDate(RandomTestUtil.nextDate());

		_bazs.add(_persistence.update(baz));

		return baz;
	}

	private List<Baz> _bazs = new ArrayList<Baz>();
	private BazPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}