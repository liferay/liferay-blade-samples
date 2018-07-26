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

package com.liferay.blade.samples.jdbcservicebuilder.service.test;

import aQute.remote.util.JMXBundleDeployer;

import com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalServiceUtil;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.persist.HsqlProperties;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Liferay
 */
@RunWith(Arquillian.class)
public class ConnectionTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		_dbManager = new DBManager();

		_dbManager.startUp();

		_addData();

		final File jarFile = new File(System.getProperty("jarFile"));

		final File apiJar = new File(System.getProperty("jdbcApiJarFile"));

		new JMXBundleDeployer().deploy(_jdbcServiceBuilderApiJarBSN, apiJar);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@AfterClass
	public static void shutdown() throws Exception {
		_dbManager.shutDown();
	}

	@Test
	public void testCountryService() throws Exception {
		Assert.assertTrue(CountryLocalServiceUtil.getCountriesCount() > 0);
	}

	private static void _addData() throws Exception {
		_executeNonQuery(
			"create table country(id bigint not null primary key, name varchar(255));");
		_executeNonQuery(
			"insert into country(id, name) values(1, 'Australia');");
	}

	private static void _executeNonQuery(String nonQuery) throws Exception {
		Statement stmt = null;

		try (Connection conn = _get()) {
			stmt = conn.createStatement();

			stmt.executeUpdate(nonQuery);
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				}
				catch (SQLException sqle) {
				}
			}
		}
	}

	private static Connection _get() throws Exception {
		Connection dbConn = null;

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			dbConn = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost:54322/xdb", "SA", "");
		}
		catch (Exception e) {
			throw e;
		}

		return dbConn;
	}

	private static DBManager _dbManager = null;
	private static String _jdbcServiceBuilderApiJarBSN =
		"com.liferay.blade.jdbcservicebuilder.api";

	private static class DBManager {

		public void shutDown() {
			try {
				_sonicServer.shutdown();
			}
			catch (Exception e) {
			}
		}

		public void startUp() throws Exception {
			HsqlProperties props = new HsqlProperties();

			props.setProperty("server.database.0", "mem:testdb;");
			props.setProperty("server.dbname.0", "xdb");
			props.setProperty("server.port", "54322");

			_sonicServer = new org.hsqldb.Server();

			try {
				_sonicServer.setProperties(props);
			}
			catch (Exception e) {
				return;
			}

			_sonicServer.start();
		}

		private org.hsqldb.server.Server _sonicServer;

	}

}