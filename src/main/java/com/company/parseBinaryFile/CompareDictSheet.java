package com.company.parseBinaryFile;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对比点表内容
 */
public class CompareDictSheet {
    public static void main(String[] args) throws FileNotFoundException {

        readFullVariable();

    }

    public static void readFullVariable() throws FileNotFoundException {
        String fileName = CompareDictSheet.class.getClassLoader().getResource("full.xlsx").getPath();
//        List<FullVariable> data = new ArrayList<>();
//        data.add(0, new FullVariable("name", "desc", "anno", "de", "dt", "port", "udp", "b", "b"));
//        EasyExcel.write("D:\\test.xlsx", FullVariable.class).sheet("模板").doWrite(data);

        List<FullVariable> objects = EasyExcel.read(new FileInputStream("D:\\test.xlsx"), FullVariable.class, new AnalysisEventListener<FullVariable>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 3000;
            /**
             *临时存储
             */
            private List<FullVariable> cachedData = new ArrayList<>(BATCH_COUNT);

            @Override
            public void invoke(FullVariable data, AnalysisContext context) {
                System.out.println("one row");
                cachedData.add(data);
                if (cachedData.size() >= BATCH_COUNT) {
                    // 存储完成清理 list
                    cachedData = new ArrayList<>(BATCH_COUNT);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                saveData();
            }


            /**
             * 加上存储数据库
             */
            private void saveData() {
                System.out.println("succeed");
            }
        }).sheet().doReadSync();
        System.out.println(objects.size());
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    private static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }

}
