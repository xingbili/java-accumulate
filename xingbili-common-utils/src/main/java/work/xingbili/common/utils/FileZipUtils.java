package work.xingbili.common.utils;

import org.apache.tools.zip.ZipFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileZipUtils {
    /**
     * zip文件压缩
     *
     * @param inputFile  待压缩文件夹/文件名
     * @param outputFile 生成的压缩包名字
     */
    public static void ZipCompress(String inputFile, String outputFile) throws Exception {
        // 创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        File input = new File(inputFile);
        compress(out, input, null);
        out.close();
    }

    /**
     * @param name 压缩文件名，可以写为null保持默认
     */
    // 递归压缩
    public static void compress(ZipOutputStream out, File input, String name)
            throws IOException {
        if (name == null) {
            name = input.getName();
        }
        // 如果路径为目录（文件夹）
        if (input.isDirectory()) {
            // 取出文件夹中的文件（或子文件夹）
            File[] flist = input.listFiles();

            if (flist.length == 0)// 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
            {
                out.putNextEntry(new ZipEntry(name + "/"));
            } else// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (int i = 0; i < flist.length; i++) {
                    compress(out, flist[i], name + "/" + flist[i].getName());
                }
            }
        } else {// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len = -1;
            // 将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            bis.close();
            fos.close();
        }
    }

    /**
     * zip解压
     *
     * @param inputFile   待解压文件名
     * @param destDirPath 解压路径
     */
    public static void ZipUncompress(String inputFile, String destDirPath) throws Exception {
        File srcFile = new File(inputFile);// 获取当前压缩文件
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new Exception(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        // 构建解压输入流
        ZipInputStream zIn = new ZipInputStream(new FileInputStream(srcFile));
        ZipEntry entry = null;
        File file = null;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();// 创建此文件的上级目录
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
        zIn.close();
    }

    /**
     * 将zip文件字节数组按照指定编码解压
     *
     * @param fileBytes
     * @param destDirPath
     * @param charset
     * @throws Exception
     */
    public static void ZipUncompress(byte[] fileBytes, String destDirPath, Charset charset)
            throws Exception {

        // 开始解压
        // 构建解压输入流
        ZipInputStream zIn = new ZipInputStream(new ByteArrayInputStream(fileBytes), charset);
        ZipEntry entry = null;
        File file = null;
        while ((entry = zIn.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                file = new File(destDirPath, entry.getName());
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();// 创建此文件的上级目录
                }
                OutputStream out = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(out);
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = zIn.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                // 关流顺序，先打开的后关闭
                bos.close();
                out.close();
            }
        }
        zIn.close();
    }
    /**
     * 替换某个 item
     *
     * @author  Aning
     * @param sourceFile: 源文件
     * @param targetFile: 目标文件
     * @param itemName: 要替换的 item 名称
     * @param itemFile:要替换的 item
     * @date  2021/7/20 10:42
     *
     **/
    @SuppressWarnings("rawtypes")
    public static void replaceItem(File sourceFile, File targetFile, String itemName, File itemFile)
            throws IOException {
        ZipFile sourceZip = new ZipFile(sourceFile);
        org.apache.tools.zip.ZipOutputStream zipOutputStream = new org.apache.tools.zip.ZipOutputStream(new FileOutputStream(targetFile));
        Enumeration e = sourceZip.getEntries();
        while (e.hasMoreElements()) {
            org.apache.tools.zip.ZipEntry entryIn = (org.apache.tools.zip.ZipEntry) e.nextElement();
            String entryName = entryIn.getName();
            org.apache.tools.zip.ZipEntry entryOut = new org.apache.tools.zip.ZipEntry(entryName);
            // 只使用 name
            zipOutputStream.putNextEntry(entryOut);
            // 缓冲区
            byte[] buf = new byte[1024];
            int len;

            if (entryName.equals(itemName)) {
                InputStream itemInputStream = new FileInputStream(itemFile);
                // 使用替换流
                while ((len = (itemInputStream.read(buf))) > 0) {
                    zipOutputStream.write(buf, 0, len);
                }
                close(itemInputStream);
            } else {
                InputStream in = sourceZip.getInputStream(entryIn);
                // 输出普通Zip流
                while ((len = (in.read(buf))) > 0) {
                    zipOutputStream.write(buf, 0, len);
                }
                close(in);
            }
            // 关闭此 entry
            zipOutputStream.closeEntry();

        }
        close(zipOutputStream);
        sourceZip.close();
    }

    private static void close(InputStream inputStream) {
        if (null != inputStream) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(OutputStream outputStream) {
        if (null != outputStream) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压
     *
     * @author  Aning
     * @param sourcecFile: 源文件
     * @param   targetDirPath: 解压目标路径
     * @return null
     * @date  2021/7/20 10:44
     *
     **/
    @SuppressWarnings("rawtypes")
    public static void unzip(File sourcecFile, String targetDirPath) throws IOException {
        ZipFile zipFile = new ZipFile(sourcecFile);
        Enumeration e = zipFile.getEntries();
        while (e.hasMoreElements()) {
            org.apache.tools.zip.ZipEntry zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
            File file = new File(targetDirPath + File.separator + zipEntry.getName());
            if (zipEntry.isDirectory()) {
                file.mkdirs();
                continue;
            } else {
                if (!file.getParentFile().exists() || !file.getParentFile().isDirectory()) {
                    file.getParentFile().mkdirs();
                }
            }

            InputStream in = zipFile.getInputStream(zipEntry);
            FileOutputStream out = new FileOutputStream(file);

            byte[] buf = new byte[1024];
            int readedBytes;
            while ((readedBytes = in.read(buf)) != -1) {
                out.write(buf, 0, readedBytes);
            }
            close(in);
            close(out);
        }
        zipFile.close();
    }

    /**
     * 压缩文件夹内的文件
     *
     * @author  Aning
     * @param zipDirectory: zip路径
     * @param   zipFileName：zip包名称
     * @return null
     * @date  2021/7/20 10:44
     *
     **/
    public static void zip(String zipDirectory, String zipFileName) throws IOException {
        // zipDirectoryPath:需要压缩的文件夹名
        File zipDir = new File(zipDirectory);
        if (StringUtils.isEmpty(zipFileName)) {
            zipFileName = zipDir.getName() + ".zip";
        }
        org.apache.tools.zip.ZipOutputStream zipOut =
                new org.apache.tools.zip.ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
        handleDir(zipDir, zipOut);
        close(zipOut);
    }

    /**
     * zip转docx
     *
     * @author  Aning
     * @param zipPath: zip文件路径
     * @param   docxPath：docx文件路径
     * @return null
     * @date  2021/7/20 10:45
     *
     **/
    public static void zip2Docx(String zipPath, String docxPath) throws IOException {

        ZipFile sourceZip = new ZipFile(zipPath);
        File docxFile = new File(docxPath);
        docxFile.getParentFile().mkdirs();
        org.apache.tools.zip.ZipOutputStream zipOutputStream = new org.apache.tools.zip.ZipOutputStream(new FileOutputStream(docxFile));
        Enumeration e = sourceZip.getEntries();
        while (e.hasMoreElements()) {
            org.apache.tools.zip.ZipEntry entryIn = (org.apache.tools.zip.ZipEntry) e.nextElement();
            String entryName = entryIn.getName();
            org.apache.tools.zip.ZipEntry entryOut = new org.apache.tools.zip.ZipEntry(entryName);
            // 只使用 name
            zipOutputStream.putNextEntry(entryOut);
            // 缓冲区
            byte[] buf = new byte[1024];
            int len;

            InputStream in = sourceZip.getInputStream(entryIn);
            // 输出普通Zip流
            while ((len = (in.read(buf))) > 0) {
                zipOutputStream.write(buf, 0, len);
            }
            close(in);

            // 关闭此 entry
            zipOutputStream.closeEntry();

        }
        close(zipOutputStream);
        sourceZip.close();

    }

    /**
     * 由zip调用,递归完成目录文件读取
     *
     * @author  Aning
     * @param dir:
     * @param zipOut:
     * @return null
     * @date  2021/7/20 10:55
     *
     **/
    private static void handleDir(File dir, org.apache.tools.zip.ZipOutputStream zipOut) throws IOException {
        File[] files = dir.listFiles();
        // 如果目录为空,则单独创建之.
        if (files.length == 0) {
            // ZipEntry的isDirectory()方法中,目录以"/"结尾.
            zipOut.putNextEntry(new org.apache.tools.zip.ZipEntry(dir.toString() + "/"));
            zipOut.closeEntry();
        } else {// 如果目录不为空,则分别处理目录和文件.
            for (File fileName : files) {
                if (fileName.isDirectory()) {
                    handleDir(fileName, zipOut);
                } else {
                    FileInputStream fileIn = new FileInputStream(fileName);
                    zipOut.putNextEntry(new org.apache.tools.zip.ZipEntry(fileName.toString()));
                    byte[] buf = new byte[1024];
                    int readedBytes;
                    while ((readedBytes = fileIn.read(buf)) > 0) {
                        zipOut.write(buf, 0, readedBytes);
                    }
                    close(fileIn);
                    zipOut.closeEntry();

                }
            }
        }
    }
}
