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

package com.liferay.blade.samples.jdbcservicebuilder.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blade.samples.jdbcservicebuilder.model.Country;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the country service. This utility wraps {@link com.liferay.blade.samples.jdbcservicebuilder.service.persistence.impl.CountryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryPersistence
 * @see com.liferay.blade.samples.jdbcservicebuilder.service.persistence.impl.CountryPersistenceImpl
 * @generated
 */
@ProviderType
public class CountryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Country country) {
		getPersistence().clearCache(country);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Country> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Country> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Country> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Country> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Country update(Country country) {
		return getPersistence().update(country);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Country update(Country country, ServiceContext serviceContext) {
		return getPersistence().update(country, serviceContext);
	}

	/**
	* Caches the country in the entity cache if it is enabled.
	*
	* @param country the country
	*/
	public static void cacheResult(Country country) {
		getPersistence().cacheResult(country);
	}

	/**
	* Caches the countries in the entity cache if it is enabled.
	*
	* @param countries the countries
	*/
	public static void cacheResult(List<Country> countries) {
		getPersistence().cacheResult(countries);
	}

	/**
	* Creates a new country with the primary key. Does not add the country to the database.
	*
	* @param countryId the primary key for the new country
	* @return the new country
	*/
	public static Country create(long countryId) {
		return getPersistence().create(countryId);
	}

	/**
	* Removes the country with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param countryId the primary key of the country
	* @return the country that was removed
	* @throws NoSuchCountryException if a country with the primary key could not be found
	*/
	public static Country remove(long countryId)
		throws com.liferay.blade.samples.jdbcservicebuilder.exception.NoSuchCountryException {
		return getPersistence().remove(countryId);
	}

	public static Country updateImpl(Country country) {
		return getPersistence().updateImpl(country);
	}

	/**
	* Returns the country with the primary key or throws a {@link NoSuchCountryException} if it could not be found.
	*
	* @param countryId the primary key of the country
	* @return the country
	* @throws NoSuchCountryException if a country with the primary key could not be found
	*/
	public static Country findByPrimaryKey(long countryId)
		throws com.liferay.blade.samples.jdbcservicebuilder.exception.NoSuchCountryException {
		return getPersistence().findByPrimaryKey(countryId);
	}

	/**
	* Returns the country with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param countryId the primary key of the country
	* @return the country, or <code>null</code> if a country with the primary key could not be found
	*/
	public static Country fetchByPrimaryKey(long countryId) {
		return getPersistence().fetchByPrimaryKey(countryId);
	}

	public static java.util.Map<java.io.Serializable, Country> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the countries.
	*
	* @return the countries
	*/
	public static List<Country> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the countries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CountryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of countries
	* @param end the upper bound of the range of countries (not inclusive)
	* @return the range of countries
	*/
	public static List<Country> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the countries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CountryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of countries
	* @param end the upper bound of the range of countries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of countries
	*/
	public static List<Country> findAll(int start, int end,
		OrderByComparator<Country> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the countries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CountryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of countries
	* @param end the upper bound of the range of countries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of countries
	*/
	public static List<Country> findAll(int start, int end,
		OrderByComparator<Country> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the countries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of countries.
	*
	* @return the number of countries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CountryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CountryPersistence, CountryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CountryPersistence.class);

		ServiceTracker<CountryPersistence, CountryPersistence> serviceTracker = new ServiceTracker<CountryPersistence, CountryPersistence>(bundle.getBundleContext(),
				CountryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}