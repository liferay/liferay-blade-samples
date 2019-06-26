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

package com.liferay.blade.workflow.asset.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blade.workflow.asset.exception.NoSuchQuxException;
import com.liferay.blade.workflow.asset.model.Qux;
import com.liferay.blade.workflow.asset.service.QuxLocalServiceUtil;
import com.liferay.blade.workflow.asset.service.persistence.QuxPersistence;
import com.liferay.blade.workflow.asset.service.persistence.QuxUtil;
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
public class QuxPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.blade.workflow.asset.service"));

	@Before
	public void setUp() {
		_persistence = QuxUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Qux> iterator = _quxs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Qux qux = _persistence.create(pk);

		Assert.assertNotNull(qux);

		Assert.assertEquals(qux.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Qux newQux = addQux();

		_persistence.remove(newQux);

		Qux existingQux = _persistence.fetchByPrimaryKey(
			newQux.getPrimaryKey());

		Assert.assertNull(existingQux);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addQux();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Qux newQux = _persistence.create(pk);

		newQux.setUuid(RandomTestUtil.randomString());

		newQux.setGroupId(RandomTestUtil.nextLong());

		newQux.setCompanyId(RandomTestUtil.nextLong());

		newQux.setUserId(RandomTestUtil.nextLong());

		newQux.setUserName(RandomTestUtil.randomString());

		newQux.setCreateDate(RandomTestUtil.nextDate());

		newQux.setModifiedDate(RandomTestUtil.nextDate());

		newQux.setStatus(RandomTestUtil.nextInt());

		newQux.setStatusByUserId(RandomTestUtil.nextLong());

		newQux.setStatusByUserName(RandomTestUtil.randomString());

		newQux.setStatusDate(RandomTestUtil.nextDate());

		_quxs.add(_persistence.update(newQux));

		Qux existingQux = _persistence.findByPrimaryKey(newQux.getPrimaryKey());

		Assert.assertEquals(existingQux.getUuid(), newQux.getUuid());
		Assert.assertEquals(existingQux.getQuxId(), newQux.getQuxId());
		Assert.assertEquals(existingQux.getGroupId(), newQux.getGroupId());
		Assert.assertEquals(existingQux.getCompanyId(), newQux.getCompanyId());
		Assert.assertEquals(existingQux.getUserId(), newQux.getUserId());
		Assert.assertEquals(existingQux.getUserName(), newQux.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingQux.getCreateDate()),
			Time.getShortTimestamp(newQux.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingQux.getModifiedDate()),
			Time.getShortTimestamp(newQux.getModifiedDate()));
		Assert.assertEquals(existingQux.getStatus(), newQux.getStatus());
		Assert.assertEquals(
			existingQux.getStatusByUserId(), newQux.getStatusByUserId());
		Assert.assertEquals(
			existingQux.getStatusByUserName(), newQux.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingQux.getStatusDate()),
			Time.getShortTimestamp(newQux.getStatusDate()));
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
		Qux newQux = addQux();

		Qux existingQux = _persistence.findByPrimaryKey(newQux.getPrimaryKey());

		Assert.assertEquals(existingQux, newQux);
	}

	@Test(expected = NoSuchQuxException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Qux> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"Workflow_Qux", "uuid", true, "quxId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "status", true, "statusByUserId", true,
			"statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Qux newQux = addQux();

		Qux existingQux = _persistence.fetchByPrimaryKey(
			newQux.getPrimaryKey());

		Assert.assertEquals(existingQux, newQux);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Qux missingQux = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingQux);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Qux newQux1 = addQux();
		Qux newQux2 = addQux();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newQux1.getPrimaryKey());
		primaryKeys.add(newQux2.getPrimaryKey());

		Map<Serializable, Qux> quxs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, quxs.size());
		Assert.assertEquals(newQux1, quxs.get(newQux1.getPrimaryKey()));
		Assert.assertEquals(newQux2, quxs.get(newQux2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Qux> quxs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(quxs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Qux newQux = addQux();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newQux.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Qux> quxs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, quxs.size());
		Assert.assertEquals(newQux, quxs.get(newQux.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Qux> quxs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(quxs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Qux newQux = addQux();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newQux.getPrimaryKey());

		Map<Serializable, Qux> quxs = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, quxs.size());
		Assert.assertEquals(newQux, quxs.get(newQux.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			QuxLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Qux>() {

				@Override
				public void performAction(Qux qux) {
					Assert.assertNotNull(qux);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Qux newQux = addQux();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Qux.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("quxId", newQux.getQuxId()));

		List<Qux> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Qux existingQux = result.get(0);

		Assert.assertEquals(existingQux, newQux);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Qux.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("quxId", RandomTestUtil.nextLong()));

		List<Qux> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Qux newQux = addQux();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Qux.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("quxId"));

		Object newQuxId = newQux.getQuxId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("quxId", new Object[] {newQuxId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingQuxId = result.get(0);

		Assert.assertEquals(existingQuxId, newQuxId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Qux.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("quxId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"quxId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		Qux newQux = addQux();

		_persistence.clearCache();

		Qux existingQux = _persistence.findByPrimaryKey(newQux.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingQux.getUuid(),
				ReflectionTestUtil.invoke(
					existingQux, "getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingQux.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingQux, "getOriginalGroupId", new Class<?>[0]));
	}

	protected Qux addQux() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Qux qux = _persistence.create(pk);

		qux.setUuid(RandomTestUtil.randomString());

		qux.setGroupId(RandomTestUtil.nextLong());

		qux.setCompanyId(RandomTestUtil.nextLong());

		qux.setUserId(RandomTestUtil.nextLong());

		qux.setUserName(RandomTestUtil.randomString());

		qux.setCreateDate(RandomTestUtil.nextDate());

		qux.setModifiedDate(RandomTestUtil.nextDate());

		qux.setStatus(RandomTestUtil.nextInt());

		qux.setStatusByUserId(RandomTestUtil.nextLong());

		qux.setStatusByUserName(RandomTestUtil.randomString());

		qux.setStatusDate(RandomTestUtil.nextDate());

		_quxs.add(_persistence.update(qux));

		return qux;
	}

	private List<Qux> _quxs = new ArrayList<Qux>();
	private QuxPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}