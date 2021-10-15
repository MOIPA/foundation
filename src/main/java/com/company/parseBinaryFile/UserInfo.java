package com.company.parseBinaryFile;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo  {

    @ExcelProperty(value = "信号名称", index = 0)
    private String name;

    @ExcelProperty(value = "信号描述", index = 1)
    private String desc;

    @ExcelProperty(value = "注释", index = 2)
    private String anno;

    @ExcelProperty(value = "默认值", index = 3)
    private String defaultValue;

    @ExcelProperty(value = "数据类型", index = 4)
    private String dataType;

    @ExcelProperty(value = "端口号", index = 5)
    private String port;

    @ExcelProperty(value = "UDP Byte Offset", index = 6)
    private String udpByteOffset;

    @ExcelProperty(value = "Byte", index = 7)
    private String byteInPack;

    @ExcelProperty(value = "bit", index = 8)
    private String bitInPack;
}
