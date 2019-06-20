/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.workflow.basic.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link BazLocalService}.
 *
 * @author Inácio Nery
 * @see BazLocalService
 * @generated
 */
@ProviderType
public class BazLocalServiceWrapper
	implements BazLocalService, ServiceWrapper<BazLocalService> {

	public BazLocalServiceWrapper(BazLocalService bazLocalService) {
		_bazLocalService = bazLocalService;
	}

	/**
	 * Adds the baz to the database. Also notifies the appropriate model listeners.
	 *
	 * @param baz the baz
	 * @return the baz that was added
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz addBaz(
		com.liferay.blade.workflow.basic.model.Baz baz) {

		return _bazLocalService.addBaz(baz);
	}

	@Override
	public com.liferay.blade.workflow.basic.model.Baz addBaz(
			long userId, long groupId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.addBaz(userId, groupId, serviceContext);
	}

	/**
	 * Creates a new baz with the primary key. Does not add the baz to the database.
	 *
	 * @param bazId the primary key for the new baz
	 * @return the new baz
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz createBaz(long bazId) {
		return _bazLocalService.createBaz(bazId);
	}

	/**
	 * Deletes the baz from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baz the baz
	 * @return the baz that was removed
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz deleteBaz(
		com.liferay.blade.workflow.basic.model.Baz baz) {

		return _bazLocalService.deleteBaz(baz);
	}

	/**
	 * Deletes the baz with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz that was removed
	 * @throws PortalException if a baz with the primary key could not be found
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz deleteBaz(long bazId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.deleteBaz(bazId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bazLocalService.dynamicQuery();
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

		return _bazLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _bazLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _bazLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _bazLocalService.dynamicQueryCount(dynamicQuery);
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

		return _bazLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.blade.workflow.basic.model.Baz fetchBaz(long bazId) {
		return _bazLocalService.fetchBaz(bazId);
	}

	/**
	 * Returns the baz matching the UUID and group.
	 *
	 * @param uuid the baz's UUID
	 * @param groupId the primary key of the group
	 * @return the matching baz, or <code>null</code> if a matching baz could not be found
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz fetchBazByUuidAndGroupId(
		String uuid, long groupId) {

		return _bazLocalService.fetchBazByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _bazLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the baz with the primary key.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz
	 * @throws PortalException if a baz with the primary key could not be found
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz getBaz(long bazId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.getBaz(bazId);
	}

	/**
	 * Returns the baz matching the UUID and group.
	 *
	 * @param uuid the baz's UUID
	 * @param groupId the primary key of the group
	 * @return the matching baz
	 * @throws PortalException if a matching baz could not be found
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz getBazByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.getBazByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<com.liferay.blade.workflow.basic.model.Baz> getBazs(
		int start, int end) {

		return _bazLocalService.getBazs(start, end);
	}

	/**
	 * Returns all the bazs matching the UUID and company.
	 *
	 * @param uuid the UUID of the bazs
	 * @param companyId the primary key of the company
	 * @return the matching bazs, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.blade.workflow.basic.model.Baz>
		getBazsByUuidAndCompanyId(String uuid, long companyId) {

		return _bazLocalService.getBazsByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<com.liferay.blade.workflow.basic.model.Baz>
		getBazsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blade.workflow.basic.model.Baz>
					orderByComparator) {

		return _bazLocalService.getBazsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of bazs.
	 *
	 * @return the number of bazs
	 */
	@Override
	public int getBazsCount() {
		return _bazLocalService.getBazsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _bazLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _bazLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _bazLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the baz in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param baz the baz
	 * @return the baz that was updated
	 */
	@Override
	public com.liferay.blade.workflow.basic.model.Baz updateBaz(
		com.liferay.blade.workflow.basic.model.Baz baz) {

		return _bazLocalService.updateBaz(baz);
	}

	@Override
	public com.liferay.blade.workflow.basic.model.Baz updateBaz(
			long bazId,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bazLocalService.updateBaz(bazId, serviceContext);
	}

	@Override
	public BazLocalService getWrappedService() {
		return _bazLocalService;
	}

	@Override
	public void setWrappedService(BazLocalService bazLocalService) {
		_bazLocalService = bazLocalService;
	}

	private BazLocalService _bazLocalService;

}