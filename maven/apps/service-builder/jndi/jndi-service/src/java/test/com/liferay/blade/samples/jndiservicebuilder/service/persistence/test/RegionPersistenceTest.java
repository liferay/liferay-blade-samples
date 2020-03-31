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

package com.liferay.blade.samples.jndiservicebuilder.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blade.samples.jndiservicebuilder.exception.NoSuchRegionException;
import com.liferay.blade.samples.jndiservicebuilder.model.Region;
import com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalServiceUtil;
import com.liferay.blade.samples.jndiservicebuilder.service.persistence.RegionPersistence;
import com.liferay.blade.samples.jndiservicebuilder.service.persistence.RegionUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class RegionPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.blade.samples.jndiservicebuilder.service"));

	@Before
	public void setUp() {
		_persistence = RegionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Region> iterator = _regions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Region region = _persistence.create(pk);

		Assert.assertNotNull(region);

		Assert.assertEquals(region.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Region newRegion = addRegion();

		_persistence.remove(newRegion);

		Region existingRegion = _persistence.fetchByPrimaryKey(
			newRegion.getPrimaryKey());

		Assert.assertNull(existingRegion);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRegion();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Region newRegion = _persistence.create(pk);

		newRegion.setRegionName(RandomTestUtil.randomString());

		_regions.add(_persistence.update(newRegion));

		Region existingRegion = _persistence.findByPrimaryKey(
			newRegion.getPrimaryKey());

		Assert.assertEquals(
			existingRegion.getRegionId(), newRegion.getRegionId());
		Assert.assertEquals(
			existingRegion.getRegionName(), newRegion.getRegionName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Region newRegion = addRegion();

		Region existingRegion = _persistence.findByPrimaryKey(
			newRegion.getPrimaryKey());

		Assert.assertEquals(existingRegion, newRegion);
	}

	@Test(expected = NoSuchRegionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Region> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"region", "regionId", true, "regionName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Region newRegion = addRegion();

		Region existingRegion = _persistence.fetchByPrimaryKey(
			newRegion.getPrimaryKey());

		Assert.assertEquals(existingRegion, newRegion);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Region missingRegion = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRegion);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Region newRegion1 = addRegion();
		Region newRegion2 = addRegion();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRegion1.getPrimaryKey());
		primaryKeys.add(newRegion2.getPrimaryKey());

		Map<Serializable, Region> regions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, regions.size());
		Assert.assertEquals(
			newRegion1, regions.get(newRegion1.getPrimaryKey()));
		Assert.assertEquals(
			newRegion2, regions.get(newRegion2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Region> regions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(regions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Region newRegion = addRegion();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRegion.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Region> regions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, regions.size());
		Assert.assertEquals(newRegion, regions.get(newRegion.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Region> regions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(regions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Region newRegion = addRegion();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRegion.getPrimaryKey());

		Map<Serializable, Region> regions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, regions.size());
		Assert.assertEquals(newRegion, regions.get(newRegion.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			RegionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Region>() {

				@Override
				public void performAction(Region region) {
					Assert.assertNotNull(region);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Region newRegion = addRegion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Region.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("regionId", newRegion.getRegionId()));

		List<Region> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Region existingRegion = result.get(0);

		Assert.assertEquals(existingRegion, newRegion);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Region.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("regionId", RandomTestUtil.nextLong()));

		List<Region> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Region newRegion = addRegion();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Region.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("regionId"));

		Object newRegionId = newRegion.getRegionId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("regionId", new Object[] {newRegionId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRegionId = result.get(0);

		Assert.assertEquals(existingRegionId, newRegionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Region.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("regionId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"regionId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Region addRegion() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Region region = _persistence.create(pk);

		region.setRegionName(RandomTestUtil.randomString());

		_regions.add(_persistence.update(region));

		return region;
	}

	private List<Region> _regions = new ArrayList<Region>();
	private RegionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}