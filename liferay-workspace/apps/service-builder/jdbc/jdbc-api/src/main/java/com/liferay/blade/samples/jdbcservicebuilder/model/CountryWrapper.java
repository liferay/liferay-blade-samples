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

package com.liferay.blade.samples.jdbcservicebuilder.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Country}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Country
 * @generated
 */
@ProviderType
public class CountryWrapper
	extends BaseModelWrapper<Country>
	implements Country, ModelWrapper<Country> {

	public CountryWrapper(Country country) {
		super(country);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("countryId", getCountryId());
		attributes.put("countryName", getCountryName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String countryName = (String)attributes.get("countryName");

		if (countryName != null) {
			setCountryName(countryName);
		}
	}

	/**
	 * Returns the country ID of this country.
	 *
	 * @return the country ID of this country
	 */
	@Override
	public long getCountryId() {
		return model.getCountryId();
	}

	/**
	 * Returns the country name of this country.
	 *
	 * @return the country name of this country
	 */
	@Override
	public String getCountryName() {
		return model.getCountryName();
	}

	/**
	 * Returns the primary key of this country.
	 *
	 * @return the primary key of this country
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the country ID of this country.
	 *
	 * @param countryId the country ID of this country
	 */
	@Override
	public void setCountryId(long countryId) {
		model.setCountryId(countryId);
	}

	/**
	 * Sets the country name of this country.
	 *
	 * @param countryName the country name of this country
	 */
	@Override
	public void setCountryName(String countryName) {
		model.setCountryName(countryName);
	}

	/**
	 * Sets the primary key of this country.
	 *
	 * @param primaryKey the primary key of this country
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CountryWrapper wrap(Country country) {
		return new CountryWrapper(country);
	}

}