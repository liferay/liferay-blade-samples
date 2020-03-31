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

package com.liferay.blade.samples.jdbcservicebuilder.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blade.samples.jdbcservicebuilder.exception.NoSuchCountryException;
import com.liferay.blade.samples.jdbcservicebuilder.model.Country;
import com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalServiceUtil;
import com.liferay.blade.samples.jdbcservicebuilder.service.persistence.CountryPersistence;
import com.liferay.blade.samples.jdbcservicebuilder.service.persistence.CountryUtil;
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
public class CountryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.blade.samples.jdbcservicebuilder.service"));

	@Before
	public void setUp() {
		_persistence = CountryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Country> iterator = _countries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Country country = _persistence.create(pk);

		Assert.assertNotNull(country);

		Assert.assertEquals(country.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Country newCountry = addCountry();

		_persistence.remove(newCountry);

		Country existingCountry = _persistence.fetchByPrimaryKey(
			newCountry.getPrimaryKey());

		Assert.assertNull(existingCountry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCountry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Country newCountry = _persistence.create(pk);

		newCountry.setCountryName(RandomTestUtil.randomString());

		_countries.add(_persistence.update(newCountry));

		Country existingCountry = _persistence.findByPrimaryKey(
			newCountry.getPrimaryKey());

		Assert.assertEquals(
			existingCountry.getCountryId(), newCountry.getCountryId());
		Assert.assertEquals(
			existingCountry.getCountryName(), newCountry.getCountryName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Country newCountry = addCountry();

		Country existingCountry = _persistence.findByPrimaryKey(
			newCountry.getPrimaryKey());

		Assert.assertEquals(existingCountry, newCountry);
	}

	@Test(expected = NoSuchCountryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Country> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"country", "countryId", true, "countryName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Country newCountry = addCountry();

		Country existingCountry = _persistence.fetchByPrimaryKey(
			newCountry.getPrimaryKey());

		Assert.assertEquals(existingCountry, newCountry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Country missingCountry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCountry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Country newCountry1 = addCountry();
		Country newCountry2 = addCountry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCountry1.getPrimaryKey());
		primaryKeys.add(newCountry2.getPrimaryKey());

		Map<Serializable, Country> countries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, countries.size());
		Assert.assertEquals(
			newCountry1, countries.get(newCountry1.getPrimaryKey()));
		Assert.assertEquals(
			newCountry2, countries.get(newCountry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Country> countries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(countries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Country newCountry = addCountry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCountry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Country> countries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, countries.size());
		Assert.assertEquals(
			newCountry, countries.get(newCountry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Country> countries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(countries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Country newCountry = addCountry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCountry.getPrimaryKey());

		Map<Serializable, Country> countries = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, countries.size());
		Assert.assertEquals(
			newCountry, countries.get(newCountry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CountryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Country>() {

				@Override
				public void performAction(Country country) {
					Assert.assertNotNull(country);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Country newCountry = addCountry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Country.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("countryId", newCountry.getCountryId()));

		List<Country> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Country existingCountry = result.get(0);

		Assert.assertEquals(existingCountry, newCountry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Country.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("countryId", RandomTestUtil.nextLong()));

		List<Country> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Country newCountry = addCountry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Country.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("countryId"));

		Object newCountryId = newCountry.getCountryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"countryId", new Object[] {newCountryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCountryId = result.get(0);

		Assert.assertEquals(existingCountryId, newCountryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Country.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("countryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"countryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Country addCountry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Country country = _persistence.create(pk);

		country.setCountryName(RandomTestUtil.randomString());

		_countries.add(_persistence.update(country));

		return country;
	}

	private List<Country> _countries = new ArrayList<Country>();
	private CountryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}