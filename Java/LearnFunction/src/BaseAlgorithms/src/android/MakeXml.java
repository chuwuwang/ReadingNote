package android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeXml {

    private final static String rootPath = "C:\\Users\\Lee64\\Desktop\\layout\\values-{0}x{1}\\";

    private final static float dw = 320f; // 320:1单位长度=1px 640:1单位长度=2px
    private final static float dh = 480f;

    private final static String WTemplate = "   <dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "   <dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {
        makeString(320, 480);
        makeString(480, 800);
        makeString(480, 854);
        makeString(540, 960);
        makeString(600, 1024);
        makeString(720, 1184);
        makeString(720, 1196);
        makeString(720, 1280);
        makeString(768, 1024);
        makeString(800, 1280);
        makeString(1080, 1812);
        makeString(1080, 1920);
        makeString(1440, 2560);
    }

    private static void makeString(int w, int h) {
        StringBuilder sbW = new StringBuilder();
        sbW.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbW.append("<resources>\n");

        float cellW = w / dw;
        for (int i = 1; i < 720; i++) {
            String resources = WTemplate.replace("{0}", i + "").replace("{1}", change(cellW * i) + "");
            sbW.append(resources);
        }
        String resources = WTemplate.replace("{0}", "320").replace("{1}", w + "");
        sbW.append(resources);

        sbW.append("</resources>\n");

        StringBuilder sbH = new StringBuilder();
        sbH.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbH.append("<resources>\n");

        float cellH = h / dh;
        for (int i = 1; i < 1280; i++) {
            resources = HTemplate.replace("{0}", i + "").replace("{1}", change(cellH * i) + "");
            sbH.append(resources);
        }
        resources = HTemplate.replace("{0}", "480").replace("{1}", h + "");
        sbH.append(resources);

        sbH.append("</resources>\n");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        rootFile.mkdirs();

        File layoutXFile = new File(path + "layout_x.xml");
        File layoutYFile = new File(path + "layout_y.xml");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(layoutXFile));
            pw.print(sbW.toString());
            pw.close();

            pw = new PrintWriter(new FileOutputStream(layoutYFile));
            pw.print(sbH.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    private static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }


}
