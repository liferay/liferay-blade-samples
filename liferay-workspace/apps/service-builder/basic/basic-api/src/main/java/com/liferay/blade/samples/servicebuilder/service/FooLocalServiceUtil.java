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

package com.liferay.blade.samples.servicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Foo. This utility wraps
 * {@link com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FooLocalService
 * @see com.liferay.blade.samples.servicebuilder.service.base.FooLocalServiceBaseImpl
 * @see com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl
 * @generated
 */
@ProviderType
public class FooLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the foo to the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was added
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo addFoo(
		com.liferay.blade.samples.servicebuilder.model.Foo foo) {
		return getService().addFoo(foo);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link FooLocalServiceUtil} to access the foo local service.
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo addFooWithoutId(
		com.liferay.blade.samples.servicebuilder.model.Foo foo) {
		return getService().addFooWithoutId(foo);
	}

	/**
	* Creates a new foo with the primary key. Does not add the foo to the database.
	*
	* @param fooId the primary key for the new foo
	* @return the new foo
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo createFoo(
		long fooId) {
		return getService().createFoo(fooId);
	}

	/**
	* Deletes the foo from the database. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was removed
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo deleteFoo(
		com.liferay.blade.samples.servicebuilder.model.Foo foo) {
		return getService().deleteFoo(foo);
	}

	/**
	* Deletes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the foo
	* @return the foo that was removed
	* @throws PortalException if a foo with the primary key could not be found
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo deleteFoo(
		long fooId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFoo(fooId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.servicebuilder.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.servicebuilder.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.blade.samples.servicebuilder.model.Foo fetchFoo(
		long fooId) {
		return getService().fetchFoo(fooId);
	}

	/**
	* Returns the foo matching the UUID and group.
	*
	* @param uuid the foo's UUID
	* @param groupId the primary key of the group
	* @return the matching foo, or <code>null</code> if a matching foo could not be found
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo fetchFooByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchFooByUuidAndGroupId(uuid, groupId);
	}

	public static java.lang.String fooLocal() {
		return getService().fooLocal();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns the foo with the primary key.
	*
	* @param fooId the primary key of the foo
	* @return the foo
	* @throws PortalException if a foo with the primary key could not be found
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo getFoo(
		long fooId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFoo(fooId);
	}

	/**
	* Returns the foo matching the UUID and group.
	*
	* @param uuid the foo's UUID
	* @param groupId the primary key of the group
	* @return the matching foo
	* @throws PortalException if a matching foo could not be found
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo getFooByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFooByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.servicebuilder.model.impl.FooModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of foos
	*/
	public static java.util.List<com.liferay.blade.samples.servicebuilder.model.Foo> getFoos(
		int start, int end) {
		return getService().getFoos(start, end);
	}

	/**
	* Returns all the foos matching the UUID and company.
	*
	* @param uuid the UUID of the foos
	* @param companyId the primary key of the company
	* @return the matching foos, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.blade.samples.servicebuilder.model.Foo> getFoosByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getFoosByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of foos matching the UUID and company.
	*
	* @param uuid the UUID of the foos
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching foos, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.blade.samples.servicebuilder.model.Foo> getFoosByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blade.samples.servicebuilder.model.Foo> orderByComparator) {
		return getService()
				   .getFoosByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of foos.
	*
	* @return the number of foos
	*/
	public static int getFoosCount() {
		return getService().getFoosCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the foo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param foo the foo
	* @return the foo that was updated
	*/
	public static com.liferay.blade.samples.servicebuilder.model.Foo updateFoo(
		com.liferay.blade.samples.servicebuilder.model.Foo foo) {
		return getService().updateFoo(foo);
	}

	public static FooLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FooLocalService, FooLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FooLocalService.class);

		ServiceTracker<FooLocalService, FooLocalService> serviceTracker = new ServiceTracker<FooLocalService, FooLocalService>(bundle.getBundleContext(),
				FooLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}