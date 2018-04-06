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

package com.liferay.blade.samples.jdbcservicebuilder.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blade.samples.jdbcservicebuilder.model.Country;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Country in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Country
 * @generated
 */
@ProviderType
public class CountryCacheModel implements CacheModel<Country>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CountryCacheModel)) {
			return false;
		}

		CountryCacheModel countryCacheModel = (CountryCacheModel)obj;

		if (countryId == countryCacheModel.countryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, countryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{countryId=");
		sb.append(countryId);
		sb.append(", countryName=");
		sb.append(countryName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Country toEntityModel() {
		CountryImpl countryImpl = new CountryImpl();

		countryImpl.setCountryId(countryId);

		if (countryName == null) {
			countryImpl.setCountryName("");
		}
		else {
			countryImpl.setCountryName(countryName);
		}

		countryImpl.resetOriginalValues();

		return countryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		countryId = objectInput.readLong();
		countryName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(countryId);

		if (countryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(countryName);
		}
	}

	public long countryId;
	public String countryName;
}