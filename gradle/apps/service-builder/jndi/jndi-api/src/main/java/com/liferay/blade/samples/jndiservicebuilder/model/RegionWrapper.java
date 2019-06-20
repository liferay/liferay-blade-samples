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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class RegionWrapper
	extends BaseModelWrapper<Region> implements Region, ModelWrapper<Region> {

	public RegionWrapper(Region region) {
		super(region);
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

	/**
	 * Returns the primary key of this region.
	 *
	 * @return the primary key of this region
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the region ID of this region.
	 *
	 * @return the region ID of this region
	 */
	@Override
	public long getRegionId() {
		return model.getRegionId();
	}

	/**
	 * Returns the region name of this region.
	 *
	 * @return the region name of this region
	 */
	@Override
	public String getRegionName() {
		return model.getRegionName();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the primary key of this region.
	 *
	 * @param primaryKey the primary key of this region
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the region ID of this region.
	 *
	 * @param regionId the region ID of this region
	 */
	@Override
	public void setRegionId(long regionId) {
		model.setRegionId(regionId);
	}

	/**
	 * Sets the region name of this region.
	 *
	 * @param regionName the region name of this region
	 */
	@Override
	public void setRegionName(String regionName) {
		model.setRegionName(regionName);
	}

	@Override
	protected RegionWrapper wrap(Region region) {
		return new RegionWrapper(region);
	}

}