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

package com.liferay.blade.samples.jndiservicebuilder.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blade.samples.jndiservicebuilder.exception.NoSuchRegionException;
import com.liferay.blade.samples.jndiservicebuilder.model.Region;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.blade.samples.jndiservicebuilder.service.persistence.impl.RegionPersistenceImpl
 * @see RegionUtil
 * @generated
 */
@ProviderType
public interface RegionPersistence extends BasePersistence<Region> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegionUtil} to access the region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the region in the entity cache if it is enabled.
	*
	* @param region the region
	*/
	public void cacheResult(Region region);

	/**
	* Caches the regions in the entity cache if it is enabled.
	*
	* @param regions the regions
	*/
	public void cacheResult(java.util.List<Region> regions);

	/**
	* Creates a new region with the primary key. Does not add the region to the database.
	*
	* @param regionId the primary key for the new region
	* @return the new region
	*/
	public Region create(long regionId);

	/**
	* Removes the region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param regionId the primary key of the region
	* @return the region that was removed
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region remove(long regionId) throws NoSuchRegionException;

	public Region updateImpl(Region region);

	/**
	* Returns the region with the primary key or throws a {@link NoSuchRegionException} if it could not be found.
	*
	* @param regionId the primary key of the region
	* @return the region
	* @throws NoSuchRegionException if a region with the primary key could not be found
	*/
	public Region findByPrimaryKey(long regionId) throws NoSuchRegionException;

	/**
	* Returns the region with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param regionId the primary key of the region
	* @return the region, or <code>null</code> if a region with the primary key could not be found
	*/
	public Region fetchByPrimaryKey(long regionId);

	@Override
	public java.util.Map<java.io.Serializable, Region> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the regions.
	*
	* @return the regions
	*/
	public java.util.List<Region> findAll();

	/**
	* Returns a range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @return the range of regions
	*/
	public java.util.List<Region> findAll(int start, int end);

	/**
	* Returns an ordered range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of regions
	*/
	public java.util.List<Region> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator);

	/**
	* Returns an ordered range of all the regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of regions
	* @param end the upper bound of the range of regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of regions
	*/
	public java.util.List<Region> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Region> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the regions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of regions.
	*
	* @return the number of regions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}