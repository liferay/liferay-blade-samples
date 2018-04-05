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
 * This class is a wrapper for {@link Country}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Country
 * @generated
 */
@ProviderType
public class CountryWrapper implements Country, ModelWrapper<Country> {
	public CountryWrapper(Country country) {
		_country = country;
	}

	@Override
	public Class<?> getModelClass() {
		return Country.class;
	}

	@Override
	public String getModelClassName() {
		return Country.class.getName();
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

	@Override
	public java.lang.Object clone() {
		return new CountryWrapper((Country)_country.clone());
	}

	@Override
	public int compareTo(Country country) {
		return _country.compareTo(country);
	}

	/**
	* Returns the country ID of this country.
	*
	* @return the country ID of this country
	*/
	@Override
	public long getCountryId() {
		return _country.getCountryId();
	}

	/**
	* Returns the country name of this country.
	*
	* @return the country name of this country
	*/
	@Override
	public java.lang.String getCountryName() {
		return _country.getCountryName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _country.getExpandoBridge();
	}

	/**
	* Returns the primary key of this country.
	*
	* @return the primary key of this country
	*/
	@Override
	public long getPrimaryKey() {
		return _country.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _country.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _country.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _country.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _country.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _country.isNew();
	}

	@Override
	public void persist() {
		_country.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_country.setCachedModel(cachedModel);
	}

	/**
	* Sets the country ID of this country.
	*
	* @param countryId the country ID of this country
	*/
	@Override
	public void setCountryId(long countryId) {
		_country.setCountryId(countryId);
	}

	/**
	* Sets the country name of this country.
	*
	* @param countryName the country name of this country
	*/
	@Override
	public void setCountryName(java.lang.String countryName) {
		_country.setCountryName(countryName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_country.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_country.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_country.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_country.setNew(n);
	}

	/**
	* Sets the primary key of this country.
	*
	* @param primaryKey the primary key of this country
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_country.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_country.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Country> toCacheModel() {
		return _country.toCacheModel();
	}

	@Override
	public Country toEscapedModel() {
		return new CountryWrapper(_country.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _country.toString();
	}

	@Override
	public Country toUnescapedModel() {
		return new CountryWrapper(_country.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _country.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CountryWrapper)) {
			return false;
		}

		CountryWrapper countryWrapper = (CountryWrapper)obj;

		if (Objects.equals(_country, countryWrapper._country)) {
			return true;
		}

		return false;
	}

	@Override
	public Country getWrappedModel() {
		return _country;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _country.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _country.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_country.resetOriginalValues();
	}

	private final Country _country;
}