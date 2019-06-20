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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

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
public class BarWrapper
	extends BaseModelWrapper<Bar> implements Bar, ModelWrapper<Bar> {

	public BarWrapper(Bar bar) {
		super(bar);
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

	/**
	 * Returns the bar ID of this bar.
	 *
	 * @return the bar ID of this bar
	 */
	@Override
	public long getBarId() {
		return model.getBarId();
	}

	/**
	 * Returns the company ID of this bar.
	 *
	 * @return the company ID of this bar
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this bar.
	 *
	 * @return the create date of this bar
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the field1 of this bar.
	 *
	 * @return the field1 of this bar
	 */
	@Override
	public String getField1() {
		return model.getField1();
	}

	/**
	 * Returns the field2 of this bar.
	 *
	 * @return the field2 of this bar
	 */
	@Override
	public boolean getField2() {
		return model.getField2();
	}

	/**
	 * Returns the field3 of this bar.
	 *
	 * @return the field3 of this bar
	 */
	@Override
	public int getField3() {
		return model.getField3();
	}

	/**
	 * Returns the field4 of this bar.
	 *
	 * @return the field4 of this bar
	 */
	@Override
	public Date getField4() {
		return model.getField4();
	}

	/**
	 * Returns the field5 of this bar.
	 *
	 * @return the field5 of this bar
	 */
	@Override
	public String getField5() {
		return model.getField5();
	}

	/**
	 * Returns the group ID of this bar.
	 *
	 * @return the group ID of this bar
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this bar.
	 *
	 * @return the modified date of this bar
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this bar.
	 *
	 * @return the primary key of this bar
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this bar.
	 *
	 * @return the user ID of this bar
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this bar.
	 *
	 * @return the user name of this bar
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this bar.
	 *
	 * @return the user uuid of this bar
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this bar.
	 *
	 * @return the uuid of this bar
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this bar is field2.
	 *
	 * @return <code>true</code> if this bar is field2; <code>false</code> otherwise
	 */
	@Override
	public boolean isField2() {
		return model.isField2();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the bar ID of this bar.
	 *
	 * @param barId the bar ID of this bar
	 */
	@Override
	public void setBarId(long barId) {
		model.setBarId(barId);
	}

	/**
	 * Sets the company ID of this bar.
	 *
	 * @param companyId the company ID of this bar
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this bar.
	 *
	 * @param createDate the create date of this bar
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the field1 of this bar.
	 *
	 * @param field1 the field1 of this bar
	 */
	@Override
	public void setField1(String field1) {
		model.setField1(field1);
	}

	/**
	 * Sets whether this bar is field2.
	 *
	 * @param field2 the field2 of this bar
	 */
	@Override
	public void setField2(boolean field2) {
		model.setField2(field2);
	}

	/**
	 * Sets the field3 of this bar.
	 *
	 * @param field3 the field3 of this bar
	 */
	@Override
	public void setField3(int field3) {
		model.setField3(field3);
	}

	/**
	 * Sets the field4 of this bar.
	 *
	 * @param field4 the field4 of this bar
	 */
	@Override
	public void setField4(Date field4) {
		model.setField4(field4);
	}

	/**
	 * Sets the field5 of this bar.
	 *
	 * @param field5 the field5 of this bar
	 */
	@Override
	public void setField5(String field5) {
		model.setField5(field5);
	}

	/**
	 * Sets the group ID of this bar.
	 *
	 * @param groupId the group ID of this bar
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this bar.
	 *
	 * @param modifiedDate the modified date of this bar
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this bar.
	 *
	 * @param primaryKey the primary key of this bar
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this bar.
	 *
	 * @param userId the user ID of this bar
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this bar.
	 *
	 * @param userName the user name of this bar
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this bar.
	 *
	 * @param userUuid the user uuid of this bar
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this bar.
	 *
	 * @param uuid the uuid of this bar
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected BarWrapper wrap(Bar bar) {
		return new BarWrapper(bar);
	}

}