package ericbraga.bakingapp.environment.common.repositories.local;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileRepository {
    private static final int MAX_READ_BYTES = 4 * 1024;
    private final Context mContext;
    private final File mFile;

    public FileRepository(Context context, String repositoryFile) {
        mContext = context;
        mFile = mContext.getFileStreamPath(repositoryFile);
    }

    public void save(String content) throws IOException {
        FileOutputStream stream = new FileOutputStream(mFile);
        ByteArrayInputStream byteInput = new ByteArrayInputStream(content.getBytes());
        BufferedOutputStream buffer = new BufferedOutputStream(stream);

        byte[] bytesFromContent = new byte[MAX_READ_BYTES];
        int totalRead = 0;

        while ((totalRead = byteInput.read(bytesFromContent)) != -1) {
            buffer.write(bytesFromContent, 0, totalRead);
        }

        byteInput.close();
        buffer.close();
        stream.close();
    }

    public String read() throws IOException {
        if (!mFile.exists()) {
            return "";
        }

        FileInputStream stream = new FileInputStream(mFile);
        BufferedInputStream buffer = new BufferedInputStream(stream);
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

        byte[] bytesFromStream = new byte[MAX_READ_BYTES];
        int totalRead = 0;

        while ((totalRead = buffer.read(bytesFromStream)) != -1) {
            byteOutput.write(bytesFromStream, 0, totalRead);
        }

        byteOutput.close();
        buffer.close();
        stream.close();

        return new String(byteOutput.toByteArray());
    }
}
