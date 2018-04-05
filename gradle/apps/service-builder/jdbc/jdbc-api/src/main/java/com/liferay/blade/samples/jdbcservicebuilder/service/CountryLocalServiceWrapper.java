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

package com.liferay.blade.samples.jdbcservicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CountryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CountryLocalService
 * @generated
 */
@ProviderType
public class CountryLocalServiceWrapper implements CountryLocalService,
	ServiceWrapper<CountryLocalService> {
	public CountryLocalServiceWrapper(CountryLocalService countryLocalService) {
		_countryLocalService = countryLocalService;
	}

	/**
	* Adds the country to the database. Also notifies the appropriate model listeners.
	*
	* @param country the country
	* @return the country that was added
	*/
	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country addCountry(
		com.liferay.blade.samples.jdbcservicebuilder.model.Country country) {
		return _countryLocalService.addCountry(country);
	}

	/**
	* Creates a new country with the primary key. Does not add the country to the database.
	*
	* @param countryId the primary key for the new country
	* @return the new country
	*/
	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country createCountry(
		long countryId) {
		return _countryLocalService.createCountry(countryId);
	}

	/**
	* Deletes the country from the database. Also notifies the appropriate model listeners.
	*
	* @param country the country
	* @return the country that was removed
	*/
	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country deleteCountry(
		com.liferay.blade.samples.jdbcservicebuilder.model.Country country) {
		return _countryLocalService.deleteCountry(country);
	}

	/**
	* Deletes the country with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param countryId the primary key of the country
	* @return the country that was removed
	* @throws PortalException if a country with the primary key could not be found
	*/
	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country deleteCountry(
		long countryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _countryLocalService.deleteCountry(countryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _countryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _countryLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _countryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.jdbcservicebuilder.model.impl.CountryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _countryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.jdbcservicebuilder.model.impl.CountryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _countryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _countryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _countryLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country fetchCountry(
		long countryId) {
		return _countryLocalService.fetchCountry(countryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _countryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the countries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.jdbcservicebuilder.model.impl.CountryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of countries
	* @param end the upper bound of the range of countries (not inclusive)
	* @return the range of countries
	*/
	@Override
	public java.util.List<com.liferay.blade.samples.jdbcservicebuilder.model.Country> getCountries(
		int start, int end) {
		return _countryLocalService.getCountries(start, end);
	}

	/**
	* Returns the number of countries.
	*
	* @return the number of countries
	*/
	@Override
	public int getCountriesCount() {
		return _countryLocalService.getCountriesCount();
	}

	/**
	* Returns the country with the primary key.
	*
	* @param countryId the primary key of the country
	* @return the country
	* @throws PortalException if a country with the primary key could not be found
	*/
	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country getCountry(
		long countryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _countryLocalService.getCountry(countryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _countryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _countryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _countryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the country in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param country the country
	* @return the country that was updated
	*/
	@Override
	public com.liferay.blade.samples.jdbcservicebuilder.model.Country updateCountry(
		com.liferay.blade.samples.jdbcservicebuilder.model.Country country) {
		return _countryLocalService.updateCountry(country);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link CountryLocalServiceUtil} to access the country local service.
	*/
	@Override
	public void useJDBC() {
		_countryLocalService.useJDBC();
	}

	@Override
	public CountryLocalService getWrappedService() {
		return _countryLocalService;
	}

	@Override
	public void setWrappedService(CountryLocalService countryLocalService) {
		_countryLocalService = countryLocalService;
	}

	private CountryLocalService _countryLocalService;
}