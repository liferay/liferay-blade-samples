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

package com.liferay.blade.samples.jndiservicebuilder.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Region}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Region
 * @generated
 */
@ProviderType
public class RegionWrapper implements Region, ModelWrapper<Region> {
	public RegionWrapper(Region region) {
		_region = region;
	}

	@Override
	public Class<?> getModelClass() {
		return Region.class;
	}

	@Override
	public String getModelClassName() {
		return Region.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("regionId", getRegionId());
		attributes.put("regionName", getRegionName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long regionId = (Long)attributes.get("regionId");

		if (regionId != null) {
			setRegionId(regionId);
		}

		String regionName = (String)attributes.get("regionName");

		if (regionName != null) {
			setRegionName(regionName);
		}
	}

	@Override
	public Object clone() {
		return new RegionWrapper((Region)_region.clone());
	}

	@Override
	public int compareTo(Region region) {
		return _region.compareTo(region);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _region.getExpandoBridge();
	}

	/**
	* Returns the primary key of this region.
	*
	* @return the primary key of this region
	*/
	@Override
	public long getPrimaryKey() {
		return _region.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _region.getPrimaryKeyObj();
	}

	/**
	* Returns the region ID of this region.
	*
	* @return the region ID of this region
	*/
	@Override
	public long getRegionId() {
		return _region.getRegionId();
	}

	/**
	* Returns the region name of this region.
	*
	* @return the region name of this region
	*/
	@Override
	public String getRegionName() {
		return _region.getRegionName();
	}

	@Override
	public int hashCode() {
		return _region.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _region.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _region.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _region.isNew();
	}

	@Override
	public void persist() {
		_region.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_region.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_region.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_region.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_region.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_region.setNew(n);
	}

	/**
	* Sets the primary key of this region.
	*
	* @param primaryKey the primary key of this region
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_region.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_region.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the region ID of this region.
	*
	* @param regionId the region ID of this region
	*/
	@Override
	public void setRegionId(long regionId) {
		_region.setRegionId(regionId);
	}

	/**
	* Sets the region name of this region.
	*
	* @param regionName the region name of this region
	*/
	@Override
	public void setRegionName(String regionName) {
		_region.setRegionName(regionName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Region> toCacheModel() {
		return _region.toCacheModel();
	}

	@Override
	public Region toEscapedModel() {
		return new RegionWrapper(_region.toEscapedModel());
	}

	@Override
	public String toString() {
		return _region.toString();
	}

	@Override
	public Region toUnescapedModel() {
		return new RegionWrapper(_region.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _region.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegionWrapper)) {
			return false;
		}

		RegionWrapper regionWrapper = (RegionWrapper)obj;

		if (Objects.equals(_region, regionWrapper._region)) {
			return true;
		}

		return false;
	}

	@Override
	public Region getWrappedModel() {
		return _region;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _region.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _region.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_region.resetOriginalValues();
	}

	private final Region _region;
}