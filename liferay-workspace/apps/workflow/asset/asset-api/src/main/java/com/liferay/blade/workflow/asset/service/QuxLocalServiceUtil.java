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

package com.liferay.blade.workflow.asset.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Qux. This utility wraps
 * <code>com.liferay.blade.workflow.asset.service.impl.QuxLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Inácio Nery
 * @see QuxLocalService
 * @generated
 */
@ProviderType
public class QuxLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.blade.workflow.asset.service.impl.QuxLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.blade.workflow.asset.model.Qux addQux(
			long userId, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addQux(userId, groupId, serviceContext);
	}

	/**
	 * Adds the qux to the database. Also notifies the appropriate model listeners.
	 *
	 * @param qux the qux
	 * @return the qux that was added
	 */
	public static com.liferay.blade.workflow.asset.model.Qux addQux(
		com.liferay.blade.workflow.asset.model.Qux qux) {

		return getService().addQux(qux);
	}

	/**
	 * Creates a new qux with the primary key. Does not add the qux to the database.
	 *
	 * @param quxId the primary key for the new qux
	 * @return the new qux
	 */
	public static com.liferay.blade.workflow.asset.model.Qux createQux(
		long quxId) {

		return getService().createQux(quxId);
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

	/**
	 * Deletes the qux with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param quxId the primary key of the qux
	 * @return the qux that was removed
	 * @throws PortalException if a qux with the primary key could not be found
	 */
	public static com.liferay.blade.workflow.asset.model.Qux deleteQux(
			long quxId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteQux(quxId);
	}

	/**
	 * Deletes the qux from the database. Also notifies the appropriate model listeners.
	 *
	 * @param qux the qux
	 * @return the qux that was removed
	 */
	public static com.liferay.blade.workflow.asset.model.Qux deleteQux(
		com.liferay.blade.workflow.asset.model.Qux qux) {

		return getService().deleteQux(qux);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.blade.workflow.asset.model.impl.QuxModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.blade.workflow.asset.model.impl.QuxModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.blade.workflow.asset.model.Qux fetchQux(
		long quxId) {

		return getService().fetchQux(quxId);
	}

	/**
	 * Returns the qux matching the UUID and group.
	 *
	 * @param uuid the qux's UUID
	 * @param groupId the primary key of the group
	 * @return the matching qux, or <code>null</code> if a matching qux could not be found
	 */
	public static com.liferay.blade.workflow.asset.model.Qux
		fetchQuxByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchQuxByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the qux with the primary key.
	 *
	 * @param quxId the primary key of the qux
	 * @return the qux
	 * @throws PortalException if a qux with the primary key could not be found
	 */
	public static com.liferay.blade.workflow.asset.model.Qux getQux(long quxId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getQux(quxId);
	}

	/**
	 * Returns the qux matching the UUID and group.
	 *
	 * @param uuid the qux's UUID
	 * @param groupId the primary key of the group
	 * @return the matching qux
	 * @throws PortalException if a matching qux could not be found
	 */
	public static com.liferay.blade.workflow.asset.model.Qux
			getQuxByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getQuxByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the quxs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.blade.workflow.asset.model.impl.QuxModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of quxs
	 * @param end the upper bound of the range of quxs (not inclusive)
	 * @return the range of quxs
	 */
	public static java.util.List<com.liferay.blade.workflow.asset.model.Qux>
		getQuxs(int start, int end) {

		return getService().getQuxs(start, end);
	}

	/**
	 * Returns all the quxs matching the UUID and company.
	 *
	 * @param uuid the UUID of the quxs
	 * @param companyId the primary key of the company
	 * @return the matching quxs, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.blade.workflow.asset.model.Qux>
		getQuxsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getQuxsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of quxs matching the UUID and company.
	 *
	 * @param uuid the UUID of the quxs
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of quxs
	 * @param end the upper bound of the range of quxs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching quxs, or an empty list if no matches were found
	 */
	public static java.util.List<com.liferay.blade.workflow.asset.model.Qux>
		getQuxsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blade.workflow.asset.model.Qux>
					orderByComparator) {

		return getService().getQuxsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of quxs.
	 *
	 * @return the number of quxs
	 */
	public static int getQuxsCount() {
		return getService().getQuxsCount();
	}

	public static com.liferay.blade.workflow.asset.model.Qux updateQux(
			long quxId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateQux(quxId, serviceContext);
	}

	/**
	 * Updates the qux in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param qux the qux
	 * @return the qux that was updated
	 */
	public static com.liferay.blade.workflow.asset.model.Qux updateQux(
		com.liferay.blade.workflow.asset.model.Qux qux) {

		return getService().updateQux(qux);
	}

	public static com.liferay.blade.workflow.asset.model.Qux updateStatus(
			long userId, long quxId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(userId, quxId, status);
	}

	public static QuxLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<QuxLocalService, QuxLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(QuxLocalService.class);

		ServiceTracker<QuxLocalService, QuxLocalService> serviceTracker =
			new ServiceTracker<QuxLocalService, QuxLocalService>(
				bundle.getBundleContext(), QuxLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}