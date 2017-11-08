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

package com.liferay.blade.samples.jndiservicebuilder.service.impl;

import com.liferay.blade.samples.jndiservicebuilder.service.base.RegionLocalServiceBaseImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The implementation of the region local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionLocalServiceBaseImpl
 * @see com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalServiceUtil
 */
public class RegionLocalServiceImpl extends RegionLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalServiceUtil} to access the region local service.
	 */
	public void useJNDI() {
		try {
			DataSource datasource = regionPersistence.getDataSource();

			Connection connection = datasource.getConnection();

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(
				"select id, name from region");

			while (resultSet.next()) {
				_logger.info("Record from external database:");
				String id = resultSet.getString("id");

				_logger.info("ID: " + id);

				String name = resultSet.getString("name");

				_logger.info("Name: " + name + System.lineSeparator());
			}

			connection.close();
		}
		catch (SQLException sqle) {
			_logger.error("Failed to retrieve data from external database!");

			sqle.printStackTrace();
		}
	}

	private Logger _logger = LoggerFactory.getLogger(getClass().getName());

}