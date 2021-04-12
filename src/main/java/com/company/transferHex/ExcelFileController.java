package com.company.transferHex;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelFileController {
    private String fileLocation = "";

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }

    private List<String[]> data = null;

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * 数据量不大 全部读入内存  过滤处理
     *
     * @return
     * @throws IOException
     */
    public void readRowsAndColums(String sheetName) throws BiffException, IOException, BiffException {
        //1:创建workbook
        Workbook workbook = Workbook.getWorkbook(new File(this.fileLocation));
        //2:获取第一个工作表sheet
        Sheet sheet = null;
        String[] sheetNames = workbook.getSheetNames();
        for (int i = 0; i < sheetNames.length; i++) {
            if (sheetNames[i].equals(sheetName)) {
                sheet = workbook.getSheet(i);
                break;
            }
        }
        this.data = new LinkedList<>();
        int[] properties = ConfigReader.getExeclPosi();
        //3:获取数据
        for (int i = 1; i < sheet.getRows(); i++) {
            String ifRepeat = sheet.getCell(properties[0], i).getContents();
            if (ifRepeat.equals("1")) {
                String[] tmp = new String[4];
                tmp[0] = sheet.getCell(properties[1], i).getContents();
                tmp[1] = sheet.getCell(properties[2], i).getContents();
                tmp[2] = sheet.getCell(properties[3], i).getContents();
                tmp[3] = sheet.getCell(properties[4], i).getContents();
                this.data.add(tmp);
            }
        }
        //最后一步：关闭资源
        workbook.close();
    }

}
