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

package com.liferay.blade.workflow.basic.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Baz. This utility wraps
 * <code>com.liferay.blade.workflow.basic.service.impl.BazLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Inácio Nery
 * @see BazLocalService
 * @generated
 */
@ProviderType
public class BazLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.blade.workflow.basic.service.impl.BazLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the baz to the database. Also notifies the appropriate model listeners.
	 *
	 * @param baz the baz
	 * @return the baz that was added
	 */
	public static com.liferay.blade.workflow.basic.model.Baz addBaz(
		com.liferay.blade.workflow.basic.model.Baz baz) {

		return getService().addBaz(baz);
	}

	public static com.liferay.blade.workflow.basic.model.Baz addBaz(
			long userId, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addBaz(userId, groupId, serviceContext);
	}

	/**
	 * Creates a new baz with the primary key. Does not add the baz to the database.
	 *
	 * @param bazId the primary key for the new baz
	 * @return the new baz
	 */
	public static com.liferay.blade.workflow.basic.model.Baz createBaz(
		long bazId) {

		return getService().createBaz(bazId);
	}

	/**
	 * Deletes the baz from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baz the baz
	 * @return the baz that was removed
	 */
	public static com.liferay.blade.workflow.basic.model.Baz deleteBaz(
		com.liferay.blade.workflow.basic.model.Baz baz) {

		return getService().deleteBaz(baz);
	}

	/**
	 * Deletes the baz with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz that was removed
	 * @throws PortalException if a baz with the primary key could not be found
	 */
	public static com.liferay.blade.workflow.basic.model.Baz deleteBaz(
			long bazId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteBaz(bazId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.blade.workflow.basic.model.impl.BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.blade.workflow.basic.model.impl.BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.blade.workflow.basic.model.Baz fetchBaz(
		long bazId) {

		return getService().fetchBaz(bazId);
	}

	/**
	 * Returns the baz matching the UUID and group.
	 *
	 * @param uuid the baz's UUID
	 * @param groupId the primary key of the group
	 * @return the matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public static com.liferay.blade.workflow.basic.model.Baz
		fetchBazByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchBazByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the baz with the primary key.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz
	 * @throws PortalException if a baz with the primary key could not be found
	 */
	public static com.liferay.blade.workflow.basic.model.Baz getBaz(long bazId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getBaz(bazId);
	}

	/**
	 * Returns the baz matching the UUID and group.
	 *
	 * @param uuid the baz's UUID
	 * @param groupId the primary key of the group
	 * @return the matching baz
	 * @throws PortalException if a matching baz could not be found
	 */
	public static com.liferay.blade.workflow.basic.model.Baz
			getBazByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getBazByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the bazs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.blade.workflow.basic.model.impl.BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @return the range of bazs
	 */
	public static java.util.List<com.liferay.blade.workflow.basic.model.Baz>
		getBazs(int start, int end) {

		return getService().getBazs(start, end);
	}

	/**
	 * Returns all the bazs matching the UUID and company.
	 *
	 * @param uuid the UUID of the bazs
	 * @param companyId the primary key of the company
	 * @return the matching bazs, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.blade.workflow.basic.model.Baz>
		getBazsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getBazsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of bazs matching the UUID and company.
	 *
	 * @param uuid the UUID of the bazs
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching bazs, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.blade.workflow.basic.model.Baz>
		getBazsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blade.workflow.basic.model.Baz>
					orderByComparator) {

		return getService().getBazsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of bazs.
	 *
	 * @return the number of bazs
	 */
	public static int getBazsCount() {
		return getService().getBazsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
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

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the baz in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param baz the baz
	 * @return the baz that was updated
	 */
	public static com.liferay.blade.workflow.basic.model.Baz updateBaz(
		com.liferay.blade.workflow.basic.model.Baz baz) {

		return getService().updateBaz(baz);
	}

	public static com.liferay.blade.workflow.basic.model.Baz updateBaz(
			long bazId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateBaz(bazId, serviceContext);
	}

	public static com.liferay.blade.workflow.basic.model.Baz updateStatus(
			long userId, long bazId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(userId, bazId, status);
	}

	public static BazLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BazLocalService, BazLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BazLocalService.class);

		ServiceTracker<BazLocalService, BazLocalService> serviceTracker =
			new ServiceTracker<BazLocalService, BazLocalService>(
				bundle.getBundleContext(), BazLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}