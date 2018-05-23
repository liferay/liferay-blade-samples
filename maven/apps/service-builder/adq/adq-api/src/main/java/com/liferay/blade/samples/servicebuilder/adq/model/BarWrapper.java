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

package com.liferay.blade.samples.servicebuilder.adq.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Bar}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Bar
 * @generated
 */
@ProviderType
public class BarWrapper implements Bar, ModelWrapper<Bar> {
	public BarWrapper(Bar bar) {
		_bar = bar;
	}

	@Override
	public Class<?> getModelClass() {
		return Bar.class;
	}

	@Override
	public String getModelClassName() {
		return Bar.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("barId", getBarId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("field1", getField1());
		attributes.put("field2", isField2());
		attributes.put("field3", getField3());
		attributes.put("field4", getField4());
		attributes.put("field5", getField5());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long barId = (Long)attributes.get("barId");

		if (barId != null) {
			setBarId(barId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String field1 = (String)attributes.get("field1");

		if (field1 != null) {
			setField1(field1);
		}

		Boolean field2 = (Boolean)attributes.get("field2");

		if (field2 != null) {
			setField2(field2);
		}

		Integer field3 = (Integer)attributes.get("field3");

		if (field3 != null) {
			setField3(field3);
		}

		Date field4 = (Date)attributes.get("field4");

		if (field4 != null) {
			setField4(field4);
		}

		String field5 = (String)attributes.get("field5");

		if (field5 != null) {
			setField5(field5);
		}
	}

	@Override
	public Object clone() {
		return new BarWrapper((Bar)_bar.clone());
	}

	@Override
	public int compareTo(Bar bar) {
		return _bar.compareTo(bar);
	}

	/**
	* Returns the bar ID of this bar.
	*
	* @return the bar ID of this bar
	*/
	@Override
	public long getBarId() {
		return _bar.getBarId();
	}

	/**
	* Returns the company ID of this bar.
	*
	* @return the company ID of this bar
	*/
	@Override
	public long getCompanyId() {
		return _bar.getCompanyId();
	}

	/**
	* Returns the create date of this bar.
	*
	* @return the create date of this bar
	*/
	@Override
	public Date getCreateDate() {
		return _bar.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _bar.getExpandoBridge();
	}

	/**
	* Returns the field1 of this bar.
	*
	* @return the field1 of this bar
	*/
	@Override
	public String getField1() {
		return _bar.getField1();
	}

	/**
	* Returns the field2 of this bar.
	*
	* @return the field2 of this bar
	*/
	@Override
	public boolean getField2() {
		return _bar.getField2();
	}

	/**
	* Returns the field3 of this bar.
	*
	* @return the field3 of this bar
	*/
	@Override
	public int getField3() {
		return _bar.getField3();
	}

	/**
	* Returns the field4 of this bar.
	*
	* @return the field4 of this bar
	*/
	@Override
	public Date getField4() {
		return _bar.getField4();
	}

	/**
	* Returns the field5 of this bar.
	*
	* @return the field5 of this bar
	*/
	@Override
	public String getField5() {
		return _bar.getField5();
	}

	/**
	* Returns the group ID of this bar.
	*
	* @return the group ID of this bar
	*/
	@Override
	public long getGroupId() {
		return _bar.getGroupId();
	}

	/**
	* Returns the modified date of this bar.
	*
	* @return the modified date of this bar
	*/
	@Override
	public Date getModifiedDate() {
		return _bar.getModifiedDate();
	}

	/**
	* Returns the primary key of this bar.
	*
	* @return the primary key of this bar
	*/
	@Override
	public long getPrimaryKey() {
		return _bar.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bar.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this bar.
	*
	* @return the user ID of this bar
	*/
	@Override
	public long getUserId() {
		return _bar.getUserId();
	}

	/**
	* Returns the user name of this bar.
	*
	* @return the user name of this bar
	*/
	@Override
	public String getUserName() {
		return _bar.getUserName();
	}

	/**
	* Returns the user uuid of this bar.
	*
	* @return the user uuid of this bar
	*/
	@Override
	public String getUserUuid() {
		return _bar.getUserUuid();
	}

	/**
	* Returns the uuid of this bar.
	*
	* @return the uuid of this bar
	*/
	@Override
	public String getUuid() {
		return _bar.getUuid();
	}

	@Override
	public int hashCode() {
		return _bar.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _bar.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _bar.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this bar is field2.
	*
	* @return <code>true</code> if this bar is field2; <code>false</code> otherwise
	*/
	@Override
	public boolean isField2() {
		return _bar.isField2();
	}

	@Override
	public boolean isNew() {
		return _bar.isNew();
	}

	@Override
	public void persist() {
		_bar.persist();
	}

	/**
	* Sets the bar ID of this bar.
	*
	* @param barId the bar ID of this bar
	*/
	@Override
	public void setBarId(long barId) {
		_bar.setBarId(barId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_bar.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this bar.
	*
	* @param companyId the company ID of this bar
	*/
	@Override
	public void setCompanyId(long companyId) {
		_bar.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this bar.
	*
	* @param createDate the create date of this bar
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_bar.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_bar.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_bar.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_bar.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field1 of this bar.
	*
	* @param field1 the field1 of this bar
	*/
	@Override
	public void setField1(String field1) {
		_bar.setField1(field1);
	}

	/**
	* Sets whether this bar is field2.
	*
	* @param field2 the field2 of this bar
	*/
	@Override
	public void setField2(boolean field2) {
		_bar.setField2(field2);
	}

	/**
	* Sets the field3 of this bar.
	*
	* @param field3 the field3 of this bar
	*/
	@Override
	public void setField3(int field3) {
		_bar.setField3(field3);
	}

	/**
	* Sets the field4 of this bar.
	*
	* @param field4 the field4 of this bar
	*/
	@Override
	public void setField4(Date field4) {
		_bar.setField4(field4);
	}

	/**
	* Sets the field5 of this bar.
	*
	* @param field5 the field5 of this bar
	*/
	@Override
	public void setField5(String field5) {
		_bar.setField5(field5);
	}

	/**
	* Sets the group ID of this bar.
	*
	* @param groupId the group ID of this bar
	*/
	@Override
	public void setGroupId(long groupId) {
		_bar.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this bar.
	*
	* @param modifiedDate the modified date of this bar
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_bar.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_bar.setNew(n);
	}

	/**
	* Sets the primary key of this bar.
	*
	* @param primaryKey the primary key of this bar
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_bar.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_bar.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this bar.
	*
	* @param userId the user ID of this bar
	*/
	@Override
	public void setUserId(long userId) {
		_bar.setUserId(userId);
	}

	/**
	* Sets the user name of this bar.
	*
	* @param userName the user name of this bar
	*/
	@Override
	public void setUserName(String userName) {
		_bar.setUserName(userName);
	}

	/**
	* Sets the user uuid of this bar.
	*
	* @param userUuid the user uuid of this bar
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_bar.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this bar.
	*
	* @param uuid the uuid of this bar
	*/
	@Override
	public void setUuid(String uuid) {
		_bar.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Bar> toCacheModel() {
		return _bar.toCacheModel();
	}

	@Override
	public Bar toEscapedModel() {
		return new BarWrapper(_bar.toEscapedModel());
	}

	@Override
	public String toString() {
		return _bar.toString();
	}

	@Override
	public Bar toUnescapedModel() {
		return new BarWrapper(_bar.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _bar.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BarWrapper)) {
			return false;
		}

		BarWrapper barWrapper = (BarWrapper)obj;

		if (Objects.equals(_bar, barWrapper._bar)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _bar.getStagedModelType();
	}

	@Override
	public Bar getWrappedModel() {
		return _bar;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _bar.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _bar.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_bar.resetOriginalValues();
	}

	private final Bar _bar;
}