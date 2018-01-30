package file;

import java.io.File;

public class FileRename {

    public static void main(String[] args) {
        String path = "E:\\图片\\Ultimate Material Lollipop Collection";
        File file = new File(path);
        recursionFile(file);
    }

    private static void recursionFile(File file) {
        if (file == null) return;
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return;
        String preName = "20180130_19";
        int num = 1;
        for (File item : files) {
            if (item.isDirectory()) {
                recursionFile(item);
            } else {
                num++;
                String name = item.getName();
                if (name.endsWith(".png") || name.endsWith(".jpg")) {
                    File newFile = new File(preName + num + name.substring(name.length() - 4, name.length()));
                    item.renameTo(newFile);
                }
            }
        }
    }


}
