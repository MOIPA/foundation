package com.company.UDP;

public class UDPContent {

    // 特征值同步字 1字节
    private Integer eigenValue;

    // 备用 1字节
    private Integer spare;

    // 车辆调远程指令id 2字节
    private Integer id;

    // 车辆调服务器编号 2字节
    private Integer serverCode;

    // 长度 2字节
    private Integer length;

    // 数据 控制命令地址 2字节
    private Integer cmdAddress;

    // 数据 控制命令数值 4字节
    private Long cmdValue;

    // CRC校验位 2字节
    private Integer crc;

    public UDPContent(Integer eigenValue, Integer spare, Integer id, Integer serverCode, Integer length, Integer cmdAddress, Long cmdValue, Integer crc) {
        this.eigenValue = eigenValue;
        this.spare = spare;
        this.id = id;
        this.serverCode = serverCode;
        this.length = length;
        this.cmdAddress = cmdAddress;
        this.cmdValue = cmdValue;
        this.crc = crc;
    }

    public Integer getEigenValue() {
        return eigenValue;
    }

    public void setEigenValue(Integer eigenValue) {
        this.eigenValue = eigenValue;
    }

    public Integer getSpare() {
        return spare;
    }

    public void setSpare(Integer spare) {
        this.spare = spare;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerCode() {
        return serverCode;
    }

    public void setServerCode(Integer serverCode) {
        this.serverCode = serverCode;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getCmdAddress() {
        return cmdAddress;
    }

    public void setCmdAddress(Integer cmdAddress) {
        this.cmdAddress = cmdAddress;
    }

    public Long getCmdValue() {
        return cmdValue;
    }

    public void setCmdValue(Long cmdValue) {
        this.cmdValue = cmdValue;
    }

    public Integer getCrc() {
        return crc;
    }

    public void setCrc(Integer crc) {
        this.crc = crc;
    }
}
