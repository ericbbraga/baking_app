package ericbraga.bakingapp.environment.common.repositories.local.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

import ericbraga.bakingapp.environment.common.repositories.local.FileRepository;

public class LocalRepository {
    private static final String REPOSITORY_FILE_NAME = "baking.app.json";
    private final FileRepository mFileRepository;

    public LocalRepository(Context context) {
        mFileRepository = new FileRepository(context, REPOSITORY_FILE_NAME);
    }

    public void saveContent(String content) throws IOException {
        mFileRepository.save(content);
    }

    public String readContent() throws IOException {
        return mFileRepository.read();
    }
}
