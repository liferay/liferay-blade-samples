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

package com.liferay.blade.samples.jndiservicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RegionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RegionLocalService
 * @generated
 */
@ProviderType
public class RegionLocalServiceWrapper implements RegionLocalService,
	ServiceWrapper<RegionLocalService> {
	public RegionLocalServiceWrapper(RegionLocalService regionLocalService) {
		_regionLocalService = regionLocalService;
	}

	/**
	* Adds the region to the database. Also notifies the appropriate model listeners.
	*
	* @param region the region
	* @return the region that was added
	*/
	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region addRegion(
		com.liferay.blade.samples.jndiservicebuilder.model.Region region) {
		return _regionLocalService.addRegion(region);
	}

	/**
	* Creates a new region with the primary key. Does not add the region to the database.
	*
	* @param regionId the primary key for the new region
	* @return the new region
	*/
	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region createRegion(
		long regionId) {
		return _regionLocalService.createRegion(regionId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _regionLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param regionId the primary key of the region
	* @return the region that was removed
	* @throws PortalException if a region with the primary key could not be found
	*/
	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region deleteRegion(
		long regionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _regionLocalService.deleteRegion(regionId);
	}

	/**
	* Deletes the region from the database. Also notifies the appropriate model listeners.
	*
	* @param region the region
	* @return the region that was removed
	*/
	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region deleteRegion(
		com.liferay.blade.samples.jndiservicebuilder.model.Region region) {
		return _regionLocalService.deleteRegion(region);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _regionLocalService.dynamicQuery();
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
		return _regionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _regionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _regionLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _regionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _regionLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region fetchRegion(
		long regionId) {
		return _regionLocalService.fetchRegion(regionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _regionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _regionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _regionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _regionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the region with the primary key.
	*
	* @param regionId the primary key of the region
	* @return the region
	* @throws PortalException if a region with the primary key could not be found
	*/
	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region getRegion(
		long regionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _regionLocalService.getRegion(regionId);
	}

	/**
	* Returns a range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of regions
	*/
	@Override
	public java.util.List<com.liferay.blade.samples.jndiservicebuilder.model.Region> getRegions(
		int start, int end) {
		return _regionLocalService.getRegions(start, end);
	}

	/**
	* Returns the number of regions.
	*
	* @return the number of regions
	*/
	@Override
	public int getRegionsCount() {
		return _regionLocalService.getRegionsCount();
	}

	/**
	* Updates the region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param region the region
	* @return the region that was updated
	*/
	@Override
	public com.liferay.blade.samples.jndiservicebuilder.model.Region updateRegion(
		com.liferay.blade.samples.jndiservicebuilder.model.Region region) {
		return _regionLocalService.updateRegion(region);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link RegionLocalServiceUtil} to access the region local service.
	*/
	@Override
	public void useJNDI() {
		_regionLocalService.useJNDI();
	}

	@Override
	public RegionLocalService getWrappedService() {
		return _regionLocalService;
	}

	@Override
	public void setWrappedService(RegionLocalService regionLocalService) {
		_regionLocalService = regionLocalService;
	}

	private RegionLocalService _regionLocalService;
}