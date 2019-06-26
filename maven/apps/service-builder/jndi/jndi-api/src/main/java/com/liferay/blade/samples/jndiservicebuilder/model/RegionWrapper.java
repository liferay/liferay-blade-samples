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
	public java.lang.Object clone() {
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
	public java.lang.String getRegionName() {
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
	public void setRegionName(java.lang.String regionName) {
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
	public java.lang.String toString() {
		return _region.toString();
	}

	@Override
	public Region toUnescapedModel() {
		return new RegionWrapper(_region.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
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