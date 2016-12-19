package cc.metapro.openct.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jeffrey on 12/1/16.
 */

public final class StoreHelper {

    private final static Gson gson = new Gson();

    public static void saveTextFile(Context context, String fileName, String content) throws IOException {
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fos));
        br.write(content);
        br.flush();
        br.close();
        fos.close();
    }

    @NonNull
    public static String getTextFile(Context context, String filename) throws IOException {
        FileInputStream fis = context.openFileInput(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder();
        String tmp = br.readLine();
        while (tmp != null && !"".equals(tmp)) {
            sb.append(tmp);
            tmp = br.readLine();
        }
        br.close();
        fis.close();
        return sb.toString();
    }

    @NonNull
    public static String getAssetText(Context context, String filename) throws IOException {
        InputStream fis = context.getAssets().open(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder();
        String tmp = br.readLine();
        while (tmp != null) {
            sb.append(tmp);
            tmp = br.readLine();
        }
        br.close();
        fis.close();
        return sb.toString();
    }

    public static String getJsonText(Object infos) {
        return gson.toJson(infos);
    }

}