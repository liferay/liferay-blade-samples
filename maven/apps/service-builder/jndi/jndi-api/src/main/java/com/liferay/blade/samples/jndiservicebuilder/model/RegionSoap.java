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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RegionSoap implements Serializable {
	public static RegionSoap toSoapModel(Region model) {
		RegionSoap soapModel = new RegionSoap();

		soapModel.setRegionId(model.getRegionId());
		soapModel.setRegionName(model.getRegionName());

		return soapModel;
	}

	public static RegionSoap[] toSoapModels(Region[] models) {
		RegionSoap[] soapModels = new RegionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegionSoap[][] toSoapModels(Region[][] models) {
		RegionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RegionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegionSoap[] toSoapModels(List<Region> models) {
		List<RegionSoap> soapModels = new ArrayList<RegionSoap>(models.size());

		for (Region model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegionSoap[soapModels.size()]);
	}

	public RegionSoap() {
	}

	public long getPrimaryKey() {
		return _regionId;
	}

	public void setPrimaryKey(long pk) {
		setRegionId(pk);
	}

	public long getRegionId() {
		return _regionId;
	}

	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	public String getRegionName() {
		return _regionName;
	}

	public void setRegionName(String regionName) {
		_regionName = regionName;
	}

	private long _regionId;
	private String _regionName;
}