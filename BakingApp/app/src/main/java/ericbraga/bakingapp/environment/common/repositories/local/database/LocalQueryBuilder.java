package ericbraga.bakingapp.environment.common.repositories.local.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ericbraga.bakingapp.environment.common.repositories.local.database.exception.LocalQueryBuilderException;

public class LocalQueryBuilder {
    private String mTable;
    private List<String> mColumns;
    private String mWhere;
    private String mOrderBy;


    public LocalQueryBuilder() {
        mTable = null;
        mColumns = new ArrayList<>();
        mWhere = null;
        mOrderBy = null;
    }

    public LocalQueryBuilder select(String column) {
        mColumns.add(column);
        return this;
    }

    public LocalQueryBuilder select(List<String> columns) {
        mColumns.addAll(columns);
        return this;
    }

    public LocalQueryBuilder select(String[] columns) {
        mColumns.addAll( Arrays.asList(columns) );
        return this;
    }

    public LocalQueryBuilder from(String table) {
        mTable = table;
        return this;
    }

    public LocalQueryBuilder where(String selection) {
        mWhere = selection;
        return this;
    }

    public LocalQueryBuilder orderBy(String orderBy) {
        mOrderBy = orderBy;
        return this;
    }

    Cursor build(SQLiteDatabase db) {

        if (mTable == null || mTable.isEmpty()) {
            throw new LocalQueryBuilderException("The table name is null or empty");
        }

        String[] columns = new String[mColumns.size()];
        mColumns.toArray(columns);

        return db.query(mTable, columns, mWhere,null, null, null, mOrderBy);
    }
}
