package com.lex.tang.lib.db;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by tang on 2016/4/3.
 */
public class DBUtils {
    /**
     *
     * 获取数据访问接口的配置，获取后可以通过相应方法设置相应配置
     * @return
     */
    public static final DbManager.DaoConfig mDaoConfig = new DbManager.DaoConfig();
    /**
     * 获取数据库管理对象
     */
    public static final DbManager mDbManager = x.getDb(mDaoConfig);

    public static void replace(Object entity) throws DbException {
        mDbManager.replace(entity);
    }
//切换标签快捷键，Ctrl + PgDn 下一个  / Ctrl + PgUp 上一个

    /*
    删除
     */
    public static void deleteById(Class<?> entityType, Object idValue) throws DbException {
        mDbManager.deleteById(entityType,idValue);
    }

    public static void delete(Class<?> entityType) throws DbException {
        mDbManager.delete(entityType);
    }

    public static void delete(Object entity) throws DbException {
        mDbManager.delete(entity);
    }

    public static int delete(Class<?> entityType, WhereBuilder whereBuilder) throws DbException {
        return mDbManager.delete(entityType,whereBuilder);
    }

    /*
    更新
     */
    public static void update(Object entity, String...updateColumnNames) throws DbException {
        mDbManager.update(entity,updateColumnNames);
    }

    public static int update(Class<?> entityType, WhereBuilder whereBuilder, KeyValue...nameValuePairs) throws DbException {
        return mDbManager.update(entityType,whereBuilder,nameValuePairs);
    }

    /*
    查找
     */
    public static  void findById(Class<?> entityType, Object idValue) throws DbException {
        mDbManager.findById(entityType,idValue);
    }

    public static <T> T findFirst(Class<T> entityType) throws DbException {
        return mDbManager.findFirst(entityType);
    }

    public static <T> List<T> findAll(Class<T> entityType) throws DbException {
        return mDbManager.findAll(entityType);
    }

    public static <T> Selector<T> selector(Class<T> entityType) throws DbException {
        return mDbManager.selector(entityType);
    }

    public static DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException {
        return mDbManager.findDbModelFirst(sqlInfo);
    }

    public static List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException {
        return mDbManager.findDbModelAll(sqlInfo);
    }





}
