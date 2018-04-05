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

import com.liferay.blade.samples.jdbcservicebuilder.exception.NoSuchCountryException;
import com.liferay.blade.samples.jdbcservicebuilder.model.Country;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the country service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.blade.samples.jdbcservicebuilder.service.persistence.impl.CountryPersistenceImpl
 * @see CountryUtil
 * @generated
 */
@ProviderType
public interface CountryPersistence extends BasePersistence<Country> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CountryUtil} to access the country persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the country in the entity cache if it is enabled.
	*
	* @param country the country
	*/
	public void cacheResult(Country country);

	/**
	* Caches the countries in the entity cache if it is enabled.
	*
	* @param countries the countries
	*/
	public void cacheResult(java.util.List<Country> countries);

	/**
	* Creates a new country with the primary key. Does not add the country to the database.
	*
	* @param countryId the primary key for the new country
	* @return the new country
	*/
	public Country create(long countryId);

	/**
	* Removes the country with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param countryId the primary key of the country
	* @return the country that was removed
	* @throws NoSuchCountryException if a country with the primary key could not be found
	*/
	public Country remove(long countryId) throws NoSuchCountryException;

	public Country updateImpl(Country country);

	/**
	* Returns the country with the primary key or throws a {@link NoSuchCountryException} if it could not be found.
	*
	* @param countryId the primary key of the country
	* @return the country
	* @throws NoSuchCountryException if a country with the primary key could not be found
	*/
	public Country findByPrimaryKey(long countryId)
		throws NoSuchCountryException;

	/**
	* Returns the country with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param countryId the primary key of the country
	* @return the country, or <code>null</code> if a country with the primary key could not be found
	*/
	public Country fetchByPrimaryKey(long countryId);

	@Override
	public java.util.Map<java.io.Serializable, Country> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the countries.
	*
	* @return the countries
	*/
	public java.util.List<Country> findAll();

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
	public java.util.List<Country> findAll(int start, int end);

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
	public java.util.List<Country> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Country> orderByComparator);

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
	public java.util.List<Country> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Country> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the countries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of countries.
	*
	* @return the number of countries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}