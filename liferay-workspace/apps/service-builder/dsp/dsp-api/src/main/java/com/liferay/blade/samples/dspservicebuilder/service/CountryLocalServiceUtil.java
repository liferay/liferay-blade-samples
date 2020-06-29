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

package com.liferay.blade.samples.dspservicebuilder.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Country. This utility wraps
 * <code>com.liferay.blade.samples.dspservicebuilder.service.impl.CountryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CountryLocalService
 * @generated
 */
public class CountryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.blade.samples.dspservicebuilder.service.impl.CountryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the country to the database. Also notifies the appropriate model listeners.
	 *
	 * @param country the country
	 * @return the country that was added
	 */
	public static com.liferay.blade.samples.dspservicebuilder.model.Country
		addCountry(
			com.liferay.blade.samples.dspservicebuilder.model.Country country) {

		return getService().addCountry(country);
	}

	/**
	 * Creates a new country with the primary key. Does not add the country to the database.
	 *
	 * @param countryId the primary key for the new country
	 * @return the new country
	 */
	public static com.liferay.blade.samples.dspservicebuilder.model.Country
		createCountry(long countryId) {

		return getService().createCountry(countryId);
	}

	/**
	 * Deletes the country from the database. Also notifies the appropriate model listeners.
	 *
	 * @param country the country
	 * @return the country that was removed
	 */
	public static com.liferay.blade.samples.dspservicebuilder.model.Country
		deleteCountry(
			com.liferay.blade.samples.dspservicebuilder.model.Country country) {

		return getService().deleteCountry(country);
	}

	/**
	 * Deletes the country with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param countryId the primary key of the country
	 * @return the country that was removed
	 * @throws PortalException if a country with the primary key could not be found
	 */
	public static com.liferay.blade.samples.dspservicebuilder.model.Country
			deleteCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCountry(countryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.blade.samples.dspservicebuilder.model.impl.CountryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.blade.samples.dspservicebuilder.model.impl.CountryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.blade.samples.dspservicebuilder.model.Country
		fetchCountry(long countryId) {

		return getService().fetchCountry(countryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the countries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.blade.samples.dspservicebuilder.model.impl.CountryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of countries
	 * @param end the upper bound of the range of countries (not inclusive)
	 * @return the range of countries
	 */
	public static java.util.List
		<com.liferay.blade.samples.dspservicebuilder.model.Country>
			getCountries(int start, int end) {

		return getService().getCountries(start, end);
	}

	/**
	 * Returns the number of countries.
	 *
	 * @return the number of countries
	 */
	public static int getCountriesCount() {
		return getService().getCountriesCount();
	}

	/**
	 * Returns the country with the primary key.
	 *
	 * @param countryId the primary key of the country
	 * @return the country
	 * @throws PortalException if a country with the primary key could not be found
	 */
	public static com.liferay.blade.samples.dspservicebuilder.model.Country
			getCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCountry(countryId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the country in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param country the country
	 * @return the country that was updated
	 */
	public static com.liferay.blade.samples.dspservicebuilder.model.Country
		updateCountry(
			com.liferay.blade.samples.dspservicebuilder.model.Country country) {

		return getService().updateCountry(country);
	}

	public static void useDSP() {
		getService().useDSP();
	}

	public static CountryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CountryLocalService, CountryLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CountryLocalService.class);

		ServiceTracker<CountryLocalService, CountryLocalService>
			serviceTracker =
				new ServiceTracker<CountryLocalService, CountryLocalService>(
					bundle.getBundleContext(), CountryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}