package ericbraga.bakingapp.environment.common.repositories.local;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(AndroidJUnit4.class)
public class FileReadRepositoryTest {

    private static final int MAX_READ_BYTES = 4 * 1024;

    @Test
    public void check_SimpleContents_Was_Written_Into_File() {
        Context context = InstrumentationRegistry.getTargetContext();
        String content = "My Content which should be saved on file";
        FileRepository fileRepository = new FileRepository(context, "mycontentfile");
        try {
            fileRepository.save(content);
        } catch (IOException e) {
            Assert.fail("The content should be saved");
        }

        String contentRead = null;

        try {
            contentRead = fileRepository.read();
        } catch (IOException e) {
            Assert.fail();
        }

        Assert.assertNotNull(contentRead);
        Assert.assertFalse(contentRead.isEmpty());
        Assert.assertEquals(content, contentRead);
    }

    @Test
    public void check_FullJsonFile_Was_Written_Into_File() {
        String json = null;
        try {
            json = getLinesFromJson();
        } catch (IOException e) {
            Assert.fail("Error while read the json file:" + e.getMessage());
        }

        FileRepository fileRepository = null;
        try {
            fileRepository = saveJsonOnFile(json, "full.json");
        } catch (IOException e) {
            Assert.fail("The content should be saved");
        }

        String contentRead = null;

        try {
            contentRead = fileRepository.read();
        } catch (IOException e) {
            Assert.fail();
        }

        Assert.assertNotNull(contentRead);
        Assert.assertFalse(contentRead.isEmpty());
        Assert.assertEquals(json, contentRead);
    }

    @NonNull
    private FileRepository saveJsonOnFile(String json, String filename) throws IOException {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        FileRepository fileRepository = new FileRepository(targetContext, filename);
        fileRepository.save(json);
        return fileRepository;
    }

    private String getLinesFromJson() throws IOException {
        Context testContext = InstrumentationRegistry.getInstrumentation().getContext();
        return getLinesFromValidJson(testContext);
    }

    private String getLinesFromValidJson(Context context) throws IOException {

        AssetManager assets = context.getAssets();
        InputStream stream = assets.open("valid_content.json");

        BufferedInputStream buffer = new BufferedInputStream(stream);
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

        byte[] bytesFromStream = new byte[MAX_READ_BYTES];
        int totalRead = 0;

        while ((totalRead = buffer.read(bytesFromStream)) != -1) {
            byteOutput.write(bytesFromStream, 0, totalRead);
        }

        String content = new String(byteOutput.toByteArray());

        byteOutput.close();
        buffer.close();
        stream.close();

        return content;
    }

}