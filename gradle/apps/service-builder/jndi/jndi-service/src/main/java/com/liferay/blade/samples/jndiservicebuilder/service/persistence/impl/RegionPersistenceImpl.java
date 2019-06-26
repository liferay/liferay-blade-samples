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

package com.liferay.blade.samples.jndiservicebuilder.service.persistence.impl;

import com.liferay.blade.samples.jndiservicebuilder.exception.NoSuchRegionException;
import com.liferay.blade.samples.jndiservicebuilder.model.Region;
import com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionImpl;
import com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionModelImpl;
import com.liferay.blade.samples.jndiservicebuilder.service.persistence.RegionPersistence;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence implementation for the region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RegionPersistenceImpl
	extends BasePersistenceImpl<Region> implements RegionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>RegionUtil</code> to access the region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		RegionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public RegionPersistenceImpl() {
		setModelClass(Region.class);

		setModelImplClass(RegionImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(RegionModelImpl.ENTITY_CACHE_ENABLED);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("regionId", "id");
		dbColumnNames.put("regionName", "name");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the region in the entity cache if it is enabled.
	 *
	 * @param region the region
	 */
	@Override
	public void cacheResult(Region region) {
		entityCache.putResult(
			RegionModelImpl.ENTITY_CACHE_ENABLED, RegionImpl.class,
			region.getPrimaryKey(), region);

		region.resetOriginalValues();
	}

	/**
	 * Caches the regions in the entity cache if it is enabled.
	 *
	 * @param regions the regions
	 */
	@Override
	public void cacheResult(List<Region> regions) {
		for (Region region : regions) {
			if (entityCache.getResult(
					RegionModelImpl.ENTITY_CACHE_ENABLED, RegionImpl.class,
					region.getPrimaryKey()) == null) {

				cacheResult(region);
			}
			else {
				region.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all regions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RegionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the region.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Region region) {
		entityCache.removeResult(
			RegionModelImpl.ENTITY_CACHE_ENABLED, RegionImpl.class,
			region.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Region> regions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Region region : regions) {
			entityCache.removeResult(
				RegionModelImpl.ENTITY_CACHE_ENABLED, RegionImpl.class,
				region.getPrimaryKey());
		}
	}

	/**
	 * Creates a new region with the primary key. Does not add the region to the database.
	 *
	 * @param regionId the primary key for the new region
	 * @return the new region
	 */
	@Override
	public Region create(long regionId) {
		Region region = new RegionImpl();

		region.setNew(true);
		region.setPrimaryKey(regionId);

		return region;
	}

	/**
	 * Removes the region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param regionId the primary key of the region
	 * @return the region that was removed
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	@Override
	public Region remove(long regionId) throws NoSuchRegionException {
		return remove((Serializable)regionId);
	}

	/**
	 * Removes the region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the region
	 * @return the region that was removed
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	@Override
	public Region remove(Serializable primaryKey) throws NoSuchRegionException {
		Session session = null;

		try {
			session = openSession();

			Region region = (Region)session.get(RegionImpl.class, primaryKey);

			if (region == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRegionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(region);
		}
		catch (NoSuchRegionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Region removeImpl(Region region) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(region)) {
				region = (Region)session.get(
					RegionImpl.class, region.getPrimaryKeyObj());
			}

			if (region != null) {
				session.delete(region);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (region != null) {
			clearCache(region);
		}

		return region;
	}

	@Override
	public Region updateImpl(Region region) {
		boolean isNew = region.isNew();

		Session session = null;

		try {
			session = openSession();

			if (region.isNew()) {
				session.save(region);

				region.setNew(false);
			}
			else {
				region = (Region)session.merge(region);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			RegionModelImpl.ENTITY_CACHE_ENABLED, RegionImpl.class,
			region.getPrimaryKey(), region, false);

		region.resetOriginalValues();

		return region;
	}

	/**
	 * Returns the region with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the region
	 * @return the region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	@Override
	public Region findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRegionException {

		Region region = fetchByPrimaryKey(primaryKey);

		if (region == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRegionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return region;
	}

	/**
	 * Returns the region with the primary key or throws a <code>NoSuchRegionException</code> if it could not be found.
	 *
	 * @param regionId the primary key of the region
	 * @return the region
	 * @throws NoSuchRegionException if a region with the primary key could not be found
	 */
	@Override
	public Region findByPrimaryKey(long regionId) throws NoSuchRegionException {
		return findByPrimaryKey((Serializable)regionId);
	}

	/**
	 * Returns the region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param regionId the primary key of the region
	 * @return the region, or <code>null</code> if a region with the primary key could not be found
	 */
	@Override
	public Region fetchByPrimaryKey(long regionId) {
		return fetchByPrimaryKey((Serializable)regionId);
	}

	/**
	 * Returns all the regions.
	 *
	 * @return the regions
	 */
	@Override
	public List<Region> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @return the range of regions
	 */
	@Override
	public List<Region> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of regions
	 */
	@Override
	public List<Region> findAll(
		int start, int end, OrderByComparator<Region> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>RegionModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of regions
	 * @param end the upper bound of the range of regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of regions
	 */
	@Override
	public List<Region> findAll(
		int start, int end, OrderByComparator<Region> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Region> list = null;

		if (retrieveFromCache) {
			list = (List<Region>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REGION);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REGION;

				if (pagination) {
					sql = sql.concat(RegionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Region>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Region>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the regions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Region region : findAll()) {
			remove(region);
		}
	}

	/**
	 * Returns the number of regions.
	 *
	 * @return the number of regions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REGION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "id";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_REGION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return RegionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the region persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			RegionModelImpl.ENTITY_CACHE_ENABLED,
			RegionModelImpl.FINDER_CACHE_ENABLED, RegionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			RegionModelImpl.ENTITY_CACHE_ENABLED,
			RegionModelImpl.FINDER_CACHE_ENABLED, RegionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			RegionModelImpl.ENTITY_CACHE_ENABLED,
			RegionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	public void destroy() {
		entityCache.removeCache(RegionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_REGION =
		"SELECT region FROM Region region";

	private static final String _SQL_COUNT_REGION =
		"SELECT COUNT(region) FROM Region region";

	private static final String _ORDER_BY_ENTITY_ALIAS = "region.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Region exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		RegionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"regionId", "regionName"});

}