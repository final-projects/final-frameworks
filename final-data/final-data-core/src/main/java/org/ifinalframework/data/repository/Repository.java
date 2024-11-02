/*
 * Copyright 2020-2021 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ifinalframework.data.repository;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import org.ifinalframework.core.IEntity;
import org.ifinalframework.core.IQuery;
import org.ifinalframework.core.IRepository;
import org.ifinalframework.core.IUpdate;
import org.ifinalframework.core.Pageable;
import org.ifinalframework.core.ParamsBuilder;
import org.ifinalframework.data.query.Update;
import org.ifinalframework.util.Asserts;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The superinterface of {@code CURD}.
 *
 * <ul>
 *     <li>insert the records call with {@link #insert(String, Class, boolean, Collection)};</li>
 *     <li>replace the records when the record had exists call with {@link #replace(String, Class, Collection)};</li>
 *     <li>insert the records when the record not exists and update the record when the record had exists
 *     call with {@link #save(String, Class, Collection)};</li>
 *     <li>update the record call with {@link #update(String, Class, IEntity, IUpdate, boolean, Collection, IQuery)}</li>
 *     <li>select records call with {@link #select(String, Class, Collection, IQuery)}</li>
 *     <li>select ont record call with {@link #selectOne(String, Class, Serializable, IQuery)}</li>
 *     <li>count the records call with {@link #selectCount()};</li>
 *     <li>check the record is exists call with {@link #isExists(String, Serializable)}
 *     or {@link #isExists(String, IQuery)};</li>
 *     <li>truncate table call with {@link #truncate(String)};</li>
 * </ul>
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "unchecked"})
public interface Repository<I extends Serializable, T extends IEntity<I>> extends IRepository<I, T> {

    /*==============================================================================================*/
    /*=========================================== INSERT ===========================================*/
    /*==============================================================================================*/
    default int insert(@NonNull T... entities) {
        return insert(Arrays.asList(entities));
    }

    default int insert(boolean ignore, @NonNull T... entities) {
        return insert(ignore, Arrays.asList(entities));
    }

    default int insert(@NonNull Collection<T> entities) {
        return insert(null, null, false, entities);
    }

    default int insert(boolean ignore, @NonNull Collection<T> entities) {
        return insert(null, null, ignore, entities);
    }

    default int insert(@Nullable String table, @NonNull T... entities) {
        return insert(table, Arrays.asList(entities));
    }

    default int insert(@Nullable String table, boolean ignore, @NonNull T... entities) {
        return insert(table, ignore, Arrays.asList(entities));
    }

    default int insert(@Nullable String table, @NonNull Collection<T> entities) {
        return insert(table, null, false, entities);
    }

    default int insert(@Nullable String table, boolean ignore, @NonNull Collection<T> entities) {
        return insert(table, null, ignore, entities);
    }

    default int insert(@Nullable Class<?> view, @NonNull T... entities) {
        return insert(view, Arrays.asList(entities));
    }

    default int insert(@Nullable Class<?> view, boolean ignore, @NonNull T... entities) {
        return insert(view, ignore, Arrays.asList(entities));
    }

    default int insert(@Nullable Class<?> view, @NonNull Collection<T> entities) {
        return insert(null, view, false, entities);
    }

    default int insert(@Nullable Class<?> view, boolean ignore, @NonNull Collection<T> entities) {
        return insert(null, view, ignore, entities);
    }

    default int insert(@Nullable String table, @Nullable Class<?> view, @NonNull T... entities) {
        return insert(table, view, false, Arrays.asList(entities));
    }

    default int insert(@Nullable String table, @Nullable Class<?> view, boolean ignore, @NonNull T... entities) {
        return insert(table, view, ignore, Arrays.asList(entities));
    }

    /**
     * 批量插入数据并返回影响的行数
     *
     * @param table    表名
     * @param view     视图,
     * @param ignore   是否忽略重复数据,{@literal INSERT IGNORE}
     * @param entities 实体集
     * @return 指插入数据所影响的行数
     */
    default int insert(@Nullable String table, @Nullable Class<?> view, boolean ignore, @NonNull Collection<T> entities) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return insert(builder
                .table(table)
                .view(view)
                .ignore(ignore)
                .list(entities)
                .build()
        );
    }

    /*==============================================================================================*/
    /*=========================================== REPLACE ==========================================*/
    /*==============================================================================================*/

    default int replace(@NonNull T... entities) {
        return replace(Arrays.asList(entities));
    }

    default int replace(@NonNull Collection<T> entities) {
        return replace(null, null, entities);
    }

    default int replace(@Nullable String table, @NonNull T... entities) {
        return replace(table, Arrays.asList(entities));
    }

    default int replace(@Nullable String table, @NonNull Collection<T> entities) {
        return replace(table, null, entities);
    }

    default int replace(@Nullable Class<?> view, @NonNull T... entities) {
        return replace(view, Arrays.asList(entities));
    }

    default int replace(@Nullable Class<?> view, @NonNull Collection<T> entities) {
        return replace(null, view, entities);
    }

    default int replace(@Nullable String table, @Nullable Class<?> view, @NonNull T... entities) {
        return replace(table, view, Arrays.asList(entities));
    }

    /**
     * 批量插入数据并返回影响的行数
     *
     * @param table    表名
     * @param view     视图
     * @param entities 实体集
     * @return 指插入数据所影响的行数
     */
    default int replace(@Nullable String table, @Nullable Class<?> view, @NonNull Collection<T> entities) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return replace(builder.table(table).view(view).list(entities).build());
    }

    /*==============================================================================================*/
    /*=========================================== SAVE =============================================*/
    /*==============================================================================================*/
    default int save(@NonNull T... entities) {
        return save(Arrays.asList(entities));
    }

    default int save(@NonNull Collection<T> entities) {
        return save(null, null, entities);
    }

    default int save(@Nullable String table, @NonNull T... entities) {
        return save(table, Arrays.asList(entities));
    }

    default int save(@Nullable String table, @NonNull Collection<T> entities) {
        return save(table, null, entities);
    }

    default int save(@Nullable Class<?> view, @NonNull T... entities) {
        return save(view, Arrays.asList(entities));
    }

    default int save(@Nullable Class<?> view, @NonNull Collection<T> entities) {
        return save(null, view, entities);
    }

    default int save(@Nullable String table, @Nullable Class<?> view, @NonNull T... entities) {
        return save(table, view, Arrays.asList(entities));
    }

    /**
     * 批量插入数据并返回影响的行数
     *
     * @param table    表名
     * @param view     视图
     * @param entities 实体集
     * @return 指插入数据所影响的行数
     */
    default int save(@Nullable String table, @Nullable Class<?> view, @NonNull Collection<T> entities) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return save(builder.table(table).view(view).list(entities).build());
    }

    /*==============================================================================================*/
    /*=========================================== UPDATE ===========================================*/
    /*==============================================================================================*/

    default int update(@NonNull T entity) {
        return update((String) null, entity);
    }

    default int update(@Nullable String table, @NonNull T entity) {
        return update(table, null, entity);
    }

    default int update(@Nullable Class<?> view, @NonNull T entity) {
        return update(null, view, entity);
    }

    default int update(@NonNull T entity, boolean selective) {
        return update((String) null, entity, selective);
    }

    default int update(@NonNull T entity, @NonNull I... ids) {
        return update(entity, Arrays.asList(ids));
    }

    default int update(@NonNull T entity, @NonNull Collection<I> ids) {
        return update((String) null, entity, ids);
    }

    default int update(@NonNull T entity, @NonNull IQuery query) {
        return update((String) null, entity, query);
    }

    default int update(T entity, boolean selective, I... ids) {
        return update(entity, selective, Arrays.asList(ids));
    }

    default int update(T entity, boolean selective, Collection<I> ids) {
        return update(null, null, entity, selective, ids);
    }

    default int update(T entity, boolean selective, IQuery query) {
        return update(null, null, entity, selective, query);
    }

    default int update(@Nullable String table, Class<?> view, T entity) {
        return update(table, view, entity, true);
    }

    default int update(@Nullable String table, T entity, boolean selective) {
        return update(table, null, entity, selective);
    }

    /**
     * @since 1.2.1
     */
    default int update(@Nullable String table, T entity, boolean selective, IQuery query) {
        return update(table, null, entity, selective, query);
    }

    default int update(@Nullable Class<?> view, T entity, boolean selective) {
        return update(null, view, entity, selective);
    }

    default int update(@Nullable Class<?> view, T entity, I... ids) {
        return update(view, entity, Arrays.asList(ids));
    }

    default int update(@Nullable Class<?> view, T entity, Collection<I> ids) {
        return update(null, view, entity, ids);
    }

    default int update(@Nullable Class<?> view, T entity, IQuery query) {
        return update(null, view, entity, query);
    }

    default int update(@Nullable String table, T entity, I... ids) {
        return update(table, entity, Arrays.asList(ids));
    }

    default int update(@Nullable String table, T entity, Collection<I> ids) {
        return update(table, null, entity, true, ids);
    }

    default int update(@Nullable String table, T entity, IQuery query) {
        return update(table, null, entity, query);
    }

    default int update(@Nullable String table, Class<?> view, T entity, I... ids) {
        return update(table, view, entity, Arrays.asList(ids));
    }

    default int update(@Nullable String table, Class<?> view, T entity, Collection<I> ids) {
        return update(table, view, entity, null, true, ids, null);
    }

    default int update(@Nullable String table, Class<?> view, T entity, IQuery query) {
        return update(table, view, entity, null, true, null, query);
    }

    default int update(@Nullable String table, Class<?> view, T entity, boolean selective) {
        return update(table, view, entity, selective, Objects.requireNonNull(entity.getId()));
    }

    default int update(@Nullable String table, @Nullable Class<?> view, @NonNull T entity, boolean selective,
                       @NonNull I... ids) {
        return update(table, view, entity, selective, Arrays.asList(ids));
    }

    default int update(@Nullable String table, @Nullable Class<?> view, @NonNull T entity, boolean selective,
                       @Nullable Collection<I> ids) {
        return update(table, view, entity, null, selective, ids, null);
    }

    default int update(@Nullable String table, @Nullable Class<?> view, @NonNull T entity, boolean selective,
                       @Nullable IQuery query) {
        return update(table, view, entity, null, selective, null, query);
    }

    //---- update entities

    default int update(T... entities) {
        return update(Arrays.asList(entities));
    }

    default int update(@NonNull Collection<T> entities) {
        return entities.stream()
                .mapToInt(this::update)
                .sum();
    }

    default int update(String table, T... entities) {
        return update(table, Arrays.asList(entities));
    }

    default int update(String table, Collection<T> entities) {
        return update(table, null, entities);
    }

    default int update(Class<?> view, T... entities) {
        return update(view, Arrays.asList(entities));
    }

    default int update(Class<?> view, Collection<T> entities) {
        return update(null, view, entities);
    }

    default int update(boolean selective, T... entities) {
        return update(Arrays.asList(entities), selective);
    }

    default int update(@NonNull Collection<T> entities, boolean selective) {
        return entities.stream()
                .mapToInt(it -> update(it, selective))
                .sum();
    }

    default int update(String table, Class<?> view, Collection<T> entities) {
        return update(table, view, entities, true);
    }

    default int update(String table, Collection<T> entities, boolean selective) {
        return update(table, null, entities, selective);
    }

    default int update(String table, Class<?> view, Collection<T> entities, boolean selective) {
        return entities.stream()
                .mapToInt(it -> update(table, view, it, selective))
                .sum();
    }

    // -----------------Update---------

    default int update(Update update, I... ids) {
        return update(update, Arrays.asList(ids));
    }

    default int update(Update update, Collection<I> ids) {
        return update(null, null, null, update, false, ids, null);
    }

    default int update(String table, IUpdate update, I... ids) {
        return update(table, update, Arrays.asList(ids));
    }

    default int update(String table, IUpdate update, Collection<I> ids) {
        return update(table, null, null, update, false, ids, null);
    }

    default int update(IUpdate update, IQuery query) {
        return update(null, update, query);
    }

    default int update(String table, IUpdate update, IQuery query) {
        return update(table, null, null, update, false, null, query);
    }

    /**
     * 更新数据并返回影响的行数
     *
     * @param table     表名
     * @param view      视图
     * @param entity    实体，值不为 {@code null}时，忽略 {@code update} 的值
     * @param update    更新，仅当 {@code entity}为空时有效
     * @param selective 有选择的，值为{@code true}时，不更新值为 {@code null}的属性。
     * @param ids       要更新的IDS
     * @param query     更新条件
     * @return 更新数据后影响的行数
     */
    default int update(String table, Class<?> view,
                       T entity, IUpdate update, boolean selective,
                       Collection<I> ids, IQuery query) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return update(builder.table(table).view(view).update(entity).update(update).selective(selective).ids(ids).query(query).build());
    }

    /*==============================================================================================*/
    /*=========================================== DELETE ===========================================*/
    /*==============================================================================================*/

    default int delete(@NonNull T... entities) {
        return delete(Arrays.asList(entities));
    }

    default int delete(@Nullable String table, @NonNull T... entities) {
        return delete(table, Arrays.asList(entities));
    }

    default int delete(@NonNull List<T> entities) {
        return delete(entities.stream().map(IEntity::getId).collect(Collectors.toList()));
    }

    default int delete(@Nullable String table, @NonNull List<T> entities) {
        return delete(table, entities.stream().map(IEntity::getId).collect(Collectors.toList()));
    }

    default int delete(@NonNull I... ids) {
        return delete(Arrays.asList(ids));
    }

    default int delete(@Nullable String table, @NonNull I... ids) {
        return delete(table, Arrays.asList(ids));
    }

    default int delete(@NonNull Collection<I> ids) {
        return delete(null, ids);
    }

    default int delete(@Nullable String table, @NonNull Collection<I> ids) {
        return delete(table, ids, null);
    }

    default int delete(@NonNull IQuery query) {
        return delete(null, null, query);
    }

    default int delete(@Nullable String table, @NonNull IQuery query) {
        return delete(table, null, query);
    }

    /**
     * 删除符合条件的数据并返回影响的行数
     *
     * @param table 表名
     * @param ids   IDS
     * @param query 条件
     * @return 删除符合条件的数据所影响的行数
     */
    default int delete(@Nullable String table, @Nullable Collection<I> ids, @Nullable IQuery query) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return delete(builder.table(table).ids(ids).query(query).build());
    }

    default <P> void delete(IQuery query, Listener<P, Integer> listener) {
        this.delete(null, query, listener);
    }

    default <P> void delete(String table, @NonNull IQuery query, Listener<P, Integer> listener) {
        Asserts.requiredNonNull(listener, "listener is null");
        int offset = 0;
        P param = listener.onInit();
        listener.onStart(param);
        int rows;
        do {
            rows = delete(table, query);
            if (!listener.onListening(offset, param, rows)) {
                break;
            }
        } while (rows > 0);
        listener.onFinish(param);
    }

    /*==============================================================================================*/
    /*=========================================== SELECT ===========================================*/
    /*==============================================================================================*/

    default List<T> select() {
        return select((IQuery) null);
    }

    default List<T> select(@Nullable String table) {
        return select(table, (IQuery) null);
    }

    default List<T> select(@Nullable Class<?> view) {
        return select(view, (IQuery) null);
    }

    default List<T> select(@Nullable String table, @Nullable Class<?> view) {
        return select(table, view, (IQuery) null);
    }

    default List<T> select(@NonNull I... ids) {
        return select(Arrays.asList(ids));
    }

    default List<T> select(@Nullable String table, I... ids) {
        return select(table, Arrays.asList(ids));
    }

    default List<T> select(@Nullable Class<?> view, I... ids) {
        return select(view, Arrays.asList(ids));
    }

    default List<T> select(@Nullable String table, Class<?> view, I... ids) {
        return select(table, view, Arrays.asList(ids));
    }

    default List<T> select(@NonNull Collection<I> ids) {
        return select(null, null, ids);
    }

    default List<T> select(@Nullable String table, @NonNull Collection<I> ids) {
        return select(table, null, ids, null);
    }

    default List<T> select(@Nullable Class<?> view, @NonNull Collection<I> ids) {
        return select(null, view, ids);
    }

    default List<T> select(@Nullable String table, @Nullable Class<?> view, @NonNull Collection<I> ids) {
        return select(table, view, ids, null);
    }

    default List<T> select(@Nullable IQuery query) {
        return select(null, null, query);
    }

    default List<T> select(@Nullable String table, @Nullable IQuery query) {
        return select(table, null, null, query);
    }

    default List<T> select(@Nullable Class<?> view, @Nullable IQuery query) {
        return select(null, view, query);
    }

    default List<T> select(@Nullable String table, @Nullable Class<?> view, @Nullable IQuery query) {
        return select(table, view, null, query);
    }

    /**
     * 根据 {@link I} 集合或 {@link IQuery} 查询
     *
     * @param table 表名
     * @param view  视图
     * @param ids   要查询的IDS
     * @param query 查询条件
     * @return list
     */
    default List<T> select(@Nullable String table, @Nullable Class<?> view, @Nullable Collection<I> ids,
                           @Nullable IQuery query) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return select(builder.table(table).view(view).ids(ids).query(query).build());
    }

    /*==============================================================================================*/
    /*========================================= SELECT ONE =========================================*/
    /*==============================================================================================*/

    default T selectOne(@NonNull I id) {
        return selectOne(null, null, id, null);
    }

    default T selectOne(@Nullable String table, @NonNull I id) {
        return selectOne(table, null, id, null);
    }

    default T selectOne(@Nullable Class<?> view, @NonNull I id) {
        return selectOne(null, view, id, null);
    }

    default T selectOne(@Nullable String table, @Nullable Class<?> view, @NonNull I id) {
        return selectOne(table, view, id, null);
    }

    default T selectOne(@NonNull IQuery query) {
        return selectOne(null, null, query);
    }

    default T selectOne(@Nullable String table, @NonNull IQuery query) {
        return selectOne(table, null, query);
    }

    default T selectOne(@Nullable Class<?> view, @NonNull IQuery query) {
        return selectOne(null, view, null, query);
    }

    default T selectOne(@Nullable String table, @Nullable Class<?> view, @NonNull IQuery query) {
        return selectOne(table, view, null, query);
    }

    /**
     * 返回符合查询 {@link I} 或 {@link IQuery} 的一个结果，当找不到时返回 {@code null}
     *
     * @param table 表名
     * @param view  视图
     * @param id    I
     * @param query query
     * @return 符合查询 {@link I} 或 {@link IQuery} 的一个结果
     */
    default T selectOne(@Nullable String table, @Nullable Class<?> view, @Nullable I id, @Nullable IQuery query) {
        return selectOne(ParamsBuilder.builder().table(table).view(view).id(id).query(query).build());
    }

    /*================================================================================================================*/
    /*==================================================== SCANNER ===================================================*/
    /*================================================================================================================*/

    default <P> void scan(@NonNull Pageable query, @NonNull Listener<P, List<T>> listener) {
        scan(null, null, query, listener);
    }

    default <P> void scan(@Nullable Class<?> view, @NonNull Pageable query, @NonNull Listener<P, List<T>> listener) {
        scan(null, view, query, listener);
    }

    default <P> void scan(@Nullable String table, @NonNull Pageable query, @NonNull Listener<P, List<T>> listener) {
        scan(table, null, query, listener);
    }

    default <P> void scan(@Nullable String table, @Nullable Class<?> view, @NonNull Pageable query,
                          @NonNull Listener<P, List<T>> listener) {
        if (Asserts.isNull(query.getPage()) || Asserts.isNull(query.getSize())) {
            throw new IllegalArgumentException("query page or size is null");
        }
        Asserts.requiredNonNull(listener, "listener is null");
        int index = query.getPage();
        int offset = 0;
        P param = listener.onInit();
        listener.onStart(param);

        List<T> list;

        do {
            query.setPage(index + offset);
            list = select(table, view, query);
            offset++;
            if (!listener.onListening(offset, param, list)) {
                break;
            }
        } while (Asserts.isEmpty(list) || list.size() < query.getSize());
    }

    /*==============================================================================================*/
    /*========================================= SELECT IDS =========================================*/
    /*==============================================================================================*/

    default List<I> selectIds(@NonNull IQuery query) {
        return selectIds(null, query);
    }

    /**
     * 返回符合查询条件 {@link IQuery} 的主键集合 {@link I}
     *
     * @param table 表名
     * @param query query
     * @return 符合查询条件 {@link IQuery} 的主键集合 {@link I}
     */
    default List<I> selectIds(@Nullable String table, @NonNull IQuery query) {
        return selectIds(ParamsBuilder.builder().table(table).query(query).build());
    }

    /*==============================================================================================*/
    /*======================================== SELECT COUNT ========================================*/
    /*==============================================================================================*/

    default long selectCount() {
        return selectCount((String) null);
    }

    default long selectCount(@Nullable String table) {
        return selectCount(table, null, null);
    }

    default long selectCount(@Nullable IQuery query) {
        return selectCount(null, null, query);
    }

    default long selectCount(@Nullable String table, @NonNull IQuery query) {
        return selectCount(table, null, query);
    }

    default long selectCount(@NonNull I... ids) {
        return selectCount(Arrays.asList(ids));
    }

    default long selectCount(@NonNull Collection<I> ids) {
        return selectCount(null, ids);
    }

    default long selectCount(@Nullable String table, @NonNull I... ids) {
        return selectCount(table, Arrays.asList(ids));
    }

    default long selectCount(@Nullable String table, @NonNull Collection<I> ids) {
        return selectCount(table, ids, null);
    }

    /**
     * 返回符合查询条件 {@link IQuery}的结果集的大小
     *
     * @param table 表名
     * @param ids   ids
     * @param query query
     * @return 符合查询条件 {@link IQuery}的结果集的大小
     */
    default long selectCount(@Nullable String table, @Nullable Collection<I> ids, @Nullable IQuery query) {
        final ParamsBuilder<I, T> builder = ParamsBuilder.builder();
        return selectCount(builder.table(table).ids(ids).query(query).build());
    }

    /*================================================================================================================*/
    /*=================================================== IS EXISTS ==================================================*/
    /*================================================================================================================*/
    default boolean isExists(@NonNull I id) {
        return isExists(null, id);
    }

    default boolean isExists(@Nullable String table, @NonNull I id) {
        return selectOne(table, id) != null;
    }

    default boolean isExists(@NonNull IQuery query) {
        return isExists(null, query);
    }

    default boolean isExists(@Nullable String table, @NonNull IQuery query) {
        return selectOne(table, query) != null;
    }

    /*================================================================================================================*/
    /*================================================== TRUNCATE ====================================================*/
    /*================================================================================================================*/

    default void truncate() {
        truncate((String) null);
    }

    /**
     * 清空表数据
     *
     * @param table 表名
     */
    default void truncate(@Nullable String table) {
        truncate(ParamsBuilder.builder().table(table).build());
    }

}
